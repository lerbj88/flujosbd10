package com.flujos.flujosbd.controller;

import com.flujos.flujosbd.dao.DbsretransmisionDao;
import com.flujos.flujosbd.dao.Retransmision400Dao;
import com.flujos.flujosbd.model.*;
import com.flujos.flujosbd.services.RetransmisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
public class RetransmisionController {

    @Autowired
    DbsretransmisionDao dbsretransmisionDao;

    @Autowired
    RetransmisionService retransmisionService;

    @Autowired
    Retransmision400Dao retransmision400Dao;


    @RequestMapping(value = {"retransmision/list"}, method = RequestMethod.GET)
    public String retransmitirTransaccion(
                    ModelMap model) throws SQLException {
            List<Dbsretransmision> lista = dbsretransmisionDao.getDbsIseries();
            List<Dbsretransmision> lista2 = dbsretransmisionDao.getDbsOracle();
            model.addAttribute("dbslist", lista);
            model.addAttribute("dbslist2", lista2);
        model.addAttribute("mensaje", "");
        return"retransmision/list";
    }



    @RequestMapping(value={"retransmision/listcatdbs"}, method = RequestMethod.GET)
            public String catalogoDBs( ModelMap model)throws SQLException {
            List<Dbsretransmision> lista = dbsretransmisionDao.findAll();
            model.addAttribute("lista", lista);

            return  "retransmision/listcatdbs";
        }

    @RequestMapping(value = {"retransmision/formcatdbs"}, method = RequestMethod.GET)
    public String form(ModelMap model)throws SQLException {
            Dbsretransmision dbsretransmision = new Dbsretransmision();
            model.addAttribute("dbsretransmision", dbsretransmision);
        return "retransmision/formcatdbs";
    }



    @RequestMapping(value = {"retransmision/updcatdbs"}, method = RequestMethod.GET)
    public String updcatdbform(@RequestParam(name = "fiid", required = false) Short fiid,
                       ModelMap model)throws SQLException {

            Dbsretransmision dbsretransmision = (Dbsretransmision) dbsretransmisionDao.getDb(fiid);
            model.addAttribute("dbsretransmision", dbsretransmision);
        return "retransmision/updcatdbs";
    }



    @PostMapping(value = "retransmision/formcatdbs")
    public String crearDB(
            @ModelAttribute("dbsretransmision") Dbsretransmision dbsretransmision,
            Model model) {
        dbsretransmisionDao.crearDb(dbsretransmision.getFitipo(),dbsretransmision.getFcdescripcion(),dbsretransmision.getFcdatabase(),dbsretransmision.getFcservicename(),dbsretransmision.getFcip(),dbsretransmision.getFcusuario(),dbsretransmision.getFcpassword());
        return "redirect:/retransmision/listcatdbs";

    }


    @PostMapping(value = "retransmision/updcatdbs")
    public String updDB(
            @ModelAttribute("dbsretransmision") Dbsretransmision dbsretransmision,
            Model model) {
            dbsretransmisionDao.actualizarDb(dbsretransmision.getFiid(),dbsretransmision.getFitipo(),dbsretransmision.getFcdescripcion(),dbsretransmision.getFcdatabase(),dbsretransmision.getFcservicename(),dbsretransmision.getFcip(),dbsretransmision.getFcusuario());
        return "redirect:/retransmision/listcatdbs";

    }

    @RequestMapping(value = {"retransmision/updpwd"}, method = RequestMethod.GET)
    public String actualizarcontrasena(@RequestParam("fiid") Integer fiid,
            ModelMap model)throws SQLException {
        Dbsretransmision dbsretransmision =  new Dbsretransmision();
        model.addAttribute("dbsretransmision", dbsretransmision);
        model.addAttribute("mensaje", "");
        model.addAttribute("fiid", fiid);
        return "retransmision/updpwd";
    }

    @PostMapping(value = "/retransmision/updpwd")
    public String actualizarContrasena  (@ModelAttribute(value = "dbsretransmision") Dbsretransmision dbsretransmision,
                                         Model model) {
        String mensaje = retransmisionService.actualizarContrasena(dbsretransmision.getFiid(),dbsretransmision.getFcpassword(), dbsretransmision.getFcpassword1(), dbsretransmision.getFcpassword2());
        model.addAttribute("mensaje",mensaje);

        return "retransmision/updpwd";
    }

    @RequestMapping("/retransmision/eliminardb")
    public String eliminarDb (@RequestParam("fiid") Short fiid){
        dbsretransmisionDao.eliminarDb(fiid);
        return "redirect:listcatdbs";
    }

    @PostMapping(value = "/retransmision/valuser")
    public String validarUserAS400  (
                                     @RequestParam("destino1") Short fiidconection,
                                     @RequestParam("fcusuario") String fcusuario,
                                     @RequestParam("fccontrasena") String fcpassword,
                                     RedirectAttributes redirectAttributes,
                                         Model model) throws SQLException {

            String valuser = retransmisionService.validarUsuario400(fcusuario, fcpassword, fiidconection);
            if (valuser.contains("Error")) {
                redirectAttributes.addFlashAttribute("mensaje", valuser);
                return "redirect:/retransmision/lista";
            } else {
                redirectAttributes.addFlashAttribute("mensaje", valuser);
                redirectAttributes.addFlashAttribute("fiidconection", fiidconection);

                return "redirect:/retransmision/sucursal";
            }

    }


    @RequestMapping(value = {"/retransmision/lista"}, method = RequestMethod.GET)
    public String retransmitirTransaccion2(
            @ModelAttribute("mensaje") String mensaje,
            ModelMap model) throws SQLException {
        List<Dbsretransmision> lista = dbsretransmisionDao.getDbsIseries();
        model.addAttribute("dbslist", lista);
        model.addAttribute("mensaje", mensaje);
        return"/retransmision/lista";
    }


    @RequestMapping(value = {"/retransmision/sucursal"}, method = RequestMethod.GET)
    public String retransmitirsucursal(
            @ModelAttribute("mensaje") String mensaje,
            @ModelAttribute("fiidconection") Short fiidconection,

            ModelMap model) throws SQLException {
            model.addAttribute("mensaje", mensaje);

        Dbsretransmision dbsretransmision = dbsretransmisionDao.getDb(fiidconection);
        model.addAttribute("fcdbdescripcion", dbsretransmision.getFcdescripcion());
        return"/retransmision/sucursal";
    }

    @RequestMapping(value = {"/retransmision/errores21"}, method = RequestMethod.GET)
    public String getErrores21(
            ModelMap model)throws SQLException{
        //Dbsretransmision dbsretransmision = dbsretransmisionDao.getDb(fiidconection);
       // List<Ctrlmstsuc> lista = retransmision400Dao.getErrores21();
        List<Ctrlmstsuc> lista = retransmisionService.getErrores21();
                model.addAttribute("lista", lista);
        model.addAttribute("mensaje", "");
        model.addAttribute("fcdbdescripcion", "");
        System.out.println("desde controlador"+lista);

    return "retransmision/errores21";
    }

}
