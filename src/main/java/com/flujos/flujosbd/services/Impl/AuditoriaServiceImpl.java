package com.flujos.flujosbd.services.Impl;

import com.flujos.flujosbd.dao.Taex_auditoriaDao;
import com.flujos.flujosbd.model.Taex_auditoria;
import com.flujos.flujosbd.services.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AuditoriaServiceImpl implements AuditoriaService {

    @Autowired
    private Taex_auditoriaDao taex_auditoriaDao;

    public List<Taex_auditoria> ConsultarAuditoria(Number fnflujoid, Integer fnsucursaldest, String Desde , String Hasta)
    {

        String desde2="";
        String hasta2 = "";

        if (!Desde.isEmpty()) {
            desde2 = "'" + Desde + " 00:00" + "'";
        }

        if (!Hasta.isEmpty()) {
            hasta2 = "'" + Hasta + " 23:59" + "'";
        }


        String cadena = "SELECT FNFLUJOID,FNPAISDEST, FNCANALDEST, FNSUCURSALDEST, "+
                "FCFOLIOCONSEC,  FDFECHAPROCESO, FNNOINTENTOS, FNSTATUS, " +
                "FCCADENAEJEC, FCDESCERROR, FCPROCESO , FCUSUARIO, FCWORKSTATION, " +
                "FDFECHA  FROM TAEX_AUDITORIA "+
                "WHERE "+
                "FNFLUJOID  ="+fnflujoid +
                " AND FNPAISDEST > 0" +
                " AND FNCANALDEST > 0" ;


        if (fnsucursaldest!=null){

            cadena = cadena + " and fnsucursaldest = " + fnsucursaldest;
        }
        if (fnsucursaldest==null){

            cadena = cadena + " and fnsucursaldest > 0";
        }
        if (desde2.contains("00:00")&& (Hasta.isEmpty())) {

            cadena = cadena + " AND FDFECHA>="+ desde2;
        }
        if ((Desde.isEmpty()) && (hasta2.contains("23:59"))){

            cadena = cadena+ " AND FDFECHA <="+ hasta2 ;
        }

        if (!Desde.isEmpty() && !Hasta.isEmpty()){

            cadena = cadena+ " AND FDFECHA BETWEEN " + desde2 + " AND " + hasta2 ;
        }



        List <Taex_auditoria> lista = taex_auditoriaDao.consultarCadenas(cadena);



        return lista;

    }



    public Page<Taex_auditoria> Paginado(int pag , int size, Number fnflujoid, Integer fnsucursaldest, String Desde , String Hasta){

        List<Taex_auditoria> lista = ConsultarAuditoria( fnflujoid,  fnsucursaldest,  Desde ,  Hasta);

        PageRequest page_req2 = new PageRequest(pag, size, Sort.Direction.ASC, "fnflujoid");
        int start = (int) page_req2.getOffset();
        int end = (start + page_req2.getPageSize()) > lista.size() ? lista.size() : (start + page_req2.getPageSize());

        Page<Taex_auditoria> pages;
        pages = new PageImpl<Taex_auditoria>(lista.subList(start, end), page_req2, lista.size());

        return pages;
    }




}
