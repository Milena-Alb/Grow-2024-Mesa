package com.project.Mesa.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PremiacoesController {

	@RequestMapping("/premiacoes")
	public String premiacoes() {
		
		return "paginas/premiacoes";
	}
}
