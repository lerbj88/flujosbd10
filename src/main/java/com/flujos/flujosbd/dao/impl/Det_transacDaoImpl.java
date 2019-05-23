package com.flujos.flujosbd.dao.impl;

import com.flujos.flujosbd.dao.Det_transacDao;
import com.flujos.flujosbd.model.Det_transac;
import com.flujos.flujosbd.model.Sucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
@Transactional


public class Det_transacDaoImpl    implements Det_transacDao {




    public List<Det_transac> getDetalle(String ip, Integer fitrann){

        List<Det_transac> list = null;

        String connectionUrl =
                "jdbc:jtds:sqlserver://"+ip+""
                        + "/adn;"
                        + "user=BANCO;"
                        + "password=AZTECA;"
                        ;

        ResultSet resultSet = null;


        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {

            String selectSql = "SELECT fitranno, ficonsdeta, fitiporeg, fcdatodeta from DET_transac where fitranno="+fitrann;
            List<Det_transac> lista = new ArrayList<Det_transac>();
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {

                // Lo que va entre comillas es el nombre del campo de tu bd
                Integer fitranno= resultSet.getInt("fitranno");
                Integer ficonsdeta= resultSet.getInt("ficonsdeta");
                Integer fitiporeg= resultSet.getInt("fitiporeg");
                String fcdatodeta= resultSet.getString("fcdatodeta");

                //Creas un objeto del tipo que te estas trayendo de la bd
                Det_transac k=new Det_transac(fitranno,ficonsdeta,fitiporeg,fcdatodeta);//le mandas los parametros necesarios al constructor
                lista.add(k); //agregas ese objeto a la lista

           }

            list= lista;

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    public List<Det_transac> getDetalleRango(String ip, Integer fitrann, Integer fitranno2){

        List<Det_transac> list = null;

        String connectionUrl =
                "jdbc:jtds:sqlserver://"+ip+""
                        + "/adn;"
                        + "user=BANCO;"
                        + "password=AZTECA;"
                ;

        ResultSet resultSet = null;


        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {

            String selectSql = "SELECT fitranno, ficonsdeta, fitiporeg, fcdatodeta from DET_transac where fitranno between "+fitrann+" AND "+fitranno2;
            List<Det_transac> lista = new ArrayList<Det_transac>();
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {

                // Lo que va entre comillas es el nombre del campo de tu bd
                Integer fitranno= resultSet.getInt("fitranno");
                Integer ficonsdeta= resultSet.getInt("ficonsdeta");
                Integer fitiporeg= resultSet.getInt("fitiporeg");
                String fcdatodeta= resultSet.getString("fcdatodeta");

                //Creas un objeto del tipo que te estas trayendo de la bd
                Det_transac k=new Det_transac(fitranno,ficonsdeta,fitiporeg,fcdatodeta);//le mandas los parametros necesarios al constructor
                lista.add(k); //agregas ese objeto a la lista

            }

            list= lista;

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }



}
