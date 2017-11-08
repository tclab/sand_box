/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.RecordControl;
import javax.microedition.midlet.*;

/**
 * @author juan
 */
public class GtdMidlet extends MIDlet implements CommandListener, ItemStateListener {

    private List gtdInicio, inbox, proximo, proyectos, enEspera, talVes,
            preguntas, listaMover, listaTipoNota;
    private Command salir, atras, atrasMover, atrasNota, nueva,
            mover, guardarNotaTexto, detalle,
            eliminar, editarTexto, grabar;
    private Display pantalla;
    private Displayable pantallaActual;
    private Form notaTexto, notaSonido;
    private TextField descripcionNotaTexto, descripcionNotaSonido;
    private NotaBO nota;
    private Vector notas;
    private int listaActual;
    private String notaAMover, notaEliminar;
    private TextBox detalleNota;
    private byte[] sonido;
    private final int TEXTO = 1;
    private final int SONIDO = 2;
    private StringItem mensajeGrabacion;
    private Player player;
    private Alert alerta;

    public GtdMidlet() {

        pantalla = Display.getDisplay(this);
        pantallaActual = pantalla.getCurrent();
        nota = new NotaBO(0, null, 0, null);
        notas = new Vector();
        notaAMover = new String();
        notaEliminar = new String();
        listaActual = 0;
        sonido = new byte[0];
        mensajeGrabacion = new StringItem("Grabacion", "Seleccione grabar");
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
        grabar = new Command("Grabar", Command.OK, 1);
        gtdInicio = new List("GTD", List.IMPLICIT);
        inbox = new List("Inbox", List.IMPLICIT);
        proximo = new List("Proximo", List.IMPLICIT);
        proyectos = new List("Proyectos", List.IMPLICIT);
        enEspera = new List("En espera", List.IMPLICIT);
        talVes = new List("Tal Vez", List.IMPLICIT);
        preguntas = new List("Preguntas", List.IMPLICIT);
        listaMover = new List("Donde mover?...", List.IMPLICIT);
        listaTipoNota = new List("Tipo de nota", List.IMPLICIT);

        //Tipo de nota
        listaTipoNota.append("Texto", null);
        listaTipoNota.append("Voz", null);
//        listaTipoNota.append("Dibujo", null);
        listaTipoNota.addCommand(atrasMover);
        listaTipoNota.setCommandListener(this);

        //Lista mover
        listaMover.append("A Inbox", null);
        listaMover.append("A Proximo", null);
        listaMover.append("A Proyectos", null);
        listaMover.append("A En espera", null);
        listaMover.append("A Tal vez", null);
        listaMover.append("A Preguntas", null);

        listaMover.addCommand(atrasMover);
//        listaMover.addCommand(moverNota);
        listaMover.setCommandListener(this);

        //Inbox
        inbox.addCommand(atras);
        inbox.addCommand(nueva);
        inbox.addCommand(mover);
        inbox.addCommand(detalle);
        inbox.addCommand(eliminar);
//        inbox.addCommand(enviar);
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

        //Inicio
        gtdInicio.append("Inbox", null);
        gtdInicio.append("Proximo", null);
        gtdInicio.append("Proyectos", null);
        gtdInicio.append("En espera", null);
        gtdInicio.append("Tal vez", null);
        gtdInicio.append("Preguntas", null);

//        gtdInicio.addCommand(verCalendario);
        gtdInicio.addCommand(salir);
        gtdInicio.setCommandListener(this);

        //Nota de texto
        descripcionNotaTexto = new TextField("Nota", "", 100, TextField.ANY);

        notaTexto = new Form("Nota de texto");
        notaTexto.append(descripcionNotaTexto);
        notaTexto.addCommand(atrasNota);
        notaTexto.addCommand(guardarNotaTexto);
        notaTexto.setCommandListener(this);

        //Nota de sonido
        descripcionNotaSonido = new TextField("Nota", "", 100, TextField.ANY);

        notaSonido = new Form("Nota de Sonido");
        notaSonido.append(descripcionNotaSonido);
        notaSonido.append(mensajeGrabacion);
        notaSonido.addCommand(atrasNota);
//        notaSonido.addCommand(parar);
        notaSonido.addCommand(grabar);
//        notaSonido.addCommand(guardarNotaSonido);
        notaSonido.setCommandListener(this);

        //Detalle de la nota
        detalleNota = new TextBox("Detalle de la nota", "detalle", 100, TextField.ANY | TextField.UNEDITABLE);
//        detalleNota = new TextBox("Detalle de la nota", "detalle", 100, TextField.ANY);
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
                pantalla.setCurrent(listaTipoNota);
            }

            //ELIMINAR NOTA
            if (c == eliminar) {
                pantallaActual = pantalla.getCurrent();
                NotaBO notaE = new NotaBO(0, null, 0, null);

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
                notaE = new NotaBO(0, null, 0, null);
                llenarLista(listaActual);
                pantalla.setCurrent(pantallaActual);
            }

