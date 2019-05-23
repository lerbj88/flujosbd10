package com.flujos.flujosbd.dao;

import com.flujos.flujosbd.model.Flujos;
import com.flujos.flujosbd.model.Responsablesflujos;

import java.util.List;

public interface FlujosDao {

    List<Flujos> findAll();
    public Flujos findById_flujo(Short id_flujo);
    void crearFlujo(Short id_flujo , String descripcion);
    public Flujos obtenerFlujo(Short id_flujo);
    void eliminarFlujo(Short id_flujo);
    List<Flujos> obtenerFlujosConcatenados(String flujos);
    List<Flujos> obtenerFlujosConcatenados2(String flujos);
    List<Flujos> obtenerFlujosConcatenados3(String flujos);
    List<Flujos> obtenerFlujosxUsuario(Integer fiusuario);
    public String obtenerDescipcion (Integer id_flujo);
    List<Flujos> consultarFlujos(String text);
    Integer getfitipo (Number fnfolioid);
}
