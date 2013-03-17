/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innova.TimeReport.dao;

import com.innova.TimeReport.bo.TiempoBO;
import com.innova.TimeReport.bo.TareaBO;
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
public class TiempoDAO {

    /**
     * Inserta un nuevo tiempo para una tarea.
     * @param tarea
     */
    public boolean nuevoTiempo(TiempoBO tarea) {
        boolean resultado = true;
        Connection conexion = null;

        try {
            conexion = getConeccion();

            String sql1 = "INSERT INTO TIEMPOS (FE_INICIO,FE_FIN,TIEMPO,TAREA,PROYECTO) VALUES (?,?,?,?,?)";

            PreparedStatement nuevoTiempo = conexion.prepareStatement(sql1);

            nuevoTiempo.setString(1, tarea.getFeInicio());
            nuevoTiempo.setString(2, tarea.getFeFin());
            nuevoTiempo.setString(3, tarea.getTiempo());
            nuevoTiempo.setString(4, tarea.getTarea());
            nuevoTiempo.setString(5, tarea.getProyecto());

            int tiempoRes = nuevoTiempo.executeUpdate();

            if (tiempoRes != 0) {
                System.out.println("\nSe inserto el TIEMPO satisfactoriamente");
            } else {
                resultado = false;
                System.out.println("\nSe produjo un error insertando el TIEMPO");
            }

            nuevoTiempo.close();

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
     * Devuelve un vector con los timpos de una tarea.
     * @param tarea
     * @return
     */
    public Vector<TiempoBO> obtenerTiempos(String tarea) {
        Vector<TiempoBO> tiempos = new Vector();
        TiempoBO tiempo = new TiempoBO();

        Connection conexion = null;

        try {
            conexion = getConeccion();

            String sql = "SELECT * FROM TIEMPOS WHERE TAREA = ?";

            PreparedStatement tiemposGuardados = conexion.prepareStatement(sql);

            tiemposGuardados.setString(1, tarea);

            ResultSet resultado = tiemposGuardados.executeQuery();

            while (resultado.next()) {

                tiempo.setTiempoId(resultado.getString("ID"));
                tiempo.setFeInicio(resultado.getString("FE_INICIO"));
                tiempo.setFeFin(resultado.getString("FE_FIN"));
                tiempo.setTiempo(resultado.getString("TIEMPO"));

                tiempos.addElement(tiempo);
                tiempo = new TiempoBO();
            }

            resultado.close();
            tiemposGuardados.close();

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

        return tiempos;
    }

    /**
     * Retorna la fecha de inicio de un intervalo de tiempo.
     * @param tarea
     * @return
     */
    public String obtenerInicio(String tarea, String id) {
        String inicio = new String();

        Connection conexion = null;

        try {
            conexion = getConeccion();

            String sql = "SELECT FE_INICIO FROM TIEMPOS WHERE TAREA = ? AND ID = ?";

            PreparedStatement feInicio = conexion.prepareStatement(sql);

            feInicio.setString(1, tarea);
            feInicio.setString(2, id);

            ResultSet resultado = feInicio.executeQuery();

            while (resultado.next()) {
                inicio = resultado.getString("FE_INICIO");
            }

            resultado.close();
            feInicio.close();

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
        return inicio;
    }

    /**
     * Elimina un tiempo de una tarea.
     * @param tiempo
     */
    public boolean eliminarTiempo(TiempoBO tiempo) {
        boolean resultado = true;
        Connection conexion = null;

        try {
            conexion = getConeccion();

            String sql1 = "DELETE FROM TIEMPOS WHERE ID = ? AND TAREA = ?";
            String sql2 = "UPDATE TAREAS SET TIEMPO = SUBTIME(TIEMPO, ?) WHERE DESCRIPCION = ?";
            String sql3 = "UPDATE PROYECTOS SET TIEMPO = SUBTIME(TIEMPO, ?) WHERE DESCRIPCION = ?";

            PreparedStatement eliminarTiempo = conexion.prepareStatement(sql1);
            PreparedStatement disminuirTTarea = conexion.prepareStatement(sql2);
            PreparedStatement disminuirTProyecto = conexion.prepareStatement(sql3);

            eliminarTiempo.setString(1, tiempo.getTiempoId());
            eliminarTiempo.setString(2, tiempo.getTarea());

            disminuirTTarea.setString(1, tiempo.getTiempo());
            disminuirTTarea.setString(2, tiempo.getTarea());

            disminuirTProyecto.setString(1, tiempo.getTiempo());
            disminuirTProyecto.setString(2, tiempo.getProyecto());

            int eliminarRes = eliminarTiempo.executeUpdate();
            eliminarTiempo.close();

            if (tiempo.getTiempo() != null) {
                int tareaRes = disminuirTTarea.executeUpdate();
                int proyectoRes = disminuirTProyecto.executeUpdate();


                disminuirTTarea.close();
                disminuirTProyecto.close();
                
                if ((tareaRes != 0) && (proyectoRes != 0)) {
                System.out.println("\nSe elimino el TIEMPO satisfactoriamente");
            } else {
                resultado = false;
                System.out.println("\nSe produjo un error eliminando el TIEMPO");
            }
            }

            if (eliminarRes != 0) {
                System.out.println("\nSe elimino el TIEMPO satisfactoriamente");
            } else {
                resultado = false;
                System.out.println("\nSe produjo un error eliminando el TIEMPO");
            }

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
     * Setea la fecha-tiempo de inicio de una tarea.
     * (Inicia un tiempo).
     * @param tiempo
     */
    public boolean empezarTiempo(TiempoBO tiempo) {

        boolean resultado = true;
        Connection conexion = null;

        try {
            conexion = getConeccion();

            String sql1 = "UPDATE TIEMPOS SET FE_INICIO = ? WHERE TAREA = ? AND ID = ?";

            PreparedStatement empezar = conexion.prepareStatement(sql1);

            empezar.setString(1, tiempo.getFeInicio());
            empezar.setString(2, tiempo.getTarea());
            empezar.setString(3, tiempo.getTiempoId());

            int empezarRes = empezar.executeUpdate();

            empezar.close();

            if (empezarRes != 0) {
                System.out.println("\nSe inicio el TIEMPO satisfactoriamente");
            } else {
                resultado = true;
                System.out.println("\nSe produjo un error iniciando el TIEMPO");
            }

        } catch (Exception e) {
            resultado = true;
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
     * Setea la fecha-tiempo de fin de un tiempo.
     * (termina una tarea).
     * @param tiempo
     */
    public boolean terminarTiempo(TiempoBO tiempo, String hInicio, String hFin) {
        //TODO: Aumentar tiempo en tarea y a su vez en el proyecto.
        boolean resultado = true;

        Connection conexion = null;

        try {
            conexion = getConeccion();

            String sql1 = "UPDATE TIEMPOS SET FE_FIN = ? WHERE TAREA = ? AND ID = ?";
            String sql2 = "UPDATE TIEMPOS SET TIEMPO = SUBTIME(?,?) WHERE TAREA = ? AND ID = ?"; //Seterar tiepo en intervalo
            String sql3 = "UPDATE TAREAS SET TIEMPO = ADDTIME(TIEMPO, SUBTIME(?,?)) WHERE DESCRIPCION = ?"; //Aumentar tiempo en tarea
            String sql4 = "UPDATE PROYECTOS SET TIEMPO = ADDTIME(TIEMPO, SUBTIME(?,?)) WHERE DESCRIPCION = ?"; //Aumetar tiempo en proyecto.

            PreparedStatement feFin = conexion.prepareStatement(sql1);
            PreparedStatement tiempoIntervalo = conexion.prepareStatement(sql2);
            PreparedStatement tiempoTarea = conexion.prepareStatement(sql3);
            PreparedStatement tiempoProyecto = conexion.prepareStatement(sql4);

            feFin.setString(1, tiempo.getFeFin());
            feFin.setString(2, tiempo.getTarea());
            feFin.setString(3, tiempo.getTiempoId());

            tiempoIntervalo.setString(1, hFin);
            tiempoIntervalo.setString(2, hInicio);
            tiempoIntervalo.setString(3, tiempo.getTarea());
            tiempoIntervalo.setString(4, tiempo.getTiempoId());

            tiempoTarea.setString(1, hFin);
            tiempoTarea.setString(2, hInicio);
            tiempoTarea.setString(3, tiempo.getTarea());

            tiempoProyecto.setString(1, hFin);
            tiempoProyecto.setString(2, hInicio);
            tiempoProyecto.setString(3, tiempo.getProyecto());

            int feFinRes = feFin.executeUpdate();
            int intervaloRes = tiempoIntervalo.executeUpdate();
            int tareaRes = tiempoTarea.executeUpdate();
            int proyectoRes = tiempoProyecto.executeUpdate();

            feFin.close();
            tiempoIntervalo.close();
            tiempoTarea.close();
            tiempoProyecto.close();

            if ((feFinRes != 0) && (intervaloRes != 0) && (tareaRes != 0) && (proyectoRes != 0)) {
                System.out.println("\nSe inicio el TIEMPO satisfactoriamente");
            } else {
                resultado = false;
                System.out.println("\nSe produjo un error iniciando el TIEMPO");
            }

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
     * Metodo que establece la coneccion con la base de datos
     * @return Coneccion con base de datos
     */
    public Connection getConeccion() {

        Connection c = null;

        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/TimeReport", "root", "jtorocan");
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "No se pudo establecer la conexion." +
                    "\nIntente de nuevo.", "Alerta", -1, JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return c;
    }
}
