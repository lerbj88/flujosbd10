package com.flujos.flujosbd.routing;

import java.net.ConnectException;
import java.sql.*;

public class CrearConexion400 {

    //Datos por default de la conexion
    static String strUsuario = "sa";
    static String strPassword = "pw";
    static String strBaseDatos ="bd";
    static String strHost = "localhost";



    //Constructor, le llegan los datos con los que se conectara al DBMS

    public CrearConexion400(String usr,String pw,String srvr)
    {
        strUsuario = usr;
        strPassword = pw;
        strHost = srvr;
        try
        {
            //Cargar el driver
            Class.forName("com.ibm.as400.access.AS400JDBCDriver");
        } catch ( ClassNotFoundException e )
        {
            System.out.println("ERROR: Error al cargar la clase del Driver\n");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws  SQLException {

        String url = "jdbc:as400://"+strHost;

        return DriverManager.getConnection(url,strUsuario,strPassword);
    }

    //Cierra objeto Resultset
    public static void cerrar(ResultSet rs)
    {
        try
        {
            rs.close();
        }
        catch(Exception ex)
        {}
    }

    //Cierra objeto Statement
    public static void cerrar(Statement st)
    {
        try
        {
            st.close();
        }
        catch(Exception ex)
        {}
    }

    //Cierra objeto Connection
    public static void cerrar(Connection con)
    {
        try
        {
            con.close();
        }
        catch(Exception ex)
        {}
    }
}
