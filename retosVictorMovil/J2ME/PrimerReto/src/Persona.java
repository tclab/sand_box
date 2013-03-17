
import java.util.Vector;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Victor
 */
public class Persona {

    private String nombre;
    private String genero;
    private Vector aficiones;
    private int pos = 0;

    public Persona(String nombre, String genero, Vector aficiones, int pos) {
        this.nombre = nombre;
        this.genero = genero;
        this.aficiones = aficiones;
        this.pos = pos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public int getPos() {
        return pos;
    }

    public Vector getAficiones() {
        return aficiones;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public void setAficiones(Vector aficiones) {
        this.aficiones = aficiones;
    }
}
