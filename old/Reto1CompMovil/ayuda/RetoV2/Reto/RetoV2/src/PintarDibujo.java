
import java.util.Vector;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Victor
 */
class PintarDibujo extends Canvas implements CommandListener {

    private Command cmExit;          // Exit midlet
    private Command cmClear;         // Clear display
    Vector Coord, Coord1, numV;
    Reto midlet;
    private int iniciox = 0;
    private int inicioy = 0;
    // Posici�n actual
    private int actualx = 0;
    private int actualy = 0;
    int cont = 0;
    int color;
    Vector colors;
    /*--------------------------------------------------
     * Constructor
     *-------------------------------------------------*/

    public PintarDibujo(Reto midlet, Vector Coord, Vector Coord1, Vector numV,Vector colors, int color) {
        this.midlet = midlet;
        this.Coord = Coord;
        this.Coord1 = Coord1;
        this.numV = numV;
        this.colors=colors;
        this.color = color;
        // Create exit command & listen for events
        cmExit = new Command("Exit", Command.EXIT, 1);

        addCommand(cmExit);

        setCommandListener(this);
    }

    /*--------------------------------------------------
     * Paint the text representing the key code
     *-------------------------------------------------*/
    protected void paint(Graphics g) {
        // Clear the background (to white)
//    if (clearDisplay)
//    {
//      g.setColor(255, 255, 255);
//      g.fillRect(0, 0, getWidth(), getHeight());
//
//      clearDisplay = false;
//      startx = currentx = starty = currenty = 0;
//
//      return;
//    }

        g.setColor(255, 255, 255);
        g.fillRect(0, 0, getWidth(), getHeight());
        // Draw with black pen
//    iniciox = actualx = inicioy = actualy = 0;
//        int AuxX = 0;
//        int AuxY = 0;
        // Draw line
        int i = 0;
        int j = 0;
        int k = 0;
        System.out.println("Graficando en la pantalla");

        int contador = 0;
        while (j < Coord1.size()) {
          
            iniciox = Integer.parseInt(Coord1.elementAt(j).toString());
            j++;

            inicioy = Integer.parseInt(Coord1.elementAt(j).toString());
            j++;
            int tam = Integer.parseInt(numV.elementAt(contador).toString());
             int colorAnterior=Integer.parseInt(colors.elementAt(contador).toString());
            while (k < tam) {
                g.setColor(colorAnterior);
                actualx = Integer.parseInt(Coord.elementAt(i).toString());
                i++;
                k++;
                actualy = Integer.parseInt(Coord.elementAt(i).toString());
                i++;
                k++;
                System.out.println("inicx:" + iniciox + " inicy:" + inicioy);
                System.out.println("actx:" + actualx + " acty:" + actualy);
                 if(k!=2){
                    g.drawLine(iniciox, inicioy, actualx, actualy);
                 }
                iniciox = actualx;
                inicioy = actualy;
            }
            k = 0;
            contador++;
        }

//        g.drawLine(startx, starty, currentx, currenty);
    }

//    g.drawLine(startx, starty, currentx, currenty);
//
//    // New starting point is the current position
//    startx = currentx;
//    starty = currenty;
    /*--------------------------------------------------
     * Command event handling
     *-------------------------------------------------*/
    public void commandAction(Command c, Displayable d) {
        if (c == cmExit) {
            midlet.mostrarLista();
        }
//      midlet.exitMIDlet();

    }

    /*--------------------------------------------------
     * Pointer pressed
     *-------------------------------------------------*/
}
