package com.project.Mesa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@RequestMapping("/")
	public String index(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // Adicione o nome do usuário ao modelo
        if (username.equals("admin")) {
            model.addAttribute("message", "Bem vindo administrador");
        } else if (username.equals("user2")) {
            model.addAttribute("message", "Bem vindo usuario2");
        } else if (username.equals("user")) {
            model.addAttribute("message", "Bem vindo usuario1");
        }
		return "index";
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/cadastrousuarios")
	public String cadastro() {
		return "paginas/cadastrousuarios";
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/salvarusuarios")
    public String salvar(@ModelAttribute Users usuarios) {
        userRepository.save(usuarios); 
        return "paginas/cadastrousuarios"; 
    }
}
