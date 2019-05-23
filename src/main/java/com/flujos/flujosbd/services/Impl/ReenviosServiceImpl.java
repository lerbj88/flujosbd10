package com.flujos.flujosbd.services.Impl;

import com.flujos.flujosbd.dao.*;
import com.flujos.flujosbd.model.*;
import com.flujos.flujosbd.services.ReenviosService;
import javassist.bytecode.stackmap.TypeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Repository
@Transactional
public class ReenviosServiceImpl implements ReenviosService {



    @Autowired
    private UsuariosFlujosDao usuariosFlujosDao;

    @Autowired
    private Taex_errormensajestiendaDao taex_errormensajestiendaDao;

    @Autowired
    private Taex_errormensajestiendaDao2 taex_errormensajestiendaDao2;

    @Autowired
    private Taex_histmensajestiendaDao taex_histmensajestiendaDao;

    @Autowired
    private Taex_histmensajestiendaDao2 taex_histmensajestiendaDao2;

    @Autowired
    private Taex_mensajestiendaDao taex_mensajestiendaDao;

    @Autowired
    private Taex_mensajestiendaDao2 taex_mensajestiendaDao2;

    @Autowired
    private Taex_auditoriaDao taex_auditoriaDao;

    @Autowired
    private FlujosDao flujosDao;


    public List<Taex_errormensajestienda> Obtenercadenas(Integer fiusuario)  {

        List<UsuariosFlujos> list =usuariosFlujosDao.obtenerFlujos(fiusuario);

        String flujoslist = "";
        List<Integer> items = new ArrayList<>();
        String listString = "";
        int rest= 2;
        List<Taex_errormensajestienda> lista2 = null;

        if (list.isEmpty()){

            lista2=null;

        } else {

            for (Iterator<UsuariosFlujos> i = list.iterator(); i.hasNext(); ) {
                UsuariosFlujos item = i.next();
                items.add(item.getFiflujo());
            }

            for (Integer sa : items) {
                listString += sa + ", ";
            }

            listString = listString.substring(0, listString.length() - rest);


            //List<Taex_mensajestienda> lista0 = taex_mensajestiendaDao.obtenerFlujos(listString);
            List<Taex_errormensajestienda> lista = taex_errormensajestiendaDao.obtenerFlujos(listString);
            List<Taex_errormensajestienda> lista3 = taex_errormensajestiendaDao2.obtenerFlujos(listString);

            lista.addAll(lista3);

            lista2=lista;


        }
        return lista2;
    }


    public List <Taex_errormensajestienda> Obtenercadenasporsucursal (Integer fnflujoid){

        List<Taex_errormensajestienda> lista =taex_errormensajestiendaDao.obtenerSucursales(fnflujoid);

        return lista;
    }



    public List <Taex_errormensajestienda> Obtenercadenasporsucursalflujo (Integer fnflujoid, Integer fnsucursaldest){

        List<Taex_errormensajestienda> lista =taex_errormensajestiendaDao.obtenerCadenas(fnflujoid,fnsucursaldest);

        return lista;
    }

