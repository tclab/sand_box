
import java.util.Vector;
import javax.microedition.rms.*;
import java.io.*;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Victor
 */
public class Datos {

    public static RecordStore rstore;

    public static Vector readPersons() throws Exception {
        Vector persons = new Vector();
        //RecordStore.deleteRecordStore("personas");
        rstore = RecordStore.openRecordStore("personas", true);
        //RecordStore.deleteRecordStore("personas");

        Persona persona;
        RecordEnumeration re = rstore.enumerateRecords(null, null, true);

        while (re.hasNextElement()) {
            byte[] b = re.nextRecord();
            ByteArrayInputStream bais = new ByteArrayInputStream(b);
            DataInputStream dis = new DataInputStream(bais);
            String nombre = dis.readUTF();
            System.out.println("mi nombre" + nombre);
            String genero = dis.readUTF();
            int a = dis.readInt();
            Vector aficiones = new Vector(a);
            for (int i = 0; i < a; i++) {
                aficiones.addElement(dis.readUTF());
            }
            int pos = dis.readInt();
            bais.reset();
            dis.reset();
            System.out.println("mi posicion" + pos);
            persona = new Persona(nombre, genero, aficiones, pos);
            persons.addElement(persona);
            bais.close();
            dis.close();
        }

        rstore.closeRecordStore();
        return persons;
    }

    public static void savePerson(Persona p) throws Exception {
        rstore = RecordStore.openRecordStore("personas", true);

        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bas);
        dos.writeUTF(p.getNombre());
        dos.writeUTF(p.getGenero());
        int tam = p.getAficiones().size();
        dos.writeInt(tam);
        int i = 0;

        while (i < tam) {
            dos.writeUTF(p.getAficiones().elementAt(i).toString());
            i++;
        }
        dos.writeInt(p.getPos());
        dos.flush();
        byte[] b = bas.toByteArray();
       rstore.addRecord(b, 0, b.length);
        bas.reset();
        dos.close();


        bas.close();

        rstore.closeRecordStore();

    }

    public static void modifyPerson(Persona p, String persona) throws Exception {

        rstore = RecordStore.openRecordStore("personas", true);


        RecordEnumeration re = rstore.enumerateRecords(null, null, true);
        boolean encontro = false;
        while (re.hasNextElement() && encontro == false) {
            int pos = re.nextRecordId();
            byte[] b = rstore.getRecord(pos);

            ByteArrayInputStream bais = new ByteArrayInputStream(b);
            DataInputStream dis = new DataInputStream(bais);
            String nombre = dis.readUTF();
            nombre = nombre.trim();
            System.out.println("mi nombre" + nombre);

            String genero = dis.readUTF();
            int a = dis.readInt();
            Vector aficiones = new Vector(a);
            for (int i = 0; i < a; i++) {
                aficiones.addElement(dis.readUTF());
            }
            //int pos=dis.readInt();
            System.out.println("POSICION ACTUAL" + pos);
            if (persona.equals(nombre)) {
                System.out.println("posicion actualizada" + pos);
                modify1(p, pos);
                encontro = true;

            }
            bais.reset();
            dis.reset();
            bais.close();
            dis.close();
        }
        rstore.closeRecordStore();

    }

    public static void modify1(Persona p, int pos) throws Exception {
        rstore = RecordStore.openRecordStore("personas", true);

        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bas);
        dos.writeUTF(p.getNombre());

        dos.writeUTF(p.getGenero());
        int tam = p.getAficiones().size();
        dos.writeInt(tam);
        int z = 0;

        while (z < tam) {
            dos.writeUTF(p.getAficiones().elementAt(z).toString());
            z++;
        }
        dos.writeInt(pos);
        dos.flush();
        byte[] b = bas.toByteArray();
        rstore.setRecord(pos, b, 0, b.length);
        bas.reset();
        dos.close();


        bas.close();
        rstore.closeRecordStore();
    }

//    public void leerRegistroPersona(Persona persona, int i) {
//        try {
//            rstore = RecordStore.openRecordStore("personas", true);
//            int numPersonas = rstore.getNumRecords();
//            if (numPersonas > 0) {
//                ByteArrayInputStream istream = null;
//                DataInputStream dis = null;
//
//                byte[] datos = rstore.getRecord(i);
//                istream = new ByteArrayInputStream(datos);
//                dis = new DataInputStream(istream);
//                persona.setNombre(dis.readUTF());
//                persona.setGenero(dis.readUTF());
//
//                istream.close();
//                dis.close();
//                rstore.closeRecordStore();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public static void delete(String persona) throws Exception {
        try {

            rstore = RecordStore.openRecordStore("personas", true);

            RecordEnumeration re = rstore.enumerateRecords(null, null, true);

            while (re.hasNextElement()) {
                int pos = re.nextRecordId();
                byte[] b = rstore.getRecord(pos);

                ByteArrayInputStream bais = new ByteArrayInputStream(b);
                DataInputStream dis = new DataInputStream(bais);
                String nombre = dis.readUTF();
                nombre = nombre.trim();
                System.out.println("mi nombre" + nombre);
                System.out.println("su nombre" + persona);
                String genero = dis.readUTF();
                int a = dis.readInt();
                Vector aficiones = new Vector(a);
                for (int i = 0; i < a; i++) {
                    aficiones.addElement(dis.readUTF());
                }
                //int pos=dis.readInt();
                System.out.println("POSICION ACTUAL" + pos);
                if (persona.equals(nombre)) {
                    System.out.println("posicion eliminada" + pos);
                    rstore.deleteRecord(pos);

                }
                bais.reset();
                dis.reset();
                bais.close();
                dis.close();
            }
            rstore.closeRecordStore();

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}
