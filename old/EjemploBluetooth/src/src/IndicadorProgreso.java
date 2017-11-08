package src;

import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;
/*
 * IndicadProgreso.java
 *
 * Created on 22 de septiembre de 2006, 11:11 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author insitu
 */
public class IndicadorProgreso extends Form implements Runnable{
    Gauge g;
    private boolean procesando = false;
    /** Creates a new instance of IndicadProgreso */
    public IndicadorProgreso() {
        super("Progreso");
        g = new Gauge("", false, 10, 0);
        this.append(g);
        procesando = true;
    }

    public void run() {
        int i = 0;
        while(procesando){
            g.setValue(i);
              try{
                    Thread.sleep(500);
                }catch (Exception e){
                    System.out.println("Error de ejecucion");
                }  
            i++;
            if(i==10){
                i=0;
            }
        }
    }
    public void stop(){
        procesando = false;
    }
}
