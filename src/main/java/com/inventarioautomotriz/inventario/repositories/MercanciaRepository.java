package com.inventarioautomotriz.inventario.repositories;

import com.inventarioautomotriz.inventario.entities.Mercancia;
import com.inventarioautomotriz.inventario.exceptions.MercanciaException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
public interface MercanciaRepository extends JpaRepository<Mercancia, Long> {

    // Método para registrar nueva mercancía
    Mercancia save(Mercancia mercancia) throws MercanciaException;

    // Método para eliminar mercancía
    void deleteById(Long id) throws MercanciaException;

    // Métodos para buscar mercancía

    List<Mercancia> findAll();

    boolean existsByNombre(String nombre);

    List<Mercancia> findByNombre(String nombre);

    List<Mercancia> findByFechaIngreso(LocalDate fechaIngreso);

    List<Mercancia> findByUsuarioRegistro_Id(Long usuarioId);

    List<Mercancia> findByNombreAndFechaIngreso(String nombre, LocalDate fechaIngreso);

    List<Mercancia> findByNombreAndUsuarioRegistro_Id(String nombre, Long usuarioId);

    List<Mercancia> findByFechaIngresoAndUsuarioRegistro_Id(LocalDate fechaIngreso, Long usuarioId);

}
