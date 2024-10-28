package br.com.serratec.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.dto.LancamentoVendasResponseDTO;
import br.com.serratec.entity.LancamentoVendas;
import br.com.serratec.entity.VendedorAutonomo;
import br.com.serratec.repository.LancamentoVendasRepository;
import br.com.serratec.repository.VendedorAutonomoRepository;
import br.com.serratec.service.LancamentoVendasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoVendasController {
	@Autowired
	private LancamentoVendasService service;
	
    @Autowired
    private LancamentoVendasRepository lancamentoVendasRepository;
    
    @Autowired
    private VendedorAutonomoRepository vendedorAutonomoRepository;

    //get debug para listar todos lançamentos OK
    @GetMapping("/lancamentos")
    public List<LancamentoVendasResponseDTO> getLancamentos() {
        List<LancamentoVendas> lancamentos = lancamentoVendasRepository.findAll();
        return LancamentoVendasService.toDTOList(lancamentos);
    }
    
    //post para inserir lançamentos de venda por nome OK
    @PostMapping
    public ResponseEntity<LancamentoVendasResponseDTO> inserirLancamentoVenda(@RequestBody LancamentoVendasResponseDTO lancamentoDTO) {
        //busca o vendedor
        VendedorAutonomo vendedor = vendedorAutonomoRepository.findByNome(lancamentoDTO.getNomeVendedor());
        //filtra se existe ou não
        if (vendedor == null) {
            return ResponseEntity.badRequest().body(null); // Vendedor não encontrado
        }
        
        LancamentoVendas lancamento = new LancamentoVendas();
        lancamento.setData(lancamentoDTO.getData());
        lancamento.setValor(lancamentoDTO.getValor());
        lancamento.setVendedorAutonomo(vendedor);
        
        lancamentoVendasRepository.save(lancamento);
        return new ResponseEntity<>(LancamentoVendasService.toDTO(lancamento), HttpStatus.CREATED);
    }

    // Método para listar por ID da venda OK
	@Operation(summary = "Busca venda por id", description = "A resposta retorna a venda efetuada pelo vendedor")
	@ApiResponses(value = { @ApiResponse(responseCode = "200",  content = {
			@Content(schema = @Schema(implementation = LancamentoVendas.class), mediaType = "application/json") }, description = "Lancamento de venda cadastrado com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @GetMapping("/lancamentos/vendas/{id}")
    public ResponseEntity<LancamentoVendasResponseDTO> listarPorId(@PathVariable Long id) {
        // Busca o lançamento pelo ID
        LancamentoVendas lancamento = lancamentoVendasRepository.findById(id)
                .orElse(null); // ou usar .orElseThrow() para lançar uma exceção se não encontrar

        if (lancamento == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 se não encontrar
        }

        // Criação do DTO de resposta
        LancamentoVendasResponseDTO dto = new LancamentoVendasResponseDTO();
        dto.setData(lancamento.getData());
        dto.setValor(lancamento.getValor());
        dto.setNomeVendedor(lancamento.getVendedorAutonomo().getNome());

        return ResponseEntity.ok(dto); // Retorna o DTO com status 200
    }
	
	
	// Método para listar por ID da venda OK
	@Operation(summary = "Busca venda por id", description = "A resposta retorna a venda efetuada pelo vendedor")
	@ApiResponses(value = { @ApiResponse(responseCode = "200",  content = {
			@Content(schema = @Schema(implementation = LancamentoVendas.class), mediaType = "application/json") }, description = "Lancamento de venda cadastrado com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@GetMapping("/lancamentos/vendedores/{id}")
	public ResponseEntity<List<LancamentoVendasResponseDTO>> listarPorIdVendedor(@PathVariable Long id) {
		// Busca o lançamento pelo ID
		List <LancamentoVendas> lancamentos = lancamentoVendasRepository.findByVendedorAutonomoId(id); // ou usar .orElseThrow() para lançar uma exceção se não encontrar
		
		if (lancamentos == null) {
			return ResponseEntity.notFound().build(); // Retorna 404 se não encontrar
		}
		
		// Criação do DTO de resposta
		List <LancamentoVendasResponseDTO> dtoLista = new ArrayList<>();
		
		for (LancamentoVendas lancamento : lancamentos) {			
			dtoLista.add(new LancamentoVendasResponseDTO(lancamento));
			
			/*LancamentoVendas dto = new LancamentoVendas();
			dto.setData(lancamento.getData());
			dto.setValor(lancamento.getValor());
			dto.setNomeVendedor(lancamento.getVendedorAutonomo().getNome());*/			
		}
		
		return ResponseEntity.ok(dtoLista); // Retorna o DTO com status 200
	}

	
	//Com Paginação OK
	@Operation(summary = "Lista Paginada de Vendas", description = "Retorna Lançamentos de Venda Paginadas")
	@ApiResponses(value = { @ApiResponse(responseCode = "200",  content = {
			@Content(schema = @Schema(implementation = LancamentoVendas.class), mediaType = "application/json") }, description = "Lancamento de venda cadastrado com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @GetMapping("/paginacao")
    public ResponseEntity<Page<LancamentoVendasResponseDTO>> listarLancamentos(Pageable pageable) {
        Page<LancamentoVendas> lancamentos = lancamentoVendasRepository.findAll(pageable);
        Page<LancamentoVendasResponseDTO> response = lancamentos.map(lancamento -> {
            LancamentoVendasResponseDTO dto = new LancamentoVendasResponseDTO();
            dto.setData(lancamento.getData());
            dto.setValor(lancamento.getValor());
            dto.setNomeVendedor(lancamento.getVendedorAutonomo().getNome());
            return dto;
        });
        
        return ResponseEntity.ok(response);
        //exemplo por valor decrescente: 
        //localhost:8080/lancamentos/lancamentos/paginacao?page=0&size=10&sort=valor,desc
    }
	
	/*@GetMapping NAO OK
	public ResponseEntity<List<LancamentoVendasResponseDTO>> findByVendedor() {
		return ResponseEntity.ok(service.listar());
	}*/
	
	/* NÃO OK
	@Operation(summary = "Insere uma nova venda", description = "A resposta retorna a venda efetuada")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", content = {
			@Content(schema = @Schema(implementation = LancamentoVendas.class), mediaType = "application/json") }, description = "Lancamento de venda cadastrado com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@PostMapping
	public ResponseEntity<Object> inserir(@RequestBody LancamentoVendasRequestDTO dto) {
		LancamentoVendasResponseDTO dtoResponse = service.inserir(dto);
		return ResponseEntity.created(null).body(dtoResponse);
		// o uri ja lança o /id1 no header do postman
	}
	 */
	
	/* NÃO FUNCIONANDO AINDA
    //lançamento de vendas pelo id do vendedor
    @PostMapping("/id/{id}")
    public ResponseEntity<LancamentoVendasSemNomeResponseDTO> inserirLancamentoVendaPorId(
            @PathVariable Long vendedorId, 
            @RequestBody LancamentoVendasSemNomeResponseDTO lancamentoDTO) {
        
        VendedorAutonomo vendedor = vendedorAutonomoRepository.findById(vendedorId)
                .orElse(null);

        if (vendedor == null) {
            return ResponseEntity.badRequest().body(null); // Vendedor não encontrado
        }

        // Criação do objeto LancamentoVendas
        LancamentoVendas lancamento = new LancamentoVendas();
        lancamento.setData(lancamentoDTO.getData());
        lancamento.setValor(lancamentoDTO.getValor());
        lancamento.setVendedorAutonomo(vendedor);
        
        lancamentoVendasRepository.save(lancamento);
        return new ResponseEntity<>(LancamentoVendasService.toDTO(lancamento), HttpStatus.CREATED);
    }*/
}

