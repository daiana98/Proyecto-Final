package com.example.ProyectoFinal.repositorio;

import com.example.ProyectoFinal.entidad.Autor;
import com.example.ProyectoFinal.entidad.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepositorio extends JpaRepository<Prestamo, Integer> {
}
