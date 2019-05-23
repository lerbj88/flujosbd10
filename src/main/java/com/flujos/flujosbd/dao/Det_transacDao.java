package com.flujos.flujosbd.dao;


import com.flujos.flujosbd.model.Det_transac;
import com.flujos.flujosbd.model.Sucursal;

import java.util.List;

public interface Det_transacDao {
     List<Det_transac> getDetalle(String ip , Integer fitranno);
     List<Det_transac> getDetalleRango(String ip , Integer fitranno , Integer fitranno2);
}
