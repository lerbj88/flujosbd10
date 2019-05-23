package com.flujos.flujosbd.dao;

import com.flujos.flujosbd.model.Taex_mensajestienda;

import java.sql.Timestamp;
import java.util.List;

public interface Taex_mensajestiendaDao2 {

    List<Taex_mensajestienda> obtenerFlujos(String flujos);
    public String InsertarPendientes(Number fnflujoid, Number fnpaisdest, Number fncanaldest, Number fnsucursaldest, String fnfolioconsec, Number fnpaisorig, Number fncanalorig, Number fnsucursalorig, Timestamp fdfechainsercion, Timestamp fdfechaenviotda, Number fnstatusext, String fccadenaejec, Number fnnointentos);
}
