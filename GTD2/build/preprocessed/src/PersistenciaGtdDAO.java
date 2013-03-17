package src;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Vector;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;

/**
 *
 * @author juan
 */
public class PersistenciaGtdDAO {

    public static RecordStore rstore;

    public static void guardarNota(NotaBO nota) throws Exception {

        rstore = RecordStore.openRecordStore("notas", true);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        System.out.println("Descripcion nota guardar: " + nota.getDescripcion());

        //Propiedades de la nota
        dos.writeInt(nota.getTipoNota());
        dos.writeUTF(nota.getDescripcion());
        dos.writeInt(nota.getLista());

        dos.flush();
        byte[] b = baos.toByteArray();
        rstore.addRecord(b, 0, b.length);

        baos.reset();

        dos.close();
        baos.close();

        rstore.closeRecordStore();
    }

    public static Vector leerNotas() throws Exception {

        Vector notas = new Vector();
        rstore = RecordStore.openRecordStore("notas", true);

        NotaBO nota;
        RecordEnumeration re = rstore.enumerateRecords(null, null, true);

        while (re.hasNextElement()) {
            byte[] b = re.nextRecord();

            ByteArrayInputStream bais = new ByteArrayInputStream(b);
            DataInputStream dis = new DataInputStream(bais);

            //Propiedades de la nota
            int tipoNota = dis.readInt();
            String descripcion = dis.readUTF();
            int lista = dis.readInt();

            System.out.println("Descripcion nota leer: " + descripcion);

            bais.reset();
            dis.reset();

            nota = new NotaBO(tipoNota,descripcion, lista);
            notas.addElement(nota);

            bais.close();
            dis.close();
        }

        rstore.closeRecordStore();
        return notas;
    }

    public static void actualizarNota(String nota, int lista) {
        try {

            //Se leeen las notas guardadas y se abre el RecordStrore
            Vector datosGuardados = leerNotas();

            rstore = RecordStore.openRecordStore("notas", true);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);

            //Se busca la nota con la misma descripcion y se modifica la lista
            NotaBO guardada = new NotaBO(0,null, 0);
            NotaBO aModificar = new NotaBO(0,null, 0);
            for (int i = 0; i < datosGuardados.size(); i++) {

                guardada = (NotaBO) datosGuardados.elementAt(i);

                if (nota.equals(guardada.getDescripcion())) {
                    aModificar = (NotaBO)datosGuardados.elementAt(i);
                    aModificar.setLista(lista);
                    datosGuardados.removeElementAt(i);
                    datosGuardados.addElement(aModificar);
                    System.out.println("Nota actualizada!!");
                    break;
                }
            }
            guardada = new NotaBO(0,null, 0);

            //Se actualizan las notas en el RecordStore
            NotaBO aGuardar = new NotaBO(0,null, 0);
            rstore.closeRecordStore();
            rstore.deleteRecordStore("notas");
            rstore = RecordStore.openRecordStore("notas", true);
            for (int j = 0; j < datosGuardados.size(); j++) {

                aGuardar = (NotaBO) datosGuardados.elementAt(j);

                //Propiedades de la nota
                dos.writeInt(aGuardar.getTipoNota());
                dos.writeUTF(aGuardar.getDescripcion());
                dos.writeInt(aGuardar.getLista());

                dos.flush();

                byte[] b = baos.toByteArray();
                rstore.addRecord(b, 0, b.length);

                baos.reset();
            }

            dos.close();
            baos.close();

            rstore.closeRecordStore();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void eliminarNota(NotaBO nota) {

        try {

            Vector notasGuardadas = leerNotas();

            rstore = RecordStore.openRecordStore("notas", true);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);

            NotaBO guardada = new NotaBO(0,null, 0);

            //Se recorre todo el vector preguntando por la nota a eliminar
            for (int i = 0; i < notasGuardadas.size(); i++) {

                guardada = (NotaBO) notasGuardadas.elementAt(i);

                if (nota.getDescripcion().equals(guardada.getDescripcion())) {
                    notasGuardadas.removeElementAt(i);
                    break;
                }
            }

            //Se actulizan las notas en el RecordStore
            NotaBO guardar = new NotaBO(0, null, 0);
            rstore.closeRecordStore();
            rstore.deleteRecordStore("notas");
            rstore = RecordStore.openRecordStore("notas", true);
            for (int j = 0; j < notasGuardadas.size(); j++) {

                guardar = (NotaBO) notasGuardadas.elementAt(j);

                //Propiedades de la nota
                dos.writeInt(guardar.getTipoNota());
                dos.writeUTF(guardar.getDescripcion());
                dos.writeInt(guardar.getLista());

                dos.flush();

                byte[] b = baos.toByteArray();
                rstore.addRecord(b, 0, b.length);

                baos.reset();
            }

            System.out.println("Nota borrada!!");

            dos.close();
            baos.close();

            rstore.closeRecordStore();

        } catch (Exception e) {
        }
    }

    public static NotaBO consultarNota(String descripcion){

        NotaBO consultada = new NotaBO(0, null, 0);

        try{
            Vector notasGuardadas = leerNotas();
            
            //Se recorre todo el vector preguntando por la nota a eliminar
            NotaBO guardada = new NotaBO(0,null, 0);
            for (int i = 0; i < notasGuardadas.size(); i++) {

                guardada = (NotaBO) notasGuardadas.elementAt(i);

                if (descripcion.equals(guardada.getDescripcion())) {
                    consultada = (NotaBO)notasGuardadas.elementAt(i);
                    break;
                }
            }
        }catch(Exception e){}

        return consultada;
    }
}
