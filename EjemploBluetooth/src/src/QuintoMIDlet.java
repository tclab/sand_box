package src;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author juaninsitu
 */
public class QuintoMIDlet extends MIDlet implements CommandListener {

    private boolean midletPaused = false;
    private boolean enviando = true;
    private ComunicacionBt commBt;
    private Persona persona;
    private Thread hiloEnvio;

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Command recibirCommand;
    private Command okCommand;
    private Command exitCommand;
    private Form form;
    private TextField textField1;
    private TextField textField;
    //</editor-fold>//GEN-END:|fields|0|
    private TextBox txtBoxResultados;
    private Command cmdSi;
    private Command cmdNo;

    //</editor-fold>
    /**
     * The SegundoMIDlet constructor.
     */
    public QuintoMIDlet() {
        cmdSi = new Command("SI", Command.OK, 1);
        cmdNo = new Command("NO", Command.BACK, 1);
        persona = new Persona();

    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, getForm());//GEN-LINE:|3-startMIDlet|1|3-postAction
    // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
    // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|24-getter|0|24-preInit
    /**
     * Returns an initiliazed instance of okCommand component.
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {//GEN-END:|24-getter|0|24-preInit
            // write pre-init user code here
            okCommand = new Command("Enviar", Command.OK, 0);//GEN-LINE:|24-getter|1|24-postInit
        // write post-init user code here
        }//GEN-BEGIN:|24-getter|2|
        return okCommand;
    }
    //</editor-fold>//GEN-END:|24-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|26-getter|0|26-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|26-getter|0|26-preInit
            // write pre-init user code here
            exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|26-getter|1|26-postInit
        // write post-init user code here
        }//GEN-BEGIN:|26-getter|2|
        return exitCommand;
    }
    //</editor-fold>//GEN-END:|26-getter|2|



    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == form) {//GEN-BEGIN:|7-commandAction|1|27-preAction
            if (command == exitCommand) {//GEN-END:|7-commandAction|1|27-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|2|27-postAction
                // write post-action user code here
            } else if (command == okCommand) {//GEN-LINE:|7-commandAction|3|25-preAction
                // write pre-action user code here
                enviarPersona();//GEN-LINE:|7-commandAction|4|25-postAction
            // write post-action user code here
            } else if (command == recibirCommand) {//GEN-LINE:|7-commandAction|5|53-preAction
                // write pre-action user code here
                recibirPersona();//GEN-LINE:|7-commandAction|6|53-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|7|7-postCommandAction
        }//GEN-END:|7-commandAction|7|7-postCommandAction
        else if (displayable == txtBoxResultados) {
            if (command == cmdSi) {
                commBt.enviarPersonaBt(persona);
                commBt.desconectar();
                getDisplay().setCurrent(form);
            } else if (command == cmdNo) {
                commBt.desconectar();
                getDisplay().setCurrent(form);
            }
        }

    }//GEN-BEGIN:|7-commandAction|8|
    //</editor-fold>//GEN-END:|7-commandAction|8|


    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: form ">//GEN-BEGIN:|21-getter|0|21-preInit
    /**
     * Returns an initiliazed instance of form component.
     * @return the initialized component instance
     */
    public Form getForm() {
        if (form == null) {//GEN-END:|21-getter|0|21-preInit
            // write pre-init user code here
            form = new Form("form", new Item[] { getTextField(), getTextField1() });//GEN-BEGIN:|21-getter|1|21-postInit
            form.addCommand(getOkCommand());
            form.addCommand(getExitCommand());
            form.addCommand(getRecibirCommand());
            form.setCommandListener(this);//GEN-END:|21-getter|1|21-postInit
        // write post-init user code here
        }//GEN-BEGIN:|21-getter|2|
        return form;
    }
    //</editor-fold>//GEN-END:|21-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField ">//GEN-BEGIN:|22-getter|0|22-preInit
    /**
     * Returns an initiliazed instance of textField component.
     * @return the initialized component instance
     */
    public TextField getTextField() {
        if (textField == null) {//GEN-END:|22-getter|0|22-preInit
            // write pre-init user code here
            textField = new TextField("Nombre", persona.getNombre(), 32, TextField.ANY);//GEN-LINE:|22-getter|1|22-postInit
        // write post-init user code here
        }//GEN-BEGIN:|22-getter|2|
        return textField;
    }
    //</editor-fold>//GEN-END:|22-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField1 ">//GEN-BEGIN:|23-getter|0|23-preInit
    /**
     * Returns an initiliazed instance of textField1 component.
     * @return the initialized component instance
     */
    public TextField getTextField1() {
        if (textField1 == null) {//GEN-END:|23-getter|0|23-preInit
            // write pre-init user code here
            textField1 = new TextField("Apellidos", persona.getApellido(), 32, TextField.ANY);//GEN-LINE:|23-getter|1|23-postInit
        // write post-init user code here
        }//GEN-BEGIN:|23-getter|2|
        return textField1;
    }
    //</editor-fold>//GEN-END:|23-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: enviarPersona ">//GEN-BEGIN:|40-entry|0|41-preAction
    /**
     * Performs an action assigned to the enviarPersona entry-point.
     */
    public void enviarPersona() {//GEN-END:|40-entry|0|41-preAction
        persona.setNombre(textField.getString());
        persona.setApellido(textField1.getString());
        enviando = true;
        commBt = new ComunicacionBt(this);

        getDisplay().setCurrent(commBt);

//GEN-LINE:|40-entry|1|41-postAction
    // write post-action user code here
    }//GEN-BEGIN:|40-entry|2|
    //</editor-fold>//GEN-END:|40-entry|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: recibirCommand ">//GEN-BEGIN:|52-getter|0|52-preInit
    /**
     * Returns an initiliazed instance of recibirCommand component.
     * @return the initialized component instance
     */
    public Command getRecibirCommand() {
        if (recibirCommand == null) {//GEN-END:|52-getter|0|52-preInit
            // write pre-init user code here
            recibirCommand = new Command("Recibir", Command.SCREEN, 0);//GEN-LINE:|52-getter|1|52-postInit
        // write post-init user code here
        }//GEN-BEGIN:|52-getter|2|
        return recibirCommand;
    }
    //</editor-fold>//GEN-END:|52-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: recibirPersona ">//GEN-BEGIN:|54-entry|0|55-preAction
    /**
     * Performs an action assigned to the recibirPersona entry-point.
     */
    public void recibirPersona() {//GEN-END:|54-entry|0|55-preAction

        commBt = new ComunicacionBt(this);
        enviando = false;
        getDisplay().setCurrent(commBt);

//GEN-LINE:|54-entry|1|55-postAction
    // write post-action user code here
    }//GEN-BEGIN:|54-entry|2|
    //</editor-fold>//GEN-END:|54-entry|2|

    public boolean guardarPersona(Persona persona) {
        getTextField().setString(persona.nombre);
        getTextField1().setString(persona.apellidos);
        getDisplay().setCurrent(form);
        return true;
    }

    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable(null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet();
        } else {
            initialize();
            startMIDlet();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }

    public void listoConexion() {

        if (enviando) {
            System.out.println("Emulador 2 listo para enviar");
            txtBoxResultados = new TextBox("Seguro que desea enviar contacto ?", "", 300, TextField.ANY);
            String resul = "Nombre:  " + persona.getNombre() + "\n" +
                    "genero:  " + persona.getApellido() + "\n";
            txtBoxResultados.setString(resul);
            txtBoxResultados.addCommand(cmdSi);
            txtBoxResultados.addCommand(cmdNo);
            txtBoxResultados.setCommandListener(this);
            getDisplay().setCurrent(txtBoxResultados);

        } else {
            commBt.recibirBt();
        }
    }
}
