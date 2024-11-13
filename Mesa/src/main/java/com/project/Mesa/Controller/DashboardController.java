package com.project.Mesa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.Mesa.Model.campanhas;  // Certifique-se de que a classe Campanha está sendo importada
import com.project.Mesa.Repository.CampanhasRepository;  // Verifique a existência do repositório
import com.project.Mesa.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;  // Para a lista de campanhas

@Controller
public class DashboardController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CampanhasRepository campanhasRepository;  // Repositório para acessar as campanhas

    @RequestMapping("/")
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // Verifica se o usuário está autenticado
        if (auth != null && auth.isAuthenticated()) {
			List<campanhas> comidaPreferida = (List<campanhas>) campanhasRepository.findCampanhasByPaginaComidaPremiada();
			model.addAttribute("comidaPreferida", comidaPreferida);

            // Buscar apenas as campanhas de "Melhores Empresas" usando a consulta personalizada
            List<campanhas> melhoresEmpresas = (List<campanhas>) campanhasRepository.findCampanhasByPaginaMelhoresEmpresas();
            model.addAttribute("melhoresEmpresas", melhoresEmpresas);
			List<campanhas> destaqueCorporativo = (List<campanhas>) campanhasRepository.findCampanhasByPaginaDestaqueCorporativo();
            model.addAttribute("destaqueCorporativo", destaqueCorporativo);
			List<campanhas> comeMelhor = (List<campanhas>) campanhasRepository.findCampanhasByPaginaComeMelhor();
            model.addAttribute("comeMelhor", comeMelhor);
			List<campanhas> destaque = (List<campanhas>) campanhasRepository.findCampanhasByPaginaDestaque();
            model.addAttribute("destaque", destaque);


            // Passar o nome formatado do usuário
            if (username != null) {
                String nomeFormatado = formatarNome(username);
                model.addAttribute("message", nomeFormatado);
            }

            // Retorna a página do dashboard
            return "dashboard";
        }

        // Se não autenticado, redireciona para login
        return "redirect:/login";
    }

    private String formatarNome(String username) {
        // Formata o nome para exibição (exemplo: "ErysonMoreira" -> "Eryson Moreira")
        return username.replaceAll("([a-z])([A-Z])", "$1 $2");
    }
}
