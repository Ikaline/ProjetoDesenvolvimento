package br.edu.ifrn.tcc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifrn.tcc.dominio.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	@Query("select u from Usuario u where u.email like %:email% ")
	Optional<Usuario> findByEmail(@Param("email") String email);
	
	@Query("select u from Usuario u where u.nome like %:nome% ")
	List<Usuario> findByNome(@Param("nome") String nome);
	
	@Query("select u from Usuario u where u.campus like %:campus% ")
	List<Usuario> findByCampus(@Param("campus") String campus);
	
	@Query("select u from Usuario u where u.nome like %:nome% " +
	"and u.campus like %:campus% ")
	List<Usuario> findByNomeAndCampus(@Param("nome") String nome,
											@Param("campus") String campus);
	
	@Query("select u from Usuario u where u.nome like %:nome% " +
			"and u.sexo like %:sexo% " + "and u.campus like %:campus% ")
	List<Usuario> findByNomeSexoAndCampus(@Param("nome") String nome,
											@Param("sexo") String sexo,
											@Param("campus") String campus);
	
}