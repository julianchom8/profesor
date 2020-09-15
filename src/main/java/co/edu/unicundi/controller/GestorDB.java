/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.controller;

import co.edu.unicundi.controller.pojo.Profesor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Juan José Rangel
 */
public class GestorDB {

    public static void agregarProfesor(Profesor profesor) {
        DBManager database = new DBManager();
        try {
            String query = "INSERT INTO `profesores` (`cedula`, `nombre`, `apellido`, `edad`) VALUES (?,?,?,?);";
            PreparedStatement sentenciaP = database.open().prepareStatement(query);
            sentenciaP.setInt(1, profesor.getCedula());
            sentenciaP.setString(2, profesor.getNombre());
            sentenciaP.setString(3, profesor.getApellido());
            sentenciaP.setString(4, Integer.toString(profesor.getEdad()));
            sentenciaP.executeUpdate();
            sentenciaP.close();
            database.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void editarProfesor(Profesor profesor) {
        DBManager database = new DBManager();
        try {
            String query = "UPDATE `profesores` SET `cedula` = ?, `nombre` = ?, `apellido` = ?, `edad` = ? WHERE `profesores`.`cedula` = ?;";
            PreparedStatement sentenciaP = database.open().prepareStatement(query);
            sentenciaP.setInt(1, profesor.getCedula());
            sentenciaP.setString(2, profesor.getNombre());
            sentenciaP.setString(3, profesor.getApellido());
            sentenciaP.setString(4, Integer.toString(profesor.getEdad()));
            sentenciaP.setInt(5, profesor.getCedula());
            sentenciaP.executeUpdate();
            sentenciaP.close();
            database.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void borrarProfesor(int cedula) {
        DBManager database = new DBManager();
        try {
            String query = "DELETE FROM `profesores` WHERE `profesores`.`cedula` = ?";

            PreparedStatement sentenciaP = database.open().prepareStatement(query);
            sentenciaP.setInt(1, cedula);
            sentenciaP.execute();
            sentenciaP.close();
            database.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList cargarProfesores() {
        DBManager database = new DBManager();
        ArrayList<Profesor> listaProfesores = new ArrayList<>();
        try {
            String query = "SELECT * FROM profesores;";
            PreparedStatement sentenciaP = database.open().prepareStatement(query);
            ResultSet resultado = sentenciaP.executeQuery();

            while (resultado.next()) {
                listaProfesores.add(new Profesor(resultado.getInt("cedula"), resultado.getString("nombre"), resultado.getString("apellido"), resultado.getInt("edad"), null, null));
            }
            sentenciaP.close();
            database.close();
        } catch (SQLException ae) {
            System.out.println(ae.getMessage());
        }
        return listaProfesores;
    }

    public static ArrayList cargarProfesor(int cedula) {
        DBManager database = new DBManager();
        ArrayList<Profesor> listaProfesores = new ArrayList<>();
        try {
            String query = "SELECT * FROM `profesores` WHERE `cedula` = " + cedula + ";";
            PreparedStatement sentenciaP = database.open().prepareStatement(query);
            ResultSet resultado = sentenciaP.executeQuery();

            while (resultado.next()) {
                listaProfesores.add(new Profesor(resultado.getInt("cedula"), resultado.getString("nombre"), resultado.getString("apellido"), resultado.getInt("edad"), null, null));
            }
            sentenciaP.close();
            database.close();
        } catch (SQLException ae) {
            System.out.println(ae.getMessage());
        }
        return listaProfesores;
    }

    public static ArrayList cargarProfesorMateria(int materia) {
        DBManager database = new DBManager();
        ArrayList<Profesor> listaProfesores = new ArrayList<>();
        try {
            String query = "SELECT profesores.cedula, profesores.nombre, profesores.apellido, profesores.edad FROM profesores INNER JOIN materias ON profesores.cedula = materias.profesor AND materias.idmateria = " + materia + ";";
            PreparedStatement sentenciaP = database.open().prepareStatement(query);
            ResultSet resultado = sentenciaP.executeQuery();

            while (resultado.next()) {
                listaProfesores.add(new Profesor(resultado.getInt("cedula"), resultado.getString("nombre"), resultado.getString("apellido"), resultado.getInt("edad"), null, null));
            }
            sentenciaP.close();
            database.close();
        } catch (SQLException ae) {
            System.out.println(ae.getMessage());
        }
        return listaProfesores;
    }
}
