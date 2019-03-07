package com.flujos.flujosbd.controller;


import com.flujos.flujosbd.dao.ResponsablesflujosDao;
import com.flujos.flujosbd.model.Responsablesflujos;
import com.flujos.flujosbd.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ResponsablesflujosController {


    @Autowired
    private ResponsablesflujosDao responsablesflujosDao;


    @RequestMapping(value = { "responsablesflujos/list" }, method = RequestMethod.GET)
    public String listarUsuarios(@RequestParam(name = "fcnombre", required = false) String fcnombre, Model model) throws SQLException {
        if (fcnombre != null) {
            model.addAttribute("key", fcnombre);
            Responsablesflujos responsablesflujos = (Responsablesflujos) responsablesflujosDao.findByResponsableflujo(fcnombre);
            model.addAttribute("lista", responsablesflujos);
            return "responsablesflujos/list";
        } else {

            List<Responsablesflujos> list = responsablesflujosDao.findAll();
            model.addAttribute("lista", list);
            return "responsablesflujos/list";
        }
    }



    @RequestMapping(value = {"responsablesflujos/form"}, method = RequestMethod.GET)
    public String form(@RequestParam(name = "finumempleado", required = false) Integer finumempleado, ModelMap model)throws SQLException {
        if (finumempleado != null) {
            Responsablesflujos responsablesflujos = (Responsablesflujos) responsablesflujosDao.editarResponsable(finumempleado);
            model.addAttribute("responsabledeflujo", responsablesflujos);
        }
        else {
            Responsablesflujos responsablesflujos = new Responsablesflujos();
            model.addAttribute("responsabledeflujo", responsablesflujos);
            model.addAttribute("outMessage", "");
        }
        return "responsablesflujos/form";
    }


    @PostMapping(value = "/responsablesflujos/form")
    public String crearResponsableflujo(
                                @RequestParam("finumempleado") Integer finumempleado,
                                @RequestParam("fcnombre") String fcnombre,
                                @RequestParam("fccorreo") String fccorreo,
                                @RequestParam("ficelular") Integer ficelular,
                                Model model) {
        responsablesflujosDao.crearResponsable(finumempleado, fcnombre, fccorreo, ficelular);
      //  System.out.println("name = " + fiusuario + ", password = " + fcpassword + ", rol = "+ idrol);
        return "redirect:/responsablesflujos/list";
    }

    @RequestMapping("/responsablesflujos/eliminarResponsable")
    public String eliminarUsuario (@RequestParam("finumempleado") Integer finumempleado){
        responsablesflujosDao.eliminarResponsable(finumempleado);
        return "redirect:list";
    }

}
