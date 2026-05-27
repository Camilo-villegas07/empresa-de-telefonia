package com.telefonia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // Redirige a la lista de clientes al entrar a la raíz
        return "redirect:/clientes";
    }
}
