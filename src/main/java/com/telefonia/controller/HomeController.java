package com.telefonia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // Redirige a la página de login
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String homePage() {
        // Muestra la página principal interactiva
        return "home";
    }
}
