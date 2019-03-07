package com.flujos.flujosbd.dao.impl;

import com.flujos.flujosbd.dao.UsuarioDao;
import com.flujos.flujosbd.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public void crearUsuario(Integer fiusuario, String fcpassword, Integer idrol){

        String sql = "SELECT count(1) FROM usuarios WHERE fiusuario = ?";

        String exist = (String)getJdbcTemplate().queryForObject(
                sql, new Object[] { fiusuario }, String.class);

        Integer exist2 = Integer.parseInt(exist);
        if (exist2 == 1)
        {
            jdbcTemplate.update("update usuarios set fiusuario = ? , fcpassword = ? where fiusuario = ?",fiusuario,fcpassword, fiusuario);

            String sql2 = "SELECT fiid FROM usuarios WHERE fiusuario = ?";

            String id = (String)getJdbcTemplate().queryForObject(
                    sql2, new Object[] { fiusuario }, String.class);

            Integer iduser = Integer.parseInt(id);
            Integer idrole = idrol;

            jdbcTemplate.update("update usuarios_roles set fiidrole = ? where fiidusuario = ?",idrole, iduser);
        }
        else
        {
            String password = fcpassword;
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String pwd = passwordEncoder.encode(password);
            jdbcTemplate.update("INSERT INTO usuarios(fiusuario,fcpassword)VALUES(?,?)",fiusuario,pwd);

            String sql2 = "SELECT fiid FROM usuarios WHERE fiusuario = ?";

            String id = (String)getJdbcTemplate().queryForObject(
                    sql2, new Object[] { fiusuario }, String.class);

            Integer iduser = Integer.parseInt(id);
            Integer idrole = idrol;

            jdbcTemplate.update("INSERT INTO usuarios_roles(fiidusuario,fiidrole)VALUES(?,?)",iduser,idrole);
        }

    }


    public Usuario findByUsuario(Integer fiusuario){

        String sql = "SELECT a.fiusuario, b.fcnombre FROM usuarios a inner join ResponsablesFlujos b on a.fiusuario = b.FiNumEmpleado where a.fiusuario=? ";


        Usuario  usuario = (Usuario) getJdbcTemplate().queryForObject(
                sql, new Object[] { fiusuario },
                new BeanPropertyRowMapper(Usuario.class));

        System.out.print("usuario encontrado"+usuario);
        return usuario;
    }

    public Usuario editarUsuario(Integer fiusuario){

        String sql = "SELECT A.fiusuario, A.fcpassword, B.fiidROLE  FROM usuarios A INNER JOIN usuarios_roles B ON A.fiid = B.fiidusuario WHERE A.fiUSUARIO=?";


        Usuario  usuario = (Usuario) getJdbcTemplate().queryForObject(
                sql, new Object[] { fiusuario },
                new BeanPropertyRowMapper(Usuario.class));

        System.out.print("usuario encontrado"+usuario);
        return usuario;
    }


    public void eliminarUsuario(Integer fiusuario){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource1);
        jdbcTemplate.update("DELETE FROM usuarios WHERE  fiusuario="+fiusuario);
    }



    public void encoder (){
        String password = "123";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("uno"+passwordEncoder.encode(password));
    }

}
