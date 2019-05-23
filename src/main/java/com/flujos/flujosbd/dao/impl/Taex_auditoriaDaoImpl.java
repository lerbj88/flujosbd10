package com.flujos.flujosbd.dao.impl;

import com.flujos.flujosbd.dao.Taex_auditoriaDao;
import com.flujos.flujosbd.model.Taex_auditoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class Taex_auditoriaDaoImpl extends JdbcDaoSupport implements Taex_auditoriaDao {


    @Autowired
    private DataSource dataSource1;
    private JdbcTemplate jdbcTemplate;

    public Taex_auditoriaDaoImpl(DataSource dataSource1) {
        this.setDataSource(dataSource1);
        this.jdbcTemplate = new JdbcTemplate(dataSource1);
    }




    public void crearAuditoria(Number fnflujoid, Number fnpaisdest, Number fncanaldest, Number fnsucursaldest, String fcfolioconsec, Timestamp fdfechaproceso, Number fnnointentos, Number fnstatus, String fccadenaejec, String fcdescerror, String fcproceso, String fcusuario, String fcworkstation, Timestamp fdfecha){

       jdbcTemplate.update(
               "INSERT INTO TAEX_AUDITORIA(FNFLUJOID, FNPAISDEST,FNCANALDEST, FNSUCURSALDEST, FCFOLIOCONSEC, FDFECHAPROCESO, FNNOINTENTOS, FNSTATUS, FCCADENAEJEC, FCDESCERROR, FCPROCESO, FCUSUARIO, FCWORKSTATION)VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",
               fnflujoid,fnpaisdest,fncanaldest,fnsucursaldest,  fcfolioconsec,  fdfechaproceso, fnnointentos, fnstatus,  fccadenaejec, fcdescerror, fcproceso, fcusuario, fcworkstation);


   }

    public List<Taex_auditoria> findAll(){

        List <Taex_auditoria> lista = null;

        return lista;
    }


    public List<Taex_auditoria> consultarCadenas(String cadena){



        String sql = cadena;


        List<Taex_auditoria> lista = new ArrayList<Taex_auditoria>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for (Map row : rows) {
            Taex_auditoria taex_auditoria = new Taex_auditoria();
            taex_auditoria.setFnflujoid((Integer)(row.get("fnflujoid")));
            taex_auditoria.setFnpaisdest((Integer)(row.get("fnpaisdest")));
            taex_auditoria.setFncanaldest((Integer)(row.get("fncanaldest")));
            taex_auditoria.setFnsucursaldest((Integer)(row.get("fnsucursaldest")));
            taex_auditoria.setFcfolioconsec((String)(row.get("fcfolioconsec")));
            taex_auditoria.setFdfechaproceso((Timestamp) (row.get("fdfechaproceso")));
            taex_auditoria.setFnnointentos((Integer) (row.get("fnnointentos")));
            taex_auditoria.setFnstatus((Integer) (row.get("fnstatus")));
            taex_auditoria.setFccadenaejec((String) (row.get("fccadenaejec")));
            taex_auditoria.setFcdescerror((String) (row.get("fcdescerror")));
            taex_auditoria.setFcproceso((String) (row.get("fcproceso")));
            taex_auditoria.setFcusuario((String) (row.get("fcusuario")));
            taex_auditoria.setFcworkstation((String) (row.get("fcworkstation")));
            taex_auditoria.setFdfecha((Timestamp) (row.get("fdfecha")));
            lista.add(taex_auditoria);
        }



        return lista;

    }

}
