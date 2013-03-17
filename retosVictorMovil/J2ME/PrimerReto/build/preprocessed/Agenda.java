/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.midlet.*;
import java.util.Vector;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import java.util.Vector;
import javax.microedition.rms.*;
import java.io.*;

/**
 * @author Victor
 */
public class Agenda extends MIDlet implements CommandListener, ItemStateListener {

    private Display display;
    private Alert alerta;
    private Form form;
    private final Command addCommand = new Command("Agregar Nuevo", Command.OK, 1);
    /** Removes the selected image from the published list. */
    private final Command modifyCommand = new Command("Modificar", Command.OK, 2);
    /** Shows the help message. */
    private final Command removeCommand = new Command("Eliminar", Command.OK, 3);
    private final Command viewCommand = new Command("Ver contacto", Command.OK, 4);
    /** The list control to configure images. */
    private TextField nombre;
    private ChoiceGroup genero;
    private ChoiceGroup aficiones;
    private TextField textCuales;
    private Command cmdsalir,  cmdatras,  cmdOk,  cmdNO,  cmdSI,  cmdsalir1;
    private TextBox textBoxResultados;
    private List lista;
    private int posicion = 0;
    private Persona persona = new Persona("", "", null, 0);
    private Vector personas;
    boolean seleccion = false;
    public static RecordStore rstore;
    private TextBox txtIChannel;

    public Agenda() {

        display = Display.getDisplay(this);
        txtIChannel = new TextBox("¡", null, 256, TextField.ANY);
        form = new Form("Ingrese los datos de la persona");
        lista = new List("Personas", List.IMPLICIT);
        textBoxResultados = new TextBox("Resultado ", "", 200, TextField.UNEDITABLE);
        nombre = new TextField("Nombre:", "", 10, TextField.ANY);

        genero = new ChoiceGroup("Genero:", ChoiceGroup.EXCLUSIVE);
        genero.append("Masculino", null);
        genero.append("Femenino", null);

        aficiones = new ChoiceGroup("Pasatiempos:", ChoiceGroup.MULTIPLE);
        aficiones.append("Television", null);
        aficiones.append("Cine", null);
        aficiones.append("Deporte", null);
        aficiones.append("Amistad", null);
        aficiones.append("Otros", null);

        textCuales = new TextField("Cuales ? ", "", 50, TextField.ANY);

        //Creamos el form y le añadimos los items
        form.append(nombre);
        form.append(genero);
        form.append(aficiones);

        //Creamos los Comandos del Form y del List
        cmdsalir = new Command("Salir", Command.EXIT, 3);
        cmdsalir1 = new Command("Salir", Command.EXIT, 5);
        cmdatras = new Command("Atras", Command.EXIT, 3);
        cmdOk = new Command("Ok", Command.OK, 1);
        cmdNO = new Command("No", Command.EXIT, 4);
        cmdSI = new Command("Si", Command.OK, 5);
        txtIChannel.addCommand(cmdNO);
        txtIChannel.addCommand(cmdSI);
        txtIChannel.setCommandListener(this);
        // add = new Command("Adicionar", Command.OK, 2);

        //Adicionamos los Comandos y los Escuchadores al Form y al List
        form.addCommand(cmdatras);
        form.addCommand(cmdOk);
        // list.addCommand(add);
        //lista.setCommandListener(this);
        form.setCommandListener(this);
        textBoxResultados.setCommandListener(this);
        form.setItemStateListener(this);
        lista.addCommand(cmdsalir);
        //list.addCommand(cmdOk);
        textBoxResultados.addCommand(cmdatras);
        textBoxResultados.addCommand(cmdsalir);


        lista.addCommand(addCommand);
        lista.addCommand(removeCommand);
        lista.addCommand(modifyCommand);
        lista.addCommand(viewCommand);
        lista.addCommand(cmdsalir1);
        lista.setCommandListener(this);

        getID();
        posicion++;
        System.out.println("mi ultima posicion:" + posicion);

    }

    public void getID() {
        try {
            Vector persons = new Vector();
            //RecordStore.deleteRecordStore("personas");
            rstore = RecordStore.openRecordStore("personas", true);
            //RecordStore.deleteRecordStore("personas");

            Persona persona;
            RecordEnumeration re = rstore.enumerateRecords(null, null, true);

            while (re.hasPreviousElement()) {
                byte[] b = re.previousRecord();
                ByteArrayInputStream bais = new ByteArrayInputStream(b);
                DataInputStream dis = new DataInputStream(bais);
                String nombre = dis.readUTF();
                String genero = dis.readUTF();
                int a = dis.readInt();
                Vector aficiones = new Vector(a);
                for (int i = 0; i < a; i++) {
                    aficiones.addElement(dis.readUTF());
                }
                int pos = dis.readInt();
                this.posicion = pos;
                bais.reset();
                dis.reset();

                persona = new Persona(nombre, genero, aficiones, pos);
                persons.addElement(persona);
                bais.close();
                dis.close();
            }

            rstore.closeRecordStore();
        } catch (Exception e) {
        }
    }

