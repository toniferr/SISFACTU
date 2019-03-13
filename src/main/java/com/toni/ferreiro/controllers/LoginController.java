package com.toni.ferreiro.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error, Model model, Principal principal,
			RedirectAttributes flash) {
		if (principal != null) {
			flash.addFlashAttribute("info", "La sesión ya está iniciada");
			return "redirect:/";
		}
		if (error != null) {
			model.addAttribute("danger","Error en el login");
		}
		return "login";
	}
}
