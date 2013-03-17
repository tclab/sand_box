/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.innova.TimeReport.dao;

import com.innova.TimeReport.bo.ProyectoBO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author juan
 */
public class ProyectoDAO {

    private Vector<ProyectoBO> proyectos;

    public ProyectoDAO(){}


    /**
     * Metodo que retorna un vector los proyectos almacenados.
     * @return
     */
    public Vector<ProyectoBO> proyectosTodos(){
        proyectos = new Vector();
        ProyectoBO proyecto = new ProyectoBO();

        Connection conexion = null;

        try {
            conexion = getConeccion();

            String sql = "SELECT * FROM PROYECTOS";

            PreparedStatement temasGuardados = conexion.prepareStatement(sql);

            ResultSet resultado = temasGuardados.executeQuery();

            while (resultado.next()) {

                proyecto.setNombre(resultado.getString("DESCRIPCION"));
                proyecto.setFeInicio(resultado.getString("FE_INICIO"));
                proyecto.setFeFin(resultado.getString("FE_FIN"));
                proyecto.setTiempo(resultado.getString("TIEMPO"));
                proyecto.setNumTareas(resultado.getInt("NUM_TAREAS"));
                proyecto.setCerrado(resultado.getBoolean("CERRADO"));
                proyectos.addElement(proyecto);
                proyecto = new ProyectoBO();
            }

            resultado.close();
            temasGuardados.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if ((conexion != null) && (!conexion.isClosed())) {
                    conexion.close();
                }
            } catch (Exception exc) {
                throw new RuntimeException(exc);
            }
        }

