import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Ejemplo1MIDlet extends MIDlet implements CommandListener {

private Command exitCommand; // The exit command
private Display display; // Display for this MIDlet 
private Command backCommand;
private Command nextCommand;

private TextBox txtFlirtomatic;
private TextBox txtIChannel;

public Ejemplo1MIDlet() {
    display = Display.getDisplay(this);
    exitCommand = new Command("Exit", Command.EXIT, 1);
    backCommand = new Command("Atras", Command.BACK, 1);
    nextCommand = new Command("Siguiente", Command.OK, 1);
}

public void startApp() {
    txtIChannel = new TextBox("i-Channel","El operador Ntt Docomo lanzó su servicio de noticias i-Channel en el 2005",256,0);
    txtFlirtomatic = new TextBox("Flirtomatic","Servicio de citas disponible para celulares e internet", 200, TextField.ANY);
    
    txtIChannel.addCommand(exitCommand);
    txtIChannel.addCommand(nextCommand);
    
    txtFlirtomatic.addCommand(backCommand);
    txtFlirtomatic.addCommand(exitCommand);
    
    txtIChannel.setCommandListener(this);
    txtFlirtomatic.setCommandListener(this);
    
    
    display.setCurrent(txtIChannel);
}
// CommandAction code shown in the next slide

public void commandAction(Command c, Displayable s){
    if (c == exitCommand) {
      
        destroyApp(false);
        notifyDestroyed();
        
    }else if(c==nextCommand){
        display.setCurrent(txtFlirtomatic);
    }else if(c==backCommand){
        display.setCurrent(txtIChannel);
    }
}
public void pauseApp() { }
public void destroyApp( boolean conditional ) { }
}