package com.project.Mesa.Controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Mesa.Model.Users;
import com.project.Mesa.Model.campanhas;
import com.project.Mesa.Repository.CampanhasRepository;
import com.project.Mesa.Repository.UserRepository;

@Controller
public class DashboardController {
	

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CampanhasRepository campanhasRepository;

	// Endpoint para o Chart.js buscar os dados em JSON
    @GetMapping("/api/campanhas-data")
    @ResponseBody
    public Map<String, Object> getCampanhasData() {
        List<campanhas> campanhasList = (List<campanhas>) campanhasRepository.findAll();

        // Ajuste os dados para Chart.js, conforme necessário (exemplo para metas e realizados)
        List<String> labels = campanhasList.stream().map(c -> c.getPeriodo()).collect(Collectors.toList());
        List<String> metas = campanhasList.stream().map(c -> c.getMeta()).collect(Collectors.toList());
        List<String> realizados = campanhasList.stream().map(c -> c.getRealizado()).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("labels", labels);
        response.put("metas", metas);
        response.put("realizados", realizados);

        return response;
    }
	
	@RequestMapping("/")
	public String index(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		/** Verifica qual usuário foi autenticado após login
		   System.out.println("Username: " + username);
		   System.out.println("Authorities: " + auth.getAuthorities());
		*/

		/*
		Users usuari = userRepository.findByUsername(username);
		*/
		
		// Adiciona o nome do usuário ao modelo
		if (username != null) {
			// Separar o nome e sobrenome
			String nomeFormatado = formatarNome(username);
			model.addAttribute("message", nomeFormatado);
		}

		/*
			Double metaTotal = campanhasRepository.sumMetaByCnpjAndGrupo("Base");
		model.addAttribute("metaTotal", metaTotal != null ? metaTotal : 0);*/
		
		if (auth != null && auth.isAuthenticated()) {
			// Lógica existente
			return "dashboard"; 
		}
		// Se não autenticado, redirecione para login
		
		return "redirect:/login";
	}
	
	private String formatarNome(String username) {
		// Divide o nome em letras maiúsculas e minúsculas
		// Exemplo: "ErysonMoreira" -> "Eryson Moreira"
		return username.replaceAll("([a-z])([A-Z])", "$1 $2");
	}

	


	
	
}
