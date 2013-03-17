/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;

/**
 * @author Victor
 */
public class Midlet extends MIDlet implements CommandListener {

    private Display display;
    private Form form;
    private Command exitCommand;

    public Midlet() {
        form = new Form("Mensaje");
        exitCommand = new Command("Exit", Command.EXIT, 1);
        display = Display.getDisplay(this);
        form.addCommand(exitCommand);
        form.setCommandListener(this);
    }

    public void startApp() {

        mostrarMensaje();

        // form.addCommand(exitCommand);
        System.out.println("startApp");

    }

    public void pauseApp() {
        System.out.println("tartApp");
    }

    public void mostrarMensaje() {
        // alerta=new Alert("Hola java")

        form.append("! Hola Mundo MicroEdition ¡");
        display.setCurrent(form);

    }

    public void commandAction(Command c, Displayable s) {
        if (c == exitCommand) {

            destroyApp(false);
            notifyDestroyed();

        }
    }

    public void destroyApp(boolean unconditional) {
        System.out.println("rtApp");
    }
}
