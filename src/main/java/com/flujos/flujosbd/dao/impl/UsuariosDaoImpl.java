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
import java.util.*;

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

        String sql = "SELECT a.fiusuario, a.fcnombre, a.fccorreo, a.fctelefono, d.fcrol FROM usuarios a " +
                "inner join USUARIOS_ROLES C ON A.fiid = C.fiidusuario " +
                "INNER JOIN ROLES D ON C.fiidrole = D.fiid order by a.fiusuario asc";


        List<Usuario> customers = new ArrayList<Usuario>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for (Map row : rows) {
            Usuario usuario = new Usuario();
            usuario.setFiusuario((Integer)(row.get("fiUSUARIO")));
            usuario.setFcnombre((String)row.get("fcnombre"));
            usuario.setFccorreo((String)row.get("fccorreo"));
            usuario.setFctelefono((String)row.get("fctelefono"));
            usuario.setFcrol((String)row.get("fcrol"));
            customers.add(usuario);
        }


        return customers;
    }

    public void crearUsuario(Integer fiusuario, String fcnombre, String fccorreo, String fctelefono,  Integer idrol) {

        String sql = "SELECT count(1) FROM usuarios WHERE fiusuario = ?";

        String exist = (String) getJdbcTemplate().queryForObject(
                sql, new Object[]{fiusuario}, String.class);

        Integer exist2 = Integer.parseInt(exist);


        String password = Integer.toString(fiusuario);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pwd = passwordEncoder.encode(password);


        jdbcTemplate.update("INSERT INTO usuarios(fiusuario,fcpassword,fcnombre,fccorreo,fctelefono)VALUES(?,?,?,?,?)", fiusuario, pwd,fcnombre,fccorreo,fctelefono);

        String sql2 = "SELECT fiid FROM usuarios WHERE fiusuario = ?";

        String id = (String) getJdbcTemplate().queryForObject(
                sql2, new Object[]{fiusuario}, String.class);

        Integer iduser = Integer.parseInt(id);
        Integer idrole = idrol;

        jdbcTemplate.update("INSERT INTO usuarios_roles(fiidusuario,fiidrole)VALUES(?,?)", iduser, idrole);

    }


        public void editarUsuario (Integer fiusuario,  String fcnombre, String fccorreo, String fctelefono, Integer idrol)
        {

           // String password = fcpassword;
           // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
           // String pwd = passwordEncoder.encode(password);

            jdbcTemplate.update("update usuarios set fiusuario = ?  , fcnombre = ?, fccorreo = ?, fctelefono = ? where fiusuario = ?",fiusuario,fcnombre,fccorreo,fctelefono, fiusuario);

            String sql2 = "SELECT fiid FROM usuarios WHERE fiusuario = ?";

            String id = (String)getJdbcTemplate().queryForObject(
                    sql2, new Object[] { fiusuario }, String.class);

            Integer iduser = Integer.parseInt(id);
            Integer idrole = idrol;

            jdbcTemplate.update("update usuarios_roles set fiidrole = ? where fiidusuario = ?",idrole, iduser);
        }



    public Usuario findByUsuario(Integer fiusuario){

        String sql = "SELECT a.fiusuario, b.fcnombre FROM usuarios a inner join ResponsablesFlujos b on a.fiusuario = b.FiNumEmpleado where a.fiusuario=? ";


        Usuario  usuario = (Usuario) getJdbcTemplate().queryForObject(
                sql, new Object[] { fiusuario },
                new BeanPropertyRowMapper(Usuario.class));

     //   System.out.print("usuario encontrado"+usuario);
        return usuario;
    }

    public Usuario obtenerUsuario(Integer fiusuario){

        String sql = "SELECT A.fiusuario, A.fcnombre, A.fccorreo, A.fctelefono, B.fiidROLE , C.fcrol FROM usuarios A " +
                "INNER JOIN usuarios_roles B ON A.fiid = B.fiidusuario " +
                "INNER JOIN ROLES C ON B.fiidrole = C.fiid WHERE A.fiUSUARIO=?";


        Usuario  usuario = (Usuario) getJdbcTemplate().queryForObject(
                sql, new Object[] { fiusuario },
                new BeanPropertyRowMapper(Usuario.class));


        return usuario;
    }


    public Usuario perfilUsuario(Integer fiusuario){

        String sql = "SELECT A.fiusuario, A.fcnombre, A.fccorreo, A.fctelefono, d.fcrol, A.fdfecha  FROM usuarios A\n" +
                "    INNER JOIN usuarios_roles B ON A.fiid = B.fiidusuaRIO\n" +
                "    INNER JOIN ROLES D ON  b.fiidrole = d.fiid where a.fiusuario=?;";


        Usuario  usuario = (Usuario) getJdbcTemplate().queryForObject(
                sql, new Object[] { fiusuario },
                new BeanPropertyRowMapper(Usuario.class));

//System.out.println(usuario);
        return usuario;
    }


    public void eliminarUsuario(Integer fiusuario){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource1);
        jdbcTemplate.update("DELETE FROM usuarios WHERE  fiusuario="+fiusuario);
    }



    private String encoder (String pwd){
              BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
         String pwd2 = passwordEncoder.encode(pwd);
        return pwd2;
    }


    public void validarPassword(){
        String p = "123";
        String p1 ="$2a$10$T0lWqk.pk2guoCxhCqPS9.HHxX6TSIL2559Gk7qNIF0hODKtTXda.";

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String existingPassword = p; // Password entered by user
        String dbPassword       = p1; // Load hashed DB password

        if (passwordEncoder.matches(existingPassword, dbPassword)) {

        } else {
          String pwdactual = "La contrasena actual no es valida";// Report error
        }


    }

