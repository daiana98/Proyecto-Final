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
    //biding manejo de errores con redirectAtribute, para recibir los errores de validacion
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

        prestamo.setTitulo(prestamo.getLibro().getTitulo());//guardo el titulo del libro
        prestamo.setIsbn(prestamo.getLibro().getIsbn());//guardo el isbn del libro

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

        List<Libro> libros = libroServicio.librosDisponibles();
        List<Lector> lectores = lectorServicio.listarTodosLosLectores();

        Prestamo prestamo = prestamoServicio.obtenerPrestamoPorId(id);
        modelo.addAttribute("prestamo", prestamo);
        modelo.addAttribute("librosIsbnList", libros);
        modelo.addAttribute("lectoresList", lectores);

        //retornamos una vissta retornamos el ormualrio edita html
        return "prestamo/editar_prestamo";
    }

    //un post por que vamos a alojar un dato el que envia a la BD, path variable nos ayuda a modelar el id, validated para validar el objeto contacto
    //redirect result releja los errores
    @PostMapping("/editarPrestamo/{id}")
    public String actualizarPrestamo(@PathVariable Integer id, @Validated Prestamo prestamo, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){

        Prestamo prestamoAbd = prestamoServicio.obtenerPrestamoPorId(id);

        List<Libro> libros = libroServicio.listarTodosLosLibros();
        List<Lector> lectores = lectorServicio.listarTodosLosLectores();

        if (bindingResult.hasErrors()){//nos ayuda a saber si hubo error en el omulario
            //
            model.addAttribute("prestamo", prestamo);
            model.addAttribute("librosIsbnList", libros);
            model.addAttribute("lectoresList", lectores);

            return "prestamo/editar_prestamo";
        }

        //modelamos el objeto en web
        prestamoAbd.setTitulo(prestamo.getLibro().getTitulo());//guardo el titulo del libro
        prestamoAbd.setIsbn(prestamo.getLibro().getIsbn());//guardo el isbn del libro
        prestamoAbd.setLibro(prestamo.getLibro());
        prestamoAbd.setLector(prestamo.getLector());

        //recupero el libro
        Libro libro = prestamo.getLibro();
        //bajar el stock
        libro.setCantEjemplares(libro.getCantEjemplares()-1);
        //cambio la condicion stock
        if (libro.getCantEjemplares() == 0){
            libro.setCondicionEjemplar("SIN_STOCK");
        }
        libroServicio.guardarLibro(libro);//actualizo el libro
        //libroServicio.actualizarStockLibros(prestamo.getLibro());//nueva actualizo stokc

        //guardamos el autor ne el ormulario
        prestamoServicio.actualizarPrestamo(prestamoAbd);

        //redirecionamos para que sepa que esta ok
        redirectAttributes.addFlashAttribute("msgExito", "El prestamo se ha actualizado con exito");

        //returnamos la pagina inicial con el / por uque esa es la incial
        return "redirect:/listarPrestamos";
    }

    @PostMapping("/eliminarPrestamo/{id}")
    public String eliminarPrestamoId(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        //muestra ek mensajito el redirect atribute aca lo usaremos para saber si esta ssgguro de que desea eliminar

        //sumo un libro
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
        return "redirect:/listar";
    }


}
