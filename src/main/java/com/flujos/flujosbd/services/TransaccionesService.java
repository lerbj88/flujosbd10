package com.flujos.flujosbd.services;

import com.flujos.flujosbd.model.Control;
import com.flujos.flujosbd.model.Det_transac;
import com.flujos.flujosbd.model.Transac;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface TransaccionesService {
    List<Transac> ConsultarCabecero (Integer fnsucursaldest, Integer fitranno);
    List<Det_transac> ConsultarDetalle (Integer fnsucursaldest, Integer fitranno);
    List<Control> ConsultarSucursal (Short fnsucursaldest);
    void generarXML(HttpServletResponse response, Short finotienda, Integer fitranno) throws IOException;
  //  void generarXML2(Short finotienda, Integer fitranno, Integer fitranno2) throws IOException, TransformerException;
   // void PostCurl() throws FileNotFoundException;
}
