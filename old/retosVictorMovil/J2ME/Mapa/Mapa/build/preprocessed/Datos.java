/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lab18412
 */
public class Datos {
    String nombre;
    String comentarios;
    int cordx,cordy;
    public Datos(String nombre,String comentarios,int cordx,int cordy){
        this.nombre=nombre;
        this.comentarios=comentarios;
        this.cordx=cordx;
        this.cordy=cordy;

    }
    public String getNombre(){
        return nombre;
    }
    public String getComentarios(){
        return comentarios;
    }
    public int getX(){
        return cordx;
    }
    public int getY(){
        return cordy;
    }
    public void SetX(int cordx){
        this.cordx=cordx;
    }
    public void SetY(int cordy){
        this.cordy=cordy;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public void setComentarios(String comentarios){
        this.comentarios=comentarios;
    }
}
