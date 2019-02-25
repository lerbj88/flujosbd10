package com.flujos.flujosbd.dao;

import com.flujos.flujosbd.model.Usuario;

import java.util.List;

public interface UsuarioDao {
    List<Usuario> findAll();
    void crearUsuario(Integer fiusuario , String Fcpassword);
}
