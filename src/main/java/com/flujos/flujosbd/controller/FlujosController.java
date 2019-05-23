package com.flujos.flujosbd.controller;

import com.flujos.flujosbd.dao.FlujosDao;
import com.flujos.flujosbd.dao.ResponsablesflujosDao;
import com.flujos.flujosbd.model.Flujos;
import com.flujos.flujosbd.model.Responsablesflujos;
import com.flujos.flujosbd.services.FlujosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
public class FlujosController {


    @Autowired
    private FlujosDao flujosDao;

    @Autowired
    private FlujosService flujosService;


    @RequestMapping(value = { "flujos/list" }, method = RequestMethod.GET)
    public String listarUsuarios(@RequestParam(name = "id_flujo", required = false) String texto,
                                 @RequestParam(value = "pag") Optional<Integer> page1,
                                 @RequestParam("size") Optional<Integer> size,
                                 Model model) throws SQLException {
        if (texto != null) {
            model.addAttribute("key", texto);
            Integer currentPage = page1.orElse(0);
            int pageSize = size.orElse(10);

            Page<Flujos> lista = flujosService.Paginado(currentPage , pageSize, texto);
            PageWrapper<Flujos> page = new PageWrapper<Flujos>(lista, "/flujos/list");
            model.addAttribute("lista", page.getContent());
            model.addAttribute("page", page);


            return "flujos/list";
        } else {

            Integer currentPage = page1.orElse(0);
            int pageSize = size.orElse(10);

            Page<Flujos> lista = flujosService.Paginado(currentPage , pageSize);
            PageWrapper<Flujos> page = new PageWrapper<Flujos>(lista, "/flujos/list");
            model.addAttribute("lista", page.getContent());
            model.addAttribute("page", page);

            return "flujos/list";
        }
    }



    @RequestMapping(value = {"flujos/form"}, method = RequestMethod.GET)
    public String form(@RequestParam(name = "id_flujo", required = false) Short id_flujo, ModelMap model)throws SQLException {
        if (id_flujo != null) {
            Flujos flujos = (Flujos) flujosDao.obtenerFlujo(id_flujo);
            model.addAttribute("flujo", flujos);
        }
        else {
            Flujos flujos = new Flujos();
            model.addAttribute("flujo", flujos);
            model.addAttribute("outMessage", "");
        }
        return "flujos/form";
    }


    @PostMapping(value = "flujos/form")
    public String crearResponsableflujo(
            @RequestParam("id_flujo") Short id_flujo,
            @RequestParam("descripcion") String descripcion,
            Model model) {
        flujosDao.crearFlujo(id_flujo, descripcion);
        //  System.out.println("name = " + fiusuario + ", password = " + fcpassword + ", rol = "+ idrol);
        return "redirect:/flujos/list";
    }

    @RequestMapping("flujos/eliminarflujo")
    public String eliminarUsuario (@RequestParam("id_flujo") Short id_flujo){
        flujosDao.eliminarFlujo(id_flujo);
        return "redirect:list";
    }

}
