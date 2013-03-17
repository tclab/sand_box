/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innova.TimeReport.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

/**
 *
 * @author juan
 */
public class Utils {

    public Utils() {
    }

    /**
     * Retorna la fecha actual.
     * @return
     */
    public String fechaActual() {

        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String fechaA = formatoFecha.format(fechaActual);

        return fechaA;
    }

    /**
     * Organiza alfabeticamente el vector dado como parametro.
     * @param aOrganizar
     * @return
     */
    public Vector organizarVector(Vector aOrganizar) {
        Collections.sort(aOrganizar);

        return aOrganizar;
    }

    /**
     * Valida que un string sea solo de caracteres.
     * @param palabra
     * @return
     */
    public boolean esPalabraCaracteres(String palabra) {
        for (int i = 0; i < palabra.length(); i++) {
            if (!((palabra.charAt(i) > 64 && palabra.charAt(i) < 91) ||
                    (palabra.charAt(i) > 96 && palabra.charAt(i) < 123))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Fecha y hora actual.
     * @return
     */
    public String dateTimeActual(){
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
    }

    /**
     * Obtiene la hora a partir de la fecha.
     * @param fecha
     * @return
     * @throws ParseException
     */
    public String hora(String fecha) throws ParseException{
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Date dFecha = formato.parse(fecha);

        GregorianCalendar gFecha = new GregorianCalendar();
        gFecha.setTime(dFecha);

        String hora = new String().valueOf(gFecha.get(gFecha.HOUR_OF_DAY)).length() == 1 ? "0" + gFecha.get(gFecha.HOUR_OF_DAY) : new String().valueOf(gFecha.get(gFecha.HOUR_OF_DAY));
        String minuto = new String().valueOf(gFecha.get(gFecha.MINUTE)).length() == 1 ? "0" + gFecha.get(gFecha.MINUTE) : new String().valueOf(gFecha.get(gFecha.MINUTE));
        String segundo = new String().valueOf(gFecha.get(gFecha.SECOND)).length() == 1 ? "0" + gFecha.get(gFecha.SECOND) : new String().valueOf(gFecha.get(gFecha.SECOND));

        if(hora.equals("00"))hora="12";

        String tiempo = hora + ":" + minuto + ":" + segundo;

        return tiempo;
    }
}
