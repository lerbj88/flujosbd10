package com.flujos.flujosbd.services;

import com.flujos.flujosbd.model.Usuario;
import org.springframework.data.domain.Page;

public interface UsuarioService {

   public String actualizarContrasena(Integer fiusuario, String fcpassword, String fcpassword1, String fcpassword2);
   Page<Usuario> Paginado(int pag , int size);
   Page<Usuario> Paginado(int pag , int size, String texto);

}
