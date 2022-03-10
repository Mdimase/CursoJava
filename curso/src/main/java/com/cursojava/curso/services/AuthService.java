package com.cursojava.curso.services;

import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    @Autowired
    UsuarioService usuarioService;

    public boolean verificarLogin(Usuario usuario) {
        List<Usuario> usuarioLogged = usuarioService.findByEmail(usuario.getEmail());
        String passwordHashed = usuarioLogged.get(0).getPassword();

        // usuario inexistente
        if(usuarioLogged.isEmpty()){
            return false;
        }

        //usuario existe y su password es correcta?
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        return argon2.verify(passwordHashed,usuario.getPassword());
    }

    //encriptar password con argon2
    public String encriptar(String password) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        return argon2.hash(1,1024,1,password);
    }
}
