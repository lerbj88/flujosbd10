package com.flujos.flujosbd.dao.impl;

import com.flujos.flujosbd.dao.UsuariosFlujosDao;
import com.flujos.flujosbd.model.Usuario;
import com.flujos.flujosbd.model.UsuariosFlujos;
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
public class UsuariosFlujosDaoImpl extends JdbcDaoSupport implements UsuariosFlujosDao {

    @Autowired
    private DataSource dataSource1;
    private JdbcTemplate jdbcTemplate;

    public UsuariosFlujosDaoImpl(DataSource dataSource1) {
        this.setDataSource(dataSource1);
        this.jdbcTemplate = new JdbcTemplate(dataSource1);
    }



    public void crearFlujo(Integer fiusuario, Integer fiflujo){



            jdbcTemplate.update("INSERT INTO usuarios_flujos (FIusuario, fiflujo)VALUES(?,?)",fiusuario,fiflujo);

    }


    public List<UsuariosFlujos> findAll(Integer fiusaurio)  {

        String sql = "SELECT A.fiflujo, B.Descripcion FROM usuarios_flujos A INNER JOIN FLUJOS B ON A.fiflujo = B.Id_Flujo " +
                "WHERE A.fiusuario="+fiusaurio;

        List<UsuariosFlujos> lista = new ArrayList<UsuariosFlujos>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for (Map row : rows) {
            UsuariosFlujos usuariosFlujos = new UsuariosFlujos();
            usuariosFlujos.setFiflujo((Integer)(row.get("fiflujo")));
            usuariosFlujos.setDescripcion((String)row.get("descripcion"));
            lista.add(usuariosFlujos);
        }

      //  logger.info(lista);
        return lista;
    }


    public void eliminarFlujo(Integer fiflujo){
        jdbcTemplate.update("DELETE FROM usuarios_flujos WHERE  fiflujo="+fiflujo);
    }





    public List<UsuariosFlujos> obtenerFlujos(Integer fiusaurio)  {

        String sql = "SELECT fiflujo FROM usuarios_flujos " +
                "WHERE fiusuario="+fiusaurio;
        List<UsuariosFlujos> lista = new ArrayList<UsuariosFlujos>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for (Map row : rows) {
            UsuariosFlujos usuariosFlujos = new UsuariosFlujos();
            usuariosFlujos.setFiflujo((Integer)(row.get("fiflujo")));
            lista.add(usuariosFlujos);
        }
      //  logger.info(lista);
        return lista;
    }

}
