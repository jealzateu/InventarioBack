package com.inventarioautomotriz.inventario.services;

import com.inventarioautomotriz.inventario.dto.MercanciaDTO;
import com.inventarioautomotriz.inventario.entities.Mercancia;
import com.inventarioautomotriz.inventario.entities.Usuario;
import com.inventarioautomotriz.inventario.exceptions.MercanciaException;
import com.inventarioautomotriz.inventario.exceptions.UsuarioException;
import com.inventarioautomotriz.inventario.repositories.MercanciaRepository;
import com.inventarioautomotriz.inventario.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MercanciaService {

    @Autowired
    private MercanciaRepository mercanciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método para validar las restrcciones de la mercancía

    public void validarRestriccionesMercancia(Mercancia mercancia) throws MercanciaException {
        // Verificar si ya existe una mercancía con el mismo nombre
        boolean existeMercanciaConMismoNombre = mercanciaRepository.existsByNombre(mercancia.getNombre());

        if (existeMercanciaConMismoNombre) {
            throw new MercanciaException("Ya existe una mercancía con el mismo nombre.");
        }

        // Verificar si la cantidad es un número entero positivo
        if (mercancia.getCantidad() < 0) {
            throw new MercanciaException("La cantidad debe ser un número entero positivo.");
        }

        // Verificar si la fecha de ingreso es menor o igual a la fecha actual
        LocalDate fechaActual = LocalDate.now();
        if (mercancia.getFechaIngreso().isAfter(fechaActual)) {
            throw new MercanciaException("La fecha de ingreso debe ser igual o anterior a la fecha actual.");
        }
    }


    // Método para registrar nueva mercancía
    public Mercancia registrarMercancia(Mercancia mercancia) throws MercanciaException {
        validarRestriccionesMercancia(mercancia);

        // Verificar y asignar valores predeterminados si son null
        if (mercancia.getUsuarioModificacion() == null) {
            mercancia.setUsuarioModificacion(null);
        }
        if (mercancia.getFechaModificacion() == null) {
            mercancia.setFechaModificacion(null);
        }

        Mercancia mercanciaRegistrada = mercanciaRepository.save(mercancia);

        return mercanciaRegistrada;
    }

    // Método para editar mercancía
    public Mercancia editarMercancia(Mercancia mercancia, Long id) throws MercanciaException {
        validarRestriccionesMercancia(mercancia);

        // Verificar si la fecha de modificación es mayor o igual a la fecha de ingreso
        if (mercancia.getFechaModificacion() != null && mercancia.getFechaModificacion().isBefore(mercancia.getFechaIngreso())) {
            throw new MercanciaException("La fecha de modificación debe ser mayor o igual a la fecha de ingreso.");
        }

        // Buscar la mercancía existente en la base de datos por su ID
        Mercancia mercanciaExistente = mercanciaRepository.findById(mercancia.getId())
                .orElseThrow(() -> new MercanciaException("La mercancía no existe."));

        // Actualizar los campos de la mercancía existente con los valores de la mercancía recibida
        mercanciaExistente.setNombre(mercancia.getNombre());
        mercanciaExistente.setCantidad(mercancia.getCantidad());
        mercanciaExistente.setFechaIngreso(mercancia.getFechaIngreso());

        Usuario usuarioModificacion = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioException("Usuario no encontrado"));

        mercanciaExistente.setUsuarioModificacion(usuarioModificacion);
        mercanciaExistente.setFechaModificacion(LocalDate.now());

        // Guardar la mercancía actualizada en la base de datos
        Mercancia mercanciaActualizada = mercanciaRepository.save(mercanciaExistente);

        return mercanciaActualizada;
    }


    // Método para eliminar mercancía
    public void eliminarMercancia(Long idMercancia, Long idUsuario) throws MercanciaException {
        Optional<Mercancia> mercanciaOptional = mercanciaRepository.findById(idMercancia);
        if (mercanciaOptional.isPresent()) {
            Mercancia mercancia = mercanciaOptional.get();

            // Verificar si el usuario actual es el mismo que registró la mercancía
            if (mercancia.getUsuarioRegistro().getId().equals(idUsuario)) {
                mercanciaRepository.delete(mercancia);
            } else {
                throw new MercanciaException("No tienes permiso para eliminar esta mercancía.");
            }
        } else {
            throw new MercanciaException("La mercancía no existe.");
        }
    }

    // Métodos para listar toda la mercancía
    public List<Mercancia> listarMercancia() {
        return mercanciaRepository.findAll();
    }



    // Métodos para buscar mercancía por filtros (fecha, usuario y/o nombre)
    public List<Mercancia> buscarMercanciaPorNombre(String nombre) {
        return mercanciaRepository.findByNombre(nombre);
    }

    public List<Mercancia> buscarMercanciaPorFechaIngreso(LocalDate fechaIngreso) {
        return mercanciaRepository.findByFechaIngreso(fechaIngreso);
    }

    public List<Mercancia> buscarMercanciaPorNombreDeUsuarioRegistro(Long id) {
        return mercanciaRepository.findByUsuarioRegistro_Id(id);
    }


    public List<Mercancia> findByNombreAndFechaIngreso(String nombre, LocalDate fechaIngreso) {
        return mercanciaRepository.findByNombreAndFechaIngreso(nombre, fechaIngreso);
    }

    public List<Mercancia> buscarMercanciaPorNombreYUsuarioRegistro(String nombre, Long usuarioId) {
        return mercanciaRepository.findByNombreAndUsuarioRegistro_Id(nombre, usuarioId);
    }


    public List<Mercancia> findByFechaIngresoAndUsuarioRegistro(LocalDate fechaIngreso, Long usuarioId) {
        return mercanciaRepository.findByFechaIngresoAndUsuarioRegistro_Id(fechaIngreso, usuarioId);
    }

}
