/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author juan
 */
public class UtilslMIDlet extends MIDlet implements CommandListener {

    private boolean midletPaused = false;

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Form form;
    private ChoiceGroup choiceGroup;
    private Command exitCommand;
    private Command okCommand;
    private Command screenCommand;
    //</editor-fold>//GEN-END:|fields|0|

    /**
     * The UtilslMIDlet constructor.
     */
    public UtilslMIDlet() {
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

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: form ">//GEN-BEGIN:|14-getter|0|14-preInit
    /**
     * Returns an initiliazed instance of form component.
     * @return the initialized component instance
     */
    public Form getForm() {
        if (form == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
            form = new Form("UTILIDADES", new Item[] { getChoiceGroup() });//GEN-BEGIN:|14-getter|1|14-postInit
            form.addCommand(getExitCommand());
            form.addCommand(getScreenCommand());
            form.setCommandListener(this);//GEN-END:|14-getter|1|14-postInit
            // write post-init user code here
        }//GEN-BEGIN:|14-getter|2|
        return form;
    }
    //</editor-fold>//GEN-END:|14-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: choiceGroup ">//GEN-BEGIN:|16-getter|0|16-preInit
    /**
     * Returns an initiliazed instance of choiceGroup component.
     * @return the initialized component instance
     */
    public ChoiceGroup getChoiceGroup() {
        if (choiceGroup == null) {//GEN-END:|16-getter|0|16-preInit
            // write pre-init user code here
            choiceGroup = new ChoiceGroup("Seleccione una:", Choice.MULTIPLE);//GEN-BEGIN:|16-getter|1|16-postInit
            choiceGroup.append("N\u00FAmeros aleatorios", null);
            choiceGroup.append("Numerolog\u00EDa", null);
            choiceGroup.setFitPolicy(Choice.TEXT_WRAP_DEFAULT);
            choiceGroup.setSelectedFlags(new boolean[] { false, false });//GEN-END:|16-getter|1|16-postInit
            // write post-init user code here
        }//GEN-BEGIN:|16-getter|2|
        return choiceGroup;
    }
    //</editor-fold>//GEN-END:|16-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == form) {//GEN-BEGIN:|7-commandAction|1|19-preAction
            if (command == exitCommand) {//GEN-END:|7-commandAction|1|19-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|2|19-postAction
                // write post-action user code here
            } else if (command == screenCommand) {//GEN-LINE:|7-commandAction|3|27-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|4|27-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|5|7-postCommandAction
        }//GEN-END:|7-commandAction|5|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|6|
    //</editor-fold>//GEN-END:|7-commandAction|6|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|18-getter|0|18-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|18-getter|0|18-preInit
            // write pre-init user code here
            exitCommand = new Command("Salir", Command.EXIT, 0);//GEN-LINE:|18-getter|1|18-postInit
            // write post-init user code here
        }//GEN-BEGIN:|18-getter|2|
        return exitCommand;
    }
    //</editor-fold>//GEN-END:|18-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|24-getter|0|24-preInit
    /**
     * Returns an initiliazed instance of okCommand component.
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {//GEN-END:|24-getter|0|24-preInit
            // write pre-init user code here
            okCommand = new Command("Abrir", Command.SCREEN, 0);//GEN-LINE:|24-getter|1|24-postInit
            // write post-init user code here
        }//GEN-BEGIN:|24-getter|2|
        return okCommand;
    }
    //</editor-fold>//GEN-END:|24-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand ">//GEN-BEGIN:|26-getter|0|26-preInit
    /**
     * Returns an initiliazed instance of screenCommand component.
     * @return the initialized component instance
     */
    public Command getScreenCommand() {
        if (screenCommand == null) {//GEN-END:|26-getter|0|26-preInit
            // write pre-init user code here
            screenCommand = new Command("Abrir", Command.SCREEN, 0);//GEN-LINE:|26-getter|1|26-postInit
            // write post-init user code here
        }//GEN-BEGIN:|26-getter|2|
        return screenCommand;
    }
    //</editor-fold>//GEN-END:|26-getter|2|

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
