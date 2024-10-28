package br.com.serratec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.serratec.entity.VendedorAutonomo;

public interface VendedorAutonomoRepository extends JpaRepository<VendedorAutonomo, Long>{	
    // Método para buscar pelo nome
    @Query("SELECT v FROM VendedorAutonomo v WHERE v.nome = :nome")
    VendedorAutonomo findByNome(@Param("nome") String nome); 
}
