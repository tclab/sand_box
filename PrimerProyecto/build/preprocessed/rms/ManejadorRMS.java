package rms;

import java.io.*;
import java.util.Vector;
import javax.microedition.rms.RecordStore;

public class ManejadorRMS {

    private RecordStore rstore = null;
    /*
     *Método: public void listarRegistrosPersonas(Vector personasTmp)
     *   Método para listar los regitros con información 
     *   de personas, almacenándolos en el Vector que recibe
     */

    public void leerRegistroPersona(Persona persona) {
        try {
            rstore = RecordStore.openRecordStore("personas", true);
            int numPersonas = rstore.getNumRecords();
            if (numPersonas > 0) {
                ByteArrayInputStream istream = null;
                DataInputStream dis = null;

                byte[] datos = rstore.getRecord(1);
                istream = new ByteArrayInputStream(datos);
                dis = new DataInputStream(istream);
                persona.setNombre(dis.readUTF());
                persona.setApellido(dis.readUTF());

                istream.close();
                dis.close();
                rstore.closeRecordStore();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
     *Método: public void guardarPersona(Persona persona)
     *  Agrega un registro de persona en RMS.
     */

    public void guardarPersona(Persona persona) {
        try {
            rstore = RecordStore.openRecordStore("personas", true);
            byte[] outrec;
            String nombre = persona.getNombre();
            String apellido = persona.getApellido();

            ByteArrayOutputStream ostream = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(ostream);

            dos.writeUTF(nombre);
            dos.writeUTF(apellido);

            dos.flush();
            outrec = ostream.toByteArray();
            int rec = rstore.addRecord(outrec, 0, outrec.length);

            ostream.reset();
            ostream.close();
            dos.close();
            rstore.closeRecordStore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}