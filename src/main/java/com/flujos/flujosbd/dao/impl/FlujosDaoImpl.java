package com.flujos.flujosbd.dao.impl;

import com.flujos.flujosbd.dao.FlujosDao;
import com.flujos.flujosbd.model.Flujos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



@Repository
@Transactional
public class FlujosDaoImpl extends JdbcDaoSupport implements FlujosDao {

    @Autowired
    private DataSource dataSource3;
    private JdbcTemplate jdbcTemplate;

    public FlujosDaoImpl(DataSource dataSource3) {
        this.setDataSource(dataSource3);
        this.jdbcTemplate = new JdbcTemplate(dataSource3);
    }

    public List<Flujos> findAll()  {

        String sql = "SELECT  id_flujo, descripcion FROM Flujos  ";

        List<Flujos> lista = new ArrayList<Flujos>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for (Map row : rows) {
            Flujos flujos = new Flujos();
            flujos.setId_flujo((Short)(row.get("id_flujo")));
            flujos.setDescripcion((String)row.get("descripcion"));
            lista.add(flujos);
        }

        return lista;
    }


    public Flujos findById_flujo(Short id_flujo){

        //String nombre ="'"+"%"+fcnombre+"%"+"'";

        String sql = "SELECT id_flujo, descripcion FROM Flujos where Id_Flujo = ? ";


        Flujos  flujos = (Flujos) getJdbcTemplate().queryForObject(
                sql, new Object[] {id_flujo},
                new BeanPropertyRowMapper(Flujos.class));

        return flujos;
    }

    public void crearFlujo(Short id_flujo, String descripcion){

        String sql = "SELECT count(1) FROM Flujos WHERE id_flujo = ?";

        String exist = (String)getJdbcTemplate().queryForObject(
                sql, new Object[] { id_flujo }, String.class);

        Integer exist2 = Integer.parseInt(exist);

        if (exist2 == 1)
        {
            jdbcTemplate.update("update Flujos set id_flujo = ? , descripcion = ? where Id_Flujo = ?",id_flujo, descripcion,id_flujo);

        }
        else
        {
            jdbcTemplate.update("INSERT INTO FLUJOS (id_flujo, descripcion)VALUES(?,?)",id_flujo,descripcion);

        }

    }

    public Flujos  obtenerFlujo(Short id_flujo){

        String sql = "SELECT id_flujo, descripcion  FROM Flujos WHERE Id_Flujo=?";


        Flujos  flujos = (Flujos) getJdbcTemplate().queryForObject(
                sql, new Object[] { id_flujo },
                new BeanPropertyRowMapper(Flujos.class));

     //   System.out.print(" encontrado"+flujos);
        return flujos;
    }

    public void eliminarFlujo(Short id_flujo){
        jdbcTemplate.update("DELETE FROM flujos WHERE  id_flujo="+id_flujo);

    }


    public List<Flujos> obtenerFlujosConcatenados( String flujoslist)  {

        String sql = "SELECT  id_flujo, CONCAT (id_flujo,' - ', descripcion) descripcion FROM Flujos where Id_Flujo not in ("+flujoslist+")";

        List<Flujos> lista = new ArrayList<Flujos>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for (Map row : rows) {
            Flujos flujos = new Flujos();
            flujos.setId_flujo((Short)(row.get("id_flujo")));
            flujos.setDescripcion((String)row.get("descripcion"));
            lista.add(flujos);
        }

       // System.out.println(rows);
        return lista;
    }

    public List<Flujos> obtenerFlujosConcatenados2( String flujoslist)  {

        String sql = "SELECT  id_flujo, CONCAT (id_flujo,' - ', descripcion) descripcion FROM Flujos where Id_Flujo in ("+flujoslist+")";

        List<Flujos> lista = new ArrayList<Flujos>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for (Map row : rows) {
            Flujos flujos = new Flujos();
            flujos.setId_flujo((Short)(row.get("id_flujo")));
            flujos.setDescripcion((String)row.get("descripcion"));
            lista.add(flujos);
        }

        // System.out.println(rows);
        return lista;
    }

    public List<Flujos> obtenerFlujosConcatenados3( String flujoslist)  {

        String sql = "SELECT  id_flujo,  descripcion FROM Flujos where Id_Flujo in ("+flujoslist+")";

        List<Flujos> lista = new ArrayList<Flujos>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for (Map row : rows) {
            Flujos flujos = new Flujos();
            flujos.setId_flujo((Short)(row.get("id_flujo")));
            flujos.setDescripcion((String)row.get("descripcion"));
            lista.add(flujos);
        }

        // System.out.println(rows);
        return lista;
    }

    public List<Flujos> obtenerFlujosxUsuario( Integer fiusuario)  {

        String sql = "SELECT  a.id_flujo, CONCAT (a.id_flujo,' - ', a.descripcion) descripcion FROM Flujos A " +
                "LEFT JOIN usuarios_flujos B ON A.Id_Flujo = B.fiflujo WHERE B.fiusuario="+fiusuario;

        List<Flujos> lista = new ArrayList<Flujos>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for (Map row : rows) {
            Flujos flujos = new Flujos();
            flujos.setId_flujo((Short)(row.get("id_flujo")));
            flujos.setDescripcion((String)row.get("descripcion"));
            lista.add(flujos);
        }

       // System.out.println(rows);
        return lista;
    }

    public String obtenerDescipcion (Integer id_flujo){

        String sql = "SELECT descripcion FROM flujos WHERE Id_Flujo = ?";

        String descripcion = (String)getJdbcTemplate().queryForObject(
                sql, new Object[] { id_flujo }, String.class);

     return descripcion;
    }

    public List<Flujos> consultarFlujos(String text){

        String txt = "'%"+text+"%'";

        String sql = "SELECT ID_FLUJO, DESCRIPCION FROM FLUJOS WHERE ID_FLUJO LIKE"+txt+ " or descripcion LIKE "+txt ;

        List<Flujos> lista = new ArrayList<Flujos>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for (Map row : rows) {
            Flujos flujos = new Flujos();
            flujos.setId_flujo((Short)(row.get("id_flujo")));
            flujos.setDescripcion((String)(row.get("descripcion")));
            lista.add(flujos);
        }

        return lista;

    }


    public Integer getfitipo (Number fnflujoid){

        String sql = "SELECT fitipo FROM flujos WHERE Id_Flujo = ?";

        Integer fitipo = (Integer)getJdbcTemplate().queryForObject(
                sql, new Object[] { fnflujoid }, Integer.class);

        return fitipo;
    };

}
