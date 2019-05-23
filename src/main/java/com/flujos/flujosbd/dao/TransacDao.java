package com.flujos.flujosbd.dao;

import com.flujos.flujosbd.model.Transac;

import java.util.List;

public interface TransacDao {
    List<Transac> getTransaccion(String ip,  Integer fitranno);
    List<Transac> getTransaccionRango(String ip,  Integer fitranno, Integer fitranno2);

}
