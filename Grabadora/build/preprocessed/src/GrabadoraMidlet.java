/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.RecordControl;
import javax.microedition.midlet.*;

/**
 * @author juan
 */
public class GrabadoraMidlet extends MIDlet implements CommandListener{

    public List listaGrabar;
    public Command salir;
    public Display pantalla;

    public GrabadoraMidlet(){

        pantalla = Display.getDisplay(this);
        salir = new Command("Salir", Command.EXIT, 1);
        listaGrabar = new List("Grabadora", List.IMPLICIT);

        listaGrabar.append("Grabar", null);
        listaGrabar.addCommand(salir);
        listaGrabar.setCommandListener(this);
    }

    public void startApp() {
        pantalla.setCurrent(listaGrabar);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void grabar() {
        try {
// Create a Player that captures live audio.

            Player playerRecord = Manager.createPlayer("capture://audio");
            playerRecord.realize();

            RecordControl rc = (RecordControl) playerRecord.getControl("RecordControl");

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            rc.setRecordStream(output);

            playerRecord.prefetch();

            rc.startRecord();

            playerRecord.start();

            Thread.currentThread().sleep(5000);
            rc.commit();

            playerRecord.close();
        } catch (IOException ioe)
            {
            } catch  (MediaException me) {
        } catch (InterruptedException ie) {
        }
    }

    public void commandAction(Command c, Displayable d) {
        if (c == salir) {
            destroyApp(false);
            notifyDestroyed();
        }else if (c == listaGrabar.SELECT_COMMAND) {

            switch (listaGrabar.getSelectedIndex()) {
                case 0:
                    grabar();
                    break;
            }
        }
    }
}
