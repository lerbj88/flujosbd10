package com.flujos.flujosbd.dao;

import com.flujos.flujosbd.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UsuarioDao  {
     List <Usuario> findAll();
    void crearUsuario(Integer fiusuario , String Fcpassword, Integer idrol);
    public Usuario findByUsuario(Integer usuario);
    public Usuario editarUsuario(Integer usuario);
    void eliminarUsuario(Integer fiusuario);
    void encoder();
}
