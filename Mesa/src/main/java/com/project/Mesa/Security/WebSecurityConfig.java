package com.project.Mesa.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authz) -> authz
				// Rotas acessíveis apenas pelo admin
				.requestMatchers("/cadastrousuarios", "/salvarusuarios").hasRole("MANAGER")
				// Qualquer outra rota requer autenticação
				.anyRequest().authenticated())
				// Usa o form login padrão do Spring Security
				.formLogin((form) -> form.defaultSuccessUrl("/", true) // Redireciona para a página inicial após o login
						.permitAll())
				.logout((logout) -> logout.permitAll()); // Logout permitido a todos

		return http.build();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		@SuppressWarnings("deprecation")
		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER")
				.build();
		@SuppressWarnings("deprecation")
		UserDetails user2 = User.withDefaultPasswordEncoder().username("user2").password("1234567").roles("USER")
				.build();
		@SuppressWarnings("deprecation")
		UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("12345678").roles("MANAGER")
				.build();
		return new InMemoryUserDetailsManager(user, user2, admin);
	}

}
