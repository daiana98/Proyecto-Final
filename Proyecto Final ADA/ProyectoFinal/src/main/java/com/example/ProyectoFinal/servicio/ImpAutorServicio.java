package com.example.ProyectoFinal.servicio;

import com.example.ProyectoFinal.entidad.Autor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpAutorServicio implements AutorServicio{
    @Override
    public List<Autor> listarTodosLosAutores() {
        return null;
    }

    @Override
    public Autor guardarAutor(Autor autor) {
        return null;
    }

    @Override
    public Autor obtenerAutorPorId(Integer id) {
        return null;
    }

    @Override
    public Autor actualizarAutor(Autor autor) {
        return null;
    }

    @Override
    public void Eliminar(Autor autor) {

    }
}
