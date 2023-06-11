package com.example.ProyectoFinal.controlador;

import com.example.ProyectoFinal.entidad.Autor;
import com.example.ProyectoFinal.entidad.Libro;
import com.example.ProyectoFinal.servicio.AutorServicio;
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

    /*@GetMapping("/")
    public String verPaginaDeInicio(Model modelo){
        List<Libro> libros = libroServicio.listarTodosLosLibros();

        modelo.addAttribute("libros",libros);

        return "index";
    }*/

    @GetMapping("/listarLibros")
    public String verPaginaDeInicioLibro(Model modelo){
        List<Libro> libros = libroServicio.listarTodosLosLibros();

        modelo.addAttribute("libros",libros);

        return "index_libro";
    }

    @GetMapping("/nuevoLibro")
    public String mostrarFormularioDeRegistrarLibro(Model modelo){

        List<Autor> autores = autorServicio.listarTodosLosAutores();
        modelo.addAttribute("libro", new Libro());
        modelo.addAttribute("autoresList", autores);

        return "nuevo_libro";
    }
    //biding manejo de errores con redirectAtribute, para recibir los errores de validacion
    @PostMapping("/saveLibro")
    public String guardarLibro(@Validated Libro libro, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model modelo){

        if (bindingResult.hasErrors()){//true errores
            modelo.addAttribute("libro",libro);
            return "nuevo_libro";
        }
        libro.setCondicionEjemplar("CON_STOCK");

        libroServicio.guardarLibro(libro);
        redirectAttributes.addFlashAttribute("msgExito", "El libro ha sido agregado con exito");

        return "redirect:/listarLibros";
    }

    @GetMapping("/editarLibro/{id}")
    public String mostrarFormularioDeEditarLibro(@PathVariable Long id, Model modelo){

        Libro libro = libroServicio.obtenerLibroPorIsbn(id);//recuperamos el autor
        //modelamos el autor
        modelo.addAttribute("libro", libro);

        //retornamos una vissta retornamos el ormualrio edita html
        return "editar_libro";
    }

    //un post por que vamos a alojar un dato el que envia a la BD, path variable nos ayuda a modelar el id, validated para validar el objeto contacto
    //redirect result releja los errores
    @PostMapping("/editarLibro/{id}")
    public String actualizarLibro(@PathVariable Long id, @Validated Libro libro, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        Libro libroAbd = libroServicio.obtenerLibroPorIsbn(id);
        if (bindingResult.hasErrors()){//nos ayuda a saber si hubo error en el omulario

            model.addAttribute("libro", libro);

            return "editar_libro";
        }
        //modelamos el objeto en web
        libroAbd.setTitulo(libro.getTitulo());
        libroAbd.setAnioEdicion(libro.getAnioEdicion());
        libroAbd.setCantEjemplares(libro.getCantEjemplares());
        if (libro.getCantEjemplares() <= 0){
            libroAbd.setCondicionEjemplar("SIN_STOCK");
        }else{
            libroAbd.setCondicionEjemplar("CON_STOCK");
        }

        //guardamos el autor ne el ormulario
        libroServicio.actualizarLibro(libroAbd);

        //redirecionamos para que sepa que esta ok
        redirectAttributes.addFlashAttribute("msgExito", "El libro se ha actualizado con exito");

        //returnamos la pagina inicial con el / por uque esa es la incial
        return "redirect:/listar";
    }

    @PostMapping("/eliminarLibro/{id}")
    public String eliminarLibro(@PathVariable Long isbn, RedirectAttributes redirectAttributes){
        //muestra ek mensajito el redirect atribute aca lo usaremos para saber si esta ssgguro de que desea eliminar
        Libro libro = libroServicio.obtenerLibroPorIsbn(isbn);

        libroServicio.Eliminar(libro);

        redirectAttributes.addFlashAttribute("msgExito", "El libro se ha eliminado con Exito");

        //retornamos el index
        return "redirect:/listar";
    }

    /*
    @RequestMapping(value = "/populateDropDownList", method = RequestMethod.GET)
    public String populateList(Model model) {
        List<String> options = new ArrayList<String>();

        List<Autor> autores = autorServicio.listarTodosLosAutores();
        for (Autor autor: autores) {
            options.add(autor.getNombreAutor());
        }

        model.addAttribute("options", options);
        return "nuevo_libro";
    }*/
}
