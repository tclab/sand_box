/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejemplos;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author Juan
 */
public class VisualMIDlet extends MIDlet implements CommandListener {

    private boolean midletPaused = false;

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private java.util.Hashtable __previousDisplayables = new java.util.Hashtable();
    private Form form;
    private List list;
    private Command screenCommand;
    private Command exitCommand;
    private Command backCommand;
    //</editor-fold>//GEN-END:|fields|0|

    /**
     * The VisualMIDlet constructor.
     */
    public VisualMIDlet() {
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    /**
     * Switches a display to previous displayable of the current displayable.
     * The <code>display</code> instance is obtain from the <code>getDisplay</code> method.
     */
    private void switchToPreviousDisplayable() {
        Displayable __currentDisplayable = getDisplay().getCurrent();
        if (__currentDisplayable != null) {
            Displayable __nextDisplayable = (Displayable) __previousDisplayables.get(__currentDisplayable);
            if (__nextDisplayable != null) {
                switchDisplayable(null, __nextDisplayable);
            }
        }
    }
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
        Displayable __currentDisplayable = display.getCurrent();
        if (__currentDisplayable != null  &&  nextDisplayable != null) {
            __previousDisplayables.put(nextDisplayable, __currentDisplayable);
        }
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
        if (displayable == form) {//GEN-BEGIN:|7-commandAction|1|18-preAction
            if (command == exitCommand) {//GEN-END:|7-commandAction|1|18-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|2|18-postAction
                // write post-action user code here
            } else if (command == screenCommand) {//GEN-LINE:|7-commandAction|3|16-preAction
                // write pre-action user code here
                switchDisplayable(null, getList());//GEN-LINE:|7-commandAction|4|16-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|5|21-preAction
        } else if (displayable == list) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|5|21-preAction
                // write pre-action user code here
                listAction();//GEN-LINE:|7-commandAction|6|21-postAction
                // write post-action user code here
            } else if (command == backCommand) {//GEN-LINE:|7-commandAction|7|30-preAction
                // write pre-action user code here
                switchToPreviousDisplayable();//GEN-LINE:|7-commandAction|8|30-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|9|7-postCommandAction
        }//GEN-END:|7-commandAction|9|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|10|
    //</editor-fold>//GEN-END:|7-commandAction|10|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: form ">//GEN-BEGIN:|14-getter|0|14-preInit
    /**
     * Returns an initiliazed instance of form component.
     * @return the initialized component instance
     */
    public Form getForm() {
        if (form == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
            form = new Form("form");//GEN-BEGIN:|14-getter|1|14-postInit
            form.addCommand(getScreenCommand());
            form.addCommand(getExitCommand());
            form.setCommandListener(this);//GEN-END:|14-getter|1|14-postInit
            // write post-init user code here
        }//GEN-BEGIN:|14-getter|2|
        return form;
    }
    //</editor-fold>//GEN-END:|14-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: list ">//GEN-BEGIN:|19-getter|0|19-preInit
    /**
     * Returns an initiliazed instance of list component.
     * @return the initialized component instance
     */
    public List getList() {
        if (list == null) {//GEN-END:|19-getter|0|19-preInit
            // write pre-init user code here
            list = new List("Lista de colores", Choice.IMPLICIT);//GEN-BEGIN:|19-getter|1|19-postInit
            list.append("Verde", null);
            list.append("Azul", null);
            list.append("Rojo", null);
            list.addCommand(getBackCommand());
            list.setCommandListener(this);
            list.setSelectedFlags(new boolean[] { false, false, false });//GEN-END:|19-getter|1|19-postInit
            // write post-init user code here
        }//GEN-BEGIN:|19-getter|2|
        return list;
    }
    //</editor-fold>//GEN-END:|19-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: listAction ">//GEN-BEGIN:|19-action|0|19-preAction
    /**
     * Performs an action assigned to the selected list element in the list component.
     */
    public void listAction() {//GEN-END:|19-action|0|19-preAction
        // enter pre-action user code here
        String __selectedString = getList().getString(getList().getSelectedIndex());//GEN-BEGIN:|19-action|1|23-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("Verde")) {//GEN-END:|19-action|1|23-preAction
                // write pre-action user code here
//GEN-LINE:|19-action|2|23-postAction
                // write post-action user code here
            } else if (__selectedString.equals("Azul")) {//GEN-LINE:|19-action|3|24-preAction
                // write pre-action user code here
//GEN-LINE:|19-action|4|24-postAction
                // write post-action user code here
            } else if (__selectedString.equals("Rojo")) {//GEN-LINE:|19-action|5|25-preAction
                // write pre-action user code here
//GEN-LINE:|19-action|6|25-postAction
                // write post-action user code here
            }//GEN-BEGIN:|19-action|7|19-postAction
        }//GEN-END:|19-action|7|19-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|19-action|8|
    //</editor-fold>//GEN-END:|19-action|8|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand ">//GEN-BEGIN:|15-getter|0|15-preInit
    /**
     * Returns an initiliazed instance of screenCommand component.
     * @return the initialized component instance
     */
    public Command getScreenCommand() {
        if (screenCommand == null) {//GEN-END:|15-getter|0|15-preInit
            // write pre-init user code here
            screenCommand = new Command("Screen", Command.SCREEN, 0);//GEN-LINE:|15-getter|1|15-postInit
            // write post-init user code here
        }//GEN-BEGIN:|15-getter|2|
        return screenCommand;
    }
    //</editor-fold>//GEN-END:|15-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|17-getter|0|17-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|17-getter|0|17-preInit
            // write pre-init user code here
            exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|17-getter|1|17-postInit
            // write post-init user code here
        }//GEN-BEGIN:|17-getter|2|
        return exitCommand;
    }
    //</editor-fold>//GEN-END:|17-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand ">//GEN-BEGIN:|29-getter|0|29-preInit
    /**
     * Returns an initiliazed instance of backCommand component.
     * @return the initialized component instance
     */
    public Command getBackCommand() {
        if (backCommand == null) {//GEN-END:|29-getter|0|29-preInit
            // write pre-init user code here
            backCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|29-getter|1|29-postInit
            // write post-init user code here
        }//GEN-BEGIN:|29-getter|2|
        return backCommand;
    }
    //</editor-fold>//GEN-END:|29-getter|2|

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
