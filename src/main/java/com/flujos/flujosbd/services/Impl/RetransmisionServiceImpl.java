package com.flujos.flujosbd.services.Impl;

import com.flujos.flujosbd.dao.DbsretransmisionDao;
import com.flujos.flujosbd.dao.Retransmision400Dao;
import com.flujos.flujosbd.model.Ctrlmstsuc;
import com.flujos.flujosbd.model.Dbsretransmision;
import com.flujos.flujosbd.services.RetransmisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.net.ConnectException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Repository
@Transactional
public class RetransmisionServiceImpl implements RetransmisionService {

@Autowired
    DbsretransmisionDao dbsretransmisionDao;

@Autowired
    Retransmision400Dao retransmision400Dao;

    private static final Logger logger = Logger.getLogger( RetransmisionServiceImpl.class.getName() );

    public String actualizarContrasena (Short fiid, String fcpassword, String fcpassword1, String fcpassword2)
    {

        String mensaje ="";
        String mensaje2 = "";

        String pwddb = dbsretransmisionDao.obtenerContrasena(fiid);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (passwordEncoder.matches(fcpassword, pwddb)) {

            if (Objects.equals(fcpassword1, fcpassword2)){

                String password = fcpassword1;
                BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
                String pwd = passwordEncoder2.encode(password);

                mensaje2 = dbsretransmisionDao.actualizarContrasena(fiid,pwd);

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


    public String validarUsuario400 (String user, String pwd, Short iddbconection) throws SQLException {

        String mensaje = "";
        Dbsretransmision dbsretransmision = dbsretransmisionDao.getDb(iddbconection);
        String ip = dbsretransmision.getFcip();

        boolean test400 = retransmision400Dao.testConection(user,pwd,ip);

        String valuser = String.valueOf(test400);

        if (valuser=="true"){
             mensaje = "Validacion de usuario: "+user+" Exitosa";

        }else {
            mensaje = "Error en la validacion del usuario: "+user;
        }
        return mensaje;

    }

    public List<Ctrlmstsuc>  getErrores21 (){

        Integer minTransac = null;
        Integer totFaltantes = null;
        String rangoFaltante = "";

        List<Ctrlmstsuc> lista = retransmision400Dao.getErrores21();
        List<Ctrlmstsuc> lista2 = null;

            if (lista.get(0).getFcnegocio().equals("MEXREM")){
                minTransac = retransmision400Dao.getMinTranREM(lista.get(0).getAdnsuc());
                totFaltantes = (minTransac-1) - lista.get(0).getAdnutr();
                rangoFaltante = ( lista.get(0).getAdnutr() + 1 )+ " - " + (minTransac - 1);
                lista.get(0).setTotfaltantes(totFaltantes);
                lista.get(0).setRangofaltante(rangoFaltante);
                lista2=lista;
            }

        return lista2;
    }

}
