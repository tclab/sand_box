package src;


import java.util.Vector;

public class DibujoBO {

    String nombre;
    int color;
    Vector coordenadas;

    DibujoBO(String nombre, Vector coordenadas, int color) {
        this.nombre = nombre;
        this.coordenadas = coordenadas;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Vector getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(Vector coordenadas) {
        this.coordenadas = coordenadas;
    }
}
