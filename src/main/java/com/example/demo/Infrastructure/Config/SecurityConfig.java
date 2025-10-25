package com.example.demo.Infrastructure.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
			.authorizeHttpRequests((authorize) -> {
                authorize.requestMatchers("/user/all").permitAll();
				authorize.anyRequest().authenticated();
              
            }
			)
			.formLogin(form -> {
				form.successHandler(successHandler());
				form.failureHandler(failureHandler());
				form.permitAll();
			})
			.sessionManagement(session ->{
				session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
				session.invalidSessionUrl("/login");
				session.maximumSessions(1).expiredUrl("/login").sessionRegistry(sessionRegistry());
	
			})
			.httpBasic(Customizer.withDefaults())
			.build();
	}

	@Bean
	public SessionRegistry sessionRegistry(){
		return new SessionRegistryImpl();
	}

	private AuthenticationSuccessHandler successHandler(){
		return ((request, response, authentication)->{
			response.sendRedirect("/user/session");
		});	
	}

	private AuthenticationFailureHandler failureHandler(){
		return ((request, response, authentication)->{
			response.sendRedirect("/user/fail");
		});
	}

}
