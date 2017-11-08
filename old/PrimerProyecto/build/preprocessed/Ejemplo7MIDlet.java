//import com.sun.midp.dev.GraphicalInstaller;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

 

/**

 *

 * @author  insitu

 * @version

 */

public class Ejemplo7MIDlet extends MIDlet {

    Tablero t; 

    public void startApp() {
        t = new Tablero();
        Display.getDisplay(this).setCurrent(t);
    }
    public void pauseApp() {

    }
    public void destroyApp(boolean unconditional) { 

    }

    private class Tablero extends Canvas implements CommandListener{ 

        Triangulo t;
        Cuadrado c;
        
        Command cmdSalir;
        
        public Tablero(){
            //Creacion de triangulo
            t = new Triangulo((int)getWidth()/4, (int)getHeight()/2+10); 
            
            //Creacion de rectangulo
            c = new Cuadrado(getWidth()/4*3, getHeight()/2+10); 
            
                      
            cmdSalir = new Command("Salir", Command.EXIT, 1);
            addCommand(cmdSalir);
            setCommandListener(this);
        }
        
        protected void paint(Graphics g) { 
            g.setColor(255,255,255);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            
            Font fuente = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
            g.setFont(fuente);
            g.setColor(0);
            g.drawString("Texto inicial", (int)getWidth()/4, 10, Graphics.BASELINE|Graphics.HCENTER);
            
            t.dibujarFigura(g);
            c.dibujarFigura(g);
            
            
        }
        
        protected void keyPressed(int keyCode) { 
            
            switch(keyCode){
                case KEY_NUM2:
                    t.moverDerecha();
                    c.moverIzquierda();
                    repaint();
                    break;
                case KEY_NUM8:
                    break;
                case KEY_NUM4:
                    break;
                case KEY_NUM6:
                    break;
                case KEY_NUM1:
                    break;
                    
                    
            }
        }
        
        public void commandAction(Command command, Displayable displayable) {
            if(command == cmdSalir){
                destroyApp(true);
                notifyDestroyed();
            }
        }
    }
      
    private class Triangulo{

            int x1;
            int y1;
            int x2;
            int y2;
            int x3;
            int y3;
            
            int lado = 20;
            public Triangulo(int x, int y){
                x1 = x;
                y1 = y;      
                x2 =  x1+lado/2;
                y2 = y1+lado;
                x3 = x1-lado/2;
                y3 = y1+lado;
            }
            
        public void moverDerecha(){
            actualizarPuntos(2, 0, 2, 0, 2, 0);
        }
        public void moverIzquierda(){
            //Implementar
        }
        public void agrandarFigura(){
            //Implementar
        }
        public void achicarFigura(){
            //Implementar
        }
        
       private void actualizarPuntos(int dx1, int dy1, int dx2, int dy2, int dx3, int dy3){
           x1 += dx1;
           y1 += dy1;
           x2 += dx2;
           y2 += dy2;
           x3 += dx3;
           y3 += dy3;
       }

        public void dibujarFigura(Graphics g){
            g.setColor(255, 0, 0);
            g.fillTriangle(x1, y1, x2, y2, x3, y3);
        }

    }
    private class Cuadrado{
        int x1;
        int y1;
        int lado = 20;
        public Cuadrado(int x, int y){
            x1 = x;
            y1 = y;
        }

        public void moverIzquierda(){
            actualizarPuntos(-2, 0);
        }
        public void moverDerecha(){
            //Implementar
        }
        public void agrandarFigura(){
            //Implementar
        }
        public void achicarFigura(){
            //Implementar
        }
       private void actualizarPuntos(int dx1, int dy1){
           x1 += dx1;
           y1 += dy1;
       }

         public void dibujarFigura(Graphics g){
            g.setColor(0, 255, 0);
            g.fillRect(x1, y1, lado, lado);
        }

    }
  
}