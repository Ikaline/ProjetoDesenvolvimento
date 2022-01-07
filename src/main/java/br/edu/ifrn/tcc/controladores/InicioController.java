package br.edu.ifrn.tcc.controladores;

/**
*
* OBJETIVO: essa classe tem o objetivo de controllar as URL's da pag inicio.html
* 
* @author Isadora Kaline Penha da Silva (isadorakalinesilva@gmail.com)
* @author Igor Bruno das Chagas da Fonseca (brunno.chagas.1@gmail.com)
*
* DATA DA CRIACAO: 09/03/2021
* ##################################
* ULTIMA ALTERACAO: 30/12/2021
*
*###################################
*
*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {
	
	/**URL de inicio
	 * * @return retorna para pagina de inicio
	 * 
	 */
	@GetMapping("/")
	public String inicio() {
		return "inicio";
	}
	
	/**URL de login
	 * @return retorna para pagina de login
	 * 
	*/
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	/**URL caso dê erro ao fazer login
	 * @param model manda mensagem para pagina de login
	 * @return retorna pagina de login
	*/
	@GetMapping("/login-error")
	public String loginError(ModelMap model) {
		model.addAttribute("msgErro", "Login ou senha incorretos. Tente novamente!");
		return "login";
	}

}
