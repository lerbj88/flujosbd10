package com.flujos.flujosbd.services.Impl;

import com.flujos.flujosbd.dao.FlujosDao;
import com.flujos.flujosbd.dao.Taex_histmensajestiendaDao;
import com.flujos.flujosbd.dao.Taex_histmensajestiendaDao2;
import com.flujos.flujosbd.model.Taex_errormensajestienda;
import com.flujos.flujosbd.model.Taex_histmensajestienda;
import com.flujos.flujosbd.services.ConsultarHistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Repository
@Transactional
public class ConsultarHistoricoServiceImpl implements ConsultarHistoricoService {

    @Autowired
    private Taex_histmensajestiendaDao taex_histmensajestiendaDao;

    @Autowired
    private FlujosDao flujosDao;

    @Autowired
    private Taex_histmensajestiendaDao2 taex_histmensajestiendaDao2;

    public List<Taex_histmensajestienda> ConsultarHistorico(Number fnflujoid, Integer fnsucursaldest, String Desde , String Hasta)
    {


        Integer fitipo =  getTipoFlujo (fnflujoid);
        List <Taex_histmensajestienda> lista3 = null;
        String tabla1="XTRACT.TAEX_HISTMENSAJESTIENDA";
        String tabla2="XTRACT.TAEX_HISTMENSAJESTIENDA2";
        String tabla="";


        String desde2="";
        String hasta2 = "";

        if (!Desde.isEmpty()) {
            desde2 = "'" + Desde + "-00.00" + "'";
        }

        if (!Hasta.isEmpty()) {
            hasta2 = "'" + Hasta + "-23.59" + "'";
        }


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


        String cadena = "SELECT FNFLUJOID,FNPAISDEST, FNCANALDEST, FNSUCURSALDEST, "+
                "FNFOLIOCONSEC,  FDFECHAPROCESO, " +
                "FCCADENAEJEC, FNNOINTENTOS , FNSTATUS  FROM "+ tabla +
                " WHERE "+
                "FNFLUJOID  ="+fnflujoid +
                " AND FNPAISDEST > 0" +
                " AND FNCANALDEST > 0" ;


        if (fnsucursaldest!=null){

            cadena = cadena + " and fnsucursaldest = " + fnsucursaldest;
        }
        if (fnsucursaldest==null){

            cadena = cadena + " and fnsucursaldest > 0";
        }
        if (desde2.contains("00.00")&& (Hasta.isEmpty())) {

            cadena = cadena + " AND TO_CHAR(FDFECHAPROCESO, 'yyyy-mm-dd-hh24.mi') >="+ desde2;
        }
        if ((Desde.isEmpty()) && (hasta2.contains("23.59"))){

            cadena = cadena+ " AND TO_CHAR(FDFECHAPROCESO, 'yyyy-mm-dd-hh24.mi') <="+ hasta2 ;
        }

        if (!Desde.isEmpty() && !Hasta.isEmpty()){

            cadena = cadena+ " AND TO_CHAR(FDFECHAPROCESO, 'yyyy-mm-dd-hh24.mi') BETWEEN " + desde2 + " AND " + hasta2 ;
        }



        switch(fitipo){

            case 1: {
                List<Taex_histmensajestienda> lista = taex_histmensajestiendaDao.consultarCadenas(cadena);
                lista3=lista;
                break;
            }
            case 2:{

                List<Taex_histmensajestienda> lista = taex_histmensajestiendaDao2.consultarCadenas(cadena);
                lista3=lista;
                break;
            }
        }

        return lista3;

    }



    public Page<Taex_histmensajestienda> Paginado(int pag , int size, Number fnflujoid, Integer fnsucursaldest, String Desde , String Hasta){

        List<Taex_histmensajestienda> lista = ConsultarHistorico( fnflujoid,  fnsucursaldest,  Desde ,  Hasta);



        PageRequest page_req2 = new PageRequest(pag, size, Sort.Direction.ASC, "fnflujoid");
        int start = (int) page_req2.getOffset();
        int end = (start + page_req2.getPageSize()) > lista.size() ? lista.size() : (start + page_req2.getPageSize());

        Page<Taex_histmensajestienda> pages;
        pages = new PageImpl<Taex_histmensajestienda>(lista.subList(start, end), page_req2, lista.size());

        return pages;
    }

    private Integer getTipoFlujo (Number fnflujoid){

        Integer fitipo = flujosDao.getfitipo(fnflujoid);

        return fitipo;
    }

}