            //DETALLE NOTA
            if (c == detalle) {
                pantallaActual = pantalla.getCurrent();
                NotaBO consultada = new NotaBO(0, null, 0, null);
                int tipo = 0;


                switch (listaActual) {
                    case 1: //Inbox
                        consultada = PersistenciaGtdDAO.consultarNota(inbox.getString(inbox.getSelectedIndex()));

                        tipo = consultada.getTipoNota();
                        if (tipo == 1) {
                            detalleNota.setString(consultada.getDescripcion());
                            pantalla.setCurrent(detalleNota);
                        } else if (tipo == 2) {
                            try {
                                ByteArrayInputStream recordedInputStream = new ByteArrayInputStream(consultada.getSonido());
                                Player p2 = Manager.createPlayer(recordedInputStream, "audio/X-wav");
                                p2.prefetch();
                                p2.start();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            } catch (MediaException ex) {
                                ex.printStackTrace();
                            }
                        }

                        break;
                    case 2: //Proximo
                        consultada = PersistenciaGtdDAO.consultarNota(proximo.getString(proximo.getSelectedIndex()));

                        tipo = consultada.getTipoNota();
                        if (tipo == 1) {
                            detalleNota.setString(consultada.getDescripcion());
                            pantalla.setCurrent(detalleNota);
                        } else if (tipo == 2) {
                            try {
                                ByteArrayInputStream recordedInputStream = new ByteArrayInputStream(consultada.getSonido());
                                Player p2 = Manager.createPlayer(recordedInputStream, "audio/X-wav");
                                p2.prefetch();
                                p2.start();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            } catch (MediaException ex) {
                                ex.printStackTrace();
                            }
                        }

                        break;
                    case 3: //Proyectos
                        consultada = PersistenciaGtdDAO.consultarNota(proyectos.getString(proyectos.getSelectedIndex()));

                        tipo = consultada.getTipoNota();
                        if (tipo == 1) {
                            detalleNota.setString(consultada.getDescripcion());
                            pantalla.setCurrent(detalleNota);
                        } else if (tipo == 2) {
                            try {
                                ByteArrayInputStream recordedInputStream = new ByteArrayInputStream(consultada.getSonido());
                                Player p2 = Manager.createPlayer(recordedInputStream, "audio/X-wav");
                                p2.prefetch();
                                p2.start();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            } catch (MediaException ex) {
                                ex.printStackTrace();
                            }
                        }

                        break;
                    case 4: //En espera
                        consultada = PersistenciaGtdDAO.consultarNota(enEspera.getString(enEspera.getSelectedIndex()));

                        tipo = consultada.getTipoNota();
                        if (tipo == 1) {
                            detalleNota.setString(consultada.getDescripcion());
                            pantalla.setCurrent(detalleNota);
                        } else if (tipo == 2) {
                            try {
                                ByteArrayInputStream recordedInputStream = new ByteArrayInputStream(consultada.getSonido());
                                Player p2 = Manager.createPlayer(recordedInputStream, "audio/X-wav");
                                p2.prefetch();
                                p2.start();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            } catch (MediaException ex) {
                                ex.printStackTrace();
                            }
                        }

                        break;
                    case 5: //Tal ves
                        consultada = PersistenciaGtdDAO.consultarNota(talVes.getString(talVes.getSelectedIndex()));

                        tipo = consultada.getTipoNota();
                        if (tipo == 1) {
                            detalleNota.setString(consultada.getDescripcion());
                            pantalla.setCurrent(detalleNota);
                        } else if (tipo == 2) {
                            try {
                                ByteArrayInputStream recordedInputStream = new ByteArrayInputStream(consultada.getSonido());
                                Player p2 = Manager.createPlayer(recordedInputStream, "audio/X-wav");
                                p2.prefetch();
                                p2.start();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            } catch (MediaException ex) {
                                ex.printStackTrace();
                            }
                        }

                        break;
                    case 6: //Preguntas
                        consultada = PersistenciaGtdDAO.consultarNota(preguntas.getString(preguntas.getSelectedIndex()));

                        tipo = consultada.getTipoNota();
                        if (tipo == 1) {
                            detalleNota.setString(consultada.getDescripcion());
                            pantalla.setCurrent(detalleNota);
                        } else if (tipo == 2) {
                            try {
                                ByteArrayInputStream recordedInputStream = new ByteArrayInputStream(consultada.getSonido());
                                Player p2 = Manager.createPlayer(recordedInputStream, "audio/X-wav");
                                p2.prefetch();
                                p2.start();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            } catch (MediaException ex) {
                                ex.printStackTrace();
                            }
                        }

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

                NotaBO notaModificada = new NotaBO(0, null, 0, null);

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
        } else if (d == listaTipoNota) {
            //TIPO DE NOTA NUEVAS
            if (c == listaTipoNota.SELECT_COMMAND) {
                switch (listaTipoNota.getSelectedIndex()) {
                    case 0: //Texto
                        pantalla.setCurrent(notaTexto);
                        break;

                    case 1: //Voz
                        pantalla.setCurrent(notaSonido);
                        break;

                    case 2: //Dibujo
                        break;
                }
            }
        } else if (d == notaTexto) {
            //GUARDAR NOTA TEXTO
            if (c == guardarNotaTexto) {
                nota.setTipoNota(TEXTO);
                nota.setDescripcion(descripcionNotaTexto.getString());
                nota.setLista(1);
                nota.setSonido(sonido);
                try {
                    PersistenciaGtdDAO.guardarNota(nota);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                descripcionNotaTexto.setString("");
                nota = new NotaBO(0, null, 0, null);
                llenarLista(1);
                pantalla.setCurrent(inbox);
            }
        } else if (d == notaSonido) {
            //GUARDAR NOTA SONIDO
            if (c == grabar) {

                try {
                    if (descripcionNotaSonido.getString().equals("")) {
                        pantalla.setCurrent(alerta);
                    } else {
                        mensajeGrabacion.setText("Grabando...");
                        player = Manager.createPlayer("capture://audio");
                        player.realize();

                        RecordControl rc = (RecordControl) player.getControl("RecordControl");

                        ByteArrayOutputStream output = new ByteArrayOutputStream();
                        rc.setRecordStream(output);

                        player.prefetch();
                        rc.startRecord();
                        player.start();

                        Thread.currentThread().sleep(6000);

                        rc.commit();
                        sonido = output.toByteArray();
                        player.close();

                        //Se guarda la nota
                        nota.setTipoNota(SONIDO);
                        nota.setDescripcion(descripcionNotaSonido.getString());
                        nota.setLista(1);
                        nota.setSonido(sonido);

                        PersistenciaGtdDAO.guardarNota(nota);

                        //Se limpian variables
//                        descripcionNotaSonido.setString("");
                        nota = new NotaBO(0, null, 0, null);
                        llenarLista(1);
                        mensajeGrabacion.setText("");
//                    Thread.currentThread().sleep(1000);
                        pantalla.setCurrent(inbox);
                    }

                } catch (IOException ioe) {
                    mensajeGrabacion.setLabel("Error");
                    mensajeGrabacion.setText(ioe.toString());
                } catch (MediaException me) {
                    mensajeGrabacion.setLabel("Error");
                    mensajeGrabacion.setText(me.toString());
                } catch (InterruptedException ie) {
                    mensajeGrabacion.setLabel("Error");
                    mensajeGrabacion.setText(ie.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void llenarLista(int lista) {
        try {
            notas = PersistenciaGtdDAO.leerNotas();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Hashtable imagenes = new Hashtable();
//        imagenes.put(new Integer(1), iTexto);
//        imagenes.put(new Integer(2), iSonido);

        if (notas.isEmpty()) {
            Alert alerta = new Alert("No se leyeron las notas correctamente");
            pantalla.setCurrent(alerta);
        } else {
            switch (lista) {

                case 1: //Inbox
                    inbox.deleteAll();
                    for (int i = 0; i < notas.size(); i++) {
                        NotaBO nota = (NotaBO) notas.elementAt(i);
                        if (nota.getLista() == 1) {
                            inbox.append(nota.getDescripcion(), (Image) imagenes.get(new Integer(nota.getTipoNota())));
                        }
                    }

                    break;

                case 2: //Proximo
                    proximo.deleteAll();
                    for (int i = 0; i < notas.size(); i++) {
                        NotaBO nota = (NotaBO) notas.elementAt(i);
                        if (nota.getLista() == 2) {
                            proximo.append(nota.getDescripcion(), (Image) imagenes.get(new Integer(nota.getTipoNota())));
                        }
                    }
                    break;

                case 3: //Proyectos
                    proyectos.deleteAll();
                    for (int i = 0; i < notas.size(); i++) {
                        NotaBO nota = (NotaBO) notas.elementAt(i);
                        if (nota.getLista() == 3) {
                            proyectos.append(nota.getDescripcion(), (Image) imagenes.get(new Integer(nota.getTipoNota())));
                        }
                    }
                    break;

                case 4: //En espera
                    enEspera.deleteAll();
                    for (int i = 0; i < notas.size(); i++) {
                        NotaBO nota = (NotaBO) notas.elementAt(i);
                        if (nota.getLista() == 4) {
                            enEspera.append(nota.getDescripcion(), (Image) imagenes.get(new Integer(nota.getTipoNota())));
                        }
                    }
                    break;

                case 5: //Tal ves
                    talVes.deleteAll();
                    for (int i = 0; i < notas.size(); i++) {
                        NotaBO nota = (NotaBO) notas.elementAt(i);
                        if (nota.getLista() == 5) {
                            talVes.append(nota.getDescripcion(), (Image) imagenes.get(new Integer(nota.getTipoNota())));
                        }
                    }
                    break;

                case 6: //Preguntas
                    preguntas.deleteAll();
                    for (int i = 0; i < notas.size(); i++) {
                        NotaBO nota = (NotaBO) notas.elementAt(i);
                        if (nota.getLista() == 6) {
                            preguntas.append(nota.getDescripcion(), (Image) imagenes.get(new Integer(nota.getTipoNota())));
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

