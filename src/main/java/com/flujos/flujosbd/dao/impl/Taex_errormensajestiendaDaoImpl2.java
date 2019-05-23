package com.flujos.flujosbd.dao.impl;

import com.flujos.flujosbd.dao.Taex_errormensajestiendaDao;
import com.flujos.flujosbd.dao.Taex_errormensajestiendaDao2;
import com.flujos.flujosbd.model.Taex_errormensajestienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
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
public class Taex_errormensajestiendaDaoImpl2 extends JdbcDaoSupport implements Taex_errormensajestiendaDao2 {


    @Autowired
    private DataSource dataSource2;
    private JdbcTemplate jdbcTemplate;

    public Taex_errormensajestiendaDaoImpl2(DataSource dataSource2) {
        this.setDataSource(dataSource2);
        this.jdbcTemplate = new JdbcTemplate(dataSource2);
    }



    static final String PROC_NAME = "SPDELERROR";
    private static final String CAT_NAME = "XTRACT";

    public List<Taex_errormensajestienda> obtenerFlujos(String flujos)  {

        String flujoid = "fnflujoid";
        String counter = "total";
        String descrip = "fcdescflujo";



        String sql2 = "SELECT B.FNFLUJOID, B.FCDESCFLUJO  as concat, count(*)"+ counter +
                "  FROM XTRACT.TAEX_ERRORMENSAJESTIENDA2 A LEFT JOIN XTRACT.TAEX_CATFLUJOS B ON A.FNFLUJOID = B.FNFLUJOID " +
         " WHERE A.FNFLUJOID>=0 AND A.FNPAISDEST>=0 AND A.FNCANALDEST>=0 AND A.fnstatus>=0 AND A.FNFLUJOID IN ("+flujos+") group by B.FNFLUJOID, B.FCDESCFLUJO ORDER BY B.FNFLUJOID" ;


        List<Taex_errormensajestienda> lista = new ArrayList<Taex_errormensajestienda>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql2);
        for (Map row : rows) {
            Taex_errormensajestienda taex_errormensajestienda = new Taex_errormensajestienda();
            taex_errormensajestienda.setFnflujoid((Number)row.get("fnflujoid"));
            taex_errormensajestienda.setConcat((String)(row.get("concat")));
            taex_errormensajestienda.setTotal((Number)row.get("total"));
            lista.add(taex_errormensajestienda);
        }


