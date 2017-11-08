/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Victor
 */
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
public class PersistenciaDAO {

    public static RecordStore rstore;

    public static Vector readDibujos() throws Exception {
        Vector persons = new Vector();

        //RecordStore.deleteRecordStore("personas");
        rstore = RecordStore.openRecordStore("coordenadas", true);
        //RecordStore.deleteRecordStore("personas");

        DibujoBO dibujo;
        RecordEnumeration re = rstore.enumerateRecords(null, null, true);

        while (re.hasNextElement()) {
            byte[] b = re.nextRecord();

            ByteArrayInputStream bais = new ByteArrayInputStream(b);
            DataInputStream dis = new DataInputStream(bais);

            String nombre = dis.readUTF();
            int color = dis.readInt();
            System.out.println("mi nombre" + nombre);
            int tamanoVector = dis.readInt();

            Vector aficiones = new Vector(tamanoVector);
            for (int i = 0; i < tamanoVector; i++) {
                aficiones.addElement(dis.readUTF());
            }

            tamanoVector = dis.readInt();
            Vector aficiones1 = new Vector(tamanoVector);
            for (int i = 0; i < tamanoVector; i++) {
                aficiones1.addElement(dis.readUTF());
            }
            tamanoVector = dis.readInt();
            Vector aficiones2 = new Vector(tamanoVector);
            for (int i = 0; i < tamanoVector; i++) {
                aficiones2.addElement(dis.readUTF());
            }
            tamanoVector = dis.readInt();
            Vector colores = new Vector(tamanoVector);
            for (int i = 0; i < tamanoVector; i++) {
                colores.addElement(dis.readUTF());
            }
            bais.reset();
            dis.reset();
            dibujo = new DibujoBO(nombre, aficiones, aficiones1, aficiones2,colores, color);
            persons.addElement(dibujo);
            bais.close();
            dis.close();
        }

        rstore.closeRecordStore();
        return persons;
    }

    public static void saveDibujos(DibujoBO nombre) throws Exception {
        rstore = RecordStore.openRecordStore("coordenadas", true);
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bas);
        System.out.println("mi noim" + nombre.getNombre());
        dos.writeUTF(nombre.getNombre());
        dos.writeInt(nombre.getColor());
        int tam = nombre.getCoord().size();
        dos.writeInt(tam);
        int i = 0;

        while (i < tam) {
            dos.writeUTF((nombre.getCoord().elementAt(i).toString()));
            System.out.println("Coordenada" + nombre.getCoord().elementAt(i).toString());
            i++;
        }
        tam = nombre.getCoord1().size();
        dos.writeInt(tam);
        i = 0;
        while (i < tam) {
            dos.writeUTF((nombre.getCoord1().elementAt(i).toString()));
            System.out.println("Coordenada" + nombre.getCoord1().elementAt(i).toString());
            i++;
        }
        tam = nombre.getNumV().size();
        dos.writeInt(tam);
        i = 0;
        while (i < tam) {
            dos.writeUTF((nombre.getNumV().elementAt(i).toString()));
            System.out.println("Coordenada" + nombre.getNumV().elementAt(i).toString());
            i++;
        }
        tam = nombre.getColores().size();
        dos.writeInt(tam);
        i = 0;
        while (i < tam) {
            dos.writeUTF((nombre.getColores().elementAt(i).toString()));
            i++;
        }
        dos.flush();
        byte[] b = bas.toByteArray();
        rstore.addRecord(b, 0, b.length);
        bas.reset();
        dos.close();


        bas.close();

        rstore.closeRecordStore();

    }
}
