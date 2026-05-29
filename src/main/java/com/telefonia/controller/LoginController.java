package com.telefonia.controller;

import com.telefonia.modelo.Usuario;
import com.telefonia.modelo.Vendedor;
import com.telefonia.repository.UsuarioRepository;
import com.telefonia.repository.VendedorRepository;
import jakarta.servlet.http.HttpSession;
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

    @Autowired
    private VendedorRepository vendedorRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, 
                       @RequestParam String password,
                       HttpSession session,
                       RedirectAttributes redirectAttributes) {
        // Validación para el usuario admin (NOTA: En producción usar encriptación de contraseñas)
        if ("admin".equals(username) && "admin123".equals(password)) {
            session.setAttribute("usuario", "admin");
            session.setAttribute("isAdmin", true);
            return "redirect:/home";
        }
        
        // Buscar usuario en la base de datos
        Usuario usuario = usuarioRepository.findByUsername(username);
        
        if (usuario == null) {
            redirectAttributes.addAttribute("error", "usuario_no_encontrado");
            return "redirect:/login";
        }
        
        // Verificar contraseña (NOTA: En producción usar BCrypt o similar)
        if (!usuario.getPassword().equals(password)) {
            redirectAttributes.addAttribute("error", "password_incorrecto");
            return "redirect:/login";
        }
        
        // Verificar si el usuario está activo
        if (!usuario.getActivo()) {
            redirectAttributes.addAttribute("error", "usuario_inactivo");
            return "redirect:/login";
        }
        
        // Login exitoso - guardar sesión
        session.setAttribute("usuario", usuario.getUsername());
        session.setAttribute("isAdmin", false);
        session.setAttribute("usuarioId", usuario.getId());
        
        return "redirect:/home";
    }

    @GetMapping("/registro")
    public String registroPage() {
        return "registro";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre,
                          @RequestParam String apellido,
                          @RequestParam String email,
                          @RequestParam String username,
                          @RequestParam String telefono,
                          @RequestParam String password,
                          @RequestParam String confirmPassword,
                          @RequestParam String cedula,
                          @RequestParam String departamento,
                          @RequestParam String ciudad,
                          @RequestParam String direccion,
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
            nombre, apellido, email, username, telefono, password,
            cedula, departamento, ciudad, direccion
        );
        
        // Guardar en base de datos
        usuarioRepository.save(usuario);
        
        // Crear automáticamente el vendedor asociado
        Vendedor vendedor = new Vendedor();
        vendedor.setNombre(nombre);
        vendedor.setApellido(apellido);
        vendedor.setCorreo(email);
        vendedor.setTelefono(telefono);
        vendedor.setCedula(cedula);
        vendedor.setDireccion(direccion);
        vendedor.setUsuarioId(usuario.getId());
        vendedorRepository.save(vendedor);
        
        System.out.println("Usuario y vendedor registrados exitosamente: " + username);
        
        // Redirigir al login después del registro exitoso
        return "redirect:/login?registro=exitoso";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