    public String EnviarHistorico (Integer fnflujoid, String fnfolioconsec){

        String mensaje ="Envio exitoso";
        Number fnflujoid2 = 0;
        Number fnpaisdest = 0;
        Number fncanaldest = 0;
        Number fnsucursaldest = 0;
        String fnfolioconsec2="";
        Timestamp fdfechaproceso = null;
        Number fnpaisorig = 0;
        Number fncanalorig = 0;
        Number fnsucursalorig = 0;
        Timestamp fdfechainsercion = null;
        Timestamp fdfechaenviotda=null;
        Number fnstatus = 99;
        Number fnnotransaccion = 0;
        String fccadenaejec ="";
        Number fnnointentos = 0;
        String fcdescerror = "";
        String fcproceso = "Envio a Historico";
        String fiusuario = getUser();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        String ip = request.getRemoteAddr();

        Number fnstatus2 = 0;

        List<Taex_errormensajestienda> lista =taex_errormensajestiendaDao.obtenerCadena(fnflujoid,fnfolioconsec);

        for (Iterator<Taex_errormensajestienda> i = lista.iterator(); i.hasNext();) {
            Taex_errormensajestienda item = i.next();
            fnflujoid2 = (item.getFnflujoid());
            fnpaisdest = (item.getFnpaisdest());
            fncanaldest = (item.getFncanaldest());
            fnsucursaldest = (item.getFnsucursaldest());
            fnfolioconsec2 = (item.getFnfolioconsec());
            fdfechaproceso = (item.getFdfechaproceso());
            fnpaisorig = (item.getFnpaisorig());
            fncanalorig = (item.getFncanalorig());
            fnsucursalorig = (item.getFnsucursalorig());
            fdfechainsercion = (item.getFdfechainsercion());
            fdfechaenviotda = (item.getFdfechaenviotda());
            fccadenaejec = (item.getFccadenaejec());
            fnnointentos = (item.getFnnointentos());
            fcdescerror = (item.getFcdescerror());
            fnstatus2 = (item.getFnstatus());
        }


        String hist = taex_histmensajestiendaDao.InsertarHistorico(fnflujoid2, fnpaisdest, fncanaldest,fnsucursaldest,fnfolioconsec2,fdfechaproceso,fnpaisorig,fncanalorig,fnsucursalorig,fdfechainsercion,fdfechaenviotda,fnstatus,fnnotransaccion,fccadenaejec,fnnointentos);
        String err = taex_errormensajestiendaDao.eliminarCadena(fnflujoid2,fnpaisdest,fncanaldest,fnsucursaldest,fnfolioconsec2);

        taex_auditoriaDao.crearAuditoria(fnflujoid2,fnpaisdest,fncanaldest, fnsucursaldest,fnfolioconsec2,fdfechaproceso,fnnointentos,fnstatus2,fccadenaejec,fcdescerror,fcproceso,fiusuario,ip,fdfechaproceso);


        return mensaje;
    }


    public String EnviarHistorico2 (Integer fnflujoid, String fnfolioconsec){

        String mensaje ="Envio exitoso";
        Number fnflujoid2 = 0;
        Number fnpaisdest = 0;
        Number fncanaldest = 0;
        Number fnsucursaldest = 0;
        String fnfolioconsec2="";
        Timestamp fdfechaproceso = null;
        Number fnpaisorig = 0;
        Number fncanalorig = 0;
        Number fnsucursalorig = 0;
        Timestamp fdfechainsercion = null;
        Timestamp fdfechaenviotda=null;
        Number fnstatus = 99;
        Number fnnotransaccion = 0;
        String fccadenaejec ="";
        Number fnnointentos = 0;
        String fcdescerror = "";
        String fcproceso = "Envio a Historico";
        String fiusuario = getUser();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        String ip = request.getRemoteAddr();

        Number fnstatus2 = 0;

        List<Taex_errormensajestienda> lista =taex_errormensajestiendaDao2.obtenerCadena(fnflujoid,fnfolioconsec);

        for (Iterator<Taex_errormensajestienda> i = lista.iterator(); i.hasNext();) {
            Taex_errormensajestienda item = i.next();
            fnflujoid2 = (item.getFnflujoid());
            fnpaisdest = (item.getFnpaisdest());
            fncanaldest = (item.getFncanaldest());
            fnsucursaldest = (item.getFnsucursaldest());
            fnfolioconsec2 = (item.getFnfolioconsec());
            fdfechaproceso = (item.getFdfechaproceso());
            fnpaisorig = (item.getFnpaisorig());
            fncanalorig = (item.getFncanalorig());
            fnsucursalorig = (item.getFnsucursalorig());
            fdfechainsercion = (item.getFdfechainsercion());
            fdfechaenviotda = (item.getFdfechaenviotda());
            fccadenaejec = (item.getFccadenaejec());
            fnnointentos = (item.getFnnointentos());
            fcdescerror = (item.getFcdescerror());
            fnstatus2 = (item.getFnstatus());
        }


        String hist = taex_histmensajestiendaDao2.InsertarHistorico(fnflujoid2, fnpaisdest, fncanaldest,fnsucursaldest,fnfolioconsec2,fdfechaproceso,fnpaisorig,fncanalorig,fnsucursalorig,fdfechainsercion,fdfechaenviotda,fnstatus,fnnotransaccion,fccadenaejec,fnnointentos);
        String err = taex_errormensajestiendaDao2.eliminarCadena(fnflujoid2,fnpaisdest,fncanaldest,fnsucursaldest,fnfolioconsec2);

        taex_auditoriaDao.crearAuditoria(fnflujoid2,fnpaisdest,fncanaldest, fnsucursaldest,fnfolioconsec2,fdfechaproceso,fnnointentos,fnstatus2,fccadenaejec,fcdescerror,fcproceso,fiusuario,ip,fdfechaproceso);

        return mensaje;
    }


