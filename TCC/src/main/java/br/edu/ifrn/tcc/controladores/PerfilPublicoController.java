package br.edu.ifrn.tcc.controladores;

 

import java.util.List;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

 

import br.edu.ifrn.tcc.dominio.Foto;
import br.edu.ifrn.tcc.dominio.Usuario;
import br.edu.ifrn.tcc.repository.UsuarioRepository;

 

@Controller
@RequestMapping("/Publico")
public class PerfilPublicoController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @GetMapping("/PerfilPublico")
    public String perfilPublico(
            Usuario usuario, ModelMap model) {
        
            //Pegando os dados do usuário logado
            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();
            
            String email = authentication.getName();
            Usuario usuarioLogado = usuarioRepository.findByEmail(email).get();
            
            model.addAttribute("usuario", usuarioLogado);
        
        return "usuario/PerfilPublico";
    }
    

    @GetMapping("/visitarPerfil/{id}")
   	public String visitarPerfil(
   			@PathVariable("id")Integer idUsuario,
   			ModelMap model) {
    	
		Usuario u = usuarioRepository.findById(idUsuario).get();
	
		model.addAttribute("usuario",u );
		 return "usuario/perfilPublico";
	
   	}

   
}