package br.edu.ifrn.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.tcc.dominio.Foto;

public interface FotoRepository extends JpaRepository<Foto, Long> {

}