public String obtenerContrasena (Integer fiusuario)

    {

        String sql2 = "SELECT fcpassword FROM usuarios WHERE fiusuario = ?";

        String pwd = (String) getJdbcTemplate().queryForObject(
                sql2, new Object[]{fiusuario}, String.class);
        return pwd;
    }



    public String actualizarContrasena (Integer fiusuario, String fcpassword)
    {


        jdbcTemplate.update("update usuarios set fcpassword = ? where fiusuario = ?",fcpassword, fiusuario);
        String mensaje="Contrasena actualizada con exito";
        return mensaje;
    }



    public String reiniciarContrasena (Integer fiusuario)
    {

        String pwd = Integer.toString(fiusuario);

        String pwd2 = encoder(pwd);

        jdbcTemplate.update("update usuarios set fcpassword = ? where fiusuario = ?",pwd2, fiusuario);

        String mensaje="Contrasena reinicializada con exito";

        return mensaje;
    }


    public List<Usuario> consultarUsuarios(String text){

        String txt = "'%"+text+"%'";

        String sql = "SELECT a.fiusuario, a.fcnombre, a.fccorreo, a.fctelefono, d.fcrol FROM usuarios a " +
                "inner join USUARIOS_ROLES C ON A.fiid = C.fiidusuario " +
                "INNER JOIN ROLES D ON C.fiidrole = D.fiid " +
        "WHERE a.fiusuario LIKE"+txt+ " or a.fcnombre LIKE "+txt +
              " or a.fccorreo LIKE "+txt;

        List<Usuario> lista = new ArrayList<Usuario>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for (Map row : rows) {
            Usuario usuario = new Usuario();
            usuario.setFiusuario((Integer) (row.get("fiusuario")));
            usuario.setFcnombre((String)(row.get("fcnombre")));
            usuario.setFccorreo((String)(row.get("fccorreo")));
            usuario.setFctelefono((String)(row.get("fctelefono")));
            usuario.setFcrol((String)(row.get("fcrol")));
            lista.add(usuario);
        }

        logger.info(lista);
        return lista;

    }

}
