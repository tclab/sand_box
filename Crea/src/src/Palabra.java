/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src;

/**
 *
 * @author juan
 */
public class Palabra {

    private String descripcion;
    private int lista;

    public Palabra(String descripcion, int lista){
        this.descripcion = descripcion;
        this.lista = lista;
    }

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

}
