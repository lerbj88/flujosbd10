package com.flujos.flujosbd.dao.impl;


import com.flujos.flujosbd.dao.Taex_histmensajestiendaDao;
import com.flujos.flujosbd.dao.Taex_histmensajestiendaDao2;
import com.flujos.flujosbd.model.Taex_histmensajestienda;
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
    public class Taex_histmensajestiendaDaoImpl2 extends JdbcDaoSupport implements Taex_histmensajestiendaDao2 {


    @Autowired
    private DataSource dataSource2;
    private JdbcTemplate jdbcTemplate;

    public Taex_histmensajestiendaDaoImpl2(DataSource dataSource2) {
        this.setDataSource(dataSource2);
        this.jdbcTemplate = new JdbcTemplate(dataSource2);
    }
    private static final String CAT_NAME = "XTRACT";
    private static final String PROC_NAME = "SPINSHISTORY";




    public String InsertarHistorico2(Number fnflujoid, Number fnpaisdest, Number fncanaldest, Number fnsucursaldest, String fnfolioconsec, String fdfechaproceso, Number fnpaisorig, Number fncanalorig, Number fnsucursalorig, String fdfechainsercion, String fdfechaenviotda, Number fnstatus, Number fnnotransaccion, String fccadenaejec, Number fnnointentos ) {

        String mensjae = "Cadena insertada en historico exitosamente";


        /*SimpleJdbcCall jdbcCall = new
                SimpleJdbcCall(getDataSource())
                .withSchemaName(CAT_NAME)
                .withProcedureName(PROC_NAME);
*/
        SimpleJdbcCall caller =
                new SimpleJdbcCall(this.jdbcTemplate).withSchemaName(CAT_NAME).withProcedureName(PROC_NAME);


        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("pnflujoid", fnflujoid)
                .addValue("pnpaisdest", fnpaisdest)
                .addValue("pncanaldest", fncanaldest)
                .addValue("pnsucursaldest", fnsucursaldest)
                .addValue("pcfolioconsec", fnfolioconsec)
                .addValue("pdfechaproceso", fdfechaproceso)
                .addValue("pnpaisorig", fnpaisorig)
                .addValue("pncanalorig", fncanalorig)
                .addValue("pnsucursalorig", fnsucursalorig)
                .addValue("pdfechainsercion", fdfechainsercion)
                .addValue("pdfechaenviotda", fdfechaenviotda)
                .addValue("pnstatus", fnstatus)
                .addValue("pnnotransaccion", fnnotransaccion)
                .addValue("pccadenaejec", fccadenaejec)
                .addValue("pnnointentos", fnnointentos)
                ;
        //jdbcCall.execute(in);
        caller.execute(in);



        return mensjae;

    }



    public String InsertarHistorico (Number fnflujoid, Number fnpaisdest, Number fncanaldest, Number fnsucursaldest, String fnfolioconsec, Timestamp fdfechaproceso, Number fnpaisorig, Number fncanalorig, Number fnsucursalorig, Timestamp fdfechainsercion, Timestamp fdfechaenviotda, Number fnstatus, Number fnnotransaccion, String fccadenaejec, Number fnnointentos )
    {


        //jdbcTemplate.update("update usuarios set fcpassword = ? where fiusuario = ?",fcpassword, fiusuario);

        jdbcTemplate.update(
                "call XTRACT.SPINSHISTORY2(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                fnflujoid,fnpaisdest,fncanaldest,fnsucursaldest,  fnfolioconsec,  fdfechaproceso,  fnpaisorig,  fncanalorig,  fnsucursalorig,  fdfechainsercion,  fdfechaenviotda, fnstatus, fnnotransaccion, fccadenaejec, fnnointentos );

        String mensaje="Contrasena actualizada con exito";
        return mensaje;
    }


    public List<Taex_histmensajestienda> consultarCadenas(String cadena){

        String sql = cadena;

        List<Taex_histmensajestienda> lista = new ArrayList<Taex_histmensajestienda>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for (Map row : rows) {
            Taex_histmensajestienda taex_histmensajestienda = new Taex_histmensajestienda();
            taex_histmensajestienda.setFnflujoid((Number)(row.get("fnflujoid")));
            taex_histmensajestienda.setFnpaisdest((Number)(row.get("fnpaisdest")));
            taex_histmensajestienda.setFncanaldest((Number)(row.get("fncanaldest")));
            taex_histmensajestienda.setFnsucursaldest((Number)(row.get("fnsucursaldest")));
            taex_histmensajestienda.setFnfolioconsec((String)(row.get("fnfolioconsec")));
            //taex_histmensajestienda.setFnnointentos((Number) (row.get("fnnointentos")));
            taex_histmensajestienda.setFdfechaproceso((Timestamp) (row.get("fdfechaproceso")));
            taex_histmensajestienda.setFccadenaejec((String) (row.get("fccadenaejec")));
            taex_histmensajestienda.setFnnointentos((Number) (row.get("fnnointentos")));
            taex_histmensajestienda.setFnstatus((Number) (row.get("fnstatus")));
            lista.add(taex_histmensajestienda);
        }

       // logger.info(lista);
        return lista;

    }
}
