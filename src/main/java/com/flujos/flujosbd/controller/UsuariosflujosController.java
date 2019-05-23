package com.flujos.flujosbd.controller;


import com.flujos.flujosbd.dao.UsuariosFlujosDao;
import com.flujos.flujosbd.model.Flujos;
import com.flujos.flujosbd.model.UsuariosFlujos;
import com.flujos.flujosbd.services.UsuariosFlujosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.List;

@Controller
public class UsuariosflujosController {


    @Autowired
    private UsuariosFlujosDao usuariosFlujosDao;

    @Autowired
    private UsuariosFlujosService usuariosFlujosService;



    @RequestMapping(value = {"usuariosflujos/list"}, method = RequestMethod.GET)
    public String listarFlujos(@PageableDefault(size = 5) Pageable pageable,
                               @RequestParam(name = "fiusuario", required = false) Integer fiusuario,
                               @ModelAttribute("mensaje") String mensaje,
                               ModelMap model)throws SQLException {
        //List<UsuariosFlujos> lista = usuariosFlujosDao.findAll(fiusuario);
        List<Flujos> lista = usuariosFlujosService.FlujosxUsuario3(fiusuario);
            model.addAttribute("lista", lista);
            model.addAttribute("user",fiusuario);
        model.addAttribute("mensaje",mensaje);
        return "usuariosflujos/list";
    }


    @RequestMapping(value = {"usuariosflujos/form"}, method = RequestMethod.GET)
    public String form(@RequestParam(name = "fiusuario", required = false) Integer fiusuario,
                        ModelMap model)throws SQLException {
            UsuariosFlujos  usuariosFlujos = new UsuariosFlujos();
            model.addAttribute("usuariosflujos", usuariosFlujos);
        List<Flujos> lista = usuariosFlujosService.FlujosxUsuario(fiusuario);
        model.addAttribute("user",fiusuario);
        model.addAttribute("flujoslist",lista);

        return "usuariosflujos/form";
    }




    @PostMapping(value = "/usuariosflujos/form")
    public String crearUsuarioflujo(
            @RequestParam("fiusuario") Integer fiusuario,
            @RequestParam("id_flujo") Integer fiflujo,
            RedirectAttributes redirectAttributes, Model model) {
        String mensaje = usuariosFlujosService.agregarFlujo(fiusuario,fiflujo);
        redirectAttributes.addFlashAttribute("mensaje", mensaje);
        return "redirect:/usuariosflujos/list?fiusuario="+fiusuario;
    }


    @RequestMapping("/usuariosflujos/eliminarflujo")
    public String eliminarFlujo (@RequestParam("fiflujo") Integer fiflujo){
        usuariosFlujosDao.eliminarFlujo(fiflujo);
        return "redirect:/usuarios/list";
    }

}
