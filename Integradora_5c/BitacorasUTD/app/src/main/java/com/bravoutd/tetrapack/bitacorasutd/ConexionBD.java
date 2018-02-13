package com.bravoutd.tetrapack.bitacorasutd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Hurón Padilla on 2/13/2018.
 */

public class ConexionBD {

    public Connection connect = null;
    public ResultSet rs = null;
    public Statement sentencia = null;
    private String path = "jdbc:mysql://hirampadilla.epizy.com/epiz_19509506_bitacoras", user = "epiz_1950506", password = "amoruchiha";

    public void conectar()
    {
        try
        {   connect = DriverManager.getConnection(path,user,password);
            sentencia = connect.createStatement();
        }
        catch(Exception e1){e1.getMessage();}
    }

    public void cerrarConexion(){
        try {
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
