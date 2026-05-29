package com.telefonia.controller;

import com.telefonia.modelo.Vendedor;
import com.telefonia.service.VendedorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VendedorController {

    @Autowired
    private VendedorService service;

    @GetMapping("/vendedores")
    public String listarVendedores(Model model, HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        
        if (isAdmin != null && isAdmin) {
            // Admin ve todos los vendedores
            model.addAttribute("vendedores", service.listar());
        } else if (usuarioId != null) {
            // Vendedor solo ve su propio registro
            Vendedor vendedor = service.buscarPorUsuarioId(usuarioId);
            if (vendedor != null) {
                model.addAttribute("vendedores", List.of(vendedor));
            } else {
                model.addAttribute("vendedores", List.of());
            }
        } else {
            model.addAttribute("vendedores", List.of());
        }
        return "vendedores";
    }

    @GetMapping("/nuevoVendedor")
    public String nuevoVendedor(Model model, HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin == null || !isAdmin) {
            return "redirect:/vendedores";
        }
        model.addAttribute("vendedor", new Vendedor());
        return "formVendedor";
    }

    @PostMapping("/guardarVendedor")
    public String guardarVendedor(@ModelAttribute Vendedor vendedor, HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin == null || !isAdmin) {
            return "redirect:/vendedores";
        }
        service.guardar(vendedor);
        return "redirect:/vendedores";
    }

    @GetMapping("/editarVendedor/{id}")
    public String editarVendedor(@PathVariable Long id, Model model, HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        
        if (isAdmin != null && isAdmin) {
            model.addAttribute("vendedor", service.buscar(id));
            return "formVendedor";
        } else if (usuarioId != null) {
            var vendedor = service.buscarPorUsuarioId(usuarioId);
            if (vendedor != null && vendedor.getId().equals(id)) {
                model.addAttribute("vendedor", vendedor);
                return "formVendedor";
            }
        }
        return "redirect:/vendedores";
    }

    @GetMapping("/eliminarVendedor/{id}")
    public String eliminarVendedor(@PathVariable Long id, RedirectAttributes redirectAttributes, HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin == null || !isAdmin) {
            return "redirect:/vendedores";
        }
        try {
            service.eliminar(id);
            redirectAttributes.addFlashAttribute("success", "Vendedor eliminado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se puede eliminar el vendedor porque tiene ventas asociadas");
        }
        return "redirect:/vendedores";
    }
}
