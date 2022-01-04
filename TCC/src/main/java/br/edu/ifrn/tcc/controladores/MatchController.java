package br.edu.ifrn.tcc.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifrn.tcc.dominio.MatchClass;
import br.edu.ifrn.tcc.dominio.Usuario;
import br.edu.ifrn.tcc.repository.UsuarioRepository;


@Controller
@RequestMapping("/Match")
public class MatchController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/conferirMatch")
	public String conferirMatch() {
		
		return "usuario/match";
	}
	
	@GetMapping("/matchAprovado/{id}")
	public String solicitarMatch(
			@PathVariable("id") Integer idUsuario) {
		
		 //Pegando os dados do usuário logado
        Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Usuario usuarioLogado = usuarioRepository.findByEmail(email).get(); 
		
		MatchClass m1 = new MatchClass();
		
		m1.setDarMatch(usuarioLogado);
	       
	  usuarioRepository.findById(idUsuario);
		
	
		
		return "";
	}
	
	

}
