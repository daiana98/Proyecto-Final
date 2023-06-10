package com.example.ProyectoFinal.entidad;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.List;

@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @NotBlank(message = "Debe ingresar un nombre")
    @Column(name = "autor_nombre", length = 100, nullable = false)
    private String nombreAutor;

    @NotBlank(message = "Debe ingresar un apellido")
    @Column(name = "autor_apellido", length = 100, nullable = false)
    private String apellidoAutor;

    @NotBlank(message = "Debe ingresar un lugar de nacimiento")
    @Column(name = "lugar_nacimiento", length = 100, nullable = false)
    private String lugarNacimiento;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past
    @NotNull(message = "Debe ingresar una fecha de nacimiento")
    @Column(name = "fecha_nacimiento", nullable = false)
    private Integer fechaNacimiento;


    @OneToMany(mappedBy = "autor")
    private List<Libro> libros;

    public Autor() {
    }

    public Autor(Integer id, String nombreAutor, String apellidoAutor, String lugarNacimiento, Integer fechaNacimiento, List<Libro> libros) {
        Id = id;
        this.nombreAutor = nombreAutor;
        this.apellidoAutor = apellidoAutor;
        this.lugarNacimiento = lugarNacimiento;
        this.fechaNacimiento = fechaNacimiento;
        this.libros = libros;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getApellidoAutor() {
        return apellidoAutor;
    }

    public void setApellidoAutor(String apellidoAutor) {
        this.apellidoAutor = apellidoAutor;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
