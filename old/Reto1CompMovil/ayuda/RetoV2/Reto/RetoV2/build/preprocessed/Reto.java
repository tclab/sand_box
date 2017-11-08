/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Vector;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.rms.RecordStore;

public class Reto extends MIDlet implements CommandListener {

    private Display display;         // El display
    private CanvasDibujo canvas;      // El canvas
    private List dibujos;             // Lista de dibujos
    private Command salir, nuevo, ver;     // Comandos

    /*
     * Constuctor
     */
    public Reto() {
        display = Display.getDisplay(this);
        canvas = new CanvasDibujo(this);
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
        display.setCurrent(dibujos);
    }

    protected void pauseApp() {
    }

    protected void destroyApp(boolean unconditional) {
    }

//  public void exitMIDlet(){
//    destroyApp(true);
//    notifyDestroyed();
//  }
    public void commandAction(Command c, Displayable d) {
        if (c == nuevo) {
            canvas.setClearDisplay(true);
            display.setCurrent(canvas);

        } else if (c == ver) {
            canvas.leerResultados();
        } else if (c == salir) {
            destroyApp(false);
            notifyDestroyed();
        }
    }

    public void mostrarLista() {
        display.setCurrent(dibujos);
    }
    /*
     * Getters
     */

    public Display getDisplay() {
        return display;
    }

    public CanvasDibujo getCanvas() {
        return canvas;
    }

    public List getDibujos() {
        //TODO: Traer los dibujos guardados
        return dibujos;
    }
}

/**
 * Clase CanvasDibujo; maneja el evento pointer
 */
class CanvasDibujo extends Canvas implements CommandListener {

    private Command atras, atrasCanvas, limpiar, color, guardar;
    private Display pantalla;
    private List listaColores;
    private int colorPuntero;
    DibujoBO nombre = new DibujoBO("", null, null, null, null, 0);
    // Posicion inicial
    private int iniciox = 0;
    private int inicioy = 0;
    // Posici�n actual
    private int actualx = 0;
    private int actualy = 0;
    int cont = 0;
    int contar = 0;
    private Reto midlet;
    private boolean clearDisplay = true;
    private Displayable pantallaActual;
    public static RecordStore rstore;
    private Vector personas;
    Vector coord, coord1, numV, colores;
    int x = 255;
    int y = 255;
    int z = 255;
    boolean bandera = false;
    int colorActual;
    int colorAnterior;
    /*
     * Constructor
     */

    public CanvasDibujo(Reto midlet) {
        this.midlet = midlet;
        coord = new Vector();
        coord1 = new Vector();
        numV = new Vector();
        colores = new Vector();
        pantalla = midlet.getDisplay();
        colores.addElement(String.valueOf(colorPuntero));
        // Inicia los comandos
        atras = new Command("Cancelar", Command.BACK, 1);
        limpiar = new Command("Limpiar", Command.SCREEN, 1);

        color = new Command("Color", Command.SCREEN, 2);
        atrasCanvas = new Command("Cancelar", Command.BACK, 1);
        guardar = new Command("Guardar", Command.OK, 3);

        listaColores = new List("Lista de colores", List.IMPLICIT);
        listaColores.append("Negro", null);
        listaColores.append("Verde", null);
        listaColores.append("Rojo", null);
        listaColores.append("Morado", null);
        listaColores.append("Naranja", null);
        listaColores.append("Azul", null);
        listaColores.addCommand(atrasCanvas);
        listaColores.setCommandListener(this);

        addCommand(atras);
        addCommand(limpiar);
        addCommand(color);
        addCommand(guardar);
        setCommandListener(this);
//        getID();
    }

    protected void paint(Graphics g) {

        // Limpia el fondo
        if (clearDisplay) {
            g.setColor(x, y, z);
            g.fillRect(0, 0, getWidth(), getHeight());

            clearDisplay = false;


            iniciox = actualx = inicioy = actualy = 0;
//    coord.addElement(String.valueOf(actualx));
//    coord.addElement(String.valueOf(actualy));
//    coord.addElement(String.valueOf(iniciox));
//    coord.addElement(String.valueOf(inicioy));

            return;
        }
        if (bandera == true) {
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
            while (j < coord1.size()) {
//            System.out.println(coord.size());
                iniciox = Integer.parseInt(coord1.elementAt(j).toString());
                j++;

                inicioy = Integer.parseInt(coord1.elementAt(j).toString());
                j++;
                int tam = Integer.parseInt(numV.elementAt(contador).toString());
                int colorAnterior1 = Integer.parseInt(colores.elementAt(contador).toString());
                while (k < tam) {
                    g.setColor(colorAnterior1);
                    actualx = Integer.parseInt(coord.elementAt(i).toString());
                    i++;
                    k++;
                    actualy = Integer.parseInt(coord.elementAt(i).toString());
                    i++;
                    k++;
                    System.out.println("inicx:" + iniciox + " inicy:" + inicioy);
                    System.out.println("actx:" + actualx + " acty:" + actualy);
                    if (k != 2) {
                        g.drawLine(iniciox, inicioy, actualx, actualy);
                    }
                    iniciox = actualx;
                    inicioy = actualy;
                }
                k = 0;
                contador++;
            }
            bandera = false;
        } else {
            g.setColor(colorPuntero);

//         Dibuja una linea



            g.drawLine(iniciox, inicioy, actualx, actualy);



//         El nuevo punto de inicio es la posicion actual.

            iniciox = actualx;
            inicioy = actualy;
        }
//         Color del lapiz


    }

