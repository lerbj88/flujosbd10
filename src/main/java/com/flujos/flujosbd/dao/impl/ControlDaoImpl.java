package com.flujos.flujosbd.dao.impl;

import com.flujos.flujosbd.dao.ControlDao;
import com.flujos.flujosbd.model.CabtraRecepcion;
import com.flujos.flujosbd.model.Control;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ControlDaoImpl implements ControlDao {


    public List<Control> getSucursal(String ip, Short fitienda){



        String connectionUrl =
                "jdbc:jtds:sqlserver://"+ip+""
                        + "/adn;"
                        + "user=BANCO;"
                        + "password=AZTECA;"
                ;

        ResultSet resultSet = null;
        List<Control> list = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {

            String selectSql = "SELECT FINOTIENDA, fctdanombre, fiidcia, fccianombre, FIIDCANAL, FIIDPAIS, fiarea, firegion, fiareaop, firegionop from CONTROL where finotienda="+fitienda;
            List<Control> lista = new ArrayList<Control>();
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {

                // Lo que va entre comillas es el nombre del campo de tu bd
                Short finotienda = resultSet.getShort("finotienda");
                String fctdanombre = resultSet.getString("fctdanombre");
                Short fiidcia = resultSet.getShort("fiidcia");
                String fccianombre = resultSet.getString("fccianombre");
                Short fiidcanal= resultSet.getShort("fiidcanal");
                Short fiidpais= resultSet.getShort("fiidpais");
                Integer fiarea=resultSet.getInt("fiarea");
                Integer firegion=resultSet.getInt("firegion");
                Integer fiareaop=resultSet.getInt("fiareaop");
                Integer firegionop=resultSet.getInt("firegionop");

                //Creas un objeto del tipo que te estas trayendo de la bd
                Control k=new Control(finotienda, fctdanombre, fiidcia, fccianombre, fiidcanal, fiidpais, fiarea, firegion, fiareaop, firegionop);//le mandas los parametros necesarios al constructor
                lista.add(k); //agregas ese objeto a la lista
                list=lista;
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return  list;
    }




}
