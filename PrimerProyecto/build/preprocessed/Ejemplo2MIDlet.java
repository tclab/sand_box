import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Ejemplo2MIDlet extends MIDlet implements ItemStateListener, CommandListener{

    Display pantalla;
    Form formulario;
    TextField txtNokiaN97;
    TextField txtSonyC702;
    
    Command salir;
    Alert info;

    private static final float IVA = (float)0.16;
    
    StringItem text = new StringItem("Total con IVA ","");
    StringItem textsin = new StringItem("Total sin IVA ","");
    
    public Ejemplo2MIDlet(){
        pantalla = Display.getDisplay(this);
        formulario = new Form("");
        txtNokiaN97 = new TextField("Nokia N97","",70,TextField.NUMERIC);
        txtSonyC702 = new TextField("Sony Ericsson C702","",20,TextField.NUMERIC);
        
        
        salir = new Command("Salir",Command.EXIT,1);
        
        
        formulario.append(txtSonyC702);
        formulario.append(txtNokiaN97);
        formulario.append(textsin);
        formulario.append(text);

        
        formulario.addCommand(salir);
        
        
        formulario.setItemStateListener(this);
        formulario.setCommandListener(this);
    }
    public void startApp() {
        pantalla.setCurrent(formulario);
    }
    public void pauseApp() {
    }
    public void destroyApp(boolean unconditional) {
    }
    public void commandAction(Command c, Displayable d){
        if (c == salir){
            destroyApp(false);
            notifyDestroyed();
        }
    }
    
    private Alert setAlert(String msg){
        
        info = new Alert("Error ", msg, null, AlertType.INFO);
        info.setTimeout(5000);
        return info;
        
    }
    
    public void itemStateChanged(Item i){
        if (i == txtNokiaN97 || i == txtSonyC702) {
            String lim = txtNokiaN97.getString();
            String nar = txtSonyC702.getString();
            textsin.setText(Float.toString(total(lim, nar, false)));
            text.setText(Float.toString(total(lim, nar, true)));
            
            System.out.println("Evento detectado en el TextBox txt");
         
        }
    }

    private float total(String lim, String nar, boolean iva) {
        
        if(lim.equals("")){
            lim = "0";
        }
        if(nar.equals("")){
            nar = "0";
        }
        float total = Integer.parseInt(lim) + Integer.parseInt(nar);
        if(iva) total = total * (1+IVA); 
        return total;
    }
}