    public String EnviarPendientes (Integer fnflujoid, String fnfolioconsec) {

        String mensaje ="Envio exitoso";
        Number fnflujoid2 = 0;
        Number fnpaisdest = 0;
        Number fncanaldest = 0;
        Number fnsucursaldest = 0;
        String fnfolioconsec2="";
        Timestamp fdfechaproceso = null;
        Number fnpaisorig = 0;
        Number fncanalorig = 0;
        Number fnsucursalorig = 0;
        Timestamp fdfechainsercion = null;
        Timestamp fdfechaenviotda=null;
        Number fnstatusext = 0;
        String fccadenaejec ="";
        Number fnnointentos = 0;
        String fcdescerror = "";
        String fcproceso = "Envio a Pendientes";
        String fiusuario = getUser();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        String ip = request.getRemoteAddr();

        Number fnstatus2 = 0;


        List<Taex_errormensajestienda> lista =taex_errormensajestiendaDao.obtenerCadena(fnflujoid,fnfolioconsec);

        for (Iterator<Taex_errormensajestienda> i = lista.iterator(); i.hasNext();) {
            Taex_errormensajestienda item = i.next();
            fnflujoid2 = (item.getFnflujoid());
            fnpaisdest = (item.getFnpaisdest());
            fncanaldest = (item.getFncanaldest());
            fnsucursaldest = (item.getFnsucursaldest());
            fnfolioconsec2 = (item.getFnfolioconsec());
            fdfechaproceso = (item.getFdfechaproceso());
            fnpaisorig = (item.getFnpaisorig());
            fncanalorig = (item.getFncanalorig());
            fnsucursalorig = (item.getFnsucursalorig());
            fdfechainsercion = (item.getFdfechainsercion());
            fdfechaenviotda =(item.getFdfechaenviotda());
            fccadenaejec = (item.getFccadenaejec());
            fnnointentos = (item.getFnnointentos());
            fcdescerror = (item.getFcdescerror());
            fnstatus2 = (item.getFnstatus());
        }


        String hist = taex_mensajestiendaDao.InsertarPendientes(fnflujoid2, fnpaisdest, fncanaldest,fnsucursaldest,fnfolioconsec2,fnpaisorig,fncanalorig,fnsucursalorig,fdfechainsercion,fdfechaenviotda,fnstatusext,fccadenaejec,fnnointentos);
        String err = taex_errormensajestiendaDao.eliminarCadena(fnflujoid2,fnpaisdest,fncanaldest,fnsucursaldest,fnfolioconsec2);

        taex_auditoriaDao.crearAuditoria(fnflujoid2,fnpaisdest,fncanaldest, fnsucursaldest,fnfolioconsec2,fdfechaproceso,fnnointentos,fnstatus2,fccadenaejec,fcdescerror,fcproceso,fiusuario,ip,fdfechaproceso);

        return mensaje;
    }


