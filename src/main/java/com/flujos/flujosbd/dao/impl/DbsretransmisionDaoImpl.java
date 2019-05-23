package com.flujos.flujosbd.dao.impl;

import com.flujos.flujosbd.dao.DbsretransmisionDao;
import com.flujos.flujosbd.model.Dbsretransmision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
@Transactional
public class DbsretransmisionDaoImpl extends JdbcDaoSupport implements DbsretransmisionDao {

    @Autowired
    private DataSource dataSource1;
    private JdbcTemplate jdbcTemplate;

    public DbsretransmisionDaoImpl(DataSource dataSource1) {
        this.setDataSource(dataSource1);
        this.jdbcTemplate = new JdbcTemplate(dataSource1);
    }


    public List<Dbsretransmision> findAll()  {

        String sql = "SELECT  fiid, fitipo, fcdescripcion, fcdatabase, fcservicename, fcip, fcusuario FROM dbsretransmision order by  fitipo asc";

        List<Dbsretransmision> lista = new ArrayList<Dbsretransmision>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for (Map row : rows) {
            Dbsretransmision dbsretransmision = new Dbsretransmision();
            dbsretransmision.setFiid((Short)(row.get("fiid")));
            dbsretransmision.setFitipo((Short)row.get("fitipo"));
            dbsretransmision.setFcdescripcion((String)row.get("fcdescripcion"));
            dbsretransmision.setFcdatabase((String)row.get("fcdatabase"));
            dbsretransmision.setFcservicename((String)row.get("fcservicename"));
            dbsretransmision.setFcip((String)row.get("fcip"));
            dbsretransmision.setFcusuario((String)row.get("fcusuario"));
            lista.add(dbsretransmision);
        }
        return lista;
    }


    public void crearDb(Short fitipo, String fcdescripcion, String fcdatabase, String fcservicename, String ip, String fcusuario, String fcpassword){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pwd = passwordEncoder.encode(fcpassword);

        jdbcTemplate.update("INSERT INTO DBSRETRANSMISION (FITIPO, FCDESCRIPCION, FCDATABASE, FCSERVICENAME, FCIP, FCUSUARIO, FCPASSWORD) values (? , ?, ?, ? , ?, ?, ?) ", fitipo,  fcdescripcion,  fcdatabase,  fcservicename,  ip, fcusuario,  pwd);

    }

    public Dbsretransmision getDb(Short fiid){

        String sql = "SELECT fiid, fitipo, fcdescripcion, fcdatabase, fcservicename, fcip, fcusuario, fcpassword FROM dbsretransmision where fiid = ? ";
        Dbsretransmision  dbsretransmision = (Dbsretransmision) getJdbcTemplate().queryForObject(
                sql, new Object[] {fiid},
                new BeanPropertyRowMapper(Dbsretransmision.class));

        return dbsretransmision;

    }

    public void actualizarDb(Short fiid, Short fitipo, String fcdescripcion, String fcdatabase, String fcservicename, String ip, String fcusuario){



        jdbcTemplate.update("update dbsretransmision set fitipo = ?, fcdescripcion = ?, fcdatabase = ? , fcservicename = ? , fcip = ? , fcusuario = ?  where fiid = ?",fitipo, fcdescripcion, fcdatabase,  fcservicename, ip, fcusuario,  fiid);
    }



    public String obtenerContrasena (Short fiid)

    {
        String sql2 = "SELECT fcpassword FROM dbsretransmision WHERE fiid = ?";
        String pwd = (String) getJdbcTemplate().queryForObject(
                sql2, new Object[]{fiid}, String.class);
        return pwd;
    }



    public String actualizarContrasena (Short fiid, String fcpassword)
    {
        jdbcTemplate.update("update dbsretransmision set fcpassword = ? where fiid = ?",fcpassword, fiid);
        String mensaje="Contrasena actualizada con exito";
        return mensaje;
    }


    public void eliminarDb(Short fiid){
        jdbcTemplate.update("DELETE FROM dbsretransmision WHERE  fiid="+fiid);

    }



    public List<Dbsretransmision> getDbsIseries()  {

        String sql = "SELECT  fiid, fcdescripcion FROM dbsretransmision where fitipo = 1";

        List<Dbsretransmision> lista = new ArrayList<Dbsretransmision>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for (Map row : rows) {
            Dbsretransmision dbsretransmision = new Dbsretransmision();
            dbsretransmision.setFiid((Short)(row.get("fiid")));
            dbsretransmision.setFcdescripcion((String)row.get("fcdescripcion"));
            lista.add(dbsretransmision);
        }
        return lista;
    }


    public List<Dbsretransmision> getDbsOracle()  {

        String sql = "SELECT  fiid, fcdescripcion FROM dbsretransmision where fitipo = 2";

        List<Dbsretransmision> lista = new ArrayList<Dbsretransmision>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for (Map row : rows) {
            Dbsretransmision dbsretransmision = new Dbsretransmision();
            dbsretransmision.setFiid((Short)(row.get("fiid")));
            dbsretransmision.setFcdescripcion((String)row.get("fcdescripcion"));
            lista.add(dbsretransmision);
        }
        return lista;
    }


}
