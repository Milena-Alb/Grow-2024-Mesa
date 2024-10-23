package com.project.Mesa.Controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.Mesa.Model.Filial;
import com.project.Mesa.Model.Users;
import com.project.Mesa.Repository.FilialRepository;

@Controller
public class FilialController {
	
	@Autowired
	private FilialRepository filialRepository;
	
	@RequestMapping("/filial")
	public String filial() {	
		return "paginas/filial";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvarusuarios")
	public ModelAndView salvar(@ModelAttribute Users usuarios) {
			
		ModelAndView modelview = new ModelAndView("paginas/cadastrousuarios");
		// Salva um novo usuário
		userRepository.save(usuarios);
		
		modelview.addObject("usuarioobj", new Users());
		return modelview;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listarusuarios")
	public ModelAndView listar() {
		ModelAndView modelview = new ModelAndView("paginas/cadastrousuarios");
		Iterable<Filial> usuarios = userRepository.findAll();
		
		modelview.addObject("filials", usuarios);
		modelview.addObject("filialobj", new Filial());
		
		return modelview;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/editarusuario/{idusuario}")
	public ModelAndView editar(@PathVariable("idusuario") Long idusuario) {
		Optional<Users> usuario = userRepository.findById(idusuario);
		ModelAndView modelview = new ModelAndView("paginas/cadastrousuarios");
		if (usuario.isPresent()) {
			modelview.addObject("usuarioobj", usuario.get());
		} else {
			// Adicionar uma mensagem de erro ao modelo
			modelview = new ModelAndView("redirect:/listarusuarios");
			modelview.addObject("errorMessage", "Usuário não encontrado!");
		}

		return modelview;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/removerusuario/{idusuario}")
	public ModelAndView excluir(@PathVariable("idusuario") Long idusuario) {
		userRepository.deleteById(idusuario);
		
		ModelAndView modelview = new ModelAndView("paginas/cadastrousuarios");
		modelview.addObject("usuarios", userRepository.findAll());
		modelview.addObject("usuarioobj", new Users());
		return modelview;
	}
	
}
