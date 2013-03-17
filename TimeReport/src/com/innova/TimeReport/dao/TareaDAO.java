/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.innova.TimeReport.dao;

import com.innova.TimeReport.bo.ProyectoBO;
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
public class TareaDAO {

    public TareaDAO(){}

    /**
     * Crea una nueva tarea.
     * @param tarea
     */
    public void nuevaTarea(TareaBO tarea){
        Connection conexion = null;

        try {
            conexion = getConeccion();

            String sql1 = "INSERT INTO TAREAS (DESCRIPCION,FE_INICIO," +
                    "FE_FIN,TIEMPO,PROYECTO) VALUES (?,?,?,?,?)";
            String sql2 = "UPDATE PROYECTOS SET NUM_TAREAS = NUM_TAREAS+1 WHERE DESCRIPCION = ?";
            String sql3 = "UPDATE TAREAS SET COMENTARIO = ? WHERE DESCRIPCION = ?";

            PreparedStatement guardarProyecto = conexion.prepareStatement(sql1);
            PreparedStatement agregarTarea = conexion.prepareStatement(sql2);
            PreparedStatement comentario = conexion.prepareStatement(sql3);

            guardarProyecto.setString(1, tarea.getNombreTarea());
            guardarProyecto.setString(2, tarea.getFeInicio());
            guardarProyecto.setString(3, tarea.getFeFin());
            guardarProyecto.setString(4, tarea.getTiempo());
            guardarProyecto.setString(5, tarea.getProyecto());

            agregarTarea.setString(1, tarea.getProyecto());

            comentario.setString(1, tarea.getComentario());
            comentario.setString(2, tarea.getNombreTarea());
            
            int proyectoRes = guardarProyecto.executeUpdate();
            int tareaRes = agregarTarea.executeUpdate();
            int comentarioRes = comentario.executeUpdate();

            if (proyectoRes != 0 && tareaRes != 0 && comentarioRes != 0) {
                System.out.println("\nSe inserto la TAREA satisfactoriamente");
            } else {
                System.out.println("\nSe produjo un error insertando la TAREA");
            }

            guardarProyecto.close();
            agregarTarea.close();
            comentario.close();

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
     * Elimina una tarea. Disminuye la cuenta de tareas
     * @param tarea
     * @return
     */
    public boolean eliminarTarea(TareaBO tarea){
        boolean eliminada = true;

        Connection conexion = null;

        try {
            conexion = getConeccion();

            //Se elimina la tarea y se resta al proyecto el numero de horas trabajadas en la misma.
            String sql1 = "DELETE FROM TAREAS WHERE DESCRIPCION = ?";
            String sql2 = "UPDATE PROYECTOS SET TIEMPO = SUBTIME(TIEMPO, ?) WHERE DESCRIPCION = ?";
            String sql3 = "UPDATE PROYECTOS SET NUM_TAREAS = NUM_TAREAS - 1 WHERE DESCRIPCION = ?";
            String sql4 = "DELETE FROM TIEMPOS WHERE TAREA = ?";

            PreparedStatement eliminar = conexion.prepareStatement(sql1);
            PreparedStatement horas = conexion.prepareStatement(sql2);
            PreparedStatement tareas = conexion.prepareStatement(sql3);
            PreparedStatement tiempos = conexion.prepareStatement(sql4);
            
            eliminar.setString(1, tarea.getNombreTarea());

            horas.setString(1, tarea.getTiempo());
            horas.setString(2, tarea.getProyecto());

            tareas.setString(1, tarea.getProyecto());

            tiempos.setString(1, tarea.getNombreTarea());

            int eliminarRes = eliminar.executeUpdate();
            int horasRes = horas.executeUpdate();
            int tareasRes = tareas.executeUpdate();
            int tiempoRes = tiempos.executeUpdate();

            eliminar.close();
            horas.close();
            tareas.close();
            tiempos.close();

            if ((eliminarRes != 0) && (horasRes != 0) && (tareasRes != 0) && (tiempoRes >= 0)) {
                System.out.println("\nSe elimino la TAREA satisfactoriamente");
            } else {
                eliminada = false;
                System.out.println("\nSe produjo un error eliminando la TAREA");
            }

        } catch (Exception e) {
            eliminada = false;
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
        return eliminada;
    }

    /**
     * Modifica el nombre de una tarea.
     * @param tarea
     * @return
     */
    public boolean modificarNomTarea(String nuevoNombre, String nombre){
        boolean modificada = true;

        Connection conexion = null;

        try {
            conexion = getConeccion();
            
            String sql1 = "UPDATE TAREAS SET DESCRIPCION = ? WHERE DESCRIPCION = ?";
            String sql2 = "UPDATE TIEMPOS SET TAREA = ? WHERE TAREA = ?";
            
            PreparedStatement modNombreTarea = conexion.prepareStatement(sql1);
            PreparedStatement modNombreTiempo = conexion.prepareStatement(sql2);
            
            modNombreTarea.setString(1, nuevoNombre);
            modNombreTarea.setString(2, nombre);

            modNombreTiempo.setString(1, nuevoNombre);
            modNombreTiempo.setString(2, nombre);

            int nombreRes = modNombreTarea.executeUpdate();
            int tiempoRes = modNombreTiempo.executeUpdate();

            modNombreTarea.close();
            modNombreTiempo.close();

            if ((nombreRes != 0) && (tiempoRes != 0)) {
                System.out.println("\nSe modifico la TAREA satisfactoriamente");
            } else {
                modificada = false;
                System.out.println("\nSe produjo un error modificando la TAREA");
            }

        } catch (Exception e) {
            modificada = false;
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
        return modificada;
    }

    public boolean modificarTarea(TareaBO modTarea){
        boolean modificada = true;

        Connection conexion = null;

        try {
            conexion = getConeccion();

            String sql1 = "UPDATE TAREAS SET TIEMPO = ADDTIME(TIEMPO, ?) WHERE DESCRIPCION = ?";
            String sql2 = "UPDATE PROYECTOS SET TIEMPO = ADDTIME(TIEMPO, ?) WHERE DESCRIPCION = ?";
            String sql3 = "UPDATE TAREAS SET FE_FIN = ? WHERE DESCRIPCION = ?";
            String sql4 = "UPDATE TAREAS SET FE_INICIO = ? WHERE DESCRIPCION = ?";
            String sql5 = "UPDATE TAREAS SET COMENTARIO = ? WHERE DESCRIPCION = ?";


            PreparedStatement tiempoP = conexion.prepareStatement(sql2);
            PreparedStatement feFin = conexion.prepareStatement(sql3);
            PreparedStatement feInicio = conexion.prepareStatement(sql4);
            PreparedStatement comentario = conexion.prepareStatement(sql5);

            String ff = modTarea.getFeFin().equals("") ? null : modTarea.getFeFin();
//            String fi = modTarea.getFeInicio().equals("") ? null : modTarea.getFeInicio();
            String c = modTarea.getComentario().equals("") ? null : modTarea.getComentario();
            
            //Se setean los parametros
            feFin.setString(1, ff);
            feFin.setString(2, modTarea.getNombreTarea());

//            feInicio.setString(1, fi);
//            feInicio.setString(2, modTarea.getNombreTarea());

            comentario.setString(1, c);
            comentario.setString(2, modTarea.getNombreTarea());

            //Se hacen las consultas a la base de datos
            int feFinRes = (ff == null ? 1 : feFin.executeUpdate());
//            int feInicioRes = (fi == null ? 1 : feInicio.executeUpdate());
            int comentarioRes = ( c == null ? 1 : comentario.executeUpdate());

            //Se verifica el exito de la consulta.
            if ((feFinRes != 0)
//                    && (feInicioRes != 0) 
                    && (comentarioRes != 0)) {
                System.out.println("\nSe actualizo la TAREA satisfactoriamente");
            } else {
                modificada = false;
                System.out.println("\nSe produjo un error actualizando la TAREA");
            }

            //Se cierran los statements
            tiempoP.close();
            feFin.close();
            feInicio.close();
            comentario.close();

        } catch (Exception e) {
            modificada = false;
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

        return modificada;
    }

    /**
     * Retorna las tareas asociadas a un proyecto.
     * @param proyecto
     * @return
     */
    public Vector<TareaBO> getTareas(String proyecto){

        Vector<TareaBO> tareas = new Vector();
        TareaBO tarea = new TareaBO();

        Connection conexion = null;

        try {
            conexion = getConeccion();

            String sql = "SELECT * FROM TAREAS WHERE PROYECTO = ?";

            PreparedStatement temasGuardados = conexion.prepareStatement(sql);
            temasGuardados.setString(1, proyecto);

            ResultSet resultado = temasGuardados.executeQuery();

            while (resultado.next()) {

                tarea.setNombreTarea(resultado.getString("DESCRIPCION"));
                tarea.setFeInicio(resultado.getString("FE_INICIO"));
                tarea.setFeFin(resultado.getString("FE_FIN"));
                tarea.setTiempo(resultado.getString("TIEMPO"));
                tarea.setProyecto(resultado.getString("PROYECTO"));
                
                tareas.addElement(tarea);
                tarea = new TareaBO();
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

        return tareas;
    }

    /**
     * Metodo que retorna en numero de tareas asignadas a un proyecto.
     * @param proyecto
     * @return
     */
    public int getNumTareas(ProyectoBO proyecto){

        int numero = 0;

        Connection conexion = null;

        try {
            conexion = getConeccion();

            String sql = "SELECT * FROM TAREAS WHERE PROYECTO = ?";

            PreparedStatement numTareas = conexion.prepareStatement(sql);
            numTareas.setString(1, proyecto.getNombre());
            ResultSet resultado = numTareas.executeQuery();

            numero = resultado.getRow(); //Numero de registros encontrados


            resultado.close();
            numTareas.close();

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

        return numero;
    }

    public String getComentario(String tarea){
        String comentario = "";
        String nomTarea = tarea;

        Connection conexion = null;

        try {
            conexion = getConeccion();

            String sql = "SELECT COMENTARIO FROM TAREAS WHERE DESCRIPCION = ?";

            PreparedStatement coment = conexion.prepareStatement(sql);
            coment.setString(1, nomTarea);

            ResultSet resultado = coment.executeQuery();

            while (resultado.next()) {
                comentario = resultado.getString("COMENTARIO");
            }

            resultado.close();
            coment.close();

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

        return comentario;
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
