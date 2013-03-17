package src;


import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Reto1 extends MIDlet implements CommandListener {

    private Display pantalla;               // El display
    private CrearDibujo canvas;             // El canvas
    private List dibujos;                   // Lista de dibujos
    private Command salir, nuevo, ver;      // Comandos

    /*
     * Constuctor
     */
    public Reto1() {
        pantalla = Display.getDisplay(this);
        canvas = new CrearDibujo(this);
        ver = new Command("Ver", Command.OK, 1);
        salir = new Command("Salir", Command.EXIT, 1);
        nuevo = new Command("Nuevo", Command.SCREEN, 1);

        dibujos = new List("Dibujos guardados", List.IMPLICIT);
        dibujos.addCommand(salir);
        dibujos.addCommand(nuevo);
        dibujos.addCommand(ver);
        dibujos.setCommandListener(this);
    }

    protected void startApp() {
        pantalla.setCurrent(dibujos);
    }

    protected void pauseApp() {
    }

    protected void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == nuevo) {
            canvas.setClearDisplay(true);
            pantalla.setCurrent(canvas);
        } else if (c == ver) {
            canvas.verDibujo();
        } else if (c == salir) {
            destroyApp(false);
            notifyDestroyed();
        }
    }

    public void mostrarListaDibujos() {
        pantalla.setCurrent(dibujos);
    }

    /*
     * Getters
     */
    public Display getDisplay() {
        return pantalla;
    }

    public CrearDibujo getCanvas() {
        return canvas;
    }

    public List getDibujos() {
        return dibujos;
    }
}


