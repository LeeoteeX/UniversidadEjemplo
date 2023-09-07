
package universidadejemplo;

import Conexion.Conexion;
import java.sql.Date;
import java.sql.DriverManager;
import java.time.LocalDate;


public class UniversidadEjemplo {

    public static void main(String[] args) {
     
        Conexion.getConexion();
        Alumno alumno = new Alumno( 37810942, "Leandro", "Gramajo", Date.valueOf(LocalDate.of(1993, 06, 17)), true);
        Alumno alumno2 = new Alumno( 42903210, "Valentina", "Oyarzun", Date.valueOf(LocalDate.of(2000, 01, 8)), true);
        Alumno alumno3 = new Alumno( 28490294, "Jose", "Lopez", Date.valueOf(LocalDate.of(1995, 02, 11)), true);
        Alumno alumno4 = new Alumno( 35354219, "Juan", "Robledo", Date.valueOf(LocalDate.of(1993, 04, 10)), true);
        Alumno alumno5 = new Alumno( 35354210, "Pedro", "Curihuinca", Date.valueOf(LocalDate.of(1992, 06, 23)), true);
//        Conexion.guardarAlumno(alumno);
//        Conexion.guardarAlumno(alumno2);
//        Conexion.guardarAlumno(alumno3);
//        Conexion.guardarAlumno(alumno4);
//        Conexion.guardarAlumno(alumno5);
        Materia materia = new Materia("Fisica", 1, true);
        Materia materia2 = new Materia("Matematica", 2, true);
        Materia materia3 = new Materia("Literatura", 3, true);
        Materia materia4 = new Materia("Geografia", 3, true);
//        Conexion.insertarMateria(materia);
//        Conexion.insertarMateria(materia2);
//        Conexion.insertarMateria(materia3);
//        Conexion.insertarMateria(materia4);
  //      Conexion.inscribirAlumno(alumno, materia2, 9);
//        Conexion.inscribirAlumno(alumno, materia3, 5);
//        Conexion.inscribirAlumno(alumno2, materia4, 6);
//        Conexion.inscribirAlumno(alumno2, materia, 7);
//        Conexion.inscribirAlumno(alumno3, materia3, 8);
//        Conexion.inscribirAlumno(alumno3, materia2, 9);
//        Conexion.listarAlumnosNOTA(alumno, 8);
        //falta desinscribir un alumno de una de las materias
       Conexion.desinscribirAlumno(alumno3, materia2);
        
    }
    
}
