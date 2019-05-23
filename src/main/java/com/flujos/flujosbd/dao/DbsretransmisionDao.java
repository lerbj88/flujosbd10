package com.flujos.flujosbd.dao;

import com.flujos.flujosbd.model.Dbsretransmision;

import java.util.List;

public interface DbsretransmisionDao {
    List<Dbsretransmision> findAll();
    Dbsretransmision getDb(Short fiid);
    void crearDb(Short fitipo, String fcdescripcion, String fcdatabase, String fcservicename, String ip, String fcusuario, String fcpassword);
    void actualizarDb(Short fiid, Short fitipo, String fcdescripcion, String fcdatabase, String fcservicename, String ip, String fcusuario);
    String obtenerContrasena (Short fiid);
    String actualizarContrasena (Short fiid, String fcpassword);
    void eliminarDb(Short fiid);
    List<Dbsretransmision> getDbsIseries();
    List<Dbsretransmision> getDbsOracle();
}
