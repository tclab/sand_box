/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author juaninsitu
 */
public class MiPrimerMidlet extends MIDlet {
    public void startApp() {
        System.out.println("StartApp");
    }

    public void pauseApp() {
        System.out.println("pauseApp");
    }

    public void destroyApp(boolean unconditional) {
        System.out.println("destroyApp");
    }
}
