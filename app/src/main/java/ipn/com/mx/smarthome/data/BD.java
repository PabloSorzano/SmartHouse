/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipn.com.mx.smarthome.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo Sorzano
 */
public class BD {

    Connection c = null;
    Statement s = null;
    ResultSet rs = null;
    String servidor = "jdbc:mysql://localhost:3306/";
    String base = "SmartHouse";
    String usuario = "root";
    String pass = "n0m3l0";

    int idUsuario, idCasa, numeroPiso;
    String nama, aPat, aMat, cel, mail, contra,
            estado, muni, codigoP,//codigoPostal 5 caracteres
            col, calle, numInt, coordenadas,
            nombreCuarto, observacionCuarto;
    String datos, mensaje;

    public Connection conectar() {

        try {
            //Driver JDBC
            Class.forName("com.mysql.jdbc.Driver");

            //Se inicia la conexión
            c = DriverManager.getConnection(servidor + base, usuario, pass);

            //Se crea una declaración con la conexión establecida
            s = c.createStatement();
            rs = s.getResultSet();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            c = null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            c = null;
        } catch (Exception ex) {
            ex.printStackTrace();
            c = null;
        }
        if (c != null) {
            System.out.println("Conexion Exitosa \n");
        }
        return c;
    }

    public void agregaU(String nombre, String aPat, String aMat, String cel, String mail, String contra) {
        this.nama = nombre;
        this.aPat = aPat;
        this.aMat = aMat;
        this.cel = cel;
        this.mail = mail;
        this.contra = contra;
    }

    public void agregaC(int idUsuario, String coordenadas, String estado, String muni, String codigoP, String col, String calle, String numInt) {
        this.idUsuario = idUsuario;
        this.coordenadas = coordenadas;
        this.estado = estado;
        this.muni = muni;
        this.codigoP = codigoP;
        this.col = col;
        this.calle = calle;
        this.numInt = numInt;
    }
    
    public void agregaCuarto(int idCasa, String nombreCuarto, int numeroPiso, String observacionCuarto) {
        this.idCasa = idCasa;
        this.nombreCuarto = nombreCuarto;
        this.numeroPiso = numeroPiso;
        this.observacionCuarto = observacionCuarto;
    }

    public Statement procedimiento(String proc) {

        try {
            if (proc.equalsIgnoreCase("altaUsuario")) {
                s.execute("call smarthouse.sp_AltaUsuario('" + nama + "', '" + aPat + "', '" + aMat + "', '" + cel + "', '" + mail + "', '" + contra + "');");
                System.out.println("Usuario dado de alta con éxito");
            } else if (proc.equalsIgnoreCase("altaCasa")) {
                s.execute("call smarthouse.sp_AltaCasa(" + idUsuario + ", '" + coordenadas + "' , '" + estado + "', '" + muni + "', '" + codigoP + "', '" + col + "', '" + calle + "', '" + numInt + "');");
                System.out.println("Casa de alta con éxito");
            } else if (proc.equalsIgnoreCase("altaCuarto")) {
                s.execute("call smarthouse.sp_AltaCuarto("+idCasa+", '"+nombreCuarto+"', "+numeroPiso+", '"+observacionCuarto+"');");
                System.out.println("Cuarto dado de alta con éxito");
            } 
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public ResultSet obtenerDatos(String tabla, String valor, String condi) {
        try {
            if (condi.isEmpty()) {
                rs = s.executeQuery("select " + valor + " from " + tabla + " ;");
            } else {
                rs = s.executeQuery("select " + valor + " from " + tabla + " where " + condi + " ;");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public String eliminarDatos(String tabla, String campo, String valor, String condi) {
        try {
            if (condi.isEmpty()) {
                s.execute("delete from " + tabla + " where " + tabla + "." + campo + " = " + valor + ";");
                mensaje = "Los datos seleccionados han sido borrados";
            } else {
                s.execute("delete from " + tabla + " where " + tabla + "." + campo + " = " + valor + " and " + condi + ";");
                mensaje = "Los datos seleccionados han sido borrados";
            }
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensaje;
    }

}
