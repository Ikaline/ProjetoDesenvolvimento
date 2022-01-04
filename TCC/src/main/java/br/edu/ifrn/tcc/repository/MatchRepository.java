package br.edu.ifrn.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.tcc.dominio.MatchClass;


public interface MatchRepository extends JpaRepository<MatchClass, Long> {

}
