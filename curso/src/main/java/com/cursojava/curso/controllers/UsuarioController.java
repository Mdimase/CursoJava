package com.cursojava.curso.controllers;

import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.services.AuthService;
import com.cursojava.curso.services.UsuarioService;
import com.cursojava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthService authService;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value="usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("matias");
        usuario.setApellido("dimase");
        usuario.setEmail("mdimase@gmail.com");
        usuario.setTelefono("123");
        usuario.setPassword("pass");
        return usuario;
    }


    //obtener listado de usuarios
    @RequestMapping(value="usuarios",method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioService.getUsuarios();
    }

    //eliminar usuario
    @RequestMapping(value="usuarios/{id}",method = RequestMethod.DELETE)
    public void eliminarUsuario(@PathVariable Long id,@RequestHeader(value = "Authorization") String token){
        String usuarioId = jwtUtil.getKey(token);
        usuarioService.eliminarUsuario(id);
    }


    //registrar usuario
    @RequestMapping(value="usuarios",method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){
        usuario.setPassword(authService.encriptar(usuario.getPassword()));
        usuarioService.registrar(usuario);
    }

}
