package com.example.ProyectoFinal.entidad;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.List;


@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @Column(name = "isbn")
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
    private String condicionEjemplar;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @OneToMany(mappedBy = "libro")
    private List<Prestamo> prestamos;

    public Libro() {
    }

    public Libro(long isbn, String titulo, Integer anioEdicion, Integer cantEjemplares, String condicionEjemplar, Autor autor, List<Prestamo> prestamos) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anioEdicion = anioEdicion;
        this.cantEjemplares = cantEjemplares;
        this.condicionEjemplar = condicionEjemplar;
        this.autor = autor;
        this.prestamos = prestamos;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnioEdicion() {
        return anioEdicion;
    }

    public void setAnioEdicion(Integer anioEdicion) {
        this.anioEdicion = anioEdicion;
    }

    public Integer getCantEjemplares() {
        return cantEjemplares;
    }

    public void setCantEjemplares(Integer cantEjemplares) {
        this.cantEjemplares = cantEjemplares;
    }

    public String getCondicionEjemplar() {
        return condicionEjemplar;
    }

    public void setCondicionEjemplar(String condicionEjemplar) {
        this.condicionEjemplar = condicionEjemplar;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
}
