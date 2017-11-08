package src;

import java.util.Vector;
import javax.microedition.rms.*;
import java.io.*;

public class PersistenciaDAO {

    public static RecordStore rstore;

    public static Vector leerDatos() throws Exception {

        Vector dibujos = new Vector();
        rstore = RecordStore.openRecordStore("coordenadas", true);

        DibujoBO dibujo;
        RecordEnumeration re = rstore.enumerateRecords(null, null, true);

        while (re.hasNextElement()) {
            byte[] b = re.nextRecord();

            ByteArrayInputStream bais = new ByteArrayInputStream(b);
            DataInputStream dis = new DataInputStream(bais);

            String nombre = dis.readUTF();
            int color = dis.readInt();
            int a = dis.readInt();

            System.out.println("mi nombre" + nombre);
            
            Vector coordenadas = new Vector(a);
            
            for (int i = 0; i < a; i++) {
                coordenadas.addElement(dis.readUTF());
            }
            
            bais.reset();
            dis.reset();

            dibujo = new DibujoBO(nombre, coordenadas, color);
            dibujos.addElement(dibujo);
            
            bais.close();
            dis.close();
        }

        rstore.closeRecordStore();
        return dibujos;
    }

    public static void guardarDatos(DibujoBO nombre) throws Exception {

        rstore = RecordStore.openRecordStore("coordenadas", true);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        System.out.println("mi nombre" + nombre.getNombre());

        dos.writeUTF(nombre.getNombre());
        dos.writeInt(nombre.getColor());
        int tamanoVector = nombre.getCoordenadas().size();
        dos.writeInt(tamanoVector);

        for (int i = 0; i < tamanoVector; i++) {
            dos.writeUTF((nombre.getCoordenadas().elementAt(i).toString()));
            System.out.println("Coordenada" + nombre.getCoordenadas().elementAt(i).toString());
        }

        dos.flush();
        byte[] b = baos.toByteArray();
        rstore.addRecord(b, 0, b.length);

        baos.reset();
        
        dos.close();
        baos.close();

        rstore.closeRecordStore();
    }
}
