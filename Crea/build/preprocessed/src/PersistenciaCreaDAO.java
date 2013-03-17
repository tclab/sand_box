/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Random;
import java.util.Vector;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;

/**
 *
 * @author juan
 */
public class PersistenciaCreaDAO {

    public static RecordStore rstore;

    public static Vector leerPalabras(int listSelection) throws Exception {

        Vector palabras = new Vector();
        rstore = RecordStore.openRecordStore("palabras", true);

        Palabra palabra;
        RecordEnumeration re = rstore.enumerateRecords(null, null, true);

        while (re.hasNextElement()) {
            byte[] b = re.nextRecord();

            ByteArrayInputStream bais = new ByteArrayInputStream(b);
            DataInputStream dis = new DataInputStream(bais);

            String descripcion = dis.readUTF();
            int lista = dis.readInt();

            System.out.println("Palabra leer: " + descripcion);

            bais.reset();
            dis.reset();

            if (listSelection == -1) {
                palabra = new Palabra(descripcion, lista);
                palabras.addElement(palabra);
            } else {
                if (lista == listSelection) {
                    palabra = new Palabra(descripcion, lista);
                    palabras.addElement(palabra);
                }
            }

            bais.close();
            dis.close();
        }

        rstore.closeRecordStore();
        return palabras;
    }

    public static void guardarPalabra(Palabra palabra) throws Exception {

        rstore = RecordStore.openRecordStore("palabras", true);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        System.out.println("Palabra guardar: " + palabra.getDescripcion());

        dos.writeUTF(palabra.getDescripcion());
        dos.writeInt(palabra.getLista());

        dos.flush();
        byte[] b = baos.toByteArray();
        rstore.addRecord(b, 0, b.length);

        baos.reset();

        dos.close();
        baos.close();

        rstore.closeRecordStore();
    }

    public static void eliminarPalabra(Palabra palabra) {

        try {

            Vector palabrasGuardadas = leerPalabras(-1);

            rstore = RecordStore.openRecordStore("palabras", true);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);

            Palabra guardada = new Palabra(null, 0);

            //Se recorre todo el vector preguntando por la nota a eliminar
            for (int i = 0; i < palabrasGuardadas.size(); i++) {

                guardada = (Palabra) palabrasGuardadas.elementAt(i);

                if (palabra.getDescripcion().equals(guardada.getDescripcion())) {
                    palabrasGuardadas.removeElementAt(i);
                    break;
                }
            }

            Palabra guardar = new Palabra(null, 0);
            rstore.closeRecordStore();
            rstore.deleteRecordStore("palabras");
            rstore = RecordStore.openRecordStore("palabras", true);
            for (int j = 0; j < palabrasGuardadas.size(); j++) {

                guardar = (Palabra) palabrasGuardadas.elementAt(j);

                dos.writeUTF(guardar.getDescripcion());
                dos.writeInt(guardar.getLista());

                dos.flush();

                byte[] b = baos.toByteArray();
                rstore.addRecord(b, 0, b.length);

                baos.reset();
            }

            System.out.println("Palabra borrada!!");

            dos.close();
            baos.close();

            rstore.closeRecordStore();


        } catch (Exception e) {
        }

    }

    public static Vector pareja() {

        Vector pareja = new Vector();
        Vector palabras = new Vector();
        int numeroPalabras;
        int pos;
        Palabra palabra;

        try {
            palabras = leerPalabras(-1);
            numeroPalabras = palabras.size();

            Random pala = new Random();

            pos = pala.nextInt(numeroPalabras);
            palabra = (Palabra) palabras.elementAt(pos);
            pareja.addElement(palabra.getDescripcion());

            pos = pala.nextInt(numeroPalabras);
            palabra = (Palabra) palabras.elementAt(pos);
            pareja.addElement(palabra.getDescripcion());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return pareja;
    }

    public static String palabraAleatoria(int lista) {

        String descPalab = new String();
        
        try {
            Vector palabras = leerPalabras(lista);

            Random aleatorio = new Random();
            int alea = aleatorio.nextInt(palabras.size());

            Palabra palabra = (Palabra)palabras.elementAt(alea);
            descPalab = palabra.getDescripcion();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return descPalab;
    }
}
