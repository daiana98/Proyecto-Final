package com.example.ProyectoFinal.entidad;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long isbn;

    @NotBlank(message = "Debe ingresar un titulo")
    @Column(name = "titulo", length = 145, nullable = false)
    private String titulo;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past
    @NotNull(message = "Debe ingresar un anio de Ediciòn")
    @Column(name = "anio_edicion", nullable = false)
    private Integer anioEdicion;

    @NotBlank(message = "Debe ingresar una cantidad")
    @Column(name = "cant_ejemplares", nullable = false)
    private Integer cantEjemplares;

    @NotBlank(message = "Debe ingresar una condicion")
    @Column(name = "condicion_ejemplar", nullable = false, length = 50)

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
}