    public String EnviarPendientes2 (Integer fnflujoid, String fnfolioconsec) {

        String mensaje ="Envio exitoso";
        Number fnflujoid2 = 0;
        Number fnpaisdest = 0;
        Number fncanaldest = 0;
        Number fnsucursaldest = 0;
        String fnfolioconsec2="";
        Timestamp fdfechaproceso = null;
        Number fnpaisorig = 0;
        Number fncanalorig = 0;
        Number fnsucursalorig = 0;
        Timestamp fdfechainsercion = null;
        Timestamp fdfechaenviotda=null;
        Number fnstatusext = 0;
        String fccadenaejec ="";
        Number fnnointentos = 0;
        String fcdescerror = "";
        String fcproceso = "Envio a Pendientes";
        String fiusuario = getUser();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        String ip = request.getRemoteAddr();

        Number fnstatus2 = 0;


        List<Taex_errormensajestienda> lista =taex_errormensajestiendaDao2.obtenerCadena(fnflujoid,fnfolioconsec);

        for (Iterator<Taex_errormensajestienda> i = lista.iterator(); i.hasNext();) {
            Taex_errormensajestienda item = i.next();
            fnflujoid2 = (item.getFnflujoid());
            fnpaisdest = (item.getFnpaisdest());
            fncanaldest = (item.getFncanaldest());
            fnsucursaldest = (item.getFnsucursaldest());
            fnfolioconsec2 = (item.getFnfolioconsec());
            fdfechaproceso = (item.getFdfechaproceso());
            fnpaisorig = (item.getFnpaisorig());
            fncanalorig = (item.getFncanalorig());
            fnsucursalorig = (item.getFnsucursalorig());
            fdfechainsercion = (item.getFdfechainsercion());
            fdfechaenviotda =(item.getFdfechaenviotda());
            fccadenaejec = (item.getFccadenaejec());
            fnnointentos = (item.getFnnointentos());
            fcdescerror = (item.getFcdescerror());
            fnstatus2 = (item.getFnstatus());
        }


        String hist = taex_mensajestiendaDao2.InsertarPendientes(fnflujoid2, fnpaisdest, fncanaldest,fnsucursaldest,fnfolioconsec2,fnpaisorig,fncanalorig,fnsucursalorig,fdfechainsercion,fdfechaenviotda,fnstatusext,fccadenaejec,fnnointentos);
        String err = taex_errormensajestiendaDao2.eliminarCadena(fnflujoid2,fnpaisdest,fncanaldest,fnsucursaldest,fnfolioconsec2);

        taex_auditoriaDao.crearAuditoria(fnflujoid2,fnpaisdest,fncanaldest, fnsucursaldest,fnfolioconsec2,fdfechaproceso,fnnointentos,fnstatus2,fccadenaejec,fcdescerror,fcproceso,fiusuario,ip,fdfechaproceso);

        return mensaje;
    }



