package br.edu.ifrn.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.tcc.dominio.Arquivo;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long>{
	
	

}
