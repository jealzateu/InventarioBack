package com.inventarioautomotriz.inventario.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "mercancia")
@Data
@NoArgsConstructor
public class Mercancia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate fechaIngreso;

    @ManyToOne
    @JoinColumn(name = "usuario_registro", referencedColumnName = "id")
    private Usuario usuarioRegistro;

    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "usuario_modificacion", referencedColumnName = "id")
    private Usuario usuarioModificacion;

}
