package com.flujos.flujosbd.controller;


import com.flujos.flujosbd.dao.UsuarioDao;
import com.flujos.flujosbd.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;


    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String home(Model model) throws SQLException {

        return "home";
    }

    @RequestMapping(value = { "usuarios/list" }, method = RequestMethod.GET)
    public String listarUsuarios(@PageableDefault(size = 5) Pageable pageable, @RequestParam(name = "fiusuario", required = false) Integer fiusuario, Model model) throws SQLException {
        if (fiusuario != null) {
            model.addAttribute("key", fiusuario);
            Usuario usuario = (Usuario) usuarioDao.findByUsuario(fiusuario);
            model.addAttribute("listarUsuarios", usuario);
            return "usuarios/list";
        } else {

            List<Usuario> list = usuarioDao.findAll();
            model.addAttribute("listarUsuarios", list);
            return "usuarios/list";
        }
    }



    @RequestMapping(value = {"usuarios/form"}, method = RequestMethod.GET)
    public String form(@RequestParam(name = "fiusuario", required = false) Integer fiusuario, ModelMap model)throws SQLException {
        if (fiusuario != null) {
            Usuario usuario = (Usuario) usuarioDao.editarUsuario(fiusuario);
            model.addAttribute("usuario", usuario);
        }
        else {
            Usuario usuario = new Usuario();
            model.addAttribute("usuario", usuario);
            model.addAttribute("outMessage", "");
        }
        return "usuarios/form";
    }

    @PostMapping(value = "/usuarios/form")
    public String crearUsusario(@RequestParam("fiusuario") Integer fiusuario,
                         @RequestParam("fcpassword") String fcpassword,
                         @RequestParam("idrol") Integer idrol,
                          Model model) {
        usuarioDao.crearUsuario(fiusuario, fcpassword, idrol);
        System.out.println("name = " + fiusuario + ", password = " + fcpassword + ", rol = "+ idrol);
        return "redirect:/usuarios/list";
    }

    @RequestMapping("/usuarios/eliminarUsuario")
    public String eliminarUsuario (@RequestParam("fiusuario") Integer fiusuario){
        usuarioDao.eliminarUsuario(fiusuario);
        return "redirect:list";
    }

}
