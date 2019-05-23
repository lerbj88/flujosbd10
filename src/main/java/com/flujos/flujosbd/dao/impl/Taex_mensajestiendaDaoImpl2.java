package com.flujos.flujosbd.dao.impl;

import com.flujos.flujosbd.dao.Taex_mensajestiendaDao;
import com.flujos.flujosbd.dao.Taex_mensajestiendaDao2;
import com.flujos.flujosbd.model.Taex_mensajestienda;
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
public class Taex_mensajestiendaDaoImpl2 extends JdbcDaoSupport implements Taex_mensajestiendaDao2 {


    @Autowired
    private DataSource dataSource2;
    private JdbcTemplate jdbcTemplate;

    public Taex_mensajestiendaDaoImpl2(DataSource dataSource2) {
        this.setDataSource(dataSource2);
        this.jdbcTemplate = new JdbcTemplate(dataSource2);
    }


    static final String PROC_NAME = "SPINSMSG";
    private static final String CAT_NAME = "XTRACT";



    public List<Taex_mensajestienda> obtenerFlujos(String flujos)  {

    String flujoid = "fnflujoid";
    String counter = "total";

    String sql = "SELECT "+ flujoid +", count(*)  " + counter +" FROM XTRACT.TAEX_MENSAJESTIENDA " +
            "WHERE fnflujoid in ("+flujos+") group by fnflujoid";

    List<Taex_mensajestienda> lista = new ArrayList<Taex_mensajestienda>();

    List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for (Map row : rows) {
        Taex_mensajestienda taex_mensajestienda = new Taex_mensajestienda();
        taex_mensajestienda.setFnflujoid((Number)(row.get("fnflujoid")));
        taex_mensajestienda.setTotal((Number)row.get("total"));
        lista.add(taex_mensajestienda);
    }

      //  System.out.print("lista agrupada"+lista);


        return lista;
}





    public String InsertarPendientes2(Number fnflujoid, Number fnpaisdest, Number fncanaldest, Number fnsucursaldest, String fnfolioconsec, Number fnpaisorig, Number fncanalorig, Number fnsucursalorig, Timestamp fdfechainsercion, Timestamp fdfechaenviotda, Number fnstatusext, String fccadenaejec, Number fnnointentos ) {

        String mensjae = "Cadena insertada en pendientes exitosamente";
        SimpleJdbcCall jdbcCall = new
                SimpleJdbcCall(getDataSource())
                .withSchemaName(CAT_NAME)
                .withProcedureName(PROC_NAME);

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("pnflujoid", fnflujoid)
                .addValue("pnpaisdest", fnpaisdest)
                .addValue("pncanaldest", fncanaldest)
                .addValue("pnsucursaldest", fnsucursaldest)
                .addValue("pcfolioconsec", fnfolioconsec)
                .addValue("pnpaisorig", fnpaisorig)
                .addValue("pncanalorig", fncanalorig)
                .addValue("pnsucursalorig", fnsucursalorig)
                .addValue("pdfechainsercion", fdfechainsercion)
                .addValue("pdfechaenviotda", fdfechaenviotda)
                .addValue("pnstatusext", fnstatusext)
                .addValue("pccadenaejec", fccadenaejec)
                .addValue("pnnointentos", fnnointentos)
                ;
        jdbcCall.execute(in);
        //  Map<String, Object> out = jdbcCall.execute(in);
        //String outMessage2 = (String) out.get("P_RESULT");
        return mensjae;

    }



    public String InsertarPendientes(Number fnflujoid, Number fnpaisdest, Number fncanaldest, Number fnsucursaldest, String fnfolioconsec, Number fnpaisorig, Number fncanalorig, Number fnsucursalorig, Timestamp fdfechainsercion, Timestamp fdfechaenviotda, Number fnstatusext, String fccadenaejec, Number fnnointentos ) {

        String mensjae = "Cadena insertada en pendientes exitosamente";

        jdbcTemplate.update(
                "call XTRACT.SPINSMSG2(?,?,?,?,?,?,?,?,?,?,?,?,?)",
                fnflujoid,fnpaisdest,fncanaldest,fnsucursaldest,  fnfolioconsec,   fnpaisorig,  fncanalorig,  fnsucursalorig,  fdfechainsercion,  fdfechaenviotda, fnstatusext,  fccadenaejec, fnnointentos );


        return mensjae;

    }


}
