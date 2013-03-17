package src;


import java.util.Vector;
import javax.microedition.lcdui.*;

class PintarDibujo extends Canvas implements CommandListener {

    private Command salir;
    Vector coordenadas;
    Reto1 midlet;
    private int iniciox = 0;
    private int inicioy = 0;
    private int actualx = 0;
    private int actualy = 0;
    int cont = 0;
    int color;

    public PintarDibujo(Reto1 midlet, Vector coordenadas, int color) {
        this.midlet = midlet;
        this.coordenadas = coordenadas;
        this.color = color;

        salir = new Command("Atras", Command.EXIT, 1);

        addCommand(salir);

        setCommandListener(this);
    }

    protected void paint(Graphics g) {

        g.setColor(16777215);
        g.fillRect(0, 0, getWidth(), getHeight());

        iniciox = actualx = inicioy = actualy = 0;

        int i = 0;
        System.out.println(coordenadas.size());

        g.setColor(color);

        boolean esInicio = true;
        while (i < coordenadas.size()) {

            if (esInicio) {
                iniciox = Integer.parseInt(coordenadas.elementAt(i).toString());
                i++;

                inicioy = Integer.parseInt(coordenadas.elementAt(i).toString());
                i++;

                actualx = inicioy = Integer.parseInt(coordenadas.elementAt(i).toString());
                i++;

                actualy = inicioy = Integer.parseInt(coordenadas.elementAt(i).toString());
                i++;
                g.drawLine(iniciox, inicioy, actualx, actualy);

                iniciox = actualx;
                inicioy = actualy;

                esInicio = false;
            } else {
                //Preguntar si es inicio de nueva linea
                actualx = Integer.parseInt(coordenadas.elementAt(i).toString());
                i++;

                actualy = Integer.parseInt(coordenadas.elementAt(i).toString());
                i++;
                g.drawLine(iniciox, inicioy, actualx, actualy);

                iniciox = actualx;
                inicioy = actualy;
            }
        }
    }

    public void commandAction(Command c, Displayable d) {
        if (c == salir) {
            midlet.mostrarListaDibujos();
        }
    }
}
