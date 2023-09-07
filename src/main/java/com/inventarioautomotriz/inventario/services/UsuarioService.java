package com.inventarioautomotriz.inventario.services;


import com.inventarioautomotriz.inventario.entities.Usuario;
import com.inventarioautomotriz.inventario.exceptions.UsuarioException;
import com.inventarioautomotriz.inventario.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método para listar todos los usuarios
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Método para buscar un usuario por su ID
    public Usuario buscarUsuarioPorId(Long id) throws UsuarioException {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioException("Usuario no encontrado"));
    }

}
