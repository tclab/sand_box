import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Ejemplo3MIDlet extends MIDlet implements
            CommandListener, ItemStateListener {
                
    Display pantalla;
    Form formulario;
    
    Gauge temp;
    
    Command salir;
    
    public Ejemplo3MIDlet(){
        pantalla = Display.getDisplay(this);
        formulario = new Form("Temperatura");
        
        temp = new Gauge("",true,15,0);
        temp.setLabel("Min");
        
        salir = new
        Command("Salir",Command.EXIT,1);
        formulario.append(temp);
        
        formulario.addCommand(salir);
        formulario.setCommandListener(this);
        formulario.setItemStateListener(this);
        
    }
    public void startApp() {
        pantalla.setCurrent(formulario);
    }
    public void pauseApp() {
    }
    public void destroyApp(boolean unconditional) {
    }
    public void commandAction(Command c,
    Displayable d){
        if (c == salir){
            destroyApp(false);
            notifyDestroyed();
        }
    }
    public void itemStateChanged(Item i){
        if (i == temp){
            if (temp.getValue() == 0)
                temp.setLabel("Min");
            else if (temp.getValue() == 15) temp.setLabel("Max");
            else temp.setLabel(temp.getValue()+" º");
        }
    }
}