package com.example.ProyectoFinal.controlador;

import com.example.ProyectoFinal.entidad.Autor;
import com.example.ProyectoFinal.entidad.Lector;
import com.example.ProyectoFinal.entidad.Libro;
import com.example.ProyectoFinal.servicio.LectorServicio;
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
public class LectorControlador {

    @Autowired
    private LectorServicio lectorServicio;

    @GetMapping("/listarLectores")
    public String verPaginaDeInicioLector(Model modelo){
        List<Lector> lectores = lectorServicio.listarTodosLosLectores();

        modelo.addAttribute("lectores",lectores);

        return "lector/index_lector";
    }

    @GetMapping("/nuevoLector")
    public String mostrarFormularioDeRegistrarLector(Model modelo){
        modelo.addAttribute("lector", new Lector());

        return "lector/nuevo_lector";
    }
    //biding manejo de errores con redirectAtribute, para recibir los errores de validacion
    @PostMapping("/saveLector")
    public String guardarLector(@Validated Lector lector, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model modelo){

        if (bindingResult.hasErrors()){//true errores
            modelo.addAttribute("lector",lector);
            return "lector/nuevo_lector";
        }

        lectorServicio.guardarLector(lector);
        redirectAttributes.addFlashAttribute("msgExito", "El lector ha sido agregado con exito");

        return "redirect:/listarLectores";
    }

    @GetMapping("/editarLector/{id}")
    public String mostrarFormularioDeEditarLector(@PathVariable Integer id, Model modelo){

        Lector lector = lectorServicio.obtenerLectorPorId(id);//recuperamos el lector
        //modelamos el lector
        modelo.addAttribute("lector", lector);

        //retornamos una vissta retornamos el ormualrio edita html
        return "lector/editar_lector";
    }

    //un post por que vamos a alojar un dato el que envia a la BD, path variable nos ayuda a modelar el id, validated para validar el objeto contacto
    //redirect result releja los errores
    @PostMapping("/editarLector/{id}")
    public String actualizarLector(@PathVariable Integer id, @Validated Lector lector, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        Lector lectorAbd = lectorServicio.obtenerLectorPorId(id);
        if (bindingResult.hasErrors()){//nos ayuda a saber si hubo error en el omulario
            //
            model.addAttribute("lector", lector);

            return "lector/editar_lector";
        }
        //modelamos el objeto en web
        lectorAbd.setNombreLector(lector.getNombreLector());
        lectorAbd.setApellidoLector(lector.getApellidoLector());
        lectorAbd.setEmailLector(lector.getEmailLector());
        lectorAbd.setCelularLector(lector.getCelularLector());

        //guardamos el autor ne el ormulario
        lectorServicio.actualizarLector(lectorAbd);

        //redirecionamos para que sepa que esta ok
        redirectAttributes.addFlashAttribute("msgExito", "El lector se ha actualizado con exito");

        //returnamos la pagina inicial con el / por uque esa es la incial
        return "redirect:/listarLectores";
    }

    @PostMapping("/eliminarLector/{id}")
    public String eliminarLectorPorId(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        //muestra ek mensajito el redirect atribute aca lo usaremos para saber si esta ssgguro de que desea eliminar
        Lector lector = lectorServicio.obtenerLectorPorId(id);
        if (lector.getPrestamos().isEmpty()){
            lectorServicio.eliminarLectorId(id);

            redirectAttributes.addFlashAttribute("msgExito", "El lector se ha eliminado con Exito");
            return "redirect:/listarLectores";
        }

        redirectAttributes.addFlashAttribute("msgError", "El lector no se puede eliminar, verifique los prestamos con este Libro");

        //retornamos el index
        return "redirect:/listarLectores";
    }
}
