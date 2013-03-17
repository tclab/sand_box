package src;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Vector;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;

/**
 * @author juan
 */
public class MidletGTD extends MIDlet implements CommandListener, ItemStateListener {

    private List gtdInicio, inbox, proximo, proyectos, enEspera, talVes,
            preguntas, listaMover;
    private Command salir, atras, atrasMover, atrasNota, nueva,
            mover, guardarNotaTexto, detalle, eliminar, editarTexto;
    private Display pantalla;
    private Displayable pantallaActual;
    private Form notaTexto;
    private TextField descripcionNotaTexto;
    private NotaBO nota;
    private Vector notas;
    private int listaActual;
    private String notaAMover, notaEliminar;
    private TextBox detalleNota;
    private Alert alerta;

    public MidletGTD() {

        pantalla = Display.getDisplay(this);
        pantallaActual = pantalla.getCurrent();
        nota = new NotaBO(0, null, 0);
        notas = new Vector();
        notaAMover = new String();
        notaEliminar = new String();
        listaActual = 0;
        alerta = new Alert("Campo vacio", "Primero debe introducir una descripcion para la nota", null, AlertType.ERROR);
        alerta.setTimeout(4000);

        salir = new Command("Salir", Command.EXIT, 1);
        atras = new Command("Atras", Command.EXIT, 1);
        nueva = new Command("Nueva", Command.SCREEN, 1);
        mover = new Command("Mover", Command.SCREEN, 1);
        atrasMover = new Command("Atras", Command.EXIT, 1);
        atrasNota = new Command("Atras", Command.EXIT, 1);
        guardarNotaTexto = new Command("Guardar", Command.OK, 1);
        detalle = new Command("Detalle", Command.SCREEN, 1);
        eliminar = new Command("Eliminar", Command.OK, 1);
        editarTexto = new Command("Guardar", Command.OK, 1);
        gtdInicio = new List("GTD", List.IMPLICIT);
        inbox = new List("Inbox", List.IMPLICIT);
        proximo = new List("Proximo", List.IMPLICIT);
        proyectos = new List("Proyectos", List.IMPLICIT);
        enEspera = new List("En espera", List.IMPLICIT);
        talVes = new List("Tal Vez", List.IMPLICIT);
        preguntas = new List("Preguntas", List.IMPLICIT);
        listaMover = new List("Donde mover?...", List.IMPLICIT);

        //Inicio
        gtdInicio.append("Inbox", null);
        gtdInicio.append("Proximo", null);
        gtdInicio.append("Proyectos", null);
        gtdInicio.append("En espera", null);
        gtdInicio.append("Tal vez", null);
        gtdInicio.append("Preguntas", null);
        gtdInicio.addCommand(salir);
        gtdInicio.setCommandListener(this);

        //Lista mover
        listaMover.append("A Inbox", null);
        listaMover.append("A Proximo", null);
        listaMover.append("A Proyectos", null);
        listaMover.append("A En espera", null);
        listaMover.append("A Tal vez", null);
        listaMover.append("A Preguntas", null);
        listaMover.addCommand(atrasMover);
        listaMover.setCommandListener(this);

        //Inbox
        inbox.addCommand(atras);
        inbox.addCommand(nueva);
        inbox.addCommand(mover);
        inbox.addCommand(detalle);
        inbox.addCommand(eliminar);
        inbox.setCommandListener(this);

        //Proximo
        proximo.addCommand(atras);
        proximo.addCommand(mover);
        proximo.addCommand(detalle);
        proximo.addCommand(eliminar);
        proximo.setCommandListener(this);

        //Proyectos
        proyectos.addCommand(atras);
        proyectos.addCommand(mover);
        proyectos.addCommand(detalle);
        proyectos.addCommand(eliminar);
        proyectos.setCommandListener(this);

        //En espera
        enEspera.addCommand(atras);
        enEspera.addCommand(mover);
        enEspera.addCommand(detalle);
        enEspera.addCommand(eliminar);
        enEspera.setCommandListener(this);

        //Tal ves
        talVes.addCommand(atras);
        talVes.addCommand(mover);
        talVes.addCommand(detalle);
        talVes.addCommand(eliminar);
        talVes.setCommandListener(this);

        //Preguntas
        preguntas.addCommand(atras);
        preguntas.addCommand(mover);
        preguntas.addCommand(detalle);
        preguntas.addCommand(eliminar);
        preguntas.setCommandListener(this);

        //Nota de texto
        descripcionNotaTexto = new TextField("Nota", "", 100, TextField.ANY);

        notaTexto = new Form("Nota de texto");
        notaTexto.append(descripcionNotaTexto);
        notaTexto.addCommand(atrasNota);
        notaTexto.addCommand(guardarNotaTexto);
        notaTexto.setCommandListener(this);

        //Detalle de la nota
        detalleNota = new TextBox("Detalle de la nota", "detalle", 100, TextField.ANY | TextField.UNEDITABLE);
        detalleNota.addCommand(atrasMover);
        detalleNota.addCommand(editarTexto);
        detalleNota.setCommandListener(this);
    }

