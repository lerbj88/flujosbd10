package com.flujos.flujosbd.dao;

import com.flujos.flujosbd.model.Ctrlmstsuc;

import java.sql.SQLException;
import java.util.List;

public interface Retransmision400Dao {
    boolean testConection (String usr, String pwd, String host) throws SQLException;
    List<Ctrlmstsuc> getErrores21 ();
    Integer getMinTranREM(Integer fisucursal);
}
