package encriptacion;

import java.util.Hashtable;
import java.util.Scanner;

/**
 *
 * @author JUAN
 */
public class Metodos {

    private Constantes constantes = new Constantes();
    private Hashtable<String, Integer> letrasNumeros = constantes.getLetrasNumeros();
    private Hashtable<Integer, String> numerosLetras = constantes.getNumerosLetras();

    public Metodos(){
    }

    //TODO: Determinar cual es el incremento maximo posible y mostrarlo antes de pedirlo.
    public String simpleEncriptacion(){

        String encriptada = new String();
        Scanner teclado = new Scanner(System.in);

        System.out.println("Escriba la cadena: ");
        String cadena = teclado.nextLine();

        System.out.println("Escriba el incremento: ");
        int incremento = Integer.parseInt(teclado.nextLine());
        

        for(int i = 0; i < cadena.length(); i++){
            encriptada += numerosLetras.get(letrasNumeros.get(String.valueOf(cadena.charAt(i))) + incremento);
        }
        return encriptada;
    }

}
