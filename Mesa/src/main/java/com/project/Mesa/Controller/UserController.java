package com.project.Mesa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.Mesa.Model.Users;
import com.project.Mesa.Repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping("/")
	public String index(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		System.out.println("Username: " + username);
		System.out.println("Authorities: " + auth.getAuthorities());
		// Adicione o nome do usuário ao modelo
		if (username != null) {
			// Separar o nome e sobrenome
			String nomeFormatado = formatarNome(username);
			model.addAttribute("message", "Bem-vindo " + nomeFormatado);
		}

		return "index";
	}

	private String formatarNome(String username) {
		// Divide o nome em letras maiúsculas e minúsculas
		// Exemplo: "ErysonMoreira" -> "Eryson Moreira"
		return username.replaceAll("([a-z])([A-Z])", "$1 $2");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/cadastrousuarios")
	public String cadastro() {
		return "paginas/cadastrousuarios";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/salvarusuarios")
	public String salvar(@ModelAttribute Users usuarios) {
		usuarios.setPassword(passwordEncoder.encode(usuarios.getPassword()));
		userRepository.save(usuarios);
		return "paginas/cadastrousuarios";
	}
}
