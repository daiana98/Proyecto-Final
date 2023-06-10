package com.example.ProyectoFinal.repositorio;

import com.example.ProyectoFinal.entidad.Autor;
import com.example.ProyectoFinal.entidad.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectorRepositorio extends JpaRepository<Lector, Integer> {
}
