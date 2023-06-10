package com.example.ProyectoFinal.servicio;

import com.example.ProyectoFinal.entidad.Lector;
import com.example.ProyectoFinal.repositorio.LectorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpLectorServicio implements LectorServicio{
    @Autowired
    private LectorRepositorio lectorRepositorio;

    @Override
    public List<Lector> listarTodosLosLectores() {
        return lectorRepositorio.findAll();
    }

    @Override
    public Lector guardarLector(Lector lector) {
        return lectorRepositorio.save(lector);
    }

    @Override
    public Lector obtenerLectorPorId(Integer id) {
        return lectorRepositorio.findById(id).get();
    }

    @Override
    public Lector actualizarLector(Lector lector) {
        return lectorRepositorio.save(lector);
    }

    @Override
    public void Eliminar(Integer id) {
        lectorRepositorio.deleteById(id);
    }
}
