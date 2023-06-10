package com.example.ProyectoFinal.entidad;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "lector")
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @NotBlank(message = "Debe ingresar un nombre")
    @Column(name = "lector_nombre", length = 100, nullable = false)
    private String nombreLector;

    @NotBlank(message = "Debe ingresar un apellido")
    @Column(name = "lector_apellido", length = 100, nullable = false)
    private String apellidoLector;

    @NotEmpty(message = "Debe ingresar su email")
    @Email
    @Column(name = "lector_email", nullable = false, length = 50)
    private String emailLector;

    @NotBlank(message = "Debe ingresar su celular")
    @Column(name = "lector_celular", nullable = false, length = 100)
    private String celularLector;

    @OneToMany(mappedBy = "lector")
    private List<Prestamo> prestamos;


    public Lector() {
    }

    public Lector(Integer id, String nombreLector, String apellidoLector, String emailLector, String celularLector, List<Prestamo> prestamos) {
        Id = id;
        this.nombreLector = nombreLector;
        this.apellidoLector = apellidoLector;
        this.emailLector = emailLector;
        this.celularLector = celularLector;
        this.prestamos = prestamos;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNombreLector() {
        return nombreLector;
    }

    public void setNombreLector(String nombreLector) {
        this.nombreLector = nombreLector;
    }

    public String getApellidoLector() {
        return apellidoLector;
    }

    public void setApellidoLector(String apellidoLector) {
        this.apellidoLector = apellidoLector;
    }

    public String getEmailLector() {
        return emailLector;
    }

    public void setEmailLector(String emailLector) {
        this.emailLector = emailLector;
    }

    public String getCelularLector() {
        return celularLector;
    }

    public void setCelularLector(String celularLector) {
        this.celularLector = celularLector;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
}
