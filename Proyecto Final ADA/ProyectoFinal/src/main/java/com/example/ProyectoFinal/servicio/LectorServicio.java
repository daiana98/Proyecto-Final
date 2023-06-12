package com.example.ProyectoFinal.servicio;

import com.example.ProyectoFinal.entidad.Autor;
import com.example.ProyectoFinal.entidad.Lector;

import java.util.List;

public interface LectorServicio {
    public List<Lector> listarTodosLosLectores();

    public Lector guardarLector(Lector lector);

    public Lector obtenerLectorPorId(Integer id);

    public Lector actualizarLector(Lector lector);

    public void eliminarLectorId(Integer id);
}
