package com.cursojava.curso.services;

import com.cursojava.curso.dao.UsuarioDaoImp;
import com.cursojava.curso.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDaoImp usuarioDaoImp;
    public void eliminarUsuario(Long id) {
        usuarioDaoImp.eliminarUsuario(id);
    }

    public List<Usuario> getUsuarios(){
        return usuarioDaoImp.getUsuarios();
    }

    public void registrar(Usuario usuario) {
        usuarioDaoImp.registrarUsuario(usuario);
    }

    public List<Usuario> findByEmail(String email){
       return usuarioDaoImp.findByEmail(email);
    }
}
