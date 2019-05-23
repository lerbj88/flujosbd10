package com.flujos.flujosbd.controller;


import com.flujos.flujosbd.dao.FlujosDao;
import com.flujos.flujosbd.dao.Taex_histmensajestiendaDao;
import com.flujos.flujosbd.model.Flujos;
import com.flujos.flujosbd.model.Taex_errormensajestienda;
import com.flujos.flujosbd.model.Taex_histmensajestienda;
import com.flujos.flujosbd.services.ConsultarHistoricoService;
import com.flujos.flujosbd.services.UsuariosFlujosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class HistoricoController {


    @Autowired
    private Taex_histmensajestiendaDao taex_histmensajestiendaDao;

    @Autowired
    private FlujosDao flujosDao;

    @Autowired
    private ConsultarHistoricoService consultarHistoricoService;

    @Autowired
    private UsuariosFlujosService usuariosFlujosService;



    @RequestMapping(value = {"historico/list"}, method = RequestMethod.GET)
    public String consultarCadenas(
                                   ModelMap model)throws SQLException {

        final String user = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer  fiusuario = Integer.parseInt(user);

          // List<Flujos> flujosxusuario = flujosDao.obtenerFlujosxUsuario(fiusuario);
        List<Flujos> flujosxusuario = usuariosFlujosService.FlujosxUsuario2(fiusuario);
            model.addAttribute("flujoslist",flujosxusuario);

        return "historico/list";
    }




    @RequestMapping(value = {"historico/lists"}, method = RequestMethod.GET)
    public String consultarCadenasFiltrado(@RequestParam(name = "fnflujoid", required = false) Integer fnflujoid,
                                            @RequestParam(name = "fnsucursaldest", required = false) Integer fnsucursaldest,
                                            @RequestParam(name = "Desde", required = false) String Desde,
                                            @RequestParam(name = "Hasta", required = false) String Hasta,
                                           @RequestParam(value = "pag") Optional<Integer> page1,
                                           @RequestParam("size") Optional<Integer> size,
                                            ModelMap model)throws SQLException {

        final String user = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer  fiusuario = Integer.parseInt(user);

        //List<Flujos> flujosporusuario = flujosDao.obtenerFlujosxUsuario(fiusuario);
        List<Flujos> flujosporusuario = usuariosFlujosService.FlujosxUsuario2(fiusuario);

        Integer currentPage = page1.orElse(0);
        int pageSize = size.orElse(25);

        Page<Taex_histmensajestienda> lista = consultarHistoricoService.Paginado(currentPage , pageSize,fnflujoid,fnsucursaldest, Desde, Hasta);
        PageWrapper<Taex_histmensajestienda> page = new PageWrapper<Taex_histmensajestienda>(lista, "/historico/lists");
        model.addAttribute("lista", page.getContent());
        model.addAttribute("page", page);


        model.addAttribute("fnflujoid", fnflujoid);
        model.addAttribute("flujoid", fnflujoid);
        model.addAttribute("fnsucursaldest", fnsucursaldest);
        model.addAttribute("Desde", Desde);
        model.addAttribute("Hasta", Hasta);
        //model.addAttribute("totalregistros", totalregistros);
        model.addAttribute("flujoslist",flujosporusuario);
        //return "redirect:/historico/lists?fnflujoid="+fnflujoid+"&fnsucursaldest="+fnsucursaldest+"&Desde="+Desde+"&Hasta="+Hasta;
        return "historico/lists";

    }



    @RequestMapping(value = "/historico/downloadCSV")
    public void downloadCSV(HttpServletResponse response,
                            @RequestParam(name = "fnflujoid", required = false) Integer fnflujoid,
                            @RequestParam(name = "fnsucursaldest", required = false) Integer fnsucursaldest,
                            @RequestParam(name = "Desde", required = false) String Desde,
                            @RequestParam(name = "Hasta", required = false) String Hasta,
                            ModelMap model
                            ) throws IOException {


        Date fecha = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String fechaactual = hourdateFormat.format(fecha);

        String csvFileName = "Historico Flujo "+fnflujoid+" "+fechaactual+".csv";

        response.setContentType("text/csv;charset=utf-8");

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);

        List<Taex_histmensajestienda> lista = consultarHistoricoService.ConsultarHistorico( fnflujoid,  fnsucursaldest,  Desde ,  Hasta);
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);

        String[] header = { "fnflujoid", "fnpaisdest", "fncanaldest", "fnsucursaldest",
                "fnfolioconsec", "fdfechaproceso", "fccadenaejec", "fnnointentos" };

        csvWriter.writeHeader(header);

        for (Taex_histmensajestienda aTaex_histmensajestienda : lista) {
            csvWriter.write(aTaex_histmensajestienda, header);
        }

        csvWriter.close();
    }



}



