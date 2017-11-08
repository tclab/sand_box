package dia1;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author juan
 */
public class EjemplolMIDlet extends MIDlet implements CommandListener {

    private boolean midletPaused = false;

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private TextBox Pagina1;
    private TextBox Pagina2;
    private Form Nuevo;
    private TextField textField;
    private TextField textField1;
    private TextBox textBox;
    private Command exitCommand;
    private Command exitCommand1;
    private Command okCommand;
    private Command backCommand;
    private Command screenCommand;
    private Command screenCommand1;
    private Command backCommand1;
    private Command exitCommand2;
    private Command okCommand1;
    private Command exitCommand3;
    private Command backCommand2;
    //</editor-fold>//GEN-END:|fields|0|

    /**
     * The EjemplolMIDlet constructor.
     */
    public EjemplolMIDlet() {
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
        switchDisplayable(null, getPagina1());//GEN-LINE:|3-startMIDlet|1|3-postAction
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

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == Nuevo) {//GEN-BEGIN:|7-commandAction|1|40-preAction
            if (command == backCommand1) {//GEN-END:|7-commandAction|1|40-preAction
                // write pre-action user code here
                switchDisplayable(null, getPagina1());//GEN-LINE:|7-commandAction|2|40-postAction
                // write post-action user code here
            } else if (command == exitCommand2) {//GEN-LINE:|7-commandAction|3|38-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|4|38-postAction
                // write post-action user code here
            } else if (command == okCommand1) {//GEN-LINE:|7-commandAction|5|42-preAction
                // write pre-action user code here
                switchDisplayable(null, getTextBox());//GEN-LINE:|7-commandAction|6|42-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|7|18-preAction
        } else if (displayable == Pagina1) {
            if (command == exitCommand) {//GEN-END:|7-commandAction|7|18-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|8|18-postAction
                // write post-action user code here
            } else if (command == okCommand) {//GEN-LINE:|7-commandAction|9|22-preAction
                // write pre-action user code here
                switchDisplayable(null, getPagina2());//GEN-LINE:|7-commandAction|10|22-postAction
                // write post-action user code here
            } else if (command == screenCommand) {//GEN-LINE:|7-commandAction|11|30-preAction
                // write pre-action user code here
                switchDisplayable(null, getNuevo());//GEN-LINE:|7-commandAction|12|30-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|13|24-preAction
        } else if (displayable == Pagina2) {
            if (command == backCommand) {//GEN-END:|7-commandAction|13|24-preAction
                // write pre-action user code here
                switchDisplayable(null, getPagina1());//GEN-LINE:|7-commandAction|14|24-postAction
                // write post-action user code here
            } else if (command == exitCommand1) {//GEN-LINE:|7-commandAction|15|20-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|16|20-postAction
                // write post-action user code here
            } else if (command == screenCommand1) {//GEN-LINE:|7-commandAction|17|32-preAction
                // write pre-action user code here
                switchDisplayable(null, getNuevo());//GEN-LINE:|7-commandAction|18|32-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|19|62-preAction
        } else if (displayable == textBox) {
            if (command == backCommand2) {//GEN-END:|7-commandAction|19|62-preAction
                // write pre-action user code here
                switchDisplayable(null, getNuevo());//GEN-LINE:|7-commandAction|20|62-postAction
                // write post-action user code here
            } else if (command == exitCommand3) {//GEN-LINE:|7-commandAction|21|64-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|22|64-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|23|7-postCommandAction
        }//GEN-END:|7-commandAction|23|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|24|
    //</editor-fold>//GEN-END:|7-commandAction|24|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Pagina1 ">//GEN-BEGIN:|14-getter|0|14-preInit
    /**
     * Returns an initiliazed instance of Pagina1 component.
     * @return the initialized component instance
     */
    public TextBox getPagina1() {
        if (Pagina1 == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
            Pagina1 = new TextBox("Pagina1", "Texto pagina 1\n", 100, TextField.ANY | TextField.UNEDITABLE);//GEN-BEGIN:|14-getter|1|14-postInit
            Pagina1.addCommand(getExitCommand());
            Pagina1.addCommand(getOkCommand());
            Pagina1.addCommand(getScreenCommand());
            Pagina1.setCommandListener(this);//GEN-END:|14-getter|1|14-postInit
            // write post-init user code here
        }//GEN-BEGIN:|14-getter|2|
        return Pagina1;
    }
    //</editor-fold>//GEN-END:|14-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Pagina2 ">//GEN-BEGIN:|16-getter|0|16-preInit
    /**
     * Returns an initiliazed instance of Pagina2 component.
     * @return the initialized component instance
     */
    public TextBox getPagina2() {
        if (Pagina2 == null) {//GEN-END:|16-getter|0|16-preInit
            // write pre-init user code here
            Pagina2 = new TextBox("Pagina2", "Texto de la pagina 2", 100, TextField.ANY | TextField.UNEDITABLE);//GEN-BEGIN:|16-getter|1|16-postInit
            Pagina2.addCommand(getExitCommand1());
            Pagina2.addCommand(getBackCommand());
            Pagina2.addCommand(getScreenCommand1());
            Pagina2.setCommandListener(this);//GEN-END:|16-getter|1|16-postInit
            // write post-init user code here
        }//GEN-BEGIN:|16-getter|2|
        return Pagina2;
    }
    //</editor-fold>//GEN-END:|16-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|17-getter|0|17-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|17-getter|0|17-preInit
            // write pre-init user code here
            exitCommand = new Command("Salir", Command.EXIT, 0);//GEN-LINE:|17-getter|1|17-postInit
            // write post-init user code here
        }//GEN-BEGIN:|17-getter|2|
        return exitCommand;
    }
    //</editor-fold>//GEN-END:|17-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand1 ">//GEN-BEGIN:|19-getter|0|19-preInit
    /**
     * Returns an initiliazed instance of exitCommand1 component.
     * @return the initialized component instance
     */
    public Command getExitCommand1() {
        if (exitCommand1 == null) {//GEN-END:|19-getter|0|19-preInit
            // write pre-init user code here
            exitCommand1 = new Command("Salir", Command.EXIT, 0);//GEN-LINE:|19-getter|1|19-postInit
            // write post-init user code here
        }//GEN-BEGIN:|19-getter|2|
        return exitCommand1;
    }
    //</editor-fold>//GEN-END:|19-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|21-getter|0|21-preInit
    /**
     * Returns an initiliazed instance of okCommand component.
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {//GEN-END:|21-getter|0|21-preInit
            // write pre-init user code here
            okCommand = new Command("Siguiente", Command.OK, 0);//GEN-LINE:|21-getter|1|21-postInit
            // write post-init user code here
        }//GEN-BEGIN:|21-getter|2|
        return okCommand;
    }
    //</editor-fold>//GEN-END:|21-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand ">//GEN-BEGIN:|23-getter|0|23-preInit
    /**
     * Returns an initiliazed instance of backCommand component.
     * @return the initialized component instance
     */
    public Command getBackCommand() {
        if (backCommand == null) {//GEN-END:|23-getter|0|23-preInit
            // write pre-init user code here
            backCommand = new Command("Atras", Command.BACK, 0);//GEN-LINE:|23-getter|1|23-postInit
            // write post-init user code here
        }//GEN-BEGIN:|23-getter|2|
        return backCommand;
    }
    //</editor-fold>//GEN-END:|23-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Nuevo ">//GEN-BEGIN:|34-getter|0|34-preInit
    /**
     * Returns an initiliazed instance of Nuevo component.
     * @return the initialized component instance
     */
    public Form getNuevo() {
        if (Nuevo == null) {//GEN-END:|34-getter|0|34-preInit
            // write pre-init user code here
            Nuevo = new Form("form", new Item[] { getTextField(), getTextField1() });//GEN-BEGIN:|34-getter|1|34-postInit
            Nuevo.addCommand(getExitCommand2());
            Nuevo.addCommand(getBackCommand1());
            Nuevo.addCommand(getOkCommand1());
            Nuevo.setCommandListener(this);//GEN-END:|34-getter|1|34-postInit
            // write post-init user code here
        }//GEN-BEGIN:|34-getter|2|
        return Nuevo;
    }
    //</editor-fold>//GEN-END:|34-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField ">//GEN-BEGIN:|51-getter|0|51-preInit
    /**
     * Returns an initiliazed instance of textField component.
     * @return the initialized component instance
     */
    public TextField getTextField() {
        if (textField == null) {//GEN-END:|51-getter|0|51-preInit
            // write pre-init user code here
            textField = new TextField("Titulo", null, 32, TextField.ANY);//GEN-LINE:|51-getter|1|51-postInit
            // write post-init user code here
        }//GEN-BEGIN:|51-getter|2|
        return textField;
    }
    //</editor-fold>//GEN-END:|51-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField1 ">//GEN-BEGIN:|52-getter|0|52-preInit
    /**
     * Returns an initiliazed instance of textField1 component.
     * @return the initialized component instance
     */
    public TextField getTextField1() {
        if (textField1 == null) {//GEN-END:|52-getter|0|52-preInit
            // write pre-init user code here
            textField1 = new TextField("Texto", null, 32, TextField.ANY);//GEN-LINE:|52-getter|1|52-postInit
            // write post-init user code here
        }//GEN-BEGIN:|52-getter|2|
        return textField1;
    }
    //</editor-fold>//GEN-END:|52-getter|2|





    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand ">//GEN-BEGIN:|29-getter|0|29-preInit
    /**
     * Returns an initiliazed instance of screenCommand component.
     * @return the initialized component instance
     */
    public Command getScreenCommand() {
        if (screenCommand == null) {//GEN-END:|29-getter|0|29-preInit
            // write pre-init user code here
            screenCommand = new Command("Nuevo", Command.SCREEN, 0);//GEN-LINE:|29-getter|1|29-postInit
            // write post-init user code here
        }//GEN-BEGIN:|29-getter|2|
        return screenCommand;
    }
    //</editor-fold>//GEN-END:|29-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand1 ">//GEN-BEGIN:|31-getter|0|31-preInit
    /**
     * Returns an initiliazed instance of screenCommand1 component.
     * @return the initialized component instance
     */
    public Command getScreenCommand1() {
        if (screenCommand1 == null) {//GEN-END:|31-getter|0|31-preInit
            // write pre-init user code here
            screenCommand1 = new Command("Nuevo", Command.SCREEN, 0);//GEN-LINE:|31-getter|1|31-postInit
            // write post-init user code here
        }//GEN-BEGIN:|31-getter|2|
        return screenCommand1;
    }
    //</editor-fold>//GEN-END:|31-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand2 ">//GEN-BEGIN:|37-getter|0|37-preInit
    /**
     * Returns an initiliazed instance of exitCommand2 component.
     * @return the initialized component instance
     */
    public Command getExitCommand2() {
        if (exitCommand2 == null) {//GEN-END:|37-getter|0|37-preInit
            // write pre-init user code here
            exitCommand2 = new Command("Salir", Command.EXIT, 0);//GEN-LINE:|37-getter|1|37-postInit
            // write post-init user code here
        }//GEN-BEGIN:|37-getter|2|
        return exitCommand2;
    }
    //</editor-fold>//GEN-END:|37-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand1 ">//GEN-BEGIN:|39-getter|0|39-preInit
    /**
     * Returns an initiliazed instance of backCommand1 component.
     * @return the initialized component instance
     */
    public Command getBackCommand1() {
        if (backCommand1 == null) {//GEN-END:|39-getter|0|39-preInit
            // write pre-init user code here
            backCommand1 = new Command("Pag1", Command.BACK, 0);//GEN-LINE:|39-getter|1|39-postInit
            // write post-init user code here
        }//GEN-BEGIN:|39-getter|2|
        return backCommand1;
    }
    //</editor-fold>//GEN-END:|39-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand1 ">//GEN-BEGIN:|41-getter|0|41-preInit
    /**
     * Returns an initiliazed instance of okCommand1 component.
     * @return the initialized component instance
     */
    public Command getOkCommand1() {
        if (okCommand1 == null) {//GEN-END:|41-getter|0|41-preInit
            // write pre-init user code here
            okCommand1 = new Command("Agregar", Command.OK, 0);//GEN-LINE:|41-getter|1|41-postInit
            // write post-init user code here
        }//GEN-BEGIN:|41-getter|2|
        return okCommand1;
    }
    //</editor-fold>//GEN-END:|41-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textBox ">//GEN-BEGIN:|59-getter|0|59-preInit
    /**
     * Returns an initiliazed instance of textBox component.
     * @return the initialized component instance
     */
    public TextBox getTextBox() {
        if (textBox == null) {//GEN-END:|59-getter|0|59-preInit
            // write pre-init user code here
            textBox = new TextBox(textField.getString(), textField1.getString(), 100, TextField.ANY);//GEN-BEGIN:|59-getter|1|59-postInit
            textBox.addCommand(getBackCommand2());
            textBox.addCommand(getExitCommand3());
            textBox.setCommandListener(this);//GEN-END:|59-getter|1|59-postInit
            // write post-init user code here
        }//GEN-BEGIN:|59-getter|2|
        return textBox;
    }
    //</editor-fold>//GEN-END:|59-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand2 ">//GEN-BEGIN:|61-getter|0|61-preInit
    /**
     * Returns an initiliazed instance of backCommand2 component.
     * @return the initialized component instance
     */
    public Command getBackCommand2() {
        if (backCommand2 == null) {//GEN-END:|61-getter|0|61-preInit
            // write pre-init user code here
            backCommand2 = new Command("Atras", Command.BACK, 0);//GEN-LINE:|61-getter|1|61-postInit
            // write post-init user code here
        }//GEN-BEGIN:|61-getter|2|
        return backCommand2;
    }
    //</editor-fold>//GEN-END:|61-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand3 ">//GEN-BEGIN:|63-getter|0|63-preInit
    /**
     * Returns an initiliazed instance of exitCommand3 component.
     * @return the initialized component instance
     */
    public Command getExitCommand3() {
        if (exitCommand3 == null) {//GEN-END:|63-getter|0|63-preInit
            // write pre-init user code here
            exitCommand3 = new Command("Salir", Command.EXIT, 0);//GEN-LINE:|63-getter|1|63-postInit
            // write post-init user code here
        }//GEN-BEGIN:|63-getter|2|
        return exitCommand3;
    }
    //</editor-fold>//GEN-END:|63-getter|2|

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
