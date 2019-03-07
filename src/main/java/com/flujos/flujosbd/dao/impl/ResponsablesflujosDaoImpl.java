package com.flujos.flujosbd.dao.impl;

import com.flujos.flujosbd.dao.ResponsablesflujosDao;
import com.flujos.flujosbd.model.Responsablesflujos;
import com.flujos.flujosbd.model.Usuario;
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
public class ResponsablesflujosDaoImpl extends JdbcDaoSupport implements ResponsablesflujosDao {

    @Autowired
    private DataSource dataSource1;
    private JdbcTemplate jdbcTemplate;

    public ResponsablesflujosDaoImpl(DataSource dataSource1) {
        this.setDataSource(dataSource1);
        this.jdbcTemplate = new JdbcTemplate(dataSource1);
    }

    public List<Responsablesflujos> findAll()  {

        String sql = "SELECT  TOP 10 finumempleado, fcnombre, FcCorreo FROM ResponsablesFlujos order by FiNumEmpleado desc ";

        List<Responsablesflujos> lista = new ArrayList<Responsablesflujos>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for (Map row : rows) {
            Responsablesflujos responsablesflujos = new Responsablesflujos();
            responsablesflujos.setFinumempleado((Integer)(row.get("finumempleado")));
            responsablesflujos.setFcnombre((String)row.get("fcnombre"));
            responsablesflujos.setFccorreo((String)row.get("fccorreo"));
            lista.add(responsablesflujos);
        }

        System.out.println(rows);
        return lista;
    }

    public Responsablesflujos findByResponsableflujo(String fcnombre){

        //String nombre ="'"+"%"+fcnombre+"%"+"'";

        String sql = "SELECT finumempleado, fcnombre, Fccorreo FROM ResponsablesFlujos where FiNumEmpleado = ? ";


        Responsablesflujos  responsablesflujos = (Responsablesflujos) getJdbcTemplate().queryForObject(
                sql, new Object[] {fcnombre},
                new BeanPropertyRowMapper(Responsablesflujos.class));

        System.out.print("usuario encontrado"+responsablesflujos);
        return responsablesflujos;
    }

    public void crearResponsable(Integer finumempleado, String fcnombre,  String fccorreo, Integer ficelular){

        String sql = "SELECT count(1) FROM ResponsablesFlujos WHERE FiNumEmpleado = ?";

        String exist = (String)getJdbcTemplate().queryForObject(
                sql, new Object[] { finumempleado }, String.class);

        Integer exist2 = Integer.parseInt(exist);
        if (exist2 == 1)
        {
            jdbcTemplate.update("update ResponsablesFlujos set FiNumEmpleado = ? , fcnombre = ? , fccorreo = ? , ficelular = ? where FiNumEmpleado = ?",finumempleado,fcnombre,fccorreo,ficelular,finumempleado);

        }
        else
        {
            jdbcTemplate.update("INSERT INTO RESPONSABLESFLUJOS (FINUMEMPLEADO,FCNOMBRE, FCCORREO, FICELULAR)VALUES(?,?,?,?)",finumempleado,fcnombre,fccorreo,ficelular);

        }

    }

    public Responsablesflujos  editarResponsable(Integer finumempleado){

        String sql = "SELECT FINUMEMPLEADO, FCNOMBRE, FCCORREO, FICELULAR  FROM ResponsablesFlujos WHERE FiNumEmpleado=?";


        Responsablesflujos  responsablesflujos = (Responsablesflujos) getJdbcTemplate().queryForObject(
                sql, new Object[] { finumempleado },
                new BeanPropertyRowMapper(Responsablesflujos.class));

        System.out.print("usuario encontrado"+responsablesflujos);
        return responsablesflujos;
    }

    public void eliminarResponsable(Integer finumempleado){
        jdbcTemplate.update("DELETE FROM responsablesflujos WHERE  finumempleado="+finumempleado);
    }

}
