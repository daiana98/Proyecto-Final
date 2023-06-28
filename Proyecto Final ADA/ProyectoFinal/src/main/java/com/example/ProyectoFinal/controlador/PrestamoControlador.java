package com.example.ProyectoFinal.controlador;

import com.example.ProyectoFinal.entidad.Autor;
import com.example.ProyectoFinal.entidad.Lector;
import com.example.ProyectoFinal.entidad.Libro;
import com.example.ProyectoFinal.entidad.Prestamo;
import com.example.ProyectoFinal.servicio.LectorServicio;
import com.example.ProyectoFinal.servicio.LibroServicio;
import com.example.ProyectoFinal.servicio.PrestamoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PrestamoControlador {

    @Autowired
    private PrestamoServicio prestamoServicio;

    @Autowired
    private LibroServicio libroServicio;

    @Autowired
    private LectorServicio lectorServicio;

    @GetMapping("/listarPrestamos")
    public String verPaginaDeInicioPrestamo(Model modelo){
        List<Prestamo> prestamos = prestamoServicio.listarTodosLosPrestamos();

        modelo.addAttribute("prestamos",prestamos);

        return "prestamo/index_prestamo";
    }

    @GetMapping("/nuevoPrestamo")
    public String mostrarFormularioDeRegistrarPrestamo(Model modelo){
        List<Libro> libros = libroServicio.librosDisponibles();
        List<Lector> lectores = lectorServicio.listarTodosLosLectores();

        modelo.addAttribute("prestamo", new Prestamo());
        modelo.addAttribute("librosIsbnList", libros);
        modelo.addAttribute("lectoresList", lectores);

        return "prestamo/nuevo_prestamo";
    }

    @PostMapping("/savePrestamo")
    public String guardarPrestamo(@Validated Prestamo prestamo, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model modelo){

        List<Libro> libros = libroServicio.librosDisponibles();

        List<Lector> lectores = lectorServicio.listarTodosLosLectores();

        if (bindingResult.hasErrors()){//true errores
            modelo.addAttribute("prestamo",prestamo);
            modelo.addAttribute("librosIsbnList", libros);
            modelo.addAttribute("lectoresList", lectores);

            return "prestamo/nuevo_prestamo";
        }

        prestamo.setTitulo(prestamo.getLibro().getTitulo());
        prestamo.setIsbn(prestamo.getLibro().getIsbn());

        //recupero el libro
        Libro libro = prestamo.getLibro();
        //bajar el stock
        libro.setCantEjemplares(libro.getCantEjemplares()-1);
        //cambio la condicion stock
        if (libro.getCantEjemplares() == 0){
            libro.setCondicionEjemplar("SIN_STOCK");
        }

        libroServicio.guardarLibro(libro);
        //libroServicio.actualizarStockLibros(prestamo.getLibro());//nueva actualizo stokc NO UNCIONO

        prestamoServicio.guardarPrestamo(prestamo);
        redirectAttributes.addFlashAttribute("msgExito", "El prestamo ha sido agregado con exito");

        return "redirect:/listarPrestamos";
    }

    @GetMapping("/editarPrestamo/{id}")
    public String mostrarFormularioDeEditarPrestamo(@PathVariable Integer id, Model modelo){



        Prestamo prestamo = prestamoServicio.obtenerPrestamoPorId(id);
        modelo.addAttribute("prestamo", prestamo);

        return "prestamo/editar_prestamo";
    }


    @PostMapping("/editarPrestamo/{id}")
    public String actualizarPrestamo(@PathVariable Integer id, @Validated Prestamo prestamo, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){

        Prestamo prestamoAbd = prestamoServicio.obtenerPrestamoPorId(id);



        if (bindingResult.hasErrors()){//nos ayuda a saber si hubo error en el omulario

            model.addAttribute("prestamo", prestamo);


            return "prestamo/editar_prestamo";
        }
        prestamoAbd.setFechaMaxDevolucion(prestamo.getFechaMaxDevolucion());

        prestamoServicio.actualizarPrestamo(prestamoAbd);


        redirectAttributes.addFlashAttribute("msgExito", "El prestamo se ha actualizado con exito");


        return "redirect:/listarPrestamos";
    }

    @PostMapping("/eliminarPrestamo/{id}")
    public String eliminarPrestamoId(@PathVariable Integer id, RedirectAttributes redirectAttributes){

        Prestamo prestamo = prestamoServicio.obtenerPrestamoPorId(id);
        Libro libro = libroServicio.obtenerLibroPorIsbn(prestamo.getIsbn());

        //sumo un libro
        Integer cantLibros = libro.getCantEjemplares();
        libro.setCantEjemplares(cantLibros+1);
        if (cantLibros == 0){
            libro.setCondicionEjemplar("CON_STOCK");
        }
        libroServicio.guardarLibro(libro);

        prestamoServicio.eliminarPrestamo(id);

        redirectAttributes.addFlashAttribute("msgExito", "El prestamo se ha eliminado con Exito");

        //retornamos el index*/
        return "redirect:/listarPrestamos";
    }


}
