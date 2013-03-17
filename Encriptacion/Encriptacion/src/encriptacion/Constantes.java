package encriptacion;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

public class Constantes {

    
    Hashtable<String, Integer> letrasNumeros = new Hashtable();
    Hashtable<Integer, String> numerosLetras = new Hashtable();

    /**
     * Constructor
     */
    public Constantes(){
        adiccionarLetrasNumeros();
        adiccionarNumerosLetras();
    }

    private void adiccionarLetrasNumeros(){
        letrasNumeros.put("a", 1);
        letrasNumeros.put("b", 2);
        letrasNumeros.put("c", 3);
        letrasNumeros.put("d", 4);
        letrasNumeros.put("e", 5);
        letrasNumeros.put("f", 6);
        letrasNumeros.put("g", 7);
        letrasNumeros.put("h", 8);
        letrasNumeros.put("i", 9);
        letrasNumeros.put("j", 10);
        letrasNumeros.put("k", 11);
        letrasNumeros.put("l", 12);
        letrasNumeros.put("m", 13);
        letrasNumeros.put("n", 14);
        letrasNumeros.put("o", 15);
        letrasNumeros.put("p", 16);
        letrasNumeros.put("q", 17);
        letrasNumeros.put("r", 18);
        letrasNumeros.put("s", 19);
        letrasNumeros.put("t", 20);
        letrasNumeros.put("u", 21);
        letrasNumeros.put("v", 22);
        letrasNumeros.put("w", 23);
        letrasNumeros.put("x", 24);
        letrasNumeros.put("y", 25);
        letrasNumeros.put("z", 26);
    }

    private void adiccionarNumerosLetras(){
        numerosLetras.put(1, "a");
        numerosLetras.put(2, "b");
        numerosLetras.put(3, "c");
        numerosLetras.put(4, "d");
        numerosLetras.put(5, "e");
        numerosLetras.put(6, "f");
        numerosLetras.put(7, "g");
        numerosLetras.put(8, "h");
        numerosLetras.put(9, "i");
        numerosLetras.put(10, "j");
        numerosLetras.put(11, "k");
        numerosLetras.put(12, "l");
        numerosLetras.put(13, "m");
        numerosLetras.put(14, "n");
        numerosLetras.put(15, "o");
        numerosLetras.put(16, "p");
        numerosLetras.put(17, "q");
        numerosLetras.put(18, "r");
        numerosLetras.put(19, "s");
        numerosLetras.put(20, "t");
        numerosLetras.put(21, "u");
        numerosLetras.put(22, "v");
        numerosLetras.put(23, "w");
        numerosLetras.put(24, "x");
        numerosLetras.put(25, "y");
        numerosLetras.put(26, "z");
    }

    public Hashtable getLetrasNumeros() {
        return letrasNumeros;
    }

    public void setLetrasNumeros(Hashtable<String, Integer> letrasNumeros) {
        this.letrasNumeros = letrasNumeros;
    }

    public Hashtable getNumerosLetras() {
        return numerosLetras;
    }

    public void setNumerosLetras(Hashtable<Integer, String> numerosLetras) {
        this.numerosLetras = numerosLetras;
    }
}
