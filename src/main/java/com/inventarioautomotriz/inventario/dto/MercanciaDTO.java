package com.inventarioautomotriz.inventario.dto;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MercanciaDTO {
    private Long id;
    private String nombre;
    private int cantidad;
    private LocalDate fechaIngreso;
    private Long usuarioRegistroId;
    @Nullable
    private LocalDate fechaModificacion;
    @Nullable
    private Long usuarioModificacionId;
}
