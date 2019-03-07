package com.flujos.flujosbd.dao;

import com.flujos.flujosbd.model.Responsablesflujos;
import com.flujos.flujosbd.model.Usuario;

import java.util.List;

public interface ResponsablesflujosDao {
    List<Responsablesflujos> findAll();
    public Responsablesflujos findByResponsableflujo(String fcnombre);
    void crearResponsable(Integer finumempleado , String fcnombre, String fccorreo ,Integer ficelular);
    public Responsablesflujos editarResponsable(Integer finumempleado);
    void eliminarResponsable(Integer finumempleado);
}
