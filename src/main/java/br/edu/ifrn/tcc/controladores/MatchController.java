package br.edu.ifrn.tcc.controladores;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.tcc.dominio.MatchClass;
import br.edu.ifrn.tcc.dominio.Usuario;
import br.edu.ifrn.tcc.repository.MatchRepository;
import br.edu.ifrn.tcc.repository.UsuarioRepository;


@Controller
@RequestMapping("/Match")
public class MatchController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private MatchRepository matchRepository;
	
	@GetMapping("/conferirMatch")
	public String conferirMatch(
					String recebeMatch,
					HttpSession sessao, ModelMap model
					) {
		
		Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        
        String email = authentication.getName();
        Usuario usuarioLogado = usuarioRepository.findByEmail(email).get();
      
        //RecebeMatch
        List<MatchClass> variavelMatch = matchRepository.findByrecebeMatch(usuarioLogado.getId());
        model.addAttribute("matchesRecebidos", variavelMatch);
        
        //DarMatch
        List<MatchClass> variavelDarMatch = matchRepository.findBydarMatch(usuarioLogado.getId());
        model.addAttribute("matchesDados", variavelDarMatch);
        
        //MatchMutuo
        List<MatchClass> variavelRecebeDarMatch = matchRepository.findByrecebeDarMatch(usuarioLogado.getId(), usuarioLogado.getId());
        model.addAttribute("matchesMutuos", variavelRecebeDarMatch);
		
		return "usuario/match";
	}
	
	@GetMapping("/solicitarMatch/{id}")
	public String solicitarMatch(
			@PathVariable("id") Integer idUsuario, ModelMap model, RedirectAttributes attr) {
		
		 //Pegando os dados do usuário logado
        Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Usuario usuarioLogado = usuarioRepository.findByEmail(email).get(); 
		
		MatchClass m1 = new MatchClass();
		
		m1.setDarMatch(usuarioLogado);
	       
	 Usuario usuarioMatch = usuarioRepository.findById(idUsuario).get();
		
	m1.setRecebeMatch(usuarioMatch);
	
	matchRepository.save(m1);
	
	attr.addFlashAttribute("msgSucesso", "Match dado com sucesso!");
		
		return "redirect:/Publico/visitarPerfil/" + idUsuario;
	}
	
	@GetMapping("/aprovarMatch/{id}")
	public String aprovarMatch(
			@PathVariable ("id") Integer idMatch,
			ModelMap model,
			HttpSession sessao) {
		
		MatchClass variavelMatch = matchRepository.findById(idMatch).get();
	 
	variavelMatch.setAprovado(true);
	
	matchRepository.save(variavelMatch);	
	 
	return "redirect:/Match/conferirMatch";
	}

	

}
