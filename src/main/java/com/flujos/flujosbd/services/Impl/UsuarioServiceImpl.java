package com.flujos.flujosbd.services.Impl;

import com.flujos.flujosbd.dao.UsuarioDao;
import com.flujos.flujosbd.model.Usuario;
import com.flujos.flujosbd.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Repository
@Transactional
public class UsuarioServiceImpl implements UsuarioService {


    @Autowired
    private UsuarioDao usuarioDao;


    private static final Logger logger = Logger.getLogger( UsuarioServiceImpl.class.getName() );


    public String actualizarContrasena (Integer fiusuario, String fcpassword, String fcpassword1, String fcpassword2)
    {

        String mensaje ="";
        String mensaje2 = "";

        String pwddb = usuarioDao.obtenerContrasena(fiusuario);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (passwordEncoder.matches(fcpassword, pwddb)) {

            if (Objects.equals(fcpassword1, fcpassword2)){

                String password = fcpassword1;
                BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
                String pwd = passwordEncoder2.encode(password);

                 mensaje2 = usuarioDao.actualizarContrasena(fiusuario,pwd);

                logger.info(mensaje);
                return mensaje2;

            }
            else
            {
                String pwdnuevo = "Error - El valor de las contrasenas nuevas no coinciden";
                mensaje = pwdnuevo;
                logger.info(mensaje);
                return mensaje;
            }


        } else {
            String pwdactual = "Error - La contrasena actual no es valida";
            mensaje = pwdactual;
            logger.info(mensaje);
            return mensaje;

        }



    }


    public Page<Usuario> Paginado(int pag, int size) {

        List<Usuario> lista = usuarioDao.findAll();

        PageRequest page_req2 = new PageRequest(pag, size, Sort.Direction.ASC, "fiusuario");
        int start = (int) page_req2.getOffset();
        int end = (start + page_req2.getPageSize()) > lista.size() ? lista.size() : (start + page_req2.getPageSize());

        Page<Usuario> pages;
        pages = new PageImpl<Usuario>(lista.subList(start, end), page_req2, lista.size());

        return pages;
    }

    public Page<Usuario> Paginado(int pag, int size, String texto) {

        List<Usuario> lista = usuarioDao.consultarUsuarios(texto);

        PageRequest page_req2 = new PageRequest(pag, size, Sort.Direction.ASC, "fiusuario");
        int start = (int) page_req2.getOffset();
        int end = (start + page_req2.getPageSize()) > lista.size() ? lista.size() : (start + page_req2.getPageSize());

        Page<Usuario> pages;
        pages = new PageImpl<Usuario>(lista.subList(start, end), page_req2, lista.size());

        return pages;
    }



}