    public void startApp() {
        fullList();
        display.setCurrent(lista);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdsalir) {
            destroyApp(false);
            notifyDestroyed();
        }
        if (c == cmdsalir1) {
            display.setCurrent(lista);
        }
        if (c == cmdatras) {
            display.setCurrent(lista);
        }
        if (c == cmdNO) {
            display.setCurrent(lista);
        }
        if (c == cmdSI) {

            String g = null;

            if (genero.isSelected(0)) {
                g = genero.getString(0);
            } else {
                g = genero.getString(1);
            }
            Vector aficion = new Vector();
            for (int i = 0; i < aficiones.size() - 1; i++) {
                if (aficiones.isSelected(i)) {
                    aficion.addElement(aficiones.getString(i));
                }
            }
            if (aficiones.isSelected(aficiones.size() - 1)) {
                aficion.addElement(textCuales.getString());
            }

            persona.setNombre(nombre.getString());
            persona.setGenero(genero.getString(genero.getSelectedIndex()));
            persona.setAficiones(aficion);
            persona.setPos(posicion);

            try {
                int pos = lista.getSelectedIndex();
                String person = lista.getString(pos);
                //lista.delete(pos);
                System.out.println(pos);
                Datos.modifyPerson(persona, person);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            fullList();
            display.setCurrent(lista);
            posicion++;


        }
        if (c == addCommand) {
            clear();
            seleccion = false;
            display.setCurrent(form);
        } else if (c == removeCommand) {
            if (lista.size() != 0) {

                try {

                    int k = lista.getSelectedIndex();
                    String nomb = lista.getString(k);
                    nomb = nomb.trim();
                    lista.delete(k);
                    alerta = new Alert("Eliminando....");
                    display.setCurrent(alerta);
                    Datos.delete(nomb);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            } else {

                alerta = new Alert("No hay algun objeto seleccionado");
                display.setCurrent(alerta);
            }

        } else if (c == modifyCommand) {
            if (lista.size() != 0) {
                clear();
                leerResultados();
                seleccion = true;
                display.setCurrent(form);
            } else {

                alerta = new Alert("No hay algun objeto seleccionado");
                display.setCurrent(alerta);
            }
        } else if (c == viewCommand) {
            if (lista.size() != 0) {
                mostrarResultado();

            } else {

                alerta = new Alert("No hay algun objeto seleccionado");
                display.setCurrent(alerta);
            }
        } else if (c == cmdOk) {
            if (nombre.getString().equals("")) {
                alerta = new Alert("El nombre no puede ser vacio");
                display.setCurrent(alerta);
            } else {
                String g = null;

                if (genero.isSelected(0)) {
                    g = genero.getString(0);
                } else {
                    g = genero.getString(1);
                }
                Vector aficion = new Vector();
                for (int i = 0; i < aficiones.size() - 1; i++) {
                    if (aficiones.isSelected(i)) {
                        aficion.addElement(aficiones.getString(i));
                    }
                }
                if (aficiones.isSelected(aficiones.size() - 1)) {
                    aficion.addElement(textCuales.getString());
                }
                if (exist(nombre.getString()) == true && seleccion == false) {
                    //txtIChannel = new TextBox("¡", "Este nombre de contacto ya existe, ¿Reemplazar? "+nombre.getString(), 256, TextField.ANY);
                    txtIChannel.setString("Este nombre de contacto ya existe, ¿Reemplazar? " + nombre.getString());
                    display.setCurrent(txtIChannel);
                } else {
                    persona.setNombre(nombre.getString());
                    persona.setGenero(genero.getString(genero.getSelectedIndex()));
                    persona.setAficiones(aficion);
                    persona.setPos(posicion);
                    if (seleccion == false) {
                        try {
                            Datos.savePerson(persona);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else if (seleccion == true) {
                        try {
                            int pos = lista.getSelectedIndex();
                            String person = lista.getString(pos);
                            //lista.delete(pos);
                            System.out.println(pos);
                            Datos.modifyPerson(persona, person);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    fullList();
                    display.setCurrent(lista);
                    posicion++;
                }
            }
        }
    }

    public boolean exist(String persona) {
        boolean encontro = false;
        try {

            Vector persons = new Vector();
            //RecordStore.deleteRecordStore("personas");
            rstore = RecordStore.openRecordStore("personas", true);
            //RecordStore.deleteRecordStore("personas");


            RecordEnumeration re = rstore.enumerateRecords(null, null, true);

            while (re.hasPreviousElement()) {
                byte[] b = re.previousRecord();
                ByteArrayInputStream bais = new ByteArrayInputStream(b);
                DataInputStream dis = new DataInputStream(bais);
                String nombre = dis.readUTF();
                String genero = dis.readUTF();
                int a = dis.readInt();
                Vector aficiones = new Vector(a);
                for (int i = 0; i < a; i++) {
                    aficiones.addElement(dis.readUTF());
                }
                int pos = dis.readInt();
                this.posicion = pos;
                if (nombre.equals(persona)) {
                    encontro = true;
                    break;
                }

                bais.reset();
                dis.reset();

                bais.close();
                dis.close();

            }

            rstore.closeRecordStore();

        } catch (Exception e) {
        }
        return encontro;
    }

    public void fullList() {
        lista.deleteAll();
        try {
            personas = Datos.readPersons();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (personas.isEmpty()) {
            alerta = new Alert("No hay datos");
            display.setCurrent(alerta);
        } else {
            for (int i = 0; i < personas.size(); i++) {
                persona = (Persona) personas.elementAt(i);
                lista.append(persona.getNombre(), null);
            }
        }
    }

    public void clear() {
        nombre.setString("");
        for (int i = 0; i < aficiones.size(); i++) {
            aficiones.setSelectedIndex(i, false);
        }
        if (form.size() == 4) {
            form.delete(3);
        }

        genero.setSelectedIndex(0, true);
        textCuales.setString("");

    }

    public void leerResultados() {
        try {

            String p = lista.getString(lista.getSelectedIndex());
            System.out.println(p);
            Vector v = Datos.readPersons();
            persona = (Persona) v.elementAt(lista.getSelectedIndex());
            nombre.setString(persona.getNombre());
            String gen = persona.getGenero();
            gen.trim();
            if (gen.equals("Masculino")) {
                genero.setSelectedIndex(0, true);
            } else {
                genero.setSelectedIndex(1, true);
            }

            for (int i = 0; i < persona.getAficiones().size(); i++) {
                Object aficion = persona.getAficiones().elementAt(i);
                if (aficion.equals("Television")) {
                    aficiones.setSelectedIndex(0, true);
                } else if (aficion.equals("Cine")) {
                    aficiones.setSelectedIndex(1, true);
                } else if (aficion.equals("Deporte")) {
                    aficiones.setSelectedIndex(2, true);
                } else if (aficion.equals("Amistad")) {
                    aficiones.setSelectedIndex(3, true);
                } else {
                    aficiones.setSelectedIndex(4, true);
                    if (aficiones.isSelected(4)) {
                        textCuales.setString(aficion.toString());
                        form.append(textCuales);
                    }
                }
            }

            /* textBoxResultados.insert(nom, 0);
            textBoxResultados.insert(gen, textBoxResultados.size());
            textBoxResultados.insert(afs, textBoxResultados.size());

            display.setCurrent(textBoxResultados);*/

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void mostrarResultado() {
        try {
            textBoxResultados.setString("");
            String p = lista.getString(lista.getSelectedIndex());
            System.out.println(p);
            Vector v = Datos.readPersons();
            persona = (Persona) v.elementAt(lista.getSelectedIndex());
            String nom = "NOMBRE:    " + persona.getNombre() + "\n";
            String gen = "GENERO:    " + persona.getGenero() + "\n";
            System.out.println(gen);
            String afs = "AFICIONES: ";
            for (int i = 0; i < persona.getAficiones().size(); i++) {
                afs = afs + persona.getAficiones().elementAt(i) + " ";
            }

            textBoxResultados.insert(nom, 0);
            textBoxResultados.insert(gen, textBoxResultados.size());
            textBoxResultados.insert(afs, textBoxResultados.size());

            display.setCurrent(textBoxResultados);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void itemStateChanged(Item item) {
        if (item == aficiones) {
            if (aficiones.isSelected(4)) {
                form.append(textCuales);
            } else {
                if (form.size() == 4) {
                    form.delete(3);
                }
            }
        }
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