        public List<Taex_errormensajestienda> filtrarCadenas(Number  fnflujoid, int esquema){

        Integer fitipo =  getTipoFlujo (fnflujoid);
            List <Taex_errormensajestienda> lista3 = null;

        switch (fitipo){

            case 1:{

                String cadena = "SELECT FNPAISDEST, FNCANALDEST, FNSUCURSALDEST, FNFOLIOCONSEC," +
                        "FNNOINTENTOS, FDFECHAPROCESO, FCCADENAEJEC, FCDESCERROR" +
                        " FROM XTRACT.TAEX_ERRORMENSAJESTIENDA WHERE FNFLUJOID="+fnflujoid +
                        " AND FNPAISDEST >=0 AND FNCANALDEST>=0 " +
                        " AND FNSUCURSALDEST>0 AND FNSTATUS="+esquema;
                List <Taex_errormensajestienda> lista = taex_errormensajestiendaDao.filtrarCadenas(cadena);
                lista3 = lista;
                break;
            }

            case 2:{

                String cadena = "SELECT FNPAISDEST, FNCANALDEST, FNSUCURSALDEST, FNFOLIOCONSEC," +
                        "FNNOINTENTOS, FDFECHAPROCESO, FCCADENAEJEC, FCDESCERROR" +
                        " FROM XTRACT.TAEX_ERRORMENSAJESTIENDA2 WHERE FNFLUJOID="+fnflujoid +
                        " AND FNPAISDEST >=0 AND FNCANALDEST>=0 " +
                        " AND FNSUCURSALDEST>0 AND FNSTATUS="+esquema;
                List <Taex_errormensajestienda> lista = taex_errormensajestiendaDao2.filtrarCadenas(cadena);
                lista3 = lista;
                break;
            }
        }

        return lista3;

        }



        public  List<Taex_errormensajestienda> filtrarCadenas(Number fnflujoid, String fnfolioconsec, String fcdescerror, Number fnsucursaldest, String desde, String hasta, int esquema)
        {

            Date fecha = new Date();
            DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
            DateFormat hourdateFormat2 = new SimpleDateFormat("2000-01-01-00-00");
            String fechaactual = hourdateFormat.format(fecha);
            String desde2="";
            String hasta2 = "";
            Integer fitipo =  getTipoFlujo (fnflujoid);
            String tabla1="XTRACT.TAEX_ERRORMENSAJESTIENDA";
            String tabla2="XTRACT.TAEX_ERRORMENSAJESTIENDA2";
            String tabla="";
            List <Taex_errormensajestienda> lista3 = null;

                if (!desde.isEmpty()) {
                     desde2 = "'" + desde + "-00.00" + "'";
                }

                if (!hasta.isEmpty()) {
                     hasta2 = "'" + hasta + "-23.59" + "'";
                }

                String fechainicio =  hourdateFormat2.format(fecha);
                fechainicio = "'"+fechainicio+"'";
                fechaactual = "'"+fechaactual+"'";

                switch(fitipo){
                    case 1:{
                    tabla=tabla1;
                        break;
                    }
                    case 2:{
                        tabla=tabla2;
                        break;
                    }
                }

            String cadena = "SELECT FNPAISDEST, FNCANALDEST, FNSUCURSALDEST, FNFOLIOCONSEC," +
                    "FNNOINTENTOS, FDFECHAPROCESO, FCCADENAEJEC, FCDESCERROR" +
                    " FROM "+tabla +" WHERE FNFLUJOID="+fnflujoid + " AND FNPAISDEST >= 0" +
                    "  AND FNCANALDEST >= 0 AND  FNSTATUS="+esquema;

            if (!fnfolioconsec.isEmpty()) {
                fnfolioconsec = "'"+fnfolioconsec+"'";
                cadena = cadena + " and fnfolioconsec = " + fnfolioconsec;
            }
            if (!fcdescerror.isEmpty()) {
                fcdescerror = "'%"+fcdescerror+"%'";

               cadena = cadena + " and fcdescerror like " + fcdescerror;
                }
            if (fnsucursaldest!=null){

               cadena = cadena + " and fnsucursaldest = " + fnsucursaldest;
                    }
            if (desde2.contains("00.00")&& (hasta.isEmpty())) {

               cadena = cadena + " AND TO_CHAR(FDFECHAPROCESO, 'yyyy-mm-dd-hh24.mi') >="+ desde2;
                        }
            if ((desde.isEmpty()) && (hasta2.contains("23.59"))){

                cadena = cadena+ " AND TO_CHAR(FDFECHAPROCESO, 'yyyy-mm-dd-hh24.mi') <="+ hasta2 ;
                            }

            if (!desde.isEmpty() && !hasta.isEmpty()){

                cadena = cadena+ " AND TO_CHAR(FDFECHAPROCESO, 'yyyy-mm-dd-hh24.mi') BETWEEN " + desde2 + " AND " + hasta2 ;
            }

            switch(fitipo){

                case 1:{
                    List <Taex_errormensajestienda> lista = taex_errormensajestiendaDao.filtrarCadenas(cadena);
                    lista3=lista;
                    break;
                }
                case 2:{
                    List <Taex_errormensajestienda> lista = taex_errormensajestiendaDao2.filtrarCadenas(cadena);
                    lista3=lista;
                    break;
                }
            }


            return lista3;

        }



