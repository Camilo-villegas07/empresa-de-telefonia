package com.telefonia.controller;

import com.telefonia.modelo.Plan;
import com.telefonia.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PlanController {

    @Autowired
    private PlanService planService;

    @GetMapping("/planes")
    public String listarPlanes(Model model) {
        model.addAttribute("planes", planService.listar());
        return "planes";
    }

    @GetMapping("/nuevoPlan")
    public String nuevoPlan(Model model) {
        model.addAttribute("plan", new Plan());
        return "formPlan";
    }

    @PostMapping("/guardarPlan")
    public String guardarPlan(@ModelAttribute Plan plan) {
        planService.guardar(plan);
        return "redirect:/planes";
    }

    @GetMapping("/editarPlan/{id}")
    public String editarPlan(@PathVariable Long id, Model model) {
        model.addAttribute("plan", planService.buscar(id));
        return "formPlan";
    }

    @GetMapping("/eliminarPlan/{id}")
    public String eliminarPlan(@PathVariable Long id) {
        planService.eliminar(id);
        return "redirect:/planes";
    }
}
