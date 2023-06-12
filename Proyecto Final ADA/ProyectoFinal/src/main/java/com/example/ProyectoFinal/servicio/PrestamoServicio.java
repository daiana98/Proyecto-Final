package com.example.ProyectoFinal.servicio;


import com.example.ProyectoFinal.entidad.Prestamo;

import java.util.List;

public interface PrestamoServicio {
    public List<Prestamo> listarTodosLosPrestamos();

    public Prestamo guardarPrestamo(Prestamo prestamo);

    public Prestamo obtenerPrestamoPorId(Integer id);

    public Prestamo actualizarPrestamo(Prestamo prestamo);

    public void eliminarPrestamo(Integer id);
}
