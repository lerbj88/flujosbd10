package com.flujos.flujosbd.dao.impl;

import com.flujos.flujosbd.dao.UsuarioDao;
import com.flujos.flujosbd.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UsuariosDaoImpl  extends JdbcDaoSupport implements UsuarioDao {

    @Autowired
    private DataSource dataSource1;
    private JdbcTemplate jdbcTemplate;

    public UsuariosDaoImpl(DataSource dataSource1) {
        this.setDataSource(dataSource1);
        this.jdbcTemplate = new JdbcTemplate(dataSource1);
    }

    public List<Usuario> findAll()  {

        String sql = "SELECT a.fiusuario, b.fcnombre FROM usuarios a inner join ResponsablesFlujos b on a.fiusuario = b.FiNumEmpleado ORDER BY A.FDFECHA DESC";

        List<Usuario> customers = new ArrayList<Usuario>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for (Map row : rows) {
            Usuario usuario = new Usuario();
            usuario.setFiusuario((Integer)(row.get("fiUSUARIO")));
            usuario.setFcnombre((String)row.get("fcnombre"));
            customers.add(usuario);
        }

        System.out.println(rows);
        return customers;
    }


    public void crearUsuario(Integer fiusuario, String fcpassword){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource1);
        jdbcTemplate.update("INSERT INTO usuarios(fiusuario,fcpassword)VALUES(?,?)",fiusuario,fcpassword);

    }

}
