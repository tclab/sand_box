package Reto;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Reto1 extends MIDlet implements CommandListener{
    
  private Display  display;         // El display
  private CanvasDibujo canvas;      // El canvas
  private List dibujos;             // Lista de dibujos
  private Command salir, nuevo;     // Comandos

  /*
   * Constuctor
   */
  public Reto1(){
    display = Display.getDisplay(this);
    canvas  = new CanvasDibujo(this);

    salir = new Command("Salir", Command.EXIT, 1);
    nuevo = new Command("Nuevo", Command.SCREEN, 1);

    dibujos = new List("Dibujos guardados", List.IMPLICIT);
    dibujos.addCommand(salir);
    dibujos.addCommand(nuevo);
    dibujos.setCommandListener(this);
  }

  protected void startApp(){
    display.setCurrent( dibujos );
  }

  protected void pauseApp() { }

  protected void destroyApp( boolean unconditional ) { }

//  public void exitMIDlet(){
//    destroyApp(true);
//    notifyDestroyed();
//  }

  public void commandAction(Command c, Displayable d) {
      if (c == nuevo){
          canvas.setClearDisplay(true);
          display.setCurrent(canvas);

      }else if (c == salir){
          destroyApp(false);
          notifyDestroyed();
      }
  }

  /*
   * Getters
   */
  public Display getDisplay(){
      return display;
  }

  public CanvasDibujo getCanvas(){
      return canvas;
  }

  public List getDibujos(){
      //TODO: Traer los dibujos guardados
      return dibujos;
  }
}










/**
 * Clase CanvasDibujo; maneja el evento pointer
 */
class CanvasDibujo extends Canvas implements CommandListener{

    private Command atras, atrasCanvas, limpiar, color, guardar;
    private Display pantalla;

    private List listaColores;
    private int colorPuntero;

    // Posicion inicial
    private int iniciox = 0;
    private int inicioy = 0;

    // Posición actual
    private int actualx = 0;
    private int actualy = 0;

    private Reto1 midlet;
    private boolean clearDisplay = true;
    private Displayable pantallaActual;

    /*
    * Constructor
    */
    public CanvasDibujo(Reto1 midlet){
    this.midlet = midlet;

    pantalla =  midlet.getDisplay();

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
  }
  
  protected void paint(Graphics g){
      
    // Limpia el fondo
    if (clearDisplay){
      g.setColor(255, 255, 255);
      g.fillRect(0, 0, getWidth(), getHeight());

      clearDisplay = false;
      iniciox = actualx = inicioy = actualy = 0;
      
      return;
    }

    // Color del lapiz
    g.setColor(colorPuntero);

    // Dibuja una linea
    g.drawLine(iniciox, inicioy, actualx, actualy);

    // El nuevo punto de inicio es la posicion actual.
    iniciox = actualx;
    inicioy = actualy;
  }

  /*
  * Eventos de comandos.
  */
  public void commandAction(Command c, Displayable d){
    if (c == atras){
        pantalla.setCurrent(midlet.getDibujos());
    }
    else if (c == limpiar){
      clearDisplay = true;
      repaint();
    }
    else if (c == color){
        pantallaActual = pantalla.getCurrent();
        pantalla.setCurrent(listaColores);
    }
    else if(c == atrasCanvas){
        pantalla.setCurrent(pantallaActual);
    }
    else if(c == guardar){
        //TODO: Guardar dibujo
        pantalla.setCurrent(midlet.getDibujos());
    }
    else if(c == listaColores.SELECT_COMMAND){
        switch(listaColores.getSelectedIndex()){
            case 0:
                colorPuntero = 0;         //Negro
                pantalla.setCurrent(pantallaActual);
                clearDisplay = true;
                repaint();
                break;
            case 1:
                colorPuntero = 3394611;   //Verde
                pantalla.setCurrent(pantallaActual);
                clearDisplay = true;
                repaint();
                break;
            case 2:
                colorPuntero = 15147839;  //Rojo
                pantalla.setCurrent(pantallaActual);
                clearDisplay = true;
                repaint();
                break;
            case 3:
                colorPuntero = 13130703;  //Morado
                pantalla.setCurrent(pantallaActual);
                clearDisplay = true;
                repaint();
                break;
            case 4:
                colorPuntero = 16746536;  //Naranja
                pantalla.setCurrent(pantallaActual);
                clearDisplay = true;
                repaint();
                break;
            case 5:
                colorPuntero = 4986871;   //Azul
                pantalla.setCurrent(pantallaActual);
                clearDisplay = true;
                repaint();
                break;
        }
    }
  }

 /*
  * Puntero presionado
  */
  protected void pointerPressed(int x, int y){
    iniciox = x;
    inicioy = y;
  }

 /*
  * Movimiento de puntero
  */
  protected void pointerDragged(int x, int y){
    actualx = x;
    actualy = y;
    repaint();
  }


  public boolean getClearDisplay(){
      return clearDisplay;
  }

  public void setClearDisplay(boolean b){
      clearDisplay = b;
  }
}