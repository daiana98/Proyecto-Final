package com.example.ProyectoFinal.repositorio;

import com.example.ProyectoFinal.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    public Usuario findByEmail(String email);
}
