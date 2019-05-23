package com.flujos.flujosbd.dao.impl;

import com.flujos.flujosbd.dao.TransacDao;
import com.flujos.flujosbd.model.Transac;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class TransacDaoImpl implements TransacDao {

    public List<Transac> getTransaccion(String ip, Integer fitran){

        //String ip2=ip;

        String connectionUrl =
                "jdbc:jtds:sqlserver://"+ip+""
                        + "/adn;"
                        + "user=BANCO;"
                        + "password=AZTECA;"
                ;

        ResultSet resultSet = null;
        List<Transac> list = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {

            String selectSql = "SELECT FITRANNO, FITRANTIPO, FICONSECTIPO, FDTRANFECHR, FCTRANWS, FCTRANUSR, FITRANNOREG, FCAPLICACION, FITRANSMIT, FDINICIOTRAN, FDFINTRAN from TRANSAC where fitranno="+fitran;
            List<Transac> lista = new ArrayList<Transac>();
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {

                // Lo que va entre comillas es el nombre del campo de tu bd
                Integer fitranno=resultSet.getInt("fitranno");
                Short fitrantipo = resultSet.getShort("fitrantipo");
                Integer ficonsectipo=resultSet.getInt("ficonsectipo");
                Timestamp fdtranfechr = resultSet.getTimestamp("fdtranfechr");
                String fctranws = resultSet.getString("fctranws");
                String fctranusr = resultSet.getString("fctranusr");
                Integer fitrannoreg=resultSet.getInt("fitrannoreg");
                String fcaplicacion = resultSet.getString("fcaplicacion");
                Short fitransmit = resultSet.getShort("fitransmit");
                Timestamp fdiniciotran = resultSet.getTimestamp("fdiniciotran");
                Timestamp fdfintran = resultSet.getTimestamp("fdfintran");
                ;

                //Creas un objeto del tipo que te estas trayendo de la bd
                Transac k=new Transac(fitranno, fitrantipo, ficonsectipo, fdtranfechr, fctranws, fctranusr, fitrannoreg, fcaplicacion, fitransmit, fdiniciotran, fdfintran);//le mandas los parametros necesarios al constructor
                lista.add(k); //agregas ese objeto a la lista
                list=lista;
            }

          //  System.out.println(list);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return  list;
    }


    public List<Transac> getTransaccionRango(String ip, Integer fitran, Integer fitran2){

        //String ip2=ip;

        String connectionUrl =
                "jdbc:jtds:sqlserver://"+ip+""
                        + "/adn;"
                        + "user=BANCO;"
                        + "password=AZTECA;"
                ;

        ResultSet resultSet = null;
        List<Transac> list = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {

            String selectSql = "SELECT FITRANNO, FITRANTIPO, FICONSECTIPO, FDTRANFECHR, FCTRANWS, FCTRANUSR, FITRANNOREG, FCAPLICACION, FITRANSMIT, FDINICIOTRAN, FDFINTRAN from TRANSAC where fitranno between "+fitran+" AND "+fitran2;
            List<Transac> lista = new ArrayList<Transac>();
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {

                // Lo que va entre comillas es el nombre del campo de tu bd
                Integer fitranno=resultSet.getInt("fitranno");
                Short fitrantipo = resultSet.getShort("fitrantipo");
                Integer ficonsectipo=resultSet.getInt("ficonsectipo");
                Timestamp fdtranfechr = resultSet.getTimestamp("fdtranfechr");
                String fctranws = resultSet.getString("fctranws");
                String fctranusr = resultSet.getString("fctranusr");
                Integer fitrannoreg=resultSet.getInt("fitrannoreg");
                String fcaplicacion = resultSet.getString("fcaplicacion");
                Short fitransmit = resultSet.getShort("fitransmit");
                Timestamp fdiniciotran = resultSet.getTimestamp("fdiniciotran");
                Timestamp fdfintran = resultSet.getTimestamp("fdfintran");
                ;

                //Creas un objeto del tipo que te estas trayendo de la bd
                Transac k=new Transac(fitranno, fitrantipo, ficonsectipo, fdtranfechr, fctranws, fctranusr, fitrannoreg, fcaplicacion, fitransmit, fdiniciotran, fdfintran);//le mandas los parametros necesarios al constructor
                lista.add(k); //agregas ese objeto a la lista
                list=lista;

            }

              System.out.println(list);




        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return  list;
    }


}
