package br.com.serratec.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.serratec.entity.LancamentoVendas;

public interface LancamentoVendasRepository extends JpaRepository<LancamentoVendas, Long>{	
	Page<LancamentoVendas> findAll(Pageable pageable);
	
	@Query(value = "select * from lancamento_vendas where vendedor_autonomo_id = :id", nativeQuery = true)
	List<LancamentoVendas> findByVendedorAutonomoId(Long id);
}