    public void startApp() {
        pantalla.setCurrent(gtdInicio);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {

        //VOLVER AL INICIO
        if (c == atras) {
            pantalla.setCurrent(gtdInicio);
        }
        //ATRAS GENERICO
        if (c == atrasMover) {
            pantalla.setCurrent(pantallaActual);
        }
        //VOLVER AL INBOX
        if (c == atrasNota) {
            pantalla.setCurrent(inbox);
        }

        if (d == inbox || d == proximo || d == proyectos || d == enEspera || d == talVes || d == preguntas) {

            //NUEVA NOTA
            if (c == nueva) {
                pantalla.setCurrent(notaTexto);
            }

            //ELIMINAR NOTA
            if (c == eliminar) {
                pantallaActual = pantalla.getCurrent();
                NotaBO notaE = new NotaBO(0, null, 0);

                switch (listaActual) {
                    case 1: //Inbox
                        notaEliminar = inbox.getString(inbox.getSelectedIndex());
                        notaE.setDescripcion(notaEliminar);
                        notaE.setLista(listaActual);
                        PersistenciaGtdDAO.eliminarNota(notaE);
                        break;
                    case 2: //Proximo
                        notaEliminar = proximo.getString(proximo.getSelectedIndex());
                        notaE.setDescripcion(notaEliminar);
                        notaE.setLista(listaActual);
                        PersistenciaGtdDAO.eliminarNota(notaE);
                        break;
                    case 3: //Proyectos
                        notaEliminar = proyectos.getString(proyectos.getSelectedIndex());
                        notaE.setDescripcion(notaEliminar);
                        notaE.setLista(listaActual);
                        PersistenciaGtdDAO.eliminarNota(notaE);
                        break;
                    case 4: //En espera
                        notaEliminar = enEspera.getString(enEspera.getSelectedIndex());
                        notaE.setDescripcion(notaEliminar);
                        notaE.setLista(listaActual);
                        PersistenciaGtdDAO.eliminarNota(notaE);
                        break;
                    case 5: //Tal ves
                        notaEliminar = talVes.getString(talVes.getSelectedIndex());
                        notaE.setDescripcion(notaEliminar);
                        notaE.setLista(listaActual);
                        PersistenciaGtdDAO.eliminarNota(notaE);
                        break;
                    case 6: //Preguntas
                        notaEliminar = preguntas.getString(preguntas.getSelectedIndex());
                        notaE.setDescripcion(notaEliminar);
                        notaE.setLista(listaActual);
                        PersistenciaGtdDAO.eliminarNota(notaE);
                        break;
                }
                notaE = new NotaBO(0, null, 0);
                llenarLista(listaActual);
                pantalla.setCurrent(pantallaActual);
            }

            //DETALLE NOTA
            if (c == detalle) {
                pantallaActual = pantalla.getCurrent();
                NotaBO consultada = new NotaBO(0, null, 0);
                int tipo = 0;


                switch (listaActual) {
                    case 1: //Inbox
                        consultada = PersistenciaGtdDAO.consultarNota(inbox.getString(inbox.getSelectedIndex()));
                        detalleNota.setString(consultada.getDescripcion());
                        pantalla.setCurrent(detalleNota);

                        break;
                    case 2: //Proximo
                        consultada = PersistenciaGtdDAO.consultarNota(proximo.getString(proximo.getSelectedIndex()));
                        detalleNota.setString(consultada.getDescripcion());
                        pantalla.setCurrent(detalleNota);

                        break;
                    case 3: //Proyectos
                        consultada = PersistenciaGtdDAO.consultarNota(proyectos.getString(proyectos.getSelectedIndex()));
                        detalleNota.setString(consultada.getDescripcion());
                        pantalla.setCurrent(detalleNota);
                        break;
                    case 4: //En espera
                        consultada = PersistenciaGtdDAO.consultarNota(enEspera.getString(enEspera.getSelectedIndex()));
                        detalleNota.setString(consultada.getDescripcion());
                        pantalla.setCurrent(detalleNota);

                        break;
                    case 5: //Tal ves
                        consultada = PersistenciaGtdDAO.consultarNota(talVes.getString(talVes.getSelectedIndex()));
                        detalleNota.setString(consultada.getDescripcion());
                        pantalla.setCurrent(detalleNota);

                        break;
                    case 6: //Preguntas
                        consultada = PersistenciaGtdDAO.consultarNota(preguntas.getString(preguntas.getSelectedIndex()));
                        detalleNota.setString(consultada.getDescripcion());
                        pantalla.setCurrent(detalleNota);

                        break;
                }
            }

            //RECOGE TEXTO DE LA NOTA QUE SE VA A MOVER
            if (c == mover) {
                pantallaActual = pantalla.getCurrent();

                switch (listaActual) {
                    case 1: //Inbox
                        notaAMover = inbox.getString(inbox.getSelectedIndex());
                        break;
                    case 2: //Proximo
                        notaAMover = proximo.getString(proximo.getSelectedIndex());
                        break;
                    case 3: //Proyectos
                        notaAMover = proyectos.getString(proyectos.getSelectedIndex());
                        break;
                    case 4: //En espera
                        notaAMover = enEspera.getString(enEspera.getSelectedIndex());
                        break;
                    case 5: //Tal ves
                        notaAMover = talVes.getString(talVes.getSelectedIndex());
                        break;
                    case 6: //Preguntas
                        notaAMover = preguntas.getString(preguntas.getSelectedIndex());
                        break;
                }
                pantalla.setCurrent(listaMover);
            }

        } else if (d == gtdInicio) {

            if (c == salir) {
                destroyApp(false);
                notifyDestroyed();
            }

            //VER UNA LISTA DETERMINADA
            if (c == gtdInicio.SELECT_COMMAND) {

                System.out.println("Inicio");

                switch (gtdInicio.getSelectedIndex()) {
                    case 0:
                        llenarLista(1);
                        listaActual = 1;
                        pantalla.setCurrent(inbox);
                        break;
                    case 1:
                        llenarLista(2);
                        listaActual = 2;
                        pantalla.setCurrent(proximo);
                        break;
                    case 2:
                        llenarLista(3);
                        listaActual = 3;
                        pantalla.setCurrent(proyectos);
                        break;
                    case 3:
                        llenarLista(4);
                        listaActual = 4;
                        pantalla.setCurrent(enEspera);
                        break;
                    case 4:
                        llenarLista(5);
                        listaActual = 5;
                        pantalla.setCurrent(talVes);
                        break;
                    case 5:
                        llenarLista(6);
                        listaActual = 6;
                        pantalla.setCurrent(preguntas);
                        break;
                }
            }
        } else if (d == listaMover) {
            //MOVER LA NOTA A OTRA LISTAS
            if (c == listaMover.SELECT_COMMAND) {

                switch (listaMover.getSelectedIndex()) {
                    case 0:
                        PersistenciaGtdDAO.actualizarNota(notaAMover, listaMover.getSelectedIndex() + 1);
                        llenarLista(listaActual);
                        pantalla.setCurrent(pantallaActual);
                        break;
                    case 1:
                        PersistenciaGtdDAO.actualizarNota(notaAMover, listaMover.getSelectedIndex() + 1);
                        llenarLista(listaActual);
                        pantalla.setCurrent(pantallaActual);
                        break;
                    case 2:
                        PersistenciaGtdDAO.actualizarNota(notaAMover, listaMover.getSelectedIndex() + 1);
                        llenarLista(listaActual);
                        pantalla.setCurrent(pantallaActual);
                        break;
                    case 3:
                        PersistenciaGtdDAO.actualizarNota(notaAMover, listaMover.getSelectedIndex() + 1);
                        llenarLista(listaActual);
                        pantalla.setCurrent(pantallaActual);
                        break;
                    case 4:
                        PersistenciaGtdDAO.actualizarNota(notaAMover, listaMover.getSelectedIndex() + 1);
                        llenarLista(listaActual);
                        pantalla.setCurrent(pantallaActual);
                        break;
                    case 5:
                        PersistenciaGtdDAO.actualizarNota(notaAMover, listaMover.getSelectedIndex() + 1);
                        llenarLista(listaActual);
                        pantalla.setCurrent(pantallaActual);
                        break;
                }


            }
        } else if (d == notaTexto) {
            if (c == guardarNotaTexto) {
                nota.setDescripcion(descripcionNotaTexto.getString());
                nota.setLista(1);
                try {
                    PersistenciaGtdDAO.guardarNota(nota);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                descripcionNotaTexto.setString("");
                nota = new NotaBO(0, null, 0);
                llenarLista(1);
                pantalla.setCurrent(inbox);
            }
        }
    }

    private void llenarLista(int lista) {
        try {
            notas = PersistenciaGtdDAO.leerNotas();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (notas.isEmpty()) {
            Alert alertaFalla = new Alert("No se leyeron las notas correctamente");
            pantalla.setCurrent(alertaFalla);
        } else {
            switch (lista) {

                case 1: //Inbox
                    inbox.deleteAll();
                    for (int i = 0; i < notas.size(); i++) {
                        NotaBO notaInb = (NotaBO) notas.elementAt(i);
                        if (notaInb.getLista() == 1) {
                            inbox.append(notaInb.getDescripcion(), null); // (Image) imagenes.get(new Integer(nota.getTipoNota())));
                        }
                    }

                    break;

                case 2: //Proximo
                    proximo.deleteAll();
                    for (int i = 0; i < notas.size(); i++) {
                        NotaBO notaPro = (NotaBO) notas.elementAt(i);
                        if (notaPro.getLista() == 2) {
                            proximo.append(notaPro.getDescripcion(), null); // (Image) imagenes.get(new Integer(nota.getTipoNota())));
                        }
                    }
                    break;

                case 3: //Proyectos
                    proyectos.deleteAll();
                    for (int i = 0; i < notas.size(); i++) {
                        NotaBO notaProy = (NotaBO) notas.elementAt(i);
                        if (notaProy.getLista() == 3) {
                            proyectos.append(notaProy.getDescripcion(), null); // (Image) imagenes.get(new Integer(nota.getTipoNota())));
                        }
                    }
                    break;

                case 4: //En espera
                    enEspera.deleteAll();
                    for (int i = 0; i < notas.size(); i++) {
                        NotaBO notaEspe = (NotaBO) notas.elementAt(i);
                        if (notaEspe.getLista() == 4) {
                            enEspera.append(notaEspe.getDescripcion(), null); // (Image) imagenes.get(new Integer(nota.getTipoNota())));
                        }
                    }
                    break;

                case 5: //Tal ves
                    talVes.deleteAll();
                    for (int i = 0; i < notas.size(); i++) {
                        NotaBO notaTalV = (NotaBO) notas.elementAt(i);
                        if (notaTalV.getLista() == 5) {
                            talVes.append(notaTalV.getDescripcion(), null); // (Image) imagenes.get(new Integer(nota.getTipoNota())));
                        }
                    }
                    break;

                case 6: //Preguntas
                    preguntas.deleteAll();
                    for (int i = 0; i < notas.size(); i++) {
                        NotaBO notaPregu = (NotaBO) notas.elementAt(i);
                        if (notaPregu.getLista() == 6) {
                            preguntas.append(notaPregu.getDescripcion(), null); // (Image) imagenes.get(new Integer(nota.getTipoNota())));
                        }
                    }
                    break;

            }
        }
    }

    public void itemStateChanged(Item item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

