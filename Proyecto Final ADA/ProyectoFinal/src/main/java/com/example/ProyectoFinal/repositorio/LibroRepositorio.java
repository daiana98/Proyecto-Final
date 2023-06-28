package com.example.ProyectoFinal.repositorio;

import com.example.ProyectoFinal.entidad.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {

    public List<Libro> findBycondicionEjemplar(String condicionEjemplar);


}
