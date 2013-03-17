import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class ListaExclusivaMIDlet extends MIDlet implements CommandListener {
    Display pantalla;
    Command salir, salvar;
    List menu;
    public ListaExclusivaMIDlet(){
        pantalla = Display.getDisplay(this);
        salir = new Command("Salir",Command.EXIT,1);
        salvar = new Command("Salvar",Command.ITEM,1);
        
        String opciones[] = {"Opcion1","Opcion2","Opcion3"};
        
        menu = new List("Lista exclusiva",List.EXCLUSIVE,opciones,null);
        
        menu.addCommand(salvar);
        menu.addCommand(salir);
        menu.setCommandListener(this);
    }
    public void startApp() {
        pantalla.setCurrent(menu);
    }
    public void pauseApp() {
    }
    public void destroyApp(boolean unconditional) {
    }
    public void commandAction(Command c, Displayable d){
        
        if (c == salvar){
            int opcionelegida = menu.getSelectedIndex();
            //salvar opciones en memoria persistente.
            System.out.println("Opcion elegida nº"+(opcionelegida+1));
        }
        else{
            destroyApp(false);
            notifyDestroyed();
        }
    }
}