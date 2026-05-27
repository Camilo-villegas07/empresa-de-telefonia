package com.telefonia.repository;

import com.telefonia.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
    Usuario findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
