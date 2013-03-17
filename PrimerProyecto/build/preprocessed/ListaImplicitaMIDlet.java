import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class ListaImplicitaMIDlet extends MIDlet implements CommandListener{
    
    Command atras, salir; //Declaracion de Variables
    Display pantalla;
    List menu;
    Form formu1, formu2, formu3;
    
    public ListaImplicitaMIDlet(){
    
        pantalla = Display.getDisplay(this); //Creacion de pantallas
        menu = new List("Menú",List.IMPLICIT);
        
        menu.insert(0,"Opcion3",null);
        menu.insert(0,"Opcion2",null);
        menu.insert(0,"Opcion1",null);
        
        
        atras = new Command("Atras",Command.BACK,1);
        salir = new Command("Salir",Command.EXIT,1);
        
        menu.addCommand(salir);
        
        formu1 = new Form("Formulario 1");
        TextField t = new TextField("Titulo", "Texto Inicial", 20, TextField.ANY);
        formu1.append(t);
        
        formu2 = new Form("Formulario 2");
        formu3 = new Form("Formulario 3");
        formu1.addCommand(atras);
        formu2.addCommand(atras);
        formu3.addCommand(atras);
        
        menu.setCommandListener(this);
        formu1.setCommandListener(this);
        formu2.setCommandListener(this);
        formu3.setCommandListener(this);
    }
    public void startApp() {
        pantalla.setCurrent(menu); // Pongo el menu en pantalla
    }
    public void pauseApp() {
    }
    public void destroyApp(boolean unconditional) {
    }
    public void commandAction(Command c, Displayable d){
        if (c == menu.SELECT_COMMAND){ // Si selecciono
            switch(menu.getSelectedIndex()){ //opcion del menu
                case 0:{ pantalla.setCurrent(formu1);break;}
                case 1:{ pantalla.setCurrent(formu2);break;}
                case 2:{ pantalla.setCurrent(formu3);break;}
            }
        }
        else if (c == atras){ //Selecciono comando “Atrás”
            pantalla.setCurrent(menu);
        }
        else if (c == salir){ // Selecciono salir de la aplicacion
            destroyApp(false);
            notifyDestroyed();
        }
    }
}