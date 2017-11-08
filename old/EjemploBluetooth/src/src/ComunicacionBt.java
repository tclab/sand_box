package src;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Vector;

import javax.bluetooth.*;
import javax.microedition.io.*;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.Screen;

public class ComunicacionBt
        extends List
        implements DiscoveryListener, Runnable, CommandListener {

    public static final byte ENVIAR_PERSONA = 0;
    public static final byte TRAER_PERSONAS = 1;
    public static final byte BYE = 2;
    private String uuidStr = "A55665EE9F9146109085C2055C888B39";
    UUID[] uuids = null;
    String url = "btspp://localhost:" + uuidStr;
    DataInputStream din;
    InputStream in;
    DataOutputStream dout;
    StreamConnection connection = null;
    DiscoveryAgent discoveryAgent;
    Vector dispEnc = new Vector();
    Hashtable matchingServiceRecords = new Hashtable();
    Command actCom = new Command("Actualizar", Command.OK, 1);
    Command openCom = new Command("Open", Command.OK, 2);
    static String[] menu = {"Crear coneccion", "Unir a coneccion"};
    //MultiChat mChat;
    private boolean isServer;
    private boolean busquedaCompletada = false;
    ServiceRecord serviceRecords;
    QuintoMIDlet eje;
    Vector dispEncontrados = new Vector();
    private boolean deviceFound = false;
    /**********/
    

    public ComunicacionBt(QuintoMIDlet eje) {
        //Se inicializa la lista con las opciones: Crear coneccion y Unir a coneccion
        super("Elegir", List.IMPLICIT, menu, null);
        setCommandListener(this);
        this.eje = eje;
    }

    public void exponerServicio() {

        StreamConnectionNotifier not;
        try {
            not = (StreamConnectionNotifier) Connector.open(url);
            connection = not.acceptAndOpen();
            din = connection.openDataInputStream();
            dout = connection.openDataOutputStream();
            eje.listoConexion();

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public void buscarAmigos() {
        try {

            discoveryAgent = LocalDevice.getLocalDevice().getDiscoveryAgent();
            discoveryAgent.startInquiry(DiscoveryAgent.GIAC, this);
            setTitle("Buscando amigos");
        } catch (BluetoothStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void deviceDiscovered(RemoteDevice device, DeviceClass arg1) {

        // TODO Auto-generated method stub
        System.out.println("deviceDiscovered");
        //if(arg1.getMajorDeviceClass() == 0x200)

        try {
            String name = device.getFriendlyName(false);
            matchingServiceRecords.put(name, device);
            String btAddress = device.getBluetoothAddress();
            String fullname = btAddress + "_" + name;

            matchingServiceRecords.put(fullname, device);
            append(fullname, null);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void inquiryCompleted(int arg0) {
        if (matchingServiceRecords.size() == 0) {
            this.setTitle("No se encontraron dispositivos");
        } else {
            setTitle("Dispositivos GPS encontrados");
        }
        busquedaCompletada = true;
        append("Busqueda de dispositivos completada", null);
    }

    public void servicesDiscovered(int arg0, ServiceRecord[] serviceRecords) {

        append("Servicio Encontrado", null);
        if (serviceRecords.length == 1) {

            this.serviceRecords = serviceRecords[0];

        }
    }

    public void serviceSearchCompleted(int arg0, int arg1) {
       // append("Termine de buscar servicios", null);
       
            abrirConeccion();


    }

    public void run() {
        if (isServer) {
            try {

                LocalDevice.getLocalDevice().setDiscoverable(DiscoveryAgent.GIAC);

            } catch (BluetoothStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            exponerServicio();
        } else {

            buscarAmigos();
        }
    }

    public void commandAction(Command c, Displayable arg1) {
        Thread t = new Thread(this);
        // TODO Auto-generated method stub
        if (c == List.SELECT_COMMAND) {
            int index = getSelectedIndex();
            if (!busquedaCompletada) {
                switch (index) {
                    case 0:
                        deleteAll();
                        setTitle("Esperando amigo");
                        isServer = true;
                        break;
                    case 1:
                        deleteAll();
                        addCommand(actCom);
                        setTitle("Elija un amigo");
                        isServer = false;
                        break;
                }
                t.start();
            } else {

               
                    try {

                        UUID[] uuids = new UUID[1];
                        uuids[0] = new UUID(uuidStr, false);
                        int[] attrSet = null; // default attrSet

                        String amigoSeleccionado = getString(index);
                        int transId = discoveryAgent.searchServices(
                                attrSet,
                                uuids,
                                (RemoteDevice) matchingServiceRecords.get(amigoSeleccionado),
                                this);

                    } catch (BluetoothStateException e) {
                        e.printStackTrace();
                    }
                }

            
        } else if (c == openCom) {
            abrirConeccion();
        //abrirConeccionGPS();
        } else if (c == actCom) {
            t.start();
        }

    }

    public void enviarBt(String msg) {
        try {
            dout.writeUTF(msg);
            dout.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void enviarPersonaBt(Persona persona) {
        try {
            //El dout ya se abrio en exponerServicio(servidor) o abrirConexion(cliente)
            dout.writeUTF(persona.getNombre());
            dout.writeUTF(persona.getApellido());

            dout.flush();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void recibirBt() {
        Thread t = new Thread(new Receptor());
        t.start();
    }

    void desconectar() {
        try {
            dout.flush();
            dout.close();
            din.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void abrirConeccion() {
        new Thread() {

            public void run() {
                String dir = ComunicacionBt.this.serviceRecords.getConnectionURL(
                        ServiceRecord.NOAUTHENTICATE_NOENCRYPT,
                        false);
                try {
                    System.out.println("Abriendo conexion ");
                    connection =
                            (StreamConnection) Connector.open(dir);
                    din =
                            connection.openDataInputStream();

                    dout =
                            connection.openDataOutputStream();
                    eje.listoConexion();

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }.start();
    }

    
    private class Receptor implements Runnable {

        public void run() {
            // TODO Auto-generated method stub
            int ch;
            StringBuffer b = new StringBuffer();
            byte[] cadena;

            //esperandotexto:
            byte t = BYE;

            if (din != null) {
                try {

                    Persona persona = new Persona();

                    String nombre = din.readUTF();
                    persona.setNombre(nombre);

                    String genero = din.readUTF();
                    persona.setApellido(genero);

                    eje.guardarPersona(persona);


                    desconectar();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}

