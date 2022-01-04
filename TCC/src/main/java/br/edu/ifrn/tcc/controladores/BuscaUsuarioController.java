package br.edu.ifrn.tcc.controladores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

import br.edu.ifrn.tcc.dominio.Usuario;
import br.edu.ifrn.tcc.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class BuscaUsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/busca")
	public String entrarBusca() {
		return "usuario/busca";
	}
	
	@GetMapping("/buscar")
	public String buscar(
			@RequestParam (name="nome", required=false)String nome, 
			@RequestParam (name="mostrarTodosDados", required=false) Boolean mostrarTodosDados,
			@RequestParam (name="ordenacao", required=false) String ordenacao,
			@RequestParam (name="genero", required=false) String genero,
			@RequestParam (name="campus", required=false) String campus,
			HttpSession sessao, ModelMap model
			) {
		
		List<Usuario> usuariosEncontrados;
		
		if (genero.equals("ambos")) {
			usuariosEncontrados = usuarioRepository.findByNomeAndCampus(nome,campus);
		}
		else {
			
			usuariosEncontrados = usuarioRepository.findByNomeSexoAndCampus(nome, genero, campus);
		}
		
	
		
		//Pegando os dados do usuário logado
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        
        String email = authentication.getName();
        Usuario usuarioLogado = usuarioRepository.findByEmail(email).get();
        
        usuariosEncontrados.remove(usuarioLogado);
        
        //Percorrendo a lista de usuários encontrados na busca 
        for (int i = 0; i < usuariosEncontrados.size(); i++) {
            Usuario u = usuariosEncontrados.get(i);
            
            /* 
             * Calculando a afinidade entre o usuário logado e o usuário encontrado na busca.
             * Para cada interesse em comum entre eles, será acrescentado um ponto de afinidade. 
             * Ao final, ordenaremos a lista de acordo com a afinidade.
             */
            
            //Se tiverem o mesmo interesse
            
            if (usuarioLogado.getInteresseAnimes() != null && usuarioLogado.getInteresseAnimes().equals(u.getInteresseAnimes())) {
                //Acrescenta um ponto de afinidade
                u.setAfinidade(u.getAfinidade() + 1);
            }
            
            if (usuarioLogado.getInteresseArte() != null && usuarioLogado.getInteresseArte().equals(u.getInteresseArte())) {
                //Acrescenta um ponto de afinidade
                u.setAfinidade(u.getAfinidade() + 1);
            }
            
            if (usuarioLogado.getInteresseComedia() != null && usuarioLogado.getInteresseComedia().equals(u.getInteresseComedia())) {
                //Acrescenta um ponto de afinidade
                u.setAfinidade(u.getAfinidade() + 1);
            }
            
            if (usuarioLogado.getInteresseCozinhar() != null && usuarioLogado.getInteresseCozinhar().equals(u.getInteresseCozinhar())) {
                //Acrescenta um ponto de afinidade
                u.setAfinidade(u.getAfinidade() + 1);
            }
            
            if (usuarioLogado.getInteresseDanca() != null && usuarioLogado.getInteresseDanca().equals(u.getInteresseDanca())){
                //Acrescenta um ponto de afinidade
                u.setAfinidade(u.getAfinidade() + 1);
            }
            
            if (usuarioLogado.getInteresseEsportes() != null && usuarioLogado.getInteresseEsportes().equals(u.getInteresseEsportes())) {
                //Acrescenta um ponto de afinidade
                u.setAfinidade(u.getAfinidade() + 1);
            }
            
            if (usuarioLogado.getInteresseEstudar() != null && usuarioLogado.getInteresseEstudar().equals(u.getInteresseEstudar())) {
                //Acrescenta um ponto de afinidade
                u.setAfinidade(u.getAfinidade() + 1);
            }
            
            if (usuarioLogado.getInteresseFestas() != null && usuarioLogado.getInteresseFestas().equals(u.getInteresseFestas())) {
                //Acrescenta um ponto de afinidade
                u.setAfinidade(u.getAfinidade() + 1);
            }
            
            if (usuarioLogado.getInteresseFilmes() != null && usuarioLogado.getInteresseFilmes().equals(u.getInteresseFilmes())) {
                //Acrescenta um ponto de afinidade
                u.setAfinidade(u.getAfinidade() + 1);
            }
            
            if (usuarioLogado.getInteresseFutebol() != null && usuarioLogado.getInteresseFutebol().equals(u.getInteresseFutebol())) {
                //Acrescenta um ponto de afinidade
                u.setAfinidade(u.getAfinidade() + 1);
            }
            
            if (usuarioLogado.getInteresseMusica() != null && usuarioLogado.getInteresseMusica().equals(u.getInteresseMusica())) {
                //Acrescenta um ponto de afinidade
                u.setAfinidade(u.getAfinidade() + 1);
            }
            
            if (usuarioLogado.getInteressePolitica() != null && usuarioLogado.getInteressePolitica().equals(u.getInteressePolitica())) {
                //Acrescenta um ponto de afinidade
                u.setAfinidade(u.getAfinidade() + 1);
            }
            
            if (usuarioLogado.getInteresseSeries() != null && usuarioLogado.getInteresseSeries().equals(u.getInteresseSeries())) {
                //Acrescenta um ponto de afinidade
                u.setAfinidade(u.getAfinidade() + 1);
            }
            
            if (usuarioLogado.getInteresseViagens() != null && usuarioLogado.getInteresseViagens().equals(u.getInteresseViagens())) {
                //Acrescenta um ponto de afinidade
                u.setAfinidade(u.getAfinidade() + 1);
            }
            
        }
        
        /* 
         * Após o for, agora que calculamos as afinidades, vamos ordenar a lista de acordo
         * com essas afinidades.  
         */
        
        //Fazendo a ordenação com um método do próprio Java
        Collections.sort(usuariosEncontrados, new Comparator<Usuario>() {
            
            /* Este método faz a comparação de dois usuários para decidir
               quem irá aparecer primeiro na lista. */
            @Override
            public int compare(Usuario usuario1, Usuario usuario2){
                
                Integer afinidade1 = usuario1.getAfinidade();
                Integer afinidade2 = usuario2.getAfinidade();
                
                if (ordenacao.equals("menor")) {
                    //Ordenando pela maior afinidade
                    return afinidade1.compareTo(afinidade2);
                } else {  
                    //Ordenando pela menor afinidade
                    return afinidade2.compareTo(afinidade1);
                }
            }
        });
		
		model.addAttribute("usuariosEncontrados", usuariosEncontrados);
		
		if(mostrarTodosDados != null) {
			model.addAttribute("mostrarTodosDados", true);
		}
		
		return "usuario/busca";		
	}
	
	
	

}
