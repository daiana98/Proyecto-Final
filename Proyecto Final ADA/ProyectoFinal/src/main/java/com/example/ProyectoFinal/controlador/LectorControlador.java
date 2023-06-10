package com.example.ProyectoFinal.controlador;

import com.example.ProyectoFinal.servicio.LectorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LectorControlador {

    @Autowired
    private LectorServicio lectorServicio;
}
