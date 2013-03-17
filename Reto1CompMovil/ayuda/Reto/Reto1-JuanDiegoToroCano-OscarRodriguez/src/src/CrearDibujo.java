

package src;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.Vector;
import javax.microedition.lcdui.*;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;

class CrearDibujo extends Canvas implements CommandListener {

    private Command atras, atrasCanvas, limpiar, color, guardar;
    private Display pantalla;
    private List listaColores;
    private int colorPuntero;
    DibujoBO nombre = new DibujoBO("", null, 0);

    // Posicion inicial
    private int iniciox = 0;
    private int inicioy = 0;

    // Posicion actual
    private int actualx = 0;
    private int actualy = 0;

    int cont = 0;
    int contar = 0;

    private Reto1 midlet;
    private boolean limpiarPantalla = true;
    private Displayable pantallaActual;
    public static RecordStore rstore;
    private Vector dibujos;
    Vector coordenadas;

    int x = 255;
    int y = 255;
    int z = 255;
    boolean f = false;

    /*
     * Constructor
     */
    public CrearDibujo(Reto1 midlet) {
        this.midlet = midlet;
        coordenadas = new Vector();
        pantalla = midlet.getDisplay();

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
        getID();
    }

    protected void paint(Graphics g) {

        // Limpia el fondo
        if (limpiarPantalla) {
            g.setColor(16777215);
            g.fillRect(0, 0, getWidth(), getHeight());

            limpiarPantalla = false;

            iniciox = actualx = inicioy = actualy = 0;
            return;
        }

        // Color del lapiz
        g.setColor(colorPuntero);

        // Dibuja una linea
        g.drawLine(iniciox, inicioy, actualx, actualy);

        System.out.println(" inix" + iniciox + " iniy" + inicioy);
        System.out.println("actualx" + actualx + " actualy" + actualy);

        // El nuevo punto de inicio es la posicion actual.
        iniciox = actualx;
        inicioy = actualy;
    }

