package com.flujos.flujosbd.routing;

import java.sql.*;

public class CrearConexionSucursal {

    //Datos por default de la conexion
    static String strUsuario = "sa";
    static String strPassword = "pw";
    static String strBaseDatos ="bd";
    static String strHost = "localhost";

    //Constructor, le llegan los datos con los que se conectara al DBMS
    public CrearConexionSucursal(String usr,String pw, String bd)
    {
        strUsuario = usr;
        strPassword = pw;
        strBaseDatos =bd;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
        } catch ( ClassNotFoundException e )
        {
            System.out.println("ERROR: Error al cargar la clase del Driver");
        }
    }

    //Constructor, le llegan los datos con los que se conectara al DBMS
    // a dif. del otro constructor le llega tbn el host (servidor)
    public CrearConexionSucursal(String usr,String pw, String bd,String srvr)
    {
        strUsuario = usr;
        strPassword = pw;
        strBaseDatos =bd;
        strHost = srvr;
        try
        {
            //Cargar el driver
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
        } catch ( ClassNotFoundException e )
        {
            System.out.println("ERROR: Error al cargar la clase del Driver\n");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException
    {
        //a continuacion vamos a formar la cadena de conexion, pero...
        //OJO aca debes poner el nombre de la instancia de SQL Server que vas a usar
        String url = "jdbc:jtds:sqlserver://"+strHost+"/"+strBaseDatos+";instance=SQLSERVEREXPRESS;";
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
