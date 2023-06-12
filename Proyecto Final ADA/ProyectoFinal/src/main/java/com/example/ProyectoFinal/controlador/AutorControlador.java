package com.example.ProyectoFinal.controlador;

import com.example.ProyectoFinal.entidad.Autor;
import com.example.ProyectoFinal.servicio.AutorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/listar")
    public String verPaginaDeInicioAutor(Model modelo){
        List<Autor> autores = autorServicio.listarTodosLosAutores();

        modelo.addAttribute("autores",autores);

        return "autor/index_autor";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioDeRegistrarAutor(Model modelo){
        modelo.addAttribute("autor", new Autor());

        return "autor/nuevo_autor";
    }
    //biding manejo de errores con redirectAtribute, para recibir los errores de validacion
    @PostMapping("/save")
    public String guardarAutor(@Validated Autor autor, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model modelo){

        if (bindingResult.hasErrors()){//true errores
            modelo.addAttribute("autor",autor);
            return "autor/nuevo_autor";
        }

        autorServicio.guardarAutor(autor);
        redirectAttributes.addFlashAttribute("msgExito", "El autor ha sido agregado con exito");

        return "redirect:/listar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditarAutor(@PathVariable Integer id, Model modelo){

        Autor autor = autorServicio.obtenerAutorPorId(id);//recuperamos el autor
        //modelamos el autor
        modelo.addAttribute("autor", autor);

        //retornamos una vissta retornamos el ormualrio edita html
        return "autor/editar_autor";
    }

    //un post por que vamos a alojar un dato el que envia a la BD, path variable nos ayuda a modelar el id, validated para validar el objeto contacto
    //redirect result releja los errores
    @PostMapping("/editar/{id}")
    public String actualizarAutor(@PathVariable Integer id, @Validated Autor autor, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        Autor autorAbd = autorServicio.obtenerAutorPorId(id);
        if (bindingResult.hasErrors()){//nos ayuda a saber si hubo error en el omulario
            //
            model.addAttribute("autor", autor);

            return "autor/editar_autor";
        }
        //modelamos el objeto en web
        autorAbd.setNombreAutor(autor.getNombreAutor());
        autorAbd.setApellidoAutor(autor.getApellidoAutor());
        autorAbd.setLugarNacimiento(autor.getLugarNacimiento());
        autorAbd.setFechaNacimiento(autor.getFechaNacimiento());

        //guardamos el autor ne el ormulario
        autorServicio.actualizarAutor(autorAbd);

        //redirecionamos para que sepa que esta ok
        redirectAttributes.addFlashAttribute("msgExito", "El autor se ha actualizado con exito");

        //returnamos la pagina inicial con el / por uque esa es la incial
        return "redirect:/listar";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarAutor(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        //muestra ek mensajito el redirect atribute aca lo usaremos para saber si esta ssgguro de que desea eliminar
        Autor autor = autorServicio.obtenerAutorPorId(id);

        autorServicio.Eliminar(id);

        redirectAttributes.addFlashAttribute("msgExito", "El autor se ha eliminado con Exito");

        //retornamos el index
        return "redirect:/listar";
    }
}
