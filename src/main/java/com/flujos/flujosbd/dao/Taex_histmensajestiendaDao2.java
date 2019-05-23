package com.flujos.flujosbd.dao;


import com.flujos.flujosbd.model.Taex_histmensajestienda;

import java.sql.Timestamp;
import java.util.List;

public interface Taex_histmensajestiendaDao2 {
    public String InsertarHistorico(Number fnflujoid, Number fnpaisdest, Number fncanaldest, Number fnsucursaldest, String fnfolioconsec, Timestamp fdfechaproceso, Number fnpaisorig, Number fncanalorig, Number fnsucursalorig, Timestamp fdfechainsercion, Timestamp fdfechaenviotda, Number fnstatus, Number fnnotransaccion, String fccadenaejec, Number fnnointentos);
    List<Taex_histmensajestienda> consultarCadenas(String cadena);
}
