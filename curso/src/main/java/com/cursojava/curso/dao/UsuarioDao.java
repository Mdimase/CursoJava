package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;

import java.util.List;

public interface UsuarioDao {

    List<Usuario> getUsuarios();
    void eliminarUsuario(Long id);
    void registrarUsuario(Usuario usuario);
    List<Usuario> findByEmail(String email);
}
