package com.flujos.flujosbd.dao.impl;

import com.flujos.flujosbd.dao.Retransmision400Dao;
import com.flujos.flujosbd.model.CabtraRecepcion;
import com.flujos.flujosbd.model.Ctrlmstsuc;
import com.flujos.flujosbd.routing.CrearConexion400;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class Retransmision400DaoImpl implements Retransmision400Dao {

    static String strUsuario = "";
    static String strPassword = "";
    static String strBaseDatos ="";
    static String strHost = "";
    static Connection con2 = null;


    public boolean testConection(String usr, String pwd, String host) throws SQLException  {

     /*   strUsuario=usr;
        strPassword=pwd;
        strHost=host;
*/
        strUsuario="b345310";
        strPassword="tamasop1";
        strHost="10.82.40.34";

        Connection conn = null;
            try
            {
                CrearConexion400 con  = new CrearConexion400(strUsuario,strPassword,strHost);
                conn = con.getConnection();
                con2=conn;
                return true;
            }
            catch (HeadlessException e)
            {
                e.getMessage();
                return false;
            }



    }

    public List<Ctrlmstsuc> getErrores21 (){

        ResultSet resultSet = null;
        java.util.List<Ctrlmstsuc> list = null;

        try (
               // Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = con2.createStatement();) {

            String selectSql = "SELECT 1 as FIPAIS, 1 as FICANAL, ADNSUC, ADNUTR ,  'MEXEKAMQ' as FCNEGOCIO from mexekamq.ekamstsuc WHERE adnsts = 1 and adncer=21" +
                    " UNION" +
                    " SELECT 1 as FIPAIS, 2 as FICANAL,  ADNSUC, ADNUTR , 'MEXREM' as FCNEGOCIO from mexrem.remmstsuc WHERE adnsts = 1 and adncer=21" +
                    " UNION " +
                    " SELECT 1 as FIPAIS, 3 as FICANAL, ADNSUC, ADNUTR , 'MEXSYR' as FCNEGOCIO from mexsyr.syrmstsuc WHERE adnsts = 1 and adncer=21" +
                    " UNION " +
                    " SELECT 1 as FIPAIS, ADNCAN AS FICANAL, ADNSUC, ADNUTR , 'MEXMUL' as FCNEGOCIO from mexmul.mulmstsuc WHERE adnsts = 1 and adncer=21\n" +
                    " UNION " +
                    " SELECT ADNPAI AS FIPAIS, ADNCAN AS FICANAL, ADNSUC, ADNUTR , 'LAMMUL' as FCNEGOCIO from lammul.mulmstsuc WHERE adnsts = 1 and adncer=21\n" +
                    " UNION " +
                    " SELECT 1 as FIPAIS, 2 as FICANAL, ADNSUC, ADNUTR , 'MEXREM' as FCNEGOCIO from depaso.remmstsucbenja WHERE adnsts = 1 and adncer=21" ;

            List<Ctrlmstsuc> lista = new ArrayList<Ctrlmstsuc>();
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {

                // Lo que va entre comillas es el nombre del campo de tu bd
                Short fipais  = resultSet.getShort("fipais");
                Short ficanal = resultSet.getShort("ficanal");
                Integer adnsuc = resultSet.getInt("adnsuc");
                Integer adnutr = resultSet.getInt("adnutr");
                String fcnegocio = resultSet.getString("fcnegocio");

                //Creas un objeto del tipo que te estas trayendo de la bd
                Ctrlmstsuc k=new Ctrlmstsuc(fipais, ficanal, adnsuc, adnutr, fcnegocio);//le mandas los parametros necesarios al constructor
                lista.add(k); //agregas ese objeto a la lista
                list=lista;
            }


        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    return list;

    }


    public Integer getMinTranREM (Integer fisucursal){


        ResultSet resultSet = null;
        Integer mintransac = null;


        try
        {
           // CrearConexion400 con  = new CrearConexion400(strUsuario,strPassword,strHost);
           // conn = con.getConnection();

                Statement statement = con2.createStatement(); {
            String selectSql = "SELECT min(fitranno) as minimo from depaso.remcabtrabenja WHERE  FISUCURSAL=9093";
            resultSet  = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                mintransac = resultSet.getInt(1);
            }
            }


    } catch(SQLException e) {
            e.printStackTrace();

        }
    return mintransac;

    }



    }
