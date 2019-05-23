package com.flujos.flujosbd.controller;

import com.flujos.flujosbd.dao.FlujosDao;
import com.flujos.flujosbd.dao.Taex_errormensajestiendaDao;
import com.flujos.flujosbd.dao.UsuariosFlujosDao;
import com.flujos.flujosbd.model.Taex_errormensajestienda;
import com.flujos.flujosbd.model.UsuariosFlujos;
import com.flujos.flujosbd.services.ReenviosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ReenviosController {



    @Autowired
    private ReenviosService reenviosService;

    @Autowired
    private Taex_errormensajestiendaDao taex_errormensajestiendaDao;

    @Autowired
    private FlujosDao flujosDao;


    @RequestMapping(value = {"reenviosflujos/list"}, method = RequestMethod.GET)
    public String listarFlujos(
           // @RequestParam(name = "fiusuario", required = false) Integer fiusuario,
            ModelMap model)throws SQLException {
        final String user = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer  fiusuario = Integer.parseInt(user);

        List<Taex_errormensajestienda> lista = reenviosService.Obtenercadenas(fiusuario);

        if(lista==null){
            model.addAttribute("mensaje","Sin registros de cadenas en error");
            model.addAttribute("lista", lista);
            return "reenviosflujos/list";
        }
        else {
            model.addAttribute("lista", lista);
            //model.addAttribute("user",fiusuario);
            return "reenviosflujos/list";
        }

    }


    @RequestMapping(value = {"reenviosflujos/listSucursales"}, method = RequestMethod.GET)
    public String listarFlujosSucursales(@RequestParam(name = "fnflujoid", required = false) Integer fnflujoid, ModelMap model)throws SQLException {
        List<Taex_errormensajestienda> lista = reenviosService.Obtenercadenasporsucursal(fnflujoid);
        model.addAttribute("lista", lista);
        model.addAttribute("flujo",fnflujoid);
        return "reenviosflujos/listSucursales";
    }

    @RequestMapping(value = {"reenviosflujos/listCadenas"}, method = RequestMethod.GET)
    public String listarFlujosSucursalesCadenas(@RequestParam(name = "fnsucursaldest", required = false) Integer fnsucursaldest, @RequestParam(name = "fnflujoid", required = false) Integer fnflujoid, ModelMap model)throws SQLException {
        List<Taex_errormensajestienda> lista = reenviosService.Obtenercadenasporsucursalflujo(fnflujoid, fnsucursaldest);
        model.addAttribute("lista", lista);
        model.addAttribute("flujo",fnflujoid);
        model.addAttribute("sucursal",fnsucursaldest);
        return "reenviosflujos/listCadenas";
    }

    @RequestMapping(value = {"reenviosflujos/enviarHistorico"}, method = RequestMethod.GET)
    public String EnviarHistorico(@RequestParam(name = "fnfolioconsec") String fnfolioconsec, @RequestParam(name = "fnflujoid", required = false) Integer fnflujoid, @RequestParam(name = "fnsucursaldest", required = false) Integer fnsucursaldest, ModelMap model)throws SQLException {

        String mensaje=reenviosService.EnviarHistorico(fnflujoid,fnfolioconsec);
        List<Taex_errormensajestienda> lista = reenviosService.Obtenercadenasporsucursalflujo(fnflujoid, fnsucursaldest);
        System.out.print("lista"+lista);
        model.addAttribute("lista", lista);
        model.addAttribute("flujo",fnflujoid);
        model.addAttribute("sucursal",fnsucursaldest);
        System.out.print("exito");
        return "redirect:/reenviosflujos/listCadenas?fnsucursaldest="+fnsucursaldest+"&fnflujoid="+fnflujoid;
    }


    @RequestMapping(value = {"reenviosflujos/enviarPendientes"}, method = RequestMethod.GET)
    public String EnviarPendientes(@RequestParam(name = "fnfolioconsec") String fnfolioconsec, @RequestParam(name = "fnflujoid", required = false) Integer fnflujoid, @RequestParam(name = "fnsucursaldest", required = false) Integer fnsucursaldest, ModelMap model)throws SQLException {

        String mensaje=reenviosService.EnviarPendientes(fnflujoid,fnfolioconsec);
        List<Taex_errormensajestienda> lista = reenviosService.Obtenercadenasporsucursalflujo(fnflujoid, fnsucursaldest);
        System.out.print("lista"+lista);
        model.addAttribute("lista", lista);
        model.addAttribute("flujo",fnflujoid);
        model.addAttribute("sucursal",fnsucursaldest);
        System.out.println("insertado en pendientes con exito");
        return "redirect:/reenviosflujos/listCadenas?fnsucursaldest="+fnsucursaldest+"&fnflujoid="+fnflujoid;
    }



    @RequestMapping(value = {"reenviosflujos/listFiltro"}, method = RequestMethod.GET)
    public String listarFlujosFiltrado(@PageableDefault(size = 5) Pageable pageable,
                                       @RequestParam(name = "fnflujoid", required = false) Integer fnflujoid,
                                       @RequestParam(value = "pag") Optional<Integer> page1,
                                       @RequestParam("size") Optional<Integer> size,
                                       @ModelAttribute("mensaje") String mensaje,
                                       ModelMap model)throws SQLException {

        int esquema = 0;
        Integer currentPage = page1.orElse(0);
        int pageSize = size.orElse(25);
        String descripcionflujo = flujosDao.obtenerDescipcion(fnflujoid);

        Page<Taex_errormensajestienda> lista = reenviosService.Paginado(currentPage , pageSize,fnflujoid, esquema);
        PageWrapper<Taex_errormensajestienda> page = new PageWrapper<Taex_errormensajestienda>(lista, "/reenviosflujos/listFiltro");
        model.addAttribute("lista", page.getContent());
        model.addAttribute("page", page);

        model.addAttribute("flujo", fnflujoid);
        model.addAttribute("flujoid", fnflujoid);
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("descripcionflujo", descripcionflujo);
            return "reenviosflujos/listFiltro";

    }



    @RequestMapping(value = {"reenviosflujos/listFiltros"}, method = RequestMethod.GET)
    public String listarFlujosFiltrado(@PageableDefault(size = 5) Pageable pageable,
                                       @RequestParam(name = "fnflujoid", required = false) Integer fnflujoid,
                                       @RequestParam(name = "fnfolioconsec", required = false) String fnfolioconsec,
                                       @RequestParam(name = "fcdescerror", required = false) String fcdescerror,
                                       @RequestParam(name = "fnsucursaldest", required = false) Integer fnsucursaldest,
                                       @RequestParam(name = "desde", required = false) String desde,
                                       @RequestParam(name = "hasta", required = false) String hasta,
                                       @RequestParam(name = "flujoid", required = false) Integer flujo,
                                       @RequestParam(value = "pag") Optional<Integer> page1,
                                       @RequestParam("size") Optional<Integer> size,
                                       @ModelAttribute("mensaje") String mensaje,
                                       @ModelAttribute("fcdescerror2") String fcdescerror2,
                                       ModelMap model)throws SQLException {

        int esquema = 0;
        Integer currentPage = page1.orElse(0);
        int pageSize = size.orElse(25);

        String descripcionflujo = flujosDao.obtenerDescipcion(flujo);


        if (!fcdescerror2.isEmpty()) {
            Page<Taex_errormensajestienda> lista = reenviosService.PaginadoFiltrado(currentPage, pageSize, flujo, fnfolioconsec, fcdescerror2, fnsucursaldest, desde, hasta, esquema);
            PageWrapper<Taex_errormensajestienda> page = new PageWrapper<Taex_errormensajestienda>(lista, "/reenviosflujos/listFiltros");
            model.addAttribute("lista", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("fcdescerror", fcdescerror2);
        }else
        {
            Page<Taex_errormensajestienda> lista = reenviosService.PaginadoFiltrado(currentPage, pageSize, flujo, fnfolioconsec, fcdescerror, fnsucursaldest, desde, hasta, esquema);
            PageWrapper<Taex_errormensajestienda> page = new PageWrapper<Taex_errormensajestienda>(lista, "/reenviosflujos/listFiltros");
            model.addAttribute("lista", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("fcdescerror", fcdescerror);
        }


            model.addAttribute("flujo", flujo);
            model.addAttribute("flujoid", flujo);
        model.addAttribute("fnfolioconsec", fnfolioconsec);

            model.addAttribute("fnsucursaldest", fnsucursaldest);
            model.addAttribute("desde", desde);
            model.addAttribute("hasta", hasta);
            model.addAttribute("mensaje", mensaje);
            model.addAttribute("descripcionflujo", descripcionflujo);

            return "reenviosflujos/listFiltros";
        }




    @PostMapping(value = "reenviosflujos/listFiltro")
    public String reeenviosPendientesHistorico(
            @RequestParam("action") Integer action ,
            @RequestParam("flujoid") Integer fnflujoid,
            @RequestParam(value = "flujosids" , required = false) String[] flujosids ,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (flujosids==null) {

            redirectAttributes.addFlashAttribute("mensaje", "Se debe seleccionar al menos un registro");

        } else {

            switch (action) {
                case 2: {
                    reenviosService.EnviarHistorico(fnflujoid, flujosids);
                    if (flujosids.length == 1) {
                        redirectAttributes.addFlashAttribute("mensaje", "Se envió a Histórico el folio: " + Arrays.toString(flujosids));
                    } else {
                        redirectAttributes.addFlashAttribute("mensaje", "Se enviaron a Histórico los folios: " + Arrays.toString(flujosids));
                    }
                    break;
                }

                case 1: {
                    reenviosService.EnviarPendientes(fnflujoid, flujosids);
                    if (flujosids.length == 1) {
                        redirectAttributes.addFlashAttribute("mensaje", "Se reintentó el folio: " + Arrays.toString(flujosids));
                    } else {
                        redirectAttributes.addFlashAttribute("mensaje", "Se reintentaron los folios: " + Arrays.toString(flujosids));
                    }
                    break;
                }
            }
        }

        return "redirect:/reenviosflujos/listFiltro?fnflujoid=" + fnflujoid;
    }



    @PostMapping(value = "reenviosflujos/listFiltros")
    public String ReenviosPendientesHistorico(
            @RequestParam("action") Integer action ,
            @RequestParam("flujoid") Integer fnflujoid,
            @RequestParam(name = "fnfolioconsec", required = false) String fnfolioconsec,
            @RequestParam(name = "fcdescerror", required = false) String fcdescerror,
            @RequestParam(name = "fnsucursaldest", required = false) String fnsucursaldest,
            @RequestParam(name = "desde", required = false) String desde,
            @RequestParam(name = "hasta", required = false) String hasta,
            @RequestParam(name = "flujoid", required = false) Integer flujo,
            @RequestParam(value = "flujosids" , required = false) String[] flujosids,
            RedirectAttributes redirectAttributes,
            Model model) {
        String err1= "";

        if (flujosids==null) {

           redirectAttributes.addFlashAttribute("mensaje", "Se debe seleccionar al menos un registro");


        } else {

            switch (action) {
                case 2: {
                    reenviosService.EnviarHistorico(fnflujoid, flujosids);
                    if (flujosids.length == 1) {
                        redirectAttributes.addFlashAttribute("mensaje", "Se envió a Histórico el folio: " + Arrays.toString(flujosids));
                        String err = detalleError(fcdescerror);
                        err1=err;
                    } else {
                        redirectAttributes.addFlashAttribute("mensaje", "Se enviaron a Histórico los folios: " + Arrays.toString(flujosids));
                    }
                    break;
                }

                case 1: {
                    reenviosService.EnviarPendientes(fnflujoid, flujosids);
                    if (flujosids.length == 1) {
                        redirectAttributes.addFlashAttribute("mensaje", "Se reintentó el folio: " + Arrays.toString(flujosids));
                        String err = detalleError(fcdescerror);
                        err1=err;
                    } else {
                        redirectAttributes.addFlashAttribute("mensaje", "Se reintentaron los folios: " + Arrays.toString(flujosids));
                    }
                    break;
                }
            }

            redirectAttributes.addFlashAttribute("fcdescerror2",fcdescerror);
        }
        return "redirect:/reenviosflujos/listFiltros?flujoid=" + flujo+ "&fnfolioconsec="+fnfolioconsec+"&fnsucursaldest="+fnsucursaldest+"&fcdescerror="
                +err1+"&desde="+desde+"&hasta="+hasta;
    }


    @RequestMapping(value = "/reenviosflujos/downloadCSV")
    public void downloadCSV(HttpServletResponse response,
                            @RequestParam(name = "fnflujoid", required = false) Integer fnflujoid,
                            @RequestParam(name = "fnfolioconsec", required = false) String fnfolioconsec,
                            @RequestParam(name = "fcdescerror", required = false) String fcdescerror,
                            @RequestParam(name = "fnsucursaldest", required = false) Integer fnsucursaldest,
                            @RequestParam(name = "desde", required = false) String desde,
                            @RequestParam(name = "hasta", required = false) String hasta,
                            @RequestParam(name = "flujoid", required = false) Integer flujo,
                            ModelMap model
    ) throws IOException {

        int esquema=0;
        Date fecha = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String fechaactual = hourdateFormat.format(fecha);

          String csvFileName = "Esquema Extractor Errores Flujo "+fnflujoid+" "+fechaactual+".csv";
        response.setContentType("text/csv;charset=utf-8");

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        List<Taex_errormensajestienda> list;


        if (fnfolioconsec==null&&fnsucursaldest==null&&fcdescerror==null&&desde==null&&hasta==null) {
            List<Taex_errormensajestienda> lista = reenviosService.filtrarCadenas(fnflujoid, esquema);
             list= lista;
        }else
        {
            List<Taex_errormensajestienda> lista2 = reenviosService.filtrarCadenas(fnflujoid,fnfolioconsec,fcdescerror,fnsucursaldest,desde,hasta, esquema);
            list=lista2;
        }

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE );

        String[] header = { "fnpaisdest", "fncanaldest", "fnsucursaldest",
                "fnfolioconsec", "fnnointentos", "fdfechaproceso", "fccadenaejec", "fcdescerror"  };

        csvWriter.writeHeader(header);

        for (Taex_errormensajestienda aTaex_errormensajestienda : list) {
            csvWriter.write(aTaex_errormensajestienda, header);
        }

        csvWriter.close();
    }


    private String detalleError (String error){

        String error2 = "";

        error2 = error.replaceAll("\\s","");
        return error2;
    }



    @RequestMapping(value = {"reenviosflujos/listFiltroEjecutor"}, method = RequestMethod.GET)
    public String listarFlujosFiltradoEjecutor(@PageableDefault(size = 5) Pageable pageable,
                                       @RequestParam(name = "fnflujoid", required = false) Integer fnflujoid,
                                       @RequestParam(value = "pag") Optional<Integer> page1,
                                       @RequestParam("size") Optional<Integer> size,
                                       @ModelAttribute("mensaje") String mensaje,
                                       ModelMap model)throws SQLException {

        int esquema =7;
        Integer currentPage = page1.orElse(0);
        int pageSize = size.orElse(25);
        String descripcionflujo = flujosDao.obtenerDescipcion(fnflujoid);

        Page<Taex_errormensajestienda> lista = reenviosService.Paginado(currentPage , pageSize,fnflujoid, esquema);
        PageWrapper<Taex_errormensajestienda> page = new PageWrapper<Taex_errormensajestienda>(lista, "/reenviosflujos/listFiltroEjecutor");
        model.addAttribute("lista", page.getContent());
        model.addAttribute("page", page);

        model.addAttribute("flujo", fnflujoid);
        model.addAttribute("flujoid", fnflujoid);
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("descripcionflujo", descripcionflujo);
        return "reenviosflujos/listFiltroEjecutor";

    }


    @RequestMapping(value = {"reenviosflujos/listFiltrosEjecutor"}, method = RequestMethod.GET)
    public String listarFlujosFiltradoEjecutor(@PageableDefault(size = 5) Pageable pageable,
                                       @RequestParam(name = "fnflujoid", required = false) Integer fnflujoid,
                                       @RequestParam(name = "fnfolioconsec", required = false) String fnfolioconsec,
                                       @RequestParam(name = "fcdescerror", required = false) String fcdescerror,
                                       @RequestParam(name = "fnsucursaldest", required = false) Integer fnsucursaldest,
                                       @RequestParam(name = "desde", required = false) String desde,
                                       @RequestParam(name = "hasta", required = false) String hasta,
                                       @RequestParam(name = "flujoid", required = false) Integer flujo,
                                       @RequestParam(value = "pag") Optional<Integer> page1,
                                       @RequestParam("size") Optional<Integer> size,
                                       @ModelAttribute("mensaje") String mensaje,
                                       @ModelAttribute("fcdescerror2") String fcdescerror2,
                                       ModelMap model)throws SQLException {

        int esquema = 7;
        Integer currentPage = page1.orElse(0);
        int pageSize = size.orElse(25);

        String descripcionflujo = flujosDao.obtenerDescipcion(flujo);


        if (!fcdescerror2.isEmpty()) {
            Page<Taex_errormensajestienda> lista = reenviosService.PaginadoFiltrado(currentPage, pageSize, flujo, fnfolioconsec, fcdescerror2, fnsucursaldest, desde, hasta, esquema);
            PageWrapper<Taex_errormensajestienda> page = new PageWrapper<Taex_errormensajestienda>(lista, "/reenviosflujos/listFiltrosEjecutor");
            model.addAttribute("lista", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("fcdescerror", fcdescerror2);
        }else
        {
            Page<Taex_errormensajestienda> lista = reenviosService.PaginadoFiltrado(currentPage, pageSize, flujo, fnfolioconsec, fcdescerror, fnsucursaldest, desde, hasta, esquema);
            PageWrapper<Taex_errormensajestienda> page = new PageWrapper<Taex_errormensajestienda>(lista, "/reenviosflujos/listFiltrosEjecutor");
            model.addAttribute("lista", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("fcdescerror", fcdescerror);
        }


        model.addAttribute("flujo", flujo);
        model.addAttribute("flujoid", flujo);
        model.addAttribute("fnfolioconsec", fnfolioconsec);

        model.addAttribute("fnsucursaldest", fnsucursaldest);
        model.addAttribute("desde", desde);
        model.addAttribute("hasta", hasta);
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("descripcionflujo", descripcionflujo);

        return "reenviosflujos/listFiltrosEjecutor";
    }

    @RequestMapping(value = "/reenviosflujos/downloadCSVEjecutor")
    public void downloadCSVFlujos(HttpServletResponse response,
                            @RequestParam(name = "fnflujoid", required = false) Integer fnflujoid,
                            @RequestParam(name = "fnfolioconsec", required = false) String fnfolioconsec,
                            @RequestParam(name = "fcdescerror", required = false) String fcdescerror,
                            @RequestParam(name = "fnsucursaldest", required = false) Integer fnsucursaldest,
                            @RequestParam(name = "desde", required = false) String desde,
                            @RequestParam(name = "hasta", required = false) String hasta,
                            @RequestParam(name = "flujoid", required = false) Integer flujo,
                            ModelMap model
    ) throws IOException {

        int esquema=7;
        Date fecha = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String fechaactual = hourdateFormat.format(fecha);

        String csvFileName = "Esquema Ejecutor Errores Flujo "+fnflujoid+" "+fechaactual+".csv";
        response.setContentType("text/csv;charset=utf-8");

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        List<Taex_errormensajestienda> list;


        if (fnfolioconsec==null&&fnsucursaldest==null&&fcdescerror==null&&desde==null&&hasta==null) {
            List<Taex_errormensajestienda> lista = reenviosService.filtrarCadenas(fnflujoid, esquema);
            list= lista;
        }else
        {
            List<Taex_errormensajestienda> lista2 = reenviosService.filtrarCadenas(fnflujoid,fnfolioconsec,fcdescerror,fnsucursaldest,desde,hasta, esquema);
            list=lista2;
        }

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE );

        String[] header = { "fnpaisdest", "fncanaldest", "fnsucursaldest",
                "fnfolioconsec", "fnnointentos", "fdfechaproceso", "fccadenaejec", "fcdescerror"  };

        csvWriter.writeHeader(header);

        for (Taex_errormensajestienda aTaex_errormensajestienda : list) {
            csvWriter.write(aTaex_errormensajestienda, header);
        }

        csvWriter.close();
    }


    @PostMapping(value = "reenviosflujos/listFiltroEjecutor")
    public String reeenviosPendientesHistoricoEjecutor(
            @RequestParam("action") Integer action ,
            @RequestParam("flujoid") Integer fnflujoid,
            @RequestParam(value = "flujosids" , required = false) String[] flujosids ,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (flujosids==null) {

            redirectAttributes.addFlashAttribute("mensaje", "Se debe seleccionar al menos un registro");

        } else {

            switch (action) {
                case 2: {
                    reenviosService.EnviarHistorico(fnflujoid, flujosids);
                    if (flujosids.length == 1) {
                        redirectAttributes.addFlashAttribute("mensaje", "Se envió a Histórico el folio: " + Arrays.toString(flujosids));
                    } else {
                        redirectAttributes.addFlashAttribute("mensaje", "Se enviaron a Histórico los folios: " + Arrays.toString(flujosids));
                    }
                    break;
                }

                case 1: {
                    reenviosService.EnviarPendientes(fnflujoid, flujosids);
                    if (flujosids.length == 1) {
                        redirectAttributes.addFlashAttribute("mensaje", "Se reintentó el folio: " + Arrays.toString(flujosids));
                    } else {
                        redirectAttributes.addFlashAttribute("mensaje", "Se reintentaron los folios: " + Arrays.toString(flujosids));
                    }
                    break;
                }
            }
        }

        return "redirect:/reenviosflujos/listFiltroEjecutor?fnflujoid=" + fnflujoid;
    }


    @PostMapping(value = "reenviosflujos/listFiltrosejecutor")
    public String ReenviosPendientesHistoricoEjecutor(
            @RequestParam("action") Integer action ,
            @RequestParam("flujoid") Integer fnflujoid,
            @RequestParam(name = "fnfolioconsec", required = false) String fnfolioconsec,
            @RequestParam(name = "fcdescerror", required = false) String fcdescerror,
            @RequestParam(name = "fnsucursaldest", required = false) String fnsucursaldest,
            @RequestParam(name = "desde", required = false) String desde,
            @RequestParam(name = "hasta", required = false) String hasta,
            @RequestParam(name = "flujoid", required = false) Integer flujo,
            @RequestParam(value = "flujosids" , required = false) String[] flujosids,
            RedirectAttributes redirectAttributes,
            Model model) {
        String err1= "";

        if (flujosids==null) {

            redirectAttributes.addFlashAttribute("mensaje", "Se debe seleccionar al menos un registro");


        } else {

            switch (action) {
                case 2: {
                    reenviosService.EnviarHistorico(fnflujoid, flujosids);
                    if (flujosids.length == 1) {
                        redirectAttributes.addFlashAttribute("mensaje", "Se envió a Histórico el folio: " + Arrays.toString(flujosids));
                        String err = detalleError(fcdescerror);
                        err1=err;
                    } else {
                        redirectAttributes.addFlashAttribute("mensaje", "Se enviaron a Histórico los folios: " + Arrays.toString(flujosids));
                    }
                    break;
                }

                case 1: {
                    reenviosService.EnviarPendientes(fnflujoid, flujosids);
                    if (flujosids.length == 1) {
                        redirectAttributes.addFlashAttribute("mensaje", "Se reintentó el folio: " + Arrays.toString(flujosids));
                        String err = detalleError(fcdescerror);
                        err1=err;
                    } else {
                        redirectAttributes.addFlashAttribute("mensaje", "Se reintentaron los folios: " + Arrays.toString(flujosids));
                    }
                    break;
                }
            }

            redirectAttributes.addFlashAttribute("fcdescerror2",fcdescerror);
        }
        return "redirect:/reenviosflujos/listFiltrosEjecutor?flujoid=" + flujo+ "&fnfolioconsec="+fnfolioconsec+"&fnsucursaldest="+fnsucursaldest+"&fcdescerror="
                +err1+"&desde="+desde+"&hasta="+hasta;
    }


}
