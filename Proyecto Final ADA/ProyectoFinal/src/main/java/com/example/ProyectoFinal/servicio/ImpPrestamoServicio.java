package com.example.ProyectoFinal.servicio;

import com.example.ProyectoFinal.entidad.Prestamo;
import com.example.ProyectoFinal.repositorio.PrestamoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpPrestamoServicio implements PrestamoServicio{
    @Autowired
    private PrestamoRepositorio prestamoRepositorio;

    @Override
    public List<Prestamo> listarTodosLosPrestamos() {
        return prestamoRepositorio.findAll();
    }

    @Override
    public Prestamo guardarPrestamo(Prestamo prestamo) {
        return prestamoRepositorio.save(prestamo);
    }

    @Override
    public Prestamo obtenerPrestamoPorId(Integer id) {
        return prestamoRepositorio.findById(id).get();
    }

    @Override
    public Prestamo actualizarPrestamo(Prestamo prestamo) {
        return prestamoRepositorio.save(prestamo);
    }

    @Override
    public void Eliminar(Integer id) {
        prestamoRepositorio.deleteById(id);
    }
}
