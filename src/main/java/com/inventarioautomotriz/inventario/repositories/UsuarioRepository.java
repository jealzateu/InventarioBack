package com.inventarioautomotriz.inventario.repositories;


import com.inventarioautomotriz.inventario.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {

    // Método para buscar los usuarios
    List<Usuario> findAll();

    // Método para buscar un usuario por su ID
    Optional<Usuario> findById(Long id);

}
