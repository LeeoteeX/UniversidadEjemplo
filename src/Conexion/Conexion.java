/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import universidadejemplo.Alumno;
import universidadejemplo.Materia;

public class Conexion {

    private static String url = "jdbc:mariadb://localhost/universidadlp";
    private static String usuario = "root";
    private static String password = "";

    private static Conexion conexion = null;

    private Conexion() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error en el Driver");
        }
    }

    public static Connection getConexion() {
        Connection con = null;
        if (conexion == null) {
            conexion = new Conexion();
        }
        try {
            con = DriverManager.getConnection(url, usuario, password);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexion");
        }
        return con;
    }

    public static void insertarMateria(Materia materia) {
        try {
            String sql = "INSERT INTO materia (nombre, año, estado) VALUES (?, ?, ?)";
            PreparedStatement ps = Conexion.getConexion().prepareStatement(sql);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAno());
            ps.setBoolean(3, true);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexion");
        }
    }

    public static void guardarAlumno(Alumno alumno) {
        try {

            String sql = "INSERT INTO alumno (dni, nombre, apellido, estado, fechaNacimiento) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = Conexion.getConexion().prepareStatement(sql);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getNombre());
            ps.setString(3, alumno.getApellido());
            ps.setBoolean(4, true);
            ps.setDate(5, (java.sql.Date) alumno.getFechaN());
            ps.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de Conexion");
        }
    }

    public static int obtIDAlumno(Alumno alumno) {
        int idAlum = 0;
        try {

            String sql = "SELECT * FROM alumno";
            PreparedStatement ps = Conexion.getConexion().prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                if (alumno.getApellido().equals(resultado.getString("apellido"))) {
                    idAlum = resultado.getInt("idAlumno");
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de Conexion");
        }
        System.out.println("ID DE ALUMNO " + idAlum);
        return idAlum;

    }

    public static int obtIDMateria(Materia materia) {
        int id = 0;
        try {

            String sql = "SELECT * FROM materia";
            PreparedStatement ps = Conexion.getConexion().prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                if (materia.getNombre().equals(resultado.getString("nombre"))) {
                    id = resultado.getInt("idMateria");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de Conexion");
        }
        System.out.println("ID DE LA MATERIA " + id);
        return id;
    }

    public static void inscribirAlumno(Alumno alumno, Materia materia, int nota) {
        try {
            String sql = "INSERT INTO inscripcion (nota, idAlumno, idMateria) VALUES ( ?, ?, ?)";
            PreparedStatement ps = Conexion.getConexion().prepareStatement(sql);
            ps.setInt(1, nota);
            ps.setInt(2, obtIDAlumno(alumno));
            ps.setInt(3, obtIDMateria(materia));
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de Conexion");
        }
    }

    public static void listarAlumnosNOTA(Alumno alumno, int nota) {

        try {
            String sql = "SELECT * FROM alumno, inscripcion WHERE nota >= (?)";
            PreparedStatement ps = Conexion.getConexion().prepareStatement(sql);
            ps.setInt(1, nota);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                System.out.print("Nombre: " + resultado.getString("nombre"));
                System.out.print(" Apellido: " + resultado.getString("apellido"));
                System.out.print(" Nota: " + resultado.getInt("nota"));
                System.out.println("");
                ps.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de Conexion");
        }
    }

    public static void desinscribirAlumno(Alumno alumno, Materia materia) {

        try {
            String sql = "DELETE FROM inscripcion WHERE idAlumno = ? and idMateria = ?";
            PreparedStatement ps = Conexion.getConexion().prepareStatement(sql);
            int x = obtIDAlumno(alumno);
            int y = obtIDMateria(materia);
            ps.setInt(1, x);
            ps.setInt(2, y);
            ps.executeUpdate();
            System.out.println("Inscripcion eliminada");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de Conexion" + ex.getMessage());

        }

    }
}
