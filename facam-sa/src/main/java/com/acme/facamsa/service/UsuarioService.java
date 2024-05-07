package com.acme.facamsa.service;

import com.acme.facamsa.entity.Usuario;
import com.acme.facamsa.repository.UsuarioRepository;
import com.acme.facamsa.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Optional<Usuario> login(String email, String senha) {
        return repository.findFirstByEmailAndSenha(email, MD5Util.getMD5(senha));
    }
}
