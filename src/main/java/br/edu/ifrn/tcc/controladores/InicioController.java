package br.edu.ifrn.tcc.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {
	
	//URL de inicio
	@GetMapping("/")
	public String inicio() {
		return "inicio";
	}
	
	//URL de login
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	//URL caso dê erro ao fazer login
	@GetMapping("/login-error")
	public String loginError(ModelMap model) {
		model.addAttribute("msgErro", "Login ou senha incorretos. Tente novamente!");
		return "login";
	}

}
