/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package encriptacion;

import java.util.Scanner;

/**
 *
 * @author JUAN
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Metodos simpleE = new Metodos();
        

        String resultado = simpleE.simpleEncriptacion();

        System.out.println("RESULTADO: " + resultado);
    }

}
