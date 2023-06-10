package com.example.ProyectoFinal.controlador;

import com.example.ProyectoFinal.servicio.PrestamoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PrestamoControlador {

    @Autowired
    private PrestamoServicio prestamoServicio;

}
