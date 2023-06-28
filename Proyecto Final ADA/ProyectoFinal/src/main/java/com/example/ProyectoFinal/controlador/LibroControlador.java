package com.example.ProyectoFinal.controlador;

import com.example.ProyectoFinal.entidad.Autor;
import com.example.ProyectoFinal.entidad.Lector;
import com.example.ProyectoFinal.entidad.Libro;
import com.example.ProyectoFinal.servicio.AutorServicio;
import com.example.ProyectoFinal.servicio.LectorServicio;
import com.example.ProyectoFinal.servicio.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;
    @Autowired
    private AutorServicio autorServicio;



    @GetMapping("/listarLibros")
    public String verPaginaDeInicioLibro(Model modelo){
        List<Libro> libros = libroServicio.listarTodosLosLibros();

        modelo.addAttribute("libros",libros);

        return "libro/index_libro";
    }

    @GetMapping("/nuevoLibro")
    public String mostrarFormularioDeRegistrarLibro(Model modelo){

        List<Autor> autores = autorServicio.listarTodosLosAutores();


        modelo.addAttribute("libro", new Libro());
        modelo.addAttribute("autoresList", autores);


        return "libro/nuevo_libro";
    }

    @PostMapping("/saveLibro")
    public String guardarLibro(@Validated Libro libro, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model modelo){
        List<Autor> autores = autorServicio.listarTodosLosAutores();

        if (bindingResult.hasErrors()){//true errores
            modelo.addAttribute("libro",libro);
            modelo.addAttribute("autoresList", autores);
            return "libro/nuevo_libro";
        }
        libro.setCondicionEjemplar("CON_STOCK");

        libroServicio.guardarLibro(libro);
        redirectAttributes.addFlashAttribute("msgExito", "El libro ha sido agregado con exito");

        return "redirect:/listarLibros";
    }

    @GetMapping("/editarLibro/{id}")
    public String mostrarFormularioDeEditarLibro(@PathVariable Long id, Model modelo){

        Libro libro = libroServicio.obtenerLibroPorIsbn(id);
        List<Autor> autores = autorServicio.listarTodosLosAutores();

        modelo.addAttribute("libro", libro);
        modelo.addAttribute("autoresList", autores);


        return "libro/editar_libro";
    }


    @PostMapping("/editarLibro/{id}")
    public String actualizarLibro(@PathVariable Long id, @Validated Libro libro, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        Libro libroAbd = libroServicio.obtenerLibroPorIsbn(id);
        List<Autor> autores = autorServicio.listarTodosLosAutores();

        if (bindingResult.hasErrors()){

            model.addAttribute("libro", libro);
            model.addAttribute("autoresList", autores);

            return "libro/editar_libro";
        }
        //modelamos el objeto en web
        libroAbd.setTitulo(libro.getTitulo());
        libroAbd.setAnioEdicion(libro.getAnioEdicion());
        libroAbd.setCantEjemplares(libro.getCantEjemplares());
        libroAbd.setAutor(libro.getAutor());
        //libroAbd.setCondicionEjemplar("CON_STOCK");



        libroServicio.guardarLibro(libroAbd);


        redirectAttributes.addFlashAttribute("msgExito", "El libro se ha actualizado con exito");


        return "redirect:/listarLibros";
    }

    @PostMapping("/eliminarLibro/{id}")
    public String eliminarLibroPorIsbn(@PathVariable Long id, RedirectAttributes redirectAttributes){


        Libro libro = libroServicio.obtenerLibroPorIsbn(id);
        if (libro.getPrestamos().isEmpty()){
            libroServicio.eliminarLibro(id);

            redirectAttributes.addFlashAttribute("msgExito", "El libro se ha eliminado con Exito");
            return "redirect:/listarLibros";
        }

        redirectAttributes.addFlashAttribute("msgError", "El libro no se puede eliminar, verifique los prestamos con este Libro");

        //retornamos el index
        return "redirect:/listarLibros";
    }

}
