/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.innova.TimeReport.bo;

/**
 *
 * @author juan
 */
public class TiempoBO {

    /**
     * Id
     */
    private String tiempoId;
    /**
     * Fecha-hora de inicio del tiempo
     */
    private String feInicio;
    /**
     * Fecha-hora de fin del tiempo
     */
    private String feFin;
    /**
     * Tiempo transcurrido entre el inicio y fin.
     */
    private String tiempo;
    /**
     * Nombre de la tarea a la que pertenece.
     */
    private String tarea;
    /**
     * Proyecto al que pertenece.
     */
    private String proyecto;
    

    public TiempoBO(){

    }


    public String getFeFin() {
        return feFin;
    }

    public void setFeFin(String feFin) {
        this.feFin = feFin;
    }

    public String getFeInicio() {
        return feInicio;
    }

    public void setFeInicio(String feInicio) {
        this.feInicio = feInicio;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getTiempoId() {
        return tiempoId;
    }

    public void setTiempoId(String tiempoId) {
        this.tiempoId = tiempoId;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }
}
