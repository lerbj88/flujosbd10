package com.flujos.flujosbd.services;

import com.flujos.flujosbd.model.Flujos;

import java.util.List;

public interface UsuariosFlujosService {

    String  agregarFlujo(Integer fiusuario, Integer fiflujo);

    List<Flujos> FlujosxUsuario(Integer fiusuario);
    List<Flujos> FlujosxUsuario2(Integer fiusuario);
    List<Flujos> FlujosxUsuario3(Integer fiusuario);
}
