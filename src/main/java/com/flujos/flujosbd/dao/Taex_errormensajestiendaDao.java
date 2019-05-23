package com.flujos.flujosbd.dao;

import com.flujos.flujosbd.model.Taex_errormensajestienda;
import com.flujos.flujosbd.model.UsuariosFlujos;

import java.util.List;

public interface Taex_errormensajestiendaDao {
    List<Taex_errormensajestienda> obtenerFlujos(String flujos );
    List<Taex_errormensajestienda> obtenerSucursales(Integer fnflujoid );
    List<Taex_errormensajestienda> obtenerCadenas(Integer fnflujoid, Integer fnsucursaldest);
    List<Taex_errormensajestienda> obtenerCadena(Integer fnflujoid, String  fcfolioconsec);
    String eliminarCadena(Number fnflujoid, Number fnpaisdest, Number fncanaldest, Number fnsucursaldest, String fcfolioconsec);
    String obtenerSucursal (Number fnflujoid, String fnfolioconsec);
    List<Taex_errormensajestienda> filtrarCadenas(String  cadena);
}
