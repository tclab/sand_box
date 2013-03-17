/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src;

/**
 *
 * @author juan
 */
public class NotaBO {

    //Descripcion
    private String descripcion;

    //Lista a la que pertenece
    private int lista;

    //Tipo de nota
    private int tipoNota;

     //Sonido
    private byte[] sonido;

    //Dibujo

   

    //Constructor
    public NotaBO(int tipoNota, String descripcion, int lista, byte[] sonido){
        this.descripcion = descripcion;
        this.lista = lista;
        this.tipoNota = tipoNota;
        this.sonido = sonido;
    }


    
    //Getters and Setters
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

     public int getLista() {
        return lista;
    }

    public void setLista(int lista) {
        this.lista = lista;
    }

    public int getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(int tipoNota) {
        this.tipoNota = tipoNota;
    }

    public byte[] getSonido() {
        return sonido;
    }

    public void setSonido(byte[] sonido) {
        this.sonido = sonido;
    }

}