    public Page<Taex_errormensajestienda> Paginado(int pag, int size,Number fnflujoid, int esquema){

        List<Taex_errormensajestienda> lista = filtrarCadenas( fnflujoid, esquema);

        PageRequest page_req2 = new PageRequest(pag, size, Sort.Direction.ASC, "fnpaisdest");
        int start = (int) page_req2.getOffset();
        int end = (start + page_req2.getPageSize()) > lista.size() ? lista.size() : (start + page_req2.getPageSize());

        Page<Taex_errormensajestienda> pages;
        pages = new PageImpl<Taex_errormensajestienda>(lista.subList(start, end), page_req2, lista.size());

        return pages;
    }



        public Page<Taex_errormensajestienda> PaginadoFiltrado(int pag, int size, Number fnflujoid, String fnfolioconsec, String fcdescerror, Number fnsucursaldest, String desde, String hasta, int esquema){

        List<Taex_errormensajestienda> lista = filtrarCadenas( fnflujoid,  fnfolioconsec,  fcdescerror,  fnsucursaldest,  desde,  hasta, esquema);

            PageRequest page_req2 = new PageRequest(pag, size, Sort.Direction.ASC, "fnpaisdest");
            int start = (int) page_req2.getOffset();
            int end = (start + page_req2.getPageSize()) > lista.size() ? lista.size() : (start + page_req2.getPageSize());

            Page<Taex_errormensajestienda> pages;
            pages = new PageImpl<Taex_errormensajestienda>(lista.subList(start, end), page_req2, lista.size());

            return pages;

        }


        public String EnviarHistorico(Integer fnflujoid, String [] flujosids){

        String mensaje = "Exito";

            Integer fitipo =  getTipoFlujo (fnflujoid);

            switch (fitipo) {

                case 1: {
                    for (int i = 0; i < flujosids.length; i++){

                            EnviarHistorico(fnflujoid,flujosids[i]);

                    }
                    break;
                }
                case 2: {
                    for (int i = 0; i < flujosids.length; i++){

                        EnviarHistorico2(fnflujoid,flujosids[i]);

                    }
                    break;
                }
            }


        return mensaje;
    }


    public String EnviarPendientes(Integer fnflujoid, String [] flujosids){

        String mensaje = "Exito";
        Integer fitipo =  getTipoFlujo (fnflujoid);

        switch (fitipo) {

            case 1: {
                for (int i = 0; i < flujosids.length; i++) {
                    EnviarPendientes(fnflujoid, flujosids[i]);
                }
                break;
            }
            case 2: {
                for (int i = 0; i < flujosids.length; i++) {
                    EnviarPendientes2(fnflujoid, flujosids[i]);
                }
                break;
            }
        }
        return mensaje;
    }

    public String getNamepc (){

        String hostname = "Unknown";

        try
        {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            hostname = addr.getHostName();
        }

        catch (UnknownHostException ex)
        {
            System.out.println("Hostname can not be resolved");
        }
        System.out.println("equipo"+hostname);
        return hostname;
    }


    public String getUser (){

        final String user = SecurityContextHolder.getContext().getAuthentication().getName();

        return user;
    }


    private Integer getTipoFlujo (Number fnflujoid){

        Integer fitipo = flujosDao.getfitipo(fnflujoid);

        return fitipo;
    }



}
