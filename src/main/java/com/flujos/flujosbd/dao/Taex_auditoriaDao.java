package com.flujos.flujosbd.dao;

import com.flujos.flujosbd.model.Taex_auditoria;

import java.sql.Timestamp;
import java.util.List;

public interface Taex_auditoriaDao {

    List<Taex_auditoria> findAll();
    void crearAuditoria(Number fnflujoid, Number fnpaisdest, Number fncanaldest, Number fnsucursaldest, String fcfolioconsec, Timestamp fdfechaproceso, Number fnnointentos, Number fnstatus, String fccadenaejec, String fcdescerror, String fcproceso, String fcusuario, String fcworkstation, Timestamp fdfecha);
    List<Taex_auditoria> consultarCadenas(String cadena);
}
