package com.telefonia.service;

import com.telefonia.modelo.Plan;
import com.telefonia.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    public List<Plan> listar() {
        return planRepository.findAll();
    }

    public Plan buscar(Long id) {
        Optional<Plan> optional = planRepository.findById(id);
        return optional.orElse(null);
    }

    public void guardar(Plan plan) {
        planRepository.save(plan);
    }

    public void eliminar(Long id) {
        planRepository.deleteById(id);
    }
}
