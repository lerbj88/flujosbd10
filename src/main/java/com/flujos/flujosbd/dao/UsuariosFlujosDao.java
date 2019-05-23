package com.flujos.flujosbd.dao;

import com.flujos.flujosbd.model.Usuario;
import com.flujos.flujosbd.model.UsuariosFlujos;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UsuariosFlujosDao {

    void crearFlujo(Integer fiusuario, Integer fiflujo);
    List<UsuariosFlujos> findAll(Integer fiusuario);
    void eliminarFlujo(Integer fiflujo);
    List<UsuariosFlujos> obtenerFlujos(Integer fiusuario);

}
