/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.innova.TimeReport.bo;

/**
 *
 * @author juan
 */
public class TareaBO {

    /*
     * Nombre de la tarea
     */
    private String nombreTarea;
    /*
     * Tiempo de duracion del proyecto
     */
    private String tiempo;
    /*
     * Proyecto al que pertenece
     */
    private String proyecto;
    /*
     * Comentario
     */
    private String comentario;

    private String feInicio;

    private String feFin;

    public TareaBO(){

    }

    public TareaBO(String nombreTarea, String proyecto){
        this.nombreTarea = nombreTarea;
        this.proyecto = proyecto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
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
}
