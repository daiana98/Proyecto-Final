package com.example.ProyectoFinal.entidad;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;


    @Column(name = "isbn")
    private Long isbn;


    @Column(name = "titulo", length = 145, nullable = false)
    private String titulo;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Future
    @NotNull(message = "Debe ingresar la fecha de devoluciòn")
    @Column(name = "fecha_max_devolucion", nullable = false)
    private LocalDate fechaMaxDevolucion;

    @ManyToOne
    @JoinColumn(name = "lector_id")
    private Lector lector;

    @ManyToOne
    @JoinColumn(name = "isbn_id")
    private Libro libro;

    public Prestamo() {
    }

    public Prestamo(Integer id, Long isbn, String titulo, LocalDateTime fechaRegistro, LocalDate fechaMaxDevolucion, Lector lector, Libro libro) {
        Id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.fechaRegistro = fechaRegistro;
        this.fechaMaxDevolucion = fechaMaxDevolucion;
        this.lector = lector;
        this.libro = libro;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDate getFechaMaxDevolucion() {
        return fechaMaxDevolucion;
    }

    public void setFechaMaxDevolucion(LocalDate fechaMaxDevolucion) {
        this.fechaMaxDevolucion = fechaMaxDevolucion;
    }

    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @PrePersist
    public void asignarFechaRegistros(){
        fechaRegistro = LocalDateTime.now();//toma el momento exacto que estamos registrando
    }
}
