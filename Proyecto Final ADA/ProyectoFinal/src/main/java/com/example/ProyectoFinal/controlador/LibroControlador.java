package com.example.ProyectoFinal.controlador;

import com.example.ProyectoFinal.servicio.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;
}
