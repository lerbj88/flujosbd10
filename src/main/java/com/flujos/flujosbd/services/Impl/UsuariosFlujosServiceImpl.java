package com.flujos.flujosbd.services.Impl;

import com.flujos.flujosbd.dao.FlujosDao;
import com.flujos.flujosbd.dao.UsuariosFlujosDao;
import com.flujos.flujosbd.model.Flujos;
import com.flujos.flujosbd.model.Taex_errormensajestienda;
import com.flujos.flujosbd.model.UsuariosFlujos;
import com.flujos.flujosbd.services.UsuariosFlujosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.SQLErrorCodes;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
@Transactional
public class UsuariosFlujosServiceImpl implements UsuariosFlujosService {


    @Autowired
    private UsuariosFlujosDao usuariosFlujosDao;

    @Autowired
    private FlujosDao FlujosDao;

   public  String agregarFlujo (Integer fiusuario, Integer fiflujo)
   {
        String mensaje ="";
       try {
           usuariosFlujosDao.crearFlujo(fiusuario,fiflujo);
           mensaje = "Exito - Flujo "+fiflujo + " Asignado correctamente al usuario "+fiusuario;
       } catch (Exception e) {
           //System.out.println("textosss "+e);
           mensaje = "Error - el registro no logro insertarse ";
       }

        return mensaje;

    }


    public List<Flujos> FlujosxUsuario(Integer fiusuario){


        List<UsuariosFlujos> list =usuariosFlujosDao.obtenerFlujos(fiusuario);

        String flujoslist = "";
        List<Integer> items = new ArrayList<>();
        String listString = "000";
        int rest= 2;
        List<Taex_errormensajestienda> lista2 = null;


            for (Iterator<UsuariosFlujos> i = list.iterator(); i.hasNext(); ) {
                UsuariosFlujos item = i.next();
                items.add(item.getFiflujo());
            }

            for (Integer sa : items) {
                listString += sa + ", ";
            }

            listString = listString.substring(0, listString.length() - rest);

            List <Flujos> lista0 = FlujosDao.obtenerFlujosConcatenados(listString);


            return lista0;

    }

    public List<Flujos> FlujosxUsuario2(Integer fiusuario){


        List<UsuariosFlujos> list =usuariosFlujosDao.obtenerFlujos(fiusuario);

        String flujoslist = "";
        List<Integer> items = new ArrayList<>();
        String listString = "000";
        int rest= 2;
        List<Taex_errormensajestienda> lista2 = null;


        for (Iterator<UsuariosFlujos> i = list.iterator(); i.hasNext(); ) {
            UsuariosFlujos item = i.next();
            items.add(item.getFiflujo());
        }

        for (Integer sa : items) {
            listString += sa + ", ";
        }

        listString = listString.substring(0, listString.length() - rest);

        List <Flujos> lista0 = FlujosDao.obtenerFlujosConcatenados2(listString);


        return lista0;

    }


    public List<Flujos> FlujosxUsuario3(Integer fiusuario){


        List<UsuariosFlujos> list =usuariosFlujosDao.obtenerFlujos(fiusuario);

        String flujoslist = "";
        List<Integer> items = new ArrayList<>();
        String listString = "000";
        int rest= 2;
        List<Taex_errormensajestienda> lista2 = null;


        for (Iterator<UsuariosFlujos> i = list.iterator(); i.hasNext(); ) {
            UsuariosFlujos item = i.next();
            items.add(item.getFiflujo());
        }

        for (Integer sa : items) {
            listString += sa + ", ";
        }

        listString = listString.substring(0, listString.length() - rest);

        List <Flujos> lista0 = FlujosDao.obtenerFlujosConcatenados3(listString);


        return lista0;

    }

}