    /*
     * Eventos de comandos.
     */
    public void commandAction(Command c, Displayable d) {
        if (c == atras) {
            cont = 0;
            coordenadas.removeAllElements();
            pantalla.setCurrent(midlet.getDibujos());
        }
        else if (c == limpiar) {
            limpiarPantalla = true;
            coordenadas.removeAllElements();
            cont = 0;
            repaint();
        }
        else if (c == color) {

            pantallaActual = pantalla.getCurrent();
            pantalla.setCurrent(listaColores);
        }
        else if (c == atrasCanvas) {
            pantalla.setCurrent(pantallaActual);
        }
        else if (c == guardar) { //Guardar el dibujo
            nombre.setNombre("Dibujo" + contar);
            nombre.setColor(colorPuntero);
            nombre.setCoordenadas(coordenadas);
            try {
                System.out.println(coordenadas.size());
                PersistenciaDAO.guardarDatos(nombre);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            llenarListaDibujos();
            contar++;

            pantalla.setCurrent(midlet.getDibujos());
        }
        else if (c == listaColores.SELECT_COMMAND) { //Seleccionar color de trazo
            switch (listaColores.getSelectedIndex()) {
                case 0:
                    colorPuntero = 0;         //Negro
                    limpiarPantalla = true;
                    coordenadas.removeAllElements();
                    cont = 0;
                    repaint();
                    pantalla.setCurrent(pantallaActual);
                    break;
                case 1:
                    colorPuntero = 3394611;   //Verde
                    limpiarPantalla = true;
                    coordenadas.removeAllElements();
                    cont = 0;
                    repaint();
                    pantalla.setCurrent(pantallaActual);
                    break;
                case 2:
                    colorPuntero = 15147839;  //Rojo
                    limpiarPantalla = true;
                    coordenadas.removeAllElements();
                    cont = 0;
                    repaint();
                    pantalla.setCurrent(pantallaActual);
                    break;
                case 3:
                    colorPuntero = 13130703;  //Morado
                    limpiarPantalla = true;
                    coordenadas.removeAllElements();
                    cont = 0;
                    repaint();
                    pantalla.setCurrent(pantallaActual);
                    break;
                case 4:
                    colorPuntero = 16746536;  //Naranja
                    cont = 0;
                    coordenadas.removeAllElements();
                    pantalla.setCurrent(pantallaActual);
                    limpiarPantalla = true;
                    repaint();
                    break;
                case 5:
                    colorPuntero = 4986871;   //Azul
                    cont = 0;
                    coordenadas.removeAllElements();
                    pantalla.setCurrent(pantallaActual);
                    limpiarPantalla = true;
                    repaint();
                    break;
            }
        }
    }

    public void getID() {
        try {
            Vector vecDibujos = new Vector();
            rstore = RecordStore.openRecordStore("coordenadas", true);

            DibujoBO dibujo;
            RecordEnumeration re = rstore.enumerateRecords(null, null, true);

            while (re.hasPreviousElement()) {
                byte[] b = re.previousRecord();

                ByteArrayInputStream bais = new ByteArrayInputStream(b);
                DataInputStream dis = new DataInputStream(bais);

                String nombreDibujo = dis.readUTF();
                int colorDibujo = dis.readInt();
                int tamanoCoordenadas = dis.readInt();

                Vector coordenadasDibujo = new Vector(tamanoCoordenadas);
                for (int i = 0; i < tamanoCoordenadas; i++) {
                    coordenadasDibujo.addElement(dis.readUTF());
                }

                bais.reset();
                dis.reset();

                dibujo = new DibujoBO(nombreDibujo, coordenadasDibujo, colorDibujo);
                vecDibujos.addElement(dibujo);

                bais.close();
                dis.close();
            }

            rstore.closeRecordStore();
        } catch (Exception e) {
        }
    }

    public void llenarListaDibujos() {
        midlet.getDibujos().deleteAll();
        try {
            dibujos = PersistenciaDAO.leerDatos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (dibujos.isEmpty()) {
            Alert alerta = new Alert("No se leyo el dibujo correctamente");
            pantalla.setCurrent(alerta);
        } else {
            for (int i = 0; i < dibujos.size(); i++) {
                DibujoBO persona = (DibujoBO) dibujos.elementAt(i);
                midlet.getDibujos().append(persona.getNombre(), null);
            }
        }
    }

    public void verDibujo() {
        try {

            String p = midlet.getDibujos().getString(midlet.getDibujos().getSelectedIndex());
            System.out.println(p);

            //Trae los dibujos guardados
            Vector dibujosGuardados = PersistenciaDAO.leerDatos();

            DibujoBO dibujo = (DibujoBO) dibujosGuardados.elementAt(midlet.getDibujos().getSelectedIndex());
            System.out.println(dibujo.getNombre());

            int colorDibujo = dibujo.getColor();
            Vector coordenadasDibujo = new Vector();

            for (int i = 0; i < dibujo.getCoordenadas().size(); i++) {
                String coordenada = dibujo.getCoordenadas().elementAt(i).toString();
                coordenadasDibujo.addElement(coordenada);
            }

            PintarDibujo dibujoSeleccionado = new PintarDibujo(midlet, coordenadasDibujo, colorDibujo);
            
            pantalla.setCurrent(dibujoSeleccionado);
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

        coordenadas.addElement(String.valueOf(iniciox));
        coordenadas.addElement(String.valueOf(inicioy));

        cont++;
    }

    /*
     * Movimiento de puntero
     */
    protected void pointerDragged(int x, int y) {
        actualx = x;
        actualy = y;

        coordenadas.addElement(String.valueOf(actualx));
        coordenadas.addElement(String.valueOf(actualy));

        repaint();
    }

    public boolean getClearDisplay() {
        return limpiarPantalla;
    }

    public void setClearDisplay(boolean b) {
        cont = 0;
        coordenadas.removeAllElements();
        limpiarPantalla = b;
    }
}
