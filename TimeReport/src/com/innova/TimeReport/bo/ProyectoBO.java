/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.innova.TimeReport.bo;

/**
 *
 * @author juan
 */
public class ProyectoBO {

    /*
     * Nombre del proyecto
     */
    private String nombre;

    //Ambas fechas deben tener el formato yyyy-mm-dd
    /*
     * Fecha de inicio
     */
    private String feInicio;
    /*
     * Fecha de inicio
     */
    private String feFin;
    /*
     * Duracion del proyecto
     */
    private String tiempo;
    /*
     * Indica si el proyecto esta cerrado o no
     */
    private boolean cerrado;
    /**
     * Numero de tareas asignadas a este proyecto.
     */
    private int numTareas;

    
    public ProyectoBO(){

    }

    public ProyectoBO(String nombre, String feInicio, String feFin, String tiempo, boolean cerrado){
        this.nombre = nombre;
        this.feInicio = feInicio;
        this.feFin = feFin;
        this.tiempo = tiempo;
        this.cerrado = cerrado;
    }

    public boolean getCerrado() {
        return cerrado;
    }

    public void setCerrado(boolean comentario) {
        this.cerrado = comentario;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public int getNumTareas() {
        return numTareas;
    }

    public void setNumTareas(int numTareas) {
        this.numTareas = numTareas;
    }
}
