package com.flujos.flujosbd.controller;

import com.flujos.flujosbd.model.Det_transac;

import com.flujos.flujosbd.model.Transac;
import com.flujos.flujosbd.services.TransaccionesService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
public class TransaccionesController {

    @Autowired
    TransaccionesService transaccionesService;


    @RequestMapping(value = {"transacciones/list"}, method = RequestMethod.GET)
    public String consultarTransaccion(
            @RequestParam(name = "fnsucursaldest", required = false) Integer fnsucursaldest,
            @RequestParam(name = "fitranno", required = false) Integer fitranno,
            ModelMap model) throws SQLException {
        List<Transac> lista = null;
        List<Det_transac> lista2 = null;
        if (fnsucursaldest == null && fitranno == null) {
            model.addAttribute("lista", lista);

        } else{
        lista = transaccionesService.ConsultarCabecero(fnsucursaldest, fitranno);
        lista2 = transaccionesService.ConsultarDetalle(fnsucursaldest, fitranno);
        model.addAttribute("lista", lista);
        model.addAttribute("lista2", lista2);
        model.addAttribute("fnsucursaldest",fnsucursaldest);
        model.addAttribute("fitranno",fitranno);
    }
        return"transacciones/list";
    }


    @RequestMapping(value = "/transacciones/downloadXML")
    public void downloadCSV2(HttpServletResponse response,
                            @RequestParam(name = "fnsucursaldest", required = false) Short finotiend,
                            @RequestParam(name = "fitranno", required = false) Integer fitrann,
                            ModelMap model
    ) throws IOException {
        transaccionesService.generarXML(response,finotiend,fitrann);

    }

    /*
    @RequestMapping(value = "/transacciones/downloadXML2")
    public void downloadCSV3(HttpServletResponse response,
                             @RequestParam(name = "fnsucursaldest", required = false) Short finotiend,
                             @RequestParam(name = "fitranno", required = false) Integer fitrann,
                             ModelMap model
    ) throws IOException {
        transaccionesService.generarXML(response,finotiend,fitrann);

    }
    */

}
