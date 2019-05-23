package com.flujos.flujosbd.dao;

import com.flujos.flujosbd.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UsuarioDao  {
     List <Usuario> findAll();
    void crearUsuario(Integer fiusuario , String fcnombre, String fccorreo, String fctelefono, Integer idrol);
    void editarUsuario(Integer fiusuario ,  String fcnombre, String fccorreo, String fctelefono,  Integer idrol);
    //String actualizarContrasena(Integer fiusuario, String fcpassword, String fcpassword1, String fcpassword2);
    public Usuario findByUsuario(Integer usuario);
    public Usuario obtenerUsuario(Integer fiusuario);
    public Usuario perfilUsuario(Integer fiusuario);
    void eliminarUsuario(Integer fiusuario);
    void validarPassword();
    public String obtenerContrasena (Integer fiusuario);
    public String actualizarContrasena (Integer fiusuario, String fcpassword);
    public String reiniciarContrasena(Integer fiusuario);
    public List<Usuario> consultarUsuarios(String text);

}
