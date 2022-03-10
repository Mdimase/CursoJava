package com.cursojava.curso.controllers;

import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.services.AuthService;
import com.cursojava.curso.services.UsuarioService;
import com.cursojava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JWTUtil jwtUtil;

    //login
    @RequestMapping(value="login",method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){
        if(authService.verificarLogin(usuario)){
            Usuario usuarioLogged = usuarioService.findByEmail(usuario.getEmail()).get(0);
            return jwtUtil.create(String.valueOf(usuarioLogged.getId()),usuarioLogged.getEmail());
        }
        else{
            return "FAIL";
        }
    }
}