        return proyectos;

    }

    /**
     * Metodo que retorna un vector con los proyectos abiertos.
     * @return
     */
    public Vector<ProyectoBO> proyectosAbiertos(){
        proyectos = new Vector();
        ProyectoBO proyecto = new ProyectoBO();

        Connection conexion = null;

        try {
            conexion = getConeccion();

            String sql = "SELECT * FROM PROYECTOS WHERE CERRADO = FALSE";

            PreparedStatement temasGuardados = conexion.prepareStatement(sql);

            ResultSet resultado = temasGuardados.executeQuery();

            while (resultado.next()) {

                proyecto.setNombre(resultado.getString("DESCRIPCION"));
                proyecto.setFeInicio(resultado.getString("FE_INICIO"));
                proyecto.setFeFin(resultado.getString("FE_FIN"));
                proyecto.setTiempo(resultado.getString("TIEMPO"));
                proyecto.setNumTareas(resultado.getInt("NUM_TAREAS"));
                proyecto.setCerrado(resultado.getBoolean("CERRADO"));
                proyectos.addElement(proyecto);
                proyecto = new ProyectoBO();
            }

            resultado.close();
            temasGuardados.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if ((conexion != null) && (!conexion.isClosed())) {
                    conexion.close();
                }
            } catch (Exception exc) {
                throw new RuntimeException(exc);
            }
        }

        return proyectos;

    }

    /**
     * Metodo que retorna un vector con los proyectos cerrados.
     * @return
     */
    public Vector<ProyectoBO> proyectosCerrados(){
        proyectos = new Vector();
        ProyectoBO proyecto = new ProyectoBO();

        Connection conexion = null;

        try {
            conexion = getConeccion();

            String sql = "SELECT * FROM PROYECTOS WHERE CERRADO = TRUE";

            PreparedStatement temasGuardados = conexion.prepareStatement(sql);

            ResultSet resultado = temasGuardados.executeQuery();

            while (resultado.next()) {

                proyecto.setNombre(resultado.getString("DESCRIPCION"));
                proyecto.setFeInicio(resultado.getString("FE_INICIO"));
                proyecto.setFeFin(resultado.getString("FE_FIN"));
                proyecto.setTiempo(resultado.getString("TIEMPO"));
                proyecto.setNumTareas(resultado.getInt("NUM_TAREAS"));
                proyecto.setCerrado(resultado.getBoolean("CERRADO"));
                proyectos.addElement(proyecto);
                proyecto = new ProyectoBO();
            }

            resultado.close();
            temasGuardados.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if ((conexion != null) && (!conexion.isClosed())) {
                    conexion.close();
                }
            } catch (Exception exc) {
                throw new RuntimeException(exc);
            }
        }

        return proyectos;

    }

    /**
     * Crea un nuevo proyecto.
     * @param proyecto
     */
    public void nuevoProyecto(ProyectoBO proyecto){

        Connection conexion = null;

        try {
            conexion = getConeccion();

            String sql = "INSERT INTO PROYECTOS (DESCRIPCION,FE_INICIO,FE_FIN,TIEMPO," +
                    "CERRADO) VALUES (?,?,?,?,?)";

            PreparedStatement guardarProyecto = conexion.prepareStatement(sql);

            guardarProyecto.setString(1, proyecto.getNombre());
            guardarProyecto.setString(2, proyecto.getFeInicio());
            guardarProyecto.setString(3, proyecto.getFeFin());
            guardarProyecto.setString(4, proyecto.getTiempo());
            guardarProyecto.setBoolean(5, proyecto.getCerrado());

            int resultado = guardarProyecto.executeUpdate();


            if (resultado == 1) {
                System.out.println("\nSe inserto PROYECTO satisfactoriamente");
            } else {
                System.out.println("\nSe produjo un error insertando el PROYECTO");
            }
            
            guardarProyecto.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if ((conexion != null) && (!conexion.isClosed())) {
                    conexion.close();
                }
            } catch (Exception exc) {
                throw new RuntimeException(exc);
            }
        }

    }

    /**
     * Elimina el proyecto y todas sus tareas asociadas
     * @param proyecto
     */
    public void eliminarProyecto(ProyectoBO proyecto){
        Connection conexion = null;

        try {
            conexion = getConeccion();

            String sql1 = "DELETE FROM PROYECTOS WHERE DESCRIPCION = ?";
            String sql2 = "DELETE FROM TAREAS WHERE PROYECTO = ?";
            String sql3 = "DELETE FROM TIEMPOS WHERE PROYECTO = ?";

            PreparedStatement eliminarProyecto = conexion.prepareStatement(sql1);
            PreparedStatement eliminarTareas = conexion.prepareStatement(sql2);
            PreparedStatement eliminarTiempos = conexion.prepareStatement(sql3);

            eliminarProyecto.setString(1, proyecto.getNombre());
            eliminarTareas.setString(1, proyecto.getNombre());
            eliminarTiempos.setString(1, proyecto.getNombre());
            
            
            int proyectoRes = eliminarProyecto.executeUpdate();
            int tareasRes = eliminarTareas.executeUpdate();
            int tiemposRes = eliminarTiempos.executeUpdate();

            if ((proyectoRes != 0) && (tareasRes != 0) && (tiemposRes != 0)) {
                System.out.println("\nSe elimino PROYECTO satisfactoriamente");
            } else {
                System.out.println("\nSe produjo un error eliminando el PROYECTO");
            }

            eliminarProyecto.close();
            eliminarTareas.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if ((conexion != null) && (!conexion.isClosed())) {
                    conexion.close();
                }
            } catch (Exception exc) {
                throw new RuntimeException(exc);
            }
        }
    }

    public ProyectoBO consultarProyecto(String proyectoConsultado){
        ProyectoBO proyecto = new ProyectoBO();
        String descripcion;
        String tiempo;
        int numTareas;
        boolean cerrado;
        String feInicio;
        String feFin;

        Connection conexion = null;

        try {
            conexion = getConeccion();

            String sql = "SELECT * FROM PROYECTOS WHERE DESCRIPCION = ?";

            PreparedStatement proyectoC = conexion.prepareStatement(sql);

            proyectoC.setString(1, proyectoConsultado);
            ResultSet resultado = proyectoC.executeQuery();

            while (resultado.next()) {
                
                descripcion = resultado.getString("DESCRIPCION");
                tiempo = resultado.getString("TIEMPO");
                numTareas = resultado.getInt("NUM_TAREAS");
                cerrado = resultado.getBoolean("CERRADO");
                feInicio = resultado.getString("FE_INICIO");
                feFin = resultado.getString("FE_FIN");

                proyecto = new ProyectoBO(descripcion, feInicio, feFin, tiempo, cerrado);
                proyecto.setNumTareas(numTareas);
            }

            resultado.close();
            proyectoC.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if ((conexion != null) && (!conexion.isClosed())) {
                    conexion.close();
                }
            } catch (Exception exc) {
                throw new RuntimeException(exc);
            }
        }

        return proyecto;
    }

    /**
     * Cambia el nombre del proyecto.
     */
    public boolean modificarNomProyecto(String nuevoNombre, String nombre){
        boolean actualizado = true;
        Connection conexion = null;

        try {
            conexion = getConeccion();

            String sql1 = "UPDATE PROYECTOS SET DESCRIPCION = ? WHERE DESCRIPCION = ?";
            String sql2 = "UPDATE TAREAS SET PROYECTO = ? WHERE PROYECTO = ?";

            PreparedStatement modificarProyecto = conexion.prepareStatement(sql1);
            PreparedStatement modificarTareas = conexion.prepareStatement(sql2);

            modificarProyecto.setString(1, nuevoNombre);
            modificarProyecto.setString(2, nombre);

            modificarTareas.setString(1, nuevoNombre);
            modificarTareas.setString(2, nombre);

            int proyectoRes = modificarProyecto.executeUpdate();
            int tareasRes = modificarTareas.executeUpdate();

            if ((proyectoRes != 0) && (tareasRes != 0)) {
                System.out.println("\nSe actualizo el PROYECTO satisfactoriamente");
            } else {
                actualizado = false;
                System.out.println("\nSe produjo un error actualizando el PROYECTO");
            }

            modificarProyecto.close();

        } catch (Exception e) {
            actualizado = false;
            e.printStackTrace();
        } finally {
            try {
                if ((conexion != null) && (!conexion.isClosed())) {
                    conexion.close();
                }
            } catch (Exception exc) {
                throw new RuntimeException(exc);
            }
        }

        return actualizado;
    }

    /**
     * Modifica la fecha del proyecto.
     * @return
     */
    public boolean modificarFeInicioProyecto(String feInicio, ProyectoBO proyecto){
        boolean resultado = true;

        Connection conexion = null;

        try {
            conexion = getConeccion();

            String sql1 = "UPDATE PROYECTOS SET FE_INICIO = ? WHERE DESCRIPCION = ?";

            PreparedStatement feInicioProyecto = conexion.prepareStatement(sql1);

            feInicioProyecto.setString(1, feInicio);
            feInicioProyecto.setString(2, proyecto.getNombre());

            int proyectoRes = feInicioProyecto.executeUpdate();

            if (proyectoRes != 0) {
                System.out.println("\nSe actualizo el PROYECTO satisfactoriamente");
            } else {
                resultado = false;
                System.out.println("\nSe produjo un error actualizando el PROYECTO");
            }

            feInicioProyecto.close();

        } catch (Exception e) {
            resultado = false;
            e.printStackTrace();
        } finally {
            try {
                if ((conexion != null) && (!conexion.isClosed())) {
                    conexion.close();
                }
            } catch (Exception exc) {
                throw new RuntimeException(exc);
            }
        }



        return resultado;
    }

    /**
     * Cambia el estado del proyecto (Abierto o cerrado).
     * @param proyecto
     * @param cerrar
     */
    public boolean modificarEstadoProyecto(ProyectoBO proyecto, boolean estado){

        boolean actualizado = true;
        Connection conexion = null;

        try {
            conexion = getConeccion();

            String sql = "UPDATE PROYECTOS SET CERRADO = ? WHERE DESCRIPCION = ?";

            PreparedStatement estadoProyecto = conexion.prepareStatement(sql);

            estadoProyecto.setBoolean(1, estado);
            estadoProyecto.setString(2, proyecto.getNombre());

            int resultado = estadoProyecto.executeUpdate();

            if (resultado != 0) {
                System.out.println("\nSe actualizo el PROYECTO satisfactoriamente");
            } else {
                actualizado = false;
                System.out.println("\nSe produjo un error actualizando el PROYECTO");
            }

            estadoProyecto.close();

        } catch (Exception e) {
            actualizado = false;
            e.printStackTrace();
        } finally {
            try {
                if ((conexion != null) && (!conexion.isClosed())) {
                    conexion.close();
                }
            } catch (Exception exc) {
                throw new RuntimeException(exc);
            }
        }

        return actualizado;
    }
    

    /**
     * Metodo que establece la coneccion con la base de datos
     * @return Coneccion con base de datos
     */
    public Connection getConeccion() {

        Connection c = null;

        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            c =DriverManager.getConnection("jdbc:mysql://localhost/TimeReport"
                    , "root", "jtorocan");
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "No se pudo establecer la conexion." +
                    "\nIntente de nuevo.", "Alerta", -1, JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return c;
    }
}
