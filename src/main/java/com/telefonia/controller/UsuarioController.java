package com.telefonia.controller;

import com.telefonia.modelo.Usuario;
import com.telefonia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuarios";
    }

    @GetMapping("/activarUsuario/{id}")
    public String activarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setActivo(true);
            usuarioRepository.save(usuario);
        }
        return "redirect:/usuarios";
    }

    @GetMapping("/desactivarUsuario/{id}")
    public String desactivarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setActivo(false);
            usuarioRepository.save(usuario);
        }
        return "redirect:/usuarios";
    }

    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios";
    }
}
