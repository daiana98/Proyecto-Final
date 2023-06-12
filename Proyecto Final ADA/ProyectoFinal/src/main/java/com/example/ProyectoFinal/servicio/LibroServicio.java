package com.example.ProyectoFinal.servicio;

import com.example.ProyectoFinal.entidad.Autor;
import com.example.ProyectoFinal.entidad.Libro;

import java.util.List;

public interface LibroServicio {

    public List<Libro> listarTodosLosLibros();

    public Libro guardarLibro(Libro libro);

    public Libro obtenerLibroPorIsbn(Long isbn);


    public Libro actualizarLibro(Libro libro);

    public void eliminarLibro(Long id);
}
