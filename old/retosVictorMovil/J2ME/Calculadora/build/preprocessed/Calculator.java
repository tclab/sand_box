/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author Victor
 */
public class Calculator extends MIDlet implements CommandListener {

    Display pantalla;
    Form formulario;
    TextField numero1;
    TextField numero2;
    ChoiceGroup op = new ChoiceGroup("", Choice.EXCLUSIVE);
    StringItem text = new StringItem("Result", "");
    Command exitCommand;
    Command calculate;

    public Calculator() {
        pantalla = Display.getDisplay(this);
        formulario = new Form("Calculator");
        exitCommand = new Command("Exit", Command.EXIT, 1);
        calculate = new Command("Acept", Command.OK, 1);
        numero1 = new TextField("Number 1", "", 70, TextField.NUMERIC);
        numero2 = new TextField("Number 2", "", 20, TextField.NUMERIC);
        op.append("add", null);
        op.append("Substract", null);
        op.append("Multiply", null);
        op.append("Divide", null);
        formulario.append(numero1);
        formulario.append(op);
        formulario.append(numero2);
        formulario.append(text);
        formulario.addCommand(exitCommand);
        formulario.addCommand(calculate);
        formulario.setCommandListener(this);
    }

    public void startApp() {

        pantalla.setCurrent(formulario);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == exitCommand) {
            destroyApp(false);
            notifyDestroyed();
        }
        if (c == calculate) {
            total();
        }
    }

    public void total() {
        float total = 0;


        System.out.println("Evento detectado en el TextBox txt");
        String lim = numero1.getString();
        String nar = numero2.getString();

        if (lim.equals("")) {
            lim = "0";
        }
        if (nar.equals("0")) {
            nar = "0";
        }

        int operando = op.getSelectedIndex();
        
        if (operando == 0) {
            total = Integer.parseInt(lim) + Integer.parseInt(nar);
            text.setText(Float.toString(total));
        } else if (operando == 1) {
            total = Integer.parseInt(lim) - Integer.parseInt(nar);
            text.setText(Float.toString(total));
        } else if (operando == 2) {
            total = Integer.parseInt(lim) * Integer.parseInt(nar);
            text.setText(Float.toString(total));
        } else if (operando == 3) {
            total = Integer.parseInt(lim) / Integer.parseInt(nar);
            text.setText(Float.toString(total));
        }

    }
}
