package com.example.ProyectoFinal.servicio;

import com.example.ProyectoFinal.entidad.Autor;
import com.example.ProyectoFinal.repositorio.AutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpAutorServicio implements AutorServicio{

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Override
    public List<Autor> listarTodosLosAutores() {
        return autorRepositorio.findAll();
    }

    @Override
    public Autor guardarAutor(Autor autor) {
        return autorRepositorio.save(autor);
    }

    @Override
    public Autor obtenerAutorPorId(Integer id) {
        return autorRepositorio.findById(id).get();
    }

    @Override
    public Autor actualizarAutor(Autor autor) {
        return autorRepositorio.save(autor);
    }

    @Override
    public void Eliminar(Integer id) {
        autorRepositorio.deleteById(id);
    }
}
