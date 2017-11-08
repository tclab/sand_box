package rms;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;


/**
 * @author juaninsitu
 */
public class SegundoMIDlet extends MIDlet implements CommandListener {

    private boolean midletPaused = false;
    
    private ManejadorRMS mrms;
    private Persona persona;

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Command okCommand;
    private Command exitCommand1;
    private Command exitCommand;
    private Command okCommand1;
    private Form form;
    private TextField textField2;
    private TextField textField1;
    private TextField textField;
    private Alert alert;
    //</editor-fold>//GEN-END:|fields|0|

    /**
     * The SegundoMIDlet constructor.
     */
    public SegundoMIDlet() {
        mrms = new ManejadorRMS();
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
        cargarPersona();//GEN-LINE:|3-startMIDlet|1|3-postAction
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
            okCommand = new Command("Ok", Command.OK, 0);//GEN-LINE:|24-getter|1|24-postInit
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
                guardarPersona();//GEN-LINE:|7-commandAction|4|25-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|5|7-postCommandAction
        }//GEN-END:|7-commandAction|5|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|6|
    //</editor-fold>//GEN-END:|7-commandAction|6|


    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: form ">//GEN-BEGIN:|21-getter|0|21-preInit
    /**
     * Returns an initiliazed instance of form component.
     * @return the initialized component instance
     */
    public Form getForm() {
        if (form == null) {//GEN-END:|21-getter|0|21-preInit
            // write pre-init user code here
            form = new Form("form", new Item[] { getTextField(), getTextField1(), getTextField2() });//GEN-BEGIN:|21-getter|1|21-postInit
            form.addCommand(getOkCommand());
            form.addCommand(getExitCommand());
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

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: alert ">//GEN-BEGIN:|38-getter|0|38-preInit
    /**
     * Returns an initiliazed instance of alert component.
     * @return the initialized component instance
     */
    public Alert getAlert() {
        if (alert == null) {//GEN-END:|38-getter|0|38-preInit
            // write pre-init user code here
            alert = new Alert("alert", "Persona guardada", null, null);//GEN-BEGIN:|38-getter|1|38-postInit
            alert.setTimeout(Alert.FOREVER);//GEN-END:|38-getter|1|38-postInit
            // write post-init user code here
        }//GEN-BEGIN:|38-getter|2|
        return alert;
    }
    //</editor-fold>//GEN-END:|38-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: guardarPersona ">//GEN-BEGIN:|40-entry|0|41-preAction
    /**
     * Performs an action assigned to the guardarPersona entry-point.
     */
    public void guardarPersona() {//GEN-END:|40-entry|0|41-preAction
        persona.setNombre(textField.getString());
        persona.setApellido(textField1.getString());
        mrms.guardarPersona(persona);
        switchDisplayable(null, getAlert());//GEN-LINE:|40-entry|1|41-postAction
        // write post-action user code here
    }//GEN-BEGIN:|40-entry|2|
    //</editor-fold>//GEN-END:|40-entry|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: cargarPersona ">//GEN-BEGIN:|46-entry|0|47-preAction
    /**
     * Performs an action assigned to the cargarPersona entry-point.
     */
    public void cargarPersona() {//GEN-END:|46-entry|0|47-preAction
        mrms.leerRegistroPersona(persona);
        switchDisplayable(null, getForm());//GEN-LINE:|46-entry|1|47-postAction
        // write post-action user code here
    }//GEN-BEGIN:|46-entry|2|
    //</editor-fold>//GEN-END:|46-entry|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField2 ">//GEN-BEGIN:|51-getter|0|51-preInit
    /**
     * Returns an initiliazed instance of textField2 component.
     * @return the initialized component instance
     */
    public TextField getTextField2() {
        if (textField2 == null) {//GEN-END:|51-getter|0|51-preInit
            // write pre-init user code here
            textField2 = new TextField("jo", null, 32, TextField.ANY);//GEN-LINE:|51-getter|1|51-postInit
            // write post-init user code here
        }//GEN-BEGIN:|51-getter|2|
        return textField2;
    }
    //</editor-fold>//GEN-END:|51-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand1 ">//GEN-BEGIN:|56-getter|0|56-preInit
    /**
     * Returns an initiliazed instance of okCommand1 component.
     * @return the initialized component instance
     */
    public Command getOkCommand1() {
        if (okCommand1 == null) {//GEN-END:|56-getter|0|56-preInit
            // write pre-init user code here
            okCommand1 = new Command("Ok", Command.OK, 0);//GEN-LINE:|56-getter|1|56-postInit
            // write post-init user code here
        }//GEN-BEGIN:|56-getter|2|
        return okCommand1;
    }
    //</editor-fold>//GEN-END:|56-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand1 ">//GEN-BEGIN:|58-getter|0|58-preInit
    /**
     * Returns an initiliazed instance of exitCommand1 component.
     * @return the initialized component instance
     */
    public Command getExitCommand1() {
        if (exitCommand1 == null) {//GEN-END:|58-getter|0|58-preInit
            // write pre-init user code here
            exitCommand1 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|58-getter|1|58-postInit
            // write post-init user code here
        }//GEN-BEGIN:|58-getter|2|
        return exitCommand1;
    }
    //</editor-fold>//GEN-END:|58-getter|2|







    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay () {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable (null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet ();
        } else {
            initialize ();
            startMIDlet ();
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

}