        return lista;
    }


    public List<Taex_errormensajestienda> obtenerSucursales(Integer fnflujoid)  {


        String counter = "total";

        String sql = "SELECT  FNSUCURSALDEST"+", count(*)  " + counter +" FROM XTRACT.TAEX_ERRORMENSAJESTIENDA2 " +
                "WHERE fnstatus=0 and fnflujoid in ("+fnflujoid+") group by FNSUCURSALDEST";

        List<Taex_errormensajestienda> lista = new ArrayList<Taex_errormensajestienda>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for (Map row : rows) {
            Taex_errormensajestienda taex_errormensajestienda = new Taex_errormensajestienda();
            taex_errormensajestienda.setFnsucursaldest((Number)(row.get("fnsucursaldest")));
            taex_errormensajestienda.setTotal((Number)row.get("total"));
            lista.add(taex_errormensajestienda);
        }

        return lista;
    }



    public List<Taex_errormensajestienda> obtenerCadenas(Integer fnflujoid, Integer fnsucursaldest)  {



        String sql = "SELECT FNPAISDEST, FNCANALDEST, FNSUCURSALDEST, "+
                "FNFOLIOCONSEC, FNNOINTENTOS, FDFECHAPROCESO, "+
                "FCCADENAEJEC, FCDESCERROR FROM XTRACT.TAEX_ERRORMENSAJESTIENDA2 "+
        "WHERE FNSTATUS=0 AND "+
                "FNFLUJOID  ="+fnflujoid +
        " AND FNPAISDEST >= 0 AND FNCANALDEST >= 0" +
                " AND FNSUCURSALDEST ="+ fnsucursaldest;

        List<Taex_errormensajestienda> lista = new ArrayList<Taex_errormensajestienda>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for (Map row : rows) {
            Taex_errormensajestienda taex_errormensajestienda = new Taex_errormensajestienda();
            taex_errormensajestienda.setFnpaisdest((Number)(row.get("fnpaisdest")));
            taex_errormensajestienda.setFncanaldest((Number)(row.get("fncanaldest")));
            taex_errormensajestienda.setFnsucursaldest((Number)(row.get("fnsucursaldest")));
            taex_errormensajestienda.setFnfolioconsec((String)(row.get("fnfolioconsec")));
            taex_errormensajestienda.setFnnointentos((Number) (row.get("fnnointentos")));
            taex_errormensajestienda.setFdfechaproceso((Timestamp) (row.get("fdfechaproceso")));
            taex_errormensajestienda.setFccadenaejec((String) (row.get("fccadenaejec")));
            taex_errormensajestienda.setFcdescerror((String) (row.get("fcdescerror")));
            lista.add(taex_errormensajestienda);
        }


        return lista;
    }



    public List<Taex_errormensajestienda> obtenerCadena(Integer fnflujoid, String fnfolioconsec)  {

        fnfolioconsec = "'"+fnfolioconsec+"'";

        String sql = "SELECT FNFLUJOID,FNPAISDEST,FNCANALDEST,FNSUCURSALDEST,FNFOLIOCONSEC,FDFECHAPROCESO,FNPAISORIG,FNCANALORIG," +
                "FNSUCURSALORIG,FDFECHAINSERCION,FDFECHAENVIOTDA,FCCADENAEJEC,FNNOINTENTOS,FCDESCERROR,FNSTATUS "+
                " FROM XTRACT.TAEX_ERRORMENSAJESTIENDA2 "+
                "WHERE FNSTATUS>=0 AND "+
                "FNFLUJOID  ="+fnflujoid +
                " AND FNFOLIOCONSEC  ="+fnfolioconsec +
                " AND FNPAISDEST >= 0 AND FNCANALDEST >= 0" +
                " AND FNSUCURSALDEST >=0";

        List<Taex_errormensajestienda> lista = new ArrayList<Taex_errormensajestienda>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for (Map row : rows) {
            Taex_errormensajestienda taex_errormensajestienda = new Taex_errormensajestienda();
            taex_errormensajestienda.setFnflujoid((Number)(row.get("fnflujoid")));
            taex_errormensajestienda.setFnpaisdest((Number)(row.get("fnpaisdest")));
            taex_errormensajestienda.setFncanaldest((Number)(row.get("fncanaldest")));
            taex_errormensajestienda.setFnsucursaldest((Number)(row.get("fnsucursaldest")));
            taex_errormensajestienda.setFnfolioconsec((String)(row.get("fnfolioconsec")));
            taex_errormensajestienda.setFdfechaproceso((Timestamp) (row.get("fdfechaproceso")));
            taex_errormensajestienda.setFnpaisorig((Number) (row.get("fnpaisorig")));
            taex_errormensajestienda.setFncanalorig((Number) (row.get("fncanalorig")));
            taex_errormensajestienda.setFncanalorig((Number) (row.get("fncanalorig")));
            taex_errormensajestienda.setFnsucursalorig((Number) (row.get("fnsucursalorig")));
            taex_errormensajestienda.setFdfechainsercion((Timestamp) (row.get("fdfechainsercion")));
            taex_errormensajestienda.setFdfechaenviotda((Timestamp) (row.get("fdfechaenviotda")));
            taex_errormensajestienda.setFccadenaejec((String) (row.get("fccadenaejec")));
            taex_errormensajestienda.setFnnointentos((Number) (row.get("fnnointentos")));
            taex_errormensajestienda.setFcdescerror((String) (row.get("fcdescerror")));
            taex_errormensajestienda.setFnstatus((Number) (row.get("fnstatus")));
            lista.add(taex_errormensajestienda);
        }


        return lista;
    }





    public String eliminarCadena(Number fnflujoid, Number fnpaisdest, Number fncanaldest, Number fnsucursaldest, String fcfolioconsec) {
        String mensaje = "";

        jdbcTemplate.update(
                "call XTRACT.SPDELERROR2(?,?,?,?,?)",
                fnflujoid,fnpaisdest,fncanaldest,fnsucursaldest,  fcfolioconsec);


        return mensaje;
    }



    public String obtenerSucursal (Number fnflujoid, String fnfolioconsec)
    {

        String sql2 = "SELECT fnsucursaldest FROM XTRACT.TAEX_ERRORMENSAJESTIENDA2 WHERE FNFLUJOID = ? AND FNFOLIOCONSEC=? " +
                " AND FNPAISDEST >= 0 AND FNCANALDEST >= 0 AND\n" +
                "        FNSUCURSALDEST >=0" +
                "";

        String sucursal = (String)getJdbcTemplate().queryForObject(
                sql2, new Object[] {fnflujoid, fnfolioconsec}, String.class);

//logger.info(sucursal);

    return sucursal;

    }



    public List<Taex_errormensajestienda> filtrarCadenas(String  cadena)  {



        String sql = cadena;

        List<Taex_errormensajestienda> lista = new ArrayList<Taex_errormensajestienda>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for (Map row : rows) {
            Taex_errormensajestienda taex_errormensajestienda = new Taex_errormensajestienda();
            taex_errormensajestienda.setFnpaisdest((Number)(row.get("fnpaisdest")));
            taex_errormensajestienda.setFncanaldest((Number)(row.get("fncanaldest")));
            taex_errormensajestienda.setFnsucursaldest((Number)(row.get("fnsucursaldest")));
            taex_errormensajestienda.setFnfolioconsec((String)(row.get("fnfolioconsec")));
            taex_errormensajestienda.setFnnointentos((Number) (row.get("fnnointentos")));
            taex_errormensajestienda.setFdfechaproceso((Timestamp) (row.get("fdfechaproceso")));
            taex_errormensajestienda.setFccadenaejec((String) (row.get("fccadenaejec")));
            taex_errormensajestienda.setFcdescerror((String) (row.get("fcdescerror")));
            lista.add(taex_errormensajestienda);
        }

        return lista;
    }


}
