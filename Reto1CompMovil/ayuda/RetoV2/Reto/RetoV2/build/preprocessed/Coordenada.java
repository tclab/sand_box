
import java.util.Vector;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Victor
 */
public class Coordenada {
     String nombre;
     int color;
    Vector coord;
    Vector coord1;
    Vector numV;
    Vector colores;

    public Vector getColores() {
        return colores;
    }

    public void setColores(Vector colores) {
        this.colores = colores;
    }
    Coordenada(String nombre, Vector coord,Vector coord1,Vector numV,Vector colores,int color  ){
        this.nombre=nombre;
        this.coord=coord;
        this.coord1=coord1;
        this.numV=numV;
        this.colores=colores;
        this.color=color;

    }
    public int getColor(){
        return color;
    }
    public void setColor(int color){
        this.color=color;
    }
     public String getNombre(){
         return nombre;
     }
     public void setNombre(String nombre){
         this.nombre=nombre;
     }
     public Vector getCoord() {
        return coord;
    }
     public void setCoord(Vector coord) {
        this.coord=coord;
    }
      public Vector getCoord1() {
        return coord1;
    }
     public void setCoord1(Vector coord1) {
        this.coord1=coord1;
    }
      public Vector getNumV() {
        return numV;
    }
     public void setNumV(Vector numV) {
        this.numV=numV;
    }

}
