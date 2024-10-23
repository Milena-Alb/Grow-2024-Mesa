package com.project.Mesa.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.Mesa.Model.filial;
import com.project.Mesa.Repository.FilialRepository;

@Controller
public class FilialController {
	
	@Autowired
	private FilialRepository filialrepository;
	
	@RequestMapping("/filial")
	public ModelAndView filial() {	
		ModelAndView modelview = new ModelAndView("paginas/filial");
	    modelview.addObject("filialobj", new filial());
		return modelview;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvarfiliais")
	public ModelAndView salvar(@ModelAttribute filial filiais) {
		ModelAndView modelview = new ModelAndView("paginas/filial");
		filialrepository.save(filiais);
		
		modelview.addObject("filialobj", new filial());
		return modelview;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listarfiliais")
	public ModelAndView listar() {
		ModelAndView modelview = new ModelAndView("paginas/filial");
		Iterable<filial> filiais = filialrepository.findAll();
		
		modelview.addObject("filiais", filiais);
		modelview.addObject("filialobj", new filial());
		
		return modelview;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/editarfilial/{cnpjfilial}")
	public ModelAndView editar(@PathVariable("cnpjfilial") String cnpj) {
		Optional<filial> filial = filialrepository.findById(cnpj);
		ModelAndView modelview = new ModelAndView("paginas/filial");
		if (filial.isPresent()) {
			modelview.addObject("filialobj", filial.get());
		} else {
			// Adicionar uma mensagem de erro ao modelo
			modelview = new ModelAndView("redirect:/listarfiliais");
			modelview.addObject("errorMessage", "Usuário não encontrado!");
		}

		return modelview;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/removerfilial/{cnpjfilial}")
	public ModelAndView excluir(@PathVariable("cnpjfilial") String cnpj) {
		filialrepository.deleteById(cnpj);
		
		ModelAndView modelview = new ModelAndView("paginas/filial");
		modelview.addObject("filiais", filialrepository.findAll());
		modelview.addObject("filialobj", new filial());
		return modelview;
	}

}
