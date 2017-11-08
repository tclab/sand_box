import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class ListaMultipleMIDlet extends MIDlet implements CommandListener {
    Display pantalla;
    List menu;
    Command salir, salvar;
    public ListaMultipleMIDlet(){
        pantalla = Display.getDisplay(this);
        salir = new Command("Salir",Command.EXIT,1);
        salvar = new Command("Salvar",Command.ITEM,1);
        
        
        menu = new List("Lista Multiple",List.MULTIPLE);
        /*
        menu.insert(menu.size(),"Opcion1",null);
        menu.insert(menu.size(),"Opcion3",null);
        menu.insert(10,"Opcion2",null);
        */
        
        menu.append("opcion1", null);
        menu.append("opcion2", null);
        menu.append("opcion3", null);
        
        menu.addCommand(salir);
        menu.addCommand(salvar);
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
            boolean seleccionados[] = new boolean[menu.size()];
            int opcionesSel = menu.getSelectedFlags(seleccionados);
            System.out.println(opcionesSel);
            int sels = seleccionados.length;
            for(int i = 0;i<sels;i++){
                if(seleccionados[i]){
                    System.out.println(menu.getString(i) + "  seleccionada");
                }else{
                    System.out.println(menu.getString(i) +  "  no seleccionada");
                }
            }
        }
        else{
            destroyApp(false);
            notifyDestroyed();
        }
    }
}