/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.Vector;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;

/**
 * @author juan
 */
public class CreaMidlet extends MIDlet implements CommandListener {

    private Display pantalla;
    private List menuPpal, sustantivos, verbos, tiposPalabras; //Acciones: Pareja aleatoria, palabra aleatoria, añadir palabra
    private ChoiceGroup selectTipoPalabra;
    private TextBox resultado;
    private TextField nuevaPalabra;
    private Command salir, atras, nueva, guardar, verPalabras;
    private Form nuevaPal;
    private Displayable pantallaActual;

    public CreaMidlet() {

        pantalla = Display.getDisplay(this);
        pantallaActual = pantalla.getCurrent();

        //Listas
        menuPpal = new List("MENU", List.IMPLICIT);
        sustantivos = new List("Sustantivos", List.IMPLICIT);
        verbos = new List("Verbos", List.IMPLICIT);
        selectTipoPalabra = new ChoiceGroup("TipoPalabra", List.EXCLUSIVE);
        tiposPalabras = new List("Tipos de palabras", List.IMPLICIT);

        nuevaPalabra = new TextField("Palabara", "", 100, TextField.ANY);

        //Comandos
        salir = new Command("Salir", Command.EXIT, 1);
        atras = new Command("Atras", Command.BACK, 1);
        guardar = new Command("Guardar", Command.SCREEN, 1);
        nueva = new Command("Nueva Palabra", Command.SCREEN, 1);
        verPalabras = new Command("Ver palabras", Command.SCREEN, 1);

        //Lista Menu
        menuPpal.append("Pareja", null);
        menuPpal.append("Palabra", null);
        menuPpal.addCommand(salir);
        menuPpal.addCommand(nueva);
        menuPpal.addCommand(verPalabras);
        menuPpal.setCommandListener(this);

        //Lista tipo palabra
        selectTipoPalabra.append("Sustantivos", null);       //0
        selectTipoPalabra.append("Verbos", null);     //1

        //Tipos de palabras
        tiposPalabras.append("Sustantivos", null);
        tiposPalabras.append("Verbos", null);
        tiposPalabras.addCommand(atras);
        tiposPalabras.setCommandListener(this);

        //Lista sustantivos
        sustantivos.addCommand(atras);
        sustantivos.setCommandListener(this);

        //Lista verbos
        verbos.addCommand(atras);
        verbos.setCommandListener(this);

        //Form nueva palabra
        nuevaPal = new Form("Nueva palabra");
        nuevaPal.append(nuevaPalabra);
        nuevaPal.append(selectTipoPalabra);
        nuevaPal.addCommand(atras);
        nuevaPal.addCommand(guardar);
        nuevaPal.setCommandListener(this);

        //Resusltados
        resultado = new TextBox("Resultado", "resultado", 100, TextField.ANY | TextField.UNEDITABLE);
        resultado.addCommand(atras);
        resultado.setCommandListener(this);
    }

    public void startApp() {
        pantalla.setCurrent(menuPpal);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == salir) {
            destroyApp(false);
            notifyDestroyed();
        } else if (c == nueva) {
            pantallaActual = pantalla.getCurrent();
            pantalla.setCurrent(nuevaPal);
        } else if (c == atras) {
            pantalla.setCurrent(pantallaActual);
        } else if (c == guardar) {
            try {
                Palabra nueva = new Palabra(null, 0);
                nueva.setDescripcion(nuevaPalabra.getString());
                nueva.setLista(selectTipoPalabra.getSelectedIndex());
                PersistenciaCreaDAO.guardarPalabra(nueva);
                nuevaPalabra.setString(null);
                selectTipoPalabra.setSelectedIndex(0, true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (c == menuPpal.SELECT_COMMAND) {
            pantallaActual = pantalla.getCurrent();

            switch (menuPpal.getSelectedIndex()) {
                case 0:

                    Vector palabras = PersistenciaCreaDAO.pareja();

                    if (palabras.isEmpty()) {

                        Alert alerta = new Alert("Inserte algunas palabras.");
                        pantalla.setCurrent(alerta);
                    } else {
                        String par = new String();
                        for (int i = 0; i < palabras.size(); i++) {
                            par += palabras.elementAt(i) + "\n";
                        }
                        resultado.setString(par);
                        pantalla.setCurrent(resultado);
                    }

                    break;
                case 1:
                    String palabraAleatoria = PersistenciaCreaDAO.palabraAleatoria(-1);

                    if (palabraAleatoria.length() > 0) {
                        resultado.setString(palabraAleatoria);
                        pantalla.setCurrent(resultado);
                    } else {
                        Alert alerta = new Alert("Inserte algunas palabras.");
                        pantalla.setCurrent(alerta);
                    }

                    break;
            }
        } else if (c == verPalabras) {
            pantallaActual = pantalla.getCurrent();
            pantalla.setCurrent(tiposPalabras);
        } else if (c == tiposPalabras.SELECT_COMMAND) {
            //TODO: Mostrar palabras de una lista (Solucionar problema con seleccion de elementos de diferentes listas dentro del mismo CommandListener)
        }

    }

    public void llenarLista(Vector palabras, int lista) {
        //TODO: Llenar la lista del tipo de palabra especificado con lista (en cada lista se puede eliminar la palabra seleccionada)
    }
}


