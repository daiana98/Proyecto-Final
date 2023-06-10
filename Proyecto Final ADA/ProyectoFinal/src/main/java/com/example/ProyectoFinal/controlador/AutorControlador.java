package com.example.ProyectoFinal.controlador;

import com.example.ProyectoFinal.entidad.Autor;
import com.example.ProyectoFinal.servicio.AutorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AutorControlador {
    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/")
    public String verPaginaDeInicio(Model modelo){
        List<Autor> autores = autorServicio.listarTodosLosAutores();

        modelo.addAttribute("autores",autores);

        return "index";
    }
}
