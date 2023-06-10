package com.example.ProyectoFinal.repositorio;

import com.example.ProyectoFinal.entidad.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, Integer> {
}
