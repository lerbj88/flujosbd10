package com.flujos.flujosbd.dao;

import com.flujos.flujosbd.model.Control;

import java.util.List;

public interface ControlDao {

    public List<Control> getSucursal(String Ip, Short finotienda);
}
