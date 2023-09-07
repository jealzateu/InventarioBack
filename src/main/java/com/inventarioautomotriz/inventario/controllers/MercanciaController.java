package com.inventarioautomotriz.inventario.controllers;

import com.inventarioautomotriz.inventario.dto.MercanciaDTO;
import com.inventarioautomotriz.inventario.entities.Mercancia;
import com.inventarioautomotriz.inventario.exceptions.MercanciaException;
import com.inventarioautomotriz.inventario.services.MercanciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/mercancia")
public class MercanciaController {

    @Autowired
    private MercanciaService mercanciaService;

    @PostMapping
    public ResponseEntity<Mercancia> registrarMercancia(@RequestBody Mercancia mercancia) throws MercanciaException {
        Mercancia mercanciaRegistrada = mercanciaService.registrarMercancia(mercancia);
        return ResponseEntity.status(HttpStatus.CREATED).body(mercanciaRegistrada);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Mercancia> editarMercancia(@RequestBody Mercancia mercancia, @PathVariable Long id) throws MercanciaException {
        Mercancia mercanciaEditada = mercanciaService.editarMercancia(mercancia, id);
        return ResponseEntity.ok(mercanciaEditada);
    }

    @DeleteMapping("/idMercancia/{idMercancia}/idUsuario/{idUsuario}")
    public ResponseEntity<String> eliminarMercancia(@PathVariable Long idMercancia, @PathVariable Long idUsuario) throws MercanciaException {
        mercanciaService.eliminarMercancia(idMercancia, idUsuario);
        return ResponseEntity.ok("Mercancia eliminada correctamente");
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Mercancia>> listarMercancia() throws MercanciaException {
        List<Mercancia> mercancias = mercanciaService.listarMercancia();
        return ResponseEntity.ok(mercancias);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Mercancia>> buscarMercanciaPorNombre(@PathVariable String nombre) throws MercanciaException {
        List<Mercancia> mercancias = mercanciaService.buscarMercanciaPorNombre(nombre);
        return ResponseEntity.ok(mercancias);
    }

    @GetMapping("/fechaIngreso/{fechaIngreso}")
    public ResponseEntity<List<Mercancia>> buscarMercanciaPorFechaIngreso(@PathVariable LocalDate fechaIngreso) throws MercanciaException {
        List<Mercancia> mercancias = mercanciaService.buscarMercanciaPorFechaIngreso(fechaIngreso);
        return ResponseEntity.ok(mercancias);
    }

    @GetMapping("/usuarioRegistro/{usuarioId}")
    public ResponseEntity<List<Mercancia>> buscarMercanciaPorUsuarioRegistro(@PathVariable Long usuarioId) throws MercanciaException {
        List<Mercancia> mercancias = mercanciaService.buscarMercanciaPorNombreDeUsuarioRegistro(usuarioId);
        return ResponseEntity.ok(mercancias);
    }

    @GetMapping("/nombre/{nombre}/fechaIngreso/{fechaIngreso}")
    public ResponseEntity<List<Mercancia>> buscarMercanciaPorNombreAndFechaIngreso(@PathVariable String nombre, @PathVariable LocalDate fechaIngreso) throws MercanciaException {
        List<Mercancia> mercancias = mercanciaService.findByNombreAndFechaIngreso(nombre, fechaIngreso);
        return ResponseEntity.ok(mercancias);
    }

    @GetMapping("/nombre/{nombre}/usuarioRegistro/{usuarioId}")
    public ResponseEntity<List<Mercancia>> buscarMercanciaPorNombreAndUsuarioRegistro(@PathVariable String nombre, @PathVariable Long usuarioId) throws MercanciaException {
        List<Mercancia> mercancias = mercanciaService.buscarMercanciaPorNombreYUsuarioRegistro(nombre, usuarioId);
        return ResponseEntity.ok(mercancias);
    }

    @GetMapping("/fechaIngreso/{fechaIngreso}/usuarioRegistro/{usuarioId}")
    public ResponseEntity<List<Mercancia>> buscarMercanciaPorFechaIngresoAndUsuarioRegistro(@PathVariable LocalDate fechaIngreso, @PathVariable Long usuarioId) throws MercanciaException {
        List<Mercancia> mercancias = mercanciaService.findByFechaIngresoAndUsuarioRegistro(fechaIngreso, usuarioId);
        return ResponseEntity.ok(mercancias);
    }

}
