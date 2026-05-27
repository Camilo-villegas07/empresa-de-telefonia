package com.telefonia.controller;

import com.telefonia.modelo.Cliente;
import com.telefonia.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", service.listar());
        return "clientes";
    }

    @GetMapping("/nuevoCliente")
    public String nuevoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "formCliente";
    }

    @PostMapping("/guardarCliente")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        service.guardar(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editarCliente/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        model.addAttribute("cliente", service.buscar(id));
        return "formCliente";
    }

    @GetMapping("/eliminarCliente/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/clientes";
    }
}
