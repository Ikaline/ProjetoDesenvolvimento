package br.edu.ifrn.tcc.controladores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.tcc.dominio.Usuario;
import br.edu.ifrn.tcc.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class CadastroUsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/cadastro")
	public String entrarCadastro(ModelMap model) {
		model.addAttribute("usuario", new Usuario());
		return "usuario/cadastro";
	}
	
	@Transactional(readOnly = false)
	@PostMapping ("/salvar")
	public String salvar(@Valid Usuario usuario, BindingResult result,
			RedirectAttributes attr, HttpSession sessao, ModelMap model) {
		
			if(!usuario.getConfirm().equals(usuario.getSenha())) {
				model.addAttribute("msgErro", "Senha e confirmação precisam ser iguais!");
				return "usuario/cadastro";
			}
		
			if(result.hasErrors()) {
				return "usuario/cadastro";
		}
		
		Usuario u = usuarioRepository.findByEmail(usuario.getEmail()).orElse(null);
		 	if (u != null) {
				model.addAttribute("msgErro", "Esse email já está cadastrado!");
				return "usuario/cadastro";
		}
		
		 	//criptografia de senha
		 	String senhaCriptografada =
		 			new BCryptPasswordEncoder().encode(usuario.getSenha());
		 	usuario.setSenha(senhaCriptografada);
		 	
		usuarioRepository.save(usuario);
		attr.addFlashAttribute("msgSucesso", "Operação realizada com sucesso!");
		
		return "redirect:/usuarios/cadastro";
	}
	
	@ModelAttribute ("dia")
	public List<String> getDia() {
		return Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
				"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
	}
	
	@ModelAttribute ("mes")
	public List<String> getMes() {
		return Arrays.asList("Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
				"Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro");
	}
	
	@ModelAttribute ("ano")
	public List<String> getAno() {
		return Arrays.asList("2021", "2020", "2019", "2018", "2017", "2016", "2015",
				"2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006",
				"2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997",
				"1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988",
				"1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979",
				"1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970",
				"1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961",
				"1960");
	}
	
	@ModelAttribute ("campus")
	public List<String> getCampus() {
		return Arrays.asList("Apodi", "Caicó", "Canguaretama", 
				"Ceará-Mirim", "Currais Novos", "Ipanguaçu", "João Câmara", "Jucurutu", "Lajes",
				"Macau", "Mossoró", "Natal - Central", "Natal - Cidade Alta", "Natal - Zona Leste", 
				"Natal-Zona Norte", "Nova Cruz", "Parelhas", "Parnamirim", "Pau dos Ferros",
				"Santa Cruz", "São Gonçalo do Amarante", "São Paulo do Potengi");
	}
	

}
