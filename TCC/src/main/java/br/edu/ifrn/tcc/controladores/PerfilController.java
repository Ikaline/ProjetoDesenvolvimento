package br.edu.ifrn.tcc.controladores;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.tcc.dominio.Arquivo;
import br.edu.ifrn.tcc.dominio.Usuario;
import br.edu.ifrn.tcc.repository.ArquivoRepository;
import br.edu.ifrn.tcc.repository.UsuarioRepository;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
	
	@Autowired
	private ArquivoRepository arquivoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/criar")
	public String CriarBio(ModelMap model) {
		 
		//bucando dados do usuario logado
		Authentication authentication =
				SecurityContextHolder.getContext().getAuthentication();
		
		String email = authentication.getName();
		Usuario usuario = usuarioRepository.findByEmail(email).get();
		
		model.addAttribute("usuario", usuario);
		
		return "usuario/perfil";
	}
	
	@PostMapping("/salvarPerfil")
	@Transactional(readOnly = false)
	public String salvarPerfil (Usuario usuario, @RequestParam("file") MultipartFile arquivo, RedirectAttributes attr) {
		
		try {
			
			//Carregando os dados do usuário diretamente do banco de dados
			Usuario usuarioBanco = usuarioRepository.findById(usuario.getId()).get();
			            
			//Transferindo as fotos do usuário para o objeto que vai ser salvo no banco
			//(para que as fotos não se percam)
			usuario.setPubliFoto(usuarioBanco.getPubliFoto());
			
			if(arquivo != null && !arquivo.isEmpty()){
				//normalizando o nome do arquivo
				String nomeArquivo  =
						StringUtils.cleanPath(arquivo.getOriginalFilename());
						
				
				Arquivo arquivoBD = new Arquivo(null, nomeArquivo, arquivo.getContentType(),
						arquivo.getBytes());
				
				//salvando a foto no banco
				arquivoRepository.save(arquivoBD);
				
				if(usuario.getFoto() != null && usuario.getFoto().getId() != null
						&& usuario.getFoto().getId() > 0) {
					
					arquivoRepository.delete(usuario.getFoto());
				}
				
				usuario.setFoto(arquivoBD);
			}
			else if (usuario.getFoto().getId() == null
					|| usuario.getFoto().getId() == 0){
					usuario.setFoto(null);
					}
			
			usuarioRepository.save(usuario);
			attr.addFlashAttribute("msgSucesso", "bio criada com sucesso");
		}
		catch (IOException e) {
			 e.printStackTrace();
		}
		return "redirect:/perfil/criar";
	}

}
