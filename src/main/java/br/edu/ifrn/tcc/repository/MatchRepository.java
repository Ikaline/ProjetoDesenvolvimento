package br.edu.ifrn.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifrn.tcc.dominio.MatchClass;


public interface MatchRepository extends JpaRepository<MatchClass, Integer>{
	
	@Query("select u from MatchClass u where u.recebeMatch.id = :recebeMatch ")
	List<MatchClass> findByrecebeMatch(@Param("recebeMatch") int recebeMatch);
	
	@Query("select u from MatchClass u where u.darMatch.id = :darMatch ")
	List<MatchClass> findBydarMatch(@Param("darMatch") int darMatch);
	
	@Query("select u from MatchClass u where u.recebeMatch.id = :recebeMatch " +
	"or u.darMatch.id = :darMatch " + "and u.aprovado = true")
	List<MatchClass> findByrecebeDarMatch(@Param("recebeMatch") int recebeMatch,
										  @Param("darMatch") int darMatch);

	
}
