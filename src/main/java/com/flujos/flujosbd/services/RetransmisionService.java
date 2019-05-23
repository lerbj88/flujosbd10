package com.flujos.flujosbd.services;

import com.flujos.flujosbd.model.Ctrlmstsuc;

import java.net.ConnectException;
import java.sql.SQLException;
import java.util.List;

public interface RetransmisionService {

    String actualizarContrasena(Short fiid, String fcpassword, String fcpassword1, String fcpassword2);
    String validarUsuario400 (String user, String pwd, Short iddbconection) throws SQLException;
    List<Ctrlmstsuc> getErrores21 ();
}
