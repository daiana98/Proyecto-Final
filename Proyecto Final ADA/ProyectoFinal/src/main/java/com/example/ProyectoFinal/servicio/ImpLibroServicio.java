package com.example.ProyectoFinal.servicio;

import com.example.ProyectoFinal.entidad.Libro;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpLibroServicio implements LibroServicio{
    @Override
    public List<Libro> listarTodosLosLibros() {
        return null;
    }

    @Override
    public Libro guardarLibro(Libro libro) {
        return null;
    }

    @Override
    public Libro obtenerLibroPorIsbn(Long isbn) {
        return null;
    }

    @Override
    public Libro obtenerLibroPorAutor(String autor) {
        return null;
    }

    @Override
    public Libro obtenerLibroPorTitulo(String titulo) {
        return null;
    }

    @Override
    public Libro actualizarLibro(Libro libro) {
        return null;
    }

    @Override
    public void Eliminar(Libro libro) {

    }
}