    /*
     * Eventos de comandos.
     */
    public void commandAction(Command c, Displayable d) {
        if (c == atras) {
            cont = 0;
            coord.removeAllElements();
            coord1.removeAllElements();
            numV.removeAllElements();
            colores.removeAllElements();
            colorPuntero = 0;
            colores.addElement(String.valueOf(colorPuntero));
            pantalla.setCurrent(midlet.getDibujos());
        } else if (c == limpiar) {
            clearDisplay = true;
            coord.removeAllElements();
            coord1.removeAllElements();
            numV.removeAllElements();
            colores.removeAllElements();
            colorPuntero = 0;
            colores.addElement(String.valueOf(colorPuntero));
            cont = 0;
            repaint();
        } else if (c == color) {
            pantallaActual = pantalla.getCurrent();
            pantalla.setCurrent(listaColores);
        } else if (c == atrasCanvas) {
            cont = 0;
            coord.removeAllElements();
            coord1.removeAllElements();
            numV.removeAllElements();
            colores.removeAllElements();
            colorPuntero = 0;
            colores.addElement(String.valueOf(colorPuntero));
            pantalla.setCurrent(pantallaActual);
        } else if (c == guardar) {
            //TODO: Guardar dibujo
            numV.addElement(String.valueOf(cont));
            nombre.setNombre("Dibujo" + contar);
            nombre.setColor(colorPuntero);
            nombre.setCoord1(coord1);
            nombre.setCoord(coord);
            nombre.setColores(colores);
            nombre.setNumV(numV);

            try {
                System.out.println(coord.size());
                PersistenciaDAO.saveDibujos(nombre);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
//         cont=0;
//         coord.removeAllElements();
            fullList();
            contar++;

            pantalla.setCurrent(midlet.getDibujos());
        } else if (c == listaColores.SELECT_COMMAND) {
            System.out.println("Entre aqui");
            switch (listaColores.getSelectedIndex()) {
                case 0:

                    colorAnterior = colorPuntero;
                    colorPuntero = 0;
                    if (coord.isEmpty() && coord1.isEmpty()) {
                        colores.removeElementAt(colores.size() - 1);
                    }
                    colores.addElement(String.valueOf(colorPuntero));
                    //Negro
                    numV.addElement(String.valueOf(cont));
                    cont = 0;
                    bandera = true;
                    repaint();
                    pantalla.setCurrent(pantallaActual);

                    break;
                case 1:
                    colorAnterior = colorPuntero;
                    colorPuntero = 3394611;   //Verde
//                    clearDisplay = true;
                    if (coord.isEmpty() && coord1.isEmpty()) {
//                        colores.removeElementAt(colores.size() - 1);
                    }
                    colores.addElement(String.valueOf(colorPuntero));
                    numV.addElement(String.valueOf(cont));
                    cont = 0;
                    bandera = true;
                    repaint();
                    pantalla.setCurrent(pantallaActual);

                    break;
                case 2:
                    colorAnterior = colorPuntero;
                    colorPuntero = 15147839;  //Rojo
                    if (coord.isEmpty() && coord1.isEmpty()) {
                        colores.removeElementAt(colores.size() - 1);
                    }
                    colores.addElement(String.valueOf(colorPuntero));
                    numV.addElement(String.valueOf(cont));
                    cont = 0;
                    bandera = true;
                    repaint();
                    pantalla.setCurrent(pantallaActual);

                    break;
                case 3:
                    colorAnterior = colorPuntero;
                    colorPuntero = 13130703;  //Morado
                    if (coord.isEmpty() && coord1.isEmpty()) {
                        colores.removeElementAt(colores.size() - 1);
                    }
                    colores.addElement(String.valueOf(colorPuntero));
                    numV.addElement(String.valueOf(cont));
                    cont = 0;
                    bandera = true;
                    repaint();
                    pantalla.setCurrent(pantallaActual);

                    break;
                case 4:
                    colorAnterior = colorPuntero;
                    colorPuntero = 16746536;  //Naranja
                    if (coord.isEmpty() && coord1.isEmpty()) {
                        colores.removeElementAt(colores.size() - 1);
                    }
                    colores.addElement(String.valueOf(colorPuntero));
                    numV.addElement(String.valueOf(cont));
                    cont = 0;
                    bandera = true;
                    repaint();
                    pantalla.setCurrent(pantallaActual);
                    break;
                case 5:
                    colorAnterior = colorPuntero;
                    colorPuntero = 4986871;   //Azul
                    if (coord.isEmpty() && coord1.isEmpty()) {
                        colores.removeElementAt(colores.size() - 1);
                    }
                    colores.addElement(String.valueOf(colorPuntero));
                    numV.addElement(String.valueOf(cont));
                    cont = 0;
                    bandera = true;
                    repaint();
                    pantalla.setCurrent(pantallaActual);
                    break;
            }
        }
    }

//    public void getID() {
//        try {
//            Vector persons = new Vector();
//            //RecordStore.deleteRecordStore("personas");
//            rstore = RecordStore.openRecordStore("coordenadas", true);
//            //RecordStore.deleteRecordStore("personas");
//
//            Coordenada p;
//            RecordEnumeration re = rstore.enumerateRecords(null, null, true);
//
//            while (re.hasPreviousElement()) {
//                byte[] b = re.previousRecord();
//                ByteArrayInputStream bais = new ByteArrayInputStream(b);
//                DataInputStream dis = new DataInputStream(bais);
//                String nombre = dis.readUTF();
//                int color = dis.readInt();
//                int a = dis.readInt();
//                Vector aficiones = new Vector(a);
//                for (int i = 0; i < a; i++) {
//                    aficiones.addElement(dis.readUTF());
//                }
//
//                bais.reset();
//                dis.reset();
//                p = new Coordenada(nombre, aficiones, color);
//                persons.addElement(p);
//                bais.close();
//                dis.close();
//            }
//
//            rstore.closeRecordStore();
//        } catch (Exception e) {
//        }
//    }
    public void fullList() {
        midlet.getDibujos().deleteAll();
        try {
            personas = PersistenciaDAO.readDibujos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (personas.isEmpty()) {
            Alert alerta = new Alert("No hay datos");
            pantalla.setCurrent(alerta);
        } else {
            for (int i = 0; i < personas.size(); i++) {
                DibujoBO persona = (DibujoBO) personas.elementAt(i);
                midlet.getDibujos().append(persona.getNombre(), null);
            }
        }
    }

    public void leerResultados() {
        try {

            String p = midlet.getDibujos().getString(midlet.getDibujos().getSelectedIndex());
            System.out.println(p);
            Vector v = PersistenciaDAO.readDibujos();
            DibujoBO persona = (DibujoBO) v.elementAt(midlet.getDibujos().getSelectedIndex());
            System.out.println(persona.getNombre());
            int color = persona.getColor();
            Vector cordes = new Vector();
            for (int i = 0; i < persona.getCoord().size(); i++) {
                String aficion = persona.getCoord().elementAt(i).toString();
                cordes.addElement(aficion);
                int cord = Integer.parseInt(aficion);
//                System.out.println("coord:"+cord);


            }
            Vector cordes1 = new Vector();
            for (int i = 0; i < persona.getCoord1().size(); i++) {
                String aficion = persona.getCoord1().elementAt(i).toString();
                cordes1.addElement(aficion);
                int cord = Integer.parseInt(aficion);
//                System.out.println("coord:"+cord);
            }
            Vector cordes2 = new Vector();
            for (int i = 0; i < persona.getNumV().size(); i++) {
                String aficion = persona.getNumV().elementAt(i).toString();
                cordes2.addElement(aficion);
                int cord = Integer.parseInt(aficion);
//                System.out.println("coord:"+cord);
            }
            Vector colors = new Vector();
            for (int i = 0; i < persona.getColores().size(); i++) {
                String aficion = persona.getColores().elementAt(i).toString();
                colors.addElement(aficion);

//                System.out.println("coord:"+cord);
            }
            PintarDibujo screen = new PintarDibujo(midlet, cordes, cordes1, cordes2, colors, color);
//            screen.repaint();
            pantalla.setCurrent(screen);
            //pantalla.repaint();
            /* textBoxResultados.insert(nom, 0);
            textBoxResultados.insert(gen, textBoxResultados.size());
            textBoxResultados.insert(afs, textBoxResultados.size());

            display.setCurrent(textBoxResultados);*/

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /*
     * Puntero presionado
     */

    protected void pointerPressed(int x, int y) {
        iniciox = x;
        inicioy = y;

        System.out.println("Pointer pressed");
        System.out.println("------------------------");
        System.out.println(" inix" + iniciox + " iniy" + inicioy);
        coord1.addElement(String.valueOf(iniciox));
        coord1.addElement(String.valueOf(inicioy));
        if (cont != 0) {
            numV.addElement(String.valueOf(cont));
            cont = 0;
        }
    }

    /*
     * Movimiento de puntero
     */
    protected void pointerDragged(int x, int y) {
        actualx = x;
        actualy = y;
//        System.out.println("Pointer Dregged");

        System.out.println("actualx" + actualx + " actualy" + actualy);
        coord.addElement(String.valueOf(actualx));
        cont++;
        coord.addElement(String.valueOf(actualy));
        cont++;
        repaint();
    }

    public boolean getClearDisplay() {
        return clearDisplay;
    }

    public void setClearDisplay(boolean b) {
        cont = 0;
        coord.removeAllElements();
        coord1.removeAllElements();
        numV.removeAllElements();
        colores.removeAllElements();
        colorPuntero = 0;
        colores.addElement(String.valueOf(colorPuntero));
        clearDisplay = b;
    }
}
