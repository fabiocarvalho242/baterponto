package com.assemblyconsultoria.baterponto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.assemblyconsultoria.baterponto.controller.model.LoginForm;

import jakarta.validation.Valid;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(@RequestParam(value = "error", required = false) String error, Model model) {
		if (error != null) {
			model.addAttribute("error", "Credenciais inválidas. Tente novamente.");
		}
		
		return "home";
	}

	@PostMapping("/login")
	public String login(@Valid LoginForm loginForm, Model model) {
		// Aqui você pode implementar a lógica de autenticação
		if ("usuarioValido".equals(loginForm.getUsernameOrEmail()) && "Senha@123".equals(loginForm.getPassword())) {
			
			return "redirect:/dashboard";
		}

		model.addAttribute("error", "Credenciais inválidas. Tente novamente.");
		
		return "home";
	}
}
