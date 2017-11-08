/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timejoker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author juan
 */
public class Main {

    public static String hora(String fecha) throws ParseException{
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Date dFecha = formato.parse(fecha);

        GregorianCalendar gFecha = new GregorianCalendar();
        gFecha.setTime(dFecha);

        String hora = new String().valueOf(gFecha.get(gFecha.HOUR_OF_DAY)).length() == 1 ? "0" + gFecha.get(gFecha.HOUR_OF_DAY) : new String().valueOf(gFecha.get(gFecha.HOUR_OF_DAY));
        String minuto = new String().valueOf(gFecha.get(gFecha.MINUTE)).length() == 1 ? "0" + gFecha.get(gFecha.MINUTE) : new String().valueOf(gFecha.get(gFecha.MINUTE));
        String segundo = new String().valueOf(gFecha.get(gFecha.SECOND)).length() == 1 ? "0" + gFecha.get(gFecha.SECOND) : new String().valueOf(gFecha.get(gFecha.SECOND));

        String tiempo = hora + ":" + minuto + ":" + segundo;

        return tiempo;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        GregorianCalendar gregorian = new GregorianCalendar();

        String fechaFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());


        String fecha = "2009-12-21 09:01:11";
        String fecha2 = "2009-12-21 10:39:50";

        System.out.println("Fecha: " + fechaFormat);
        System.out.println("Hora: " + hora(fecha));

    }
}
