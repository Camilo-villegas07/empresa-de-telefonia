package com.telefonia.controller;

import com.telefonia.modelo.Usuario;
import com.telefonia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, 
                       @RequestParam String password,
                       RedirectAttributes redirectAttributes) {
        // Aquí debes implementar la lógica de autenticación
        // Por ahora, es una implementación básica de ejemplo
        
        // Validación simple (debes reemplazar esto con autenticación real)
        if ("admin".equals(username) && "admin".equals(password)) {
            return "redirect:/home";
        } else {
            redirectAttributes.addAttribute("error", "true");
            return "redirect:/login";
        }
    }

    @GetMapping("/registro")
    public String registroPage() {
        return "registro";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre,
                          @RequestParam String email,
                          @RequestParam String username,
                          @RequestParam String telefono,
                          @RequestParam String password,
                          @RequestParam String confirmPassword,
                          @RequestParam String departamento,
                          @RequestParam String ciudad,
                          @RequestParam String tipoDireccion,
                          @RequestParam String tipoVia,
                          @RequestParam String numeroVia,
                          @RequestParam(required = false) String prefijoVia,
                          @RequestParam(required = false) String cardinalidadVia,
                          @RequestParam(required = false) String numeroViaCruce,
                          @RequestParam(required = false) String prefijoViaCruce,
                          @RequestParam(required = false) String cardinalidadViaCruce,
                          @RequestParam String numeroPlaca,
                          @RequestParam(required = false) String unidadUrbanizacion,
                          RedirectAttributes redirectAttributes) {
        
        // Validación de contraseñas
        if (!password.equals(confirmPassword)) {
            redirectAttributes.addAttribute("error", "password");
            return "redirect:/registro";
        }
        
        // Verificar si el username ya existe
        if (usuarioRepository.existsByUsername(username)) {
            redirectAttributes.addAttribute("error", "username");
            return "redirect:/registro";
        }
        
        // Verificar si el email ya existe
        if (usuarioRepository.existsByEmail(email)) {
            redirectAttributes.addAttribute("error", "email");
            return "redirect:/registro";
        }
        
        // Crear nuevo usuario
        Usuario usuario = new Usuario(
            nombre, email, username, telefono, password,
            departamento, ciudad, tipoDireccion, tipoVia,
            numeroVia, prefijoVia, cardinalidadVia, numeroViaCruce,
            prefijoViaCruce, cardinalidadViaCruce, numeroPlaca,
            unidadUrbanizacion
        );
        
        // Guardar en base de datos
        usuarioRepository.save(usuario);
        
        System.out.println("Usuario registrado exitosamente: " + username);
        
        // Redirigir al login después del registro exitoso
        return "redirect:/login?registro=exitoso";
    }
}
