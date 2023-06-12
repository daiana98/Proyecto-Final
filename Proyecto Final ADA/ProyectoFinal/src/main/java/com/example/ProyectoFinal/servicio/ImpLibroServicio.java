package com.example.ProyectoFinal.servicio;

import com.example.ProyectoFinal.entidad.Libro;
import com.example.ProyectoFinal.repositorio.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImpLibroServicio implements LibroServicio{

    @Autowired
    private LibroRepositorio libroRepositorio;

    @Override
    public List<Libro> listarTodosLosLibros() {
        return libroRepositorio.findAll();
    }

    @Override
    public Libro guardarLibro(Libro libro) {
        return libroRepositorio.save(libro);
    }

    @Override
    public Libro obtenerLibroPorIsbn(Long isbn) {
        return libroRepositorio.findById(isbn).get();
    }


    @Override
    public Libro actualizarLibro(Libro libro) {
        return libroRepositorio.save(libro);
    }

    @Override
    public void eliminarLibro(Long id) {
        libroRepositorio.deleteById(id);
    }

    public List<Libro> librosDisponibles(){

        return libroRepositorio.findBycondicionEjemplar("CON_STOCK");
    }

    /*public void actualizarStockLibros(Libro libro){
        Integer cantEjemplares = libro.getCantEjemplares()-1;
        Long isbn = libro.getIsbn();
        libroRepositorio.updateCantEjemplares(cantEjemplares, isbn);

        if (cantEjemplares == 0){
            libroRepositorio.updatecondicionEjemplar("SIN_STOCK", isbn);
        }
    }*/
}
