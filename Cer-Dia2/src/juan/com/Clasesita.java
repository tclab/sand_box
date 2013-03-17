package juan.com;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class Clasesita {
	ArrayList lista = new ArrayList();

	// String nombre = new String("juan diego");
	String posicion = new String();

	public void llenarList(String nombre) {
		int tam = nombre.length();
		for (int i = 0; i < tam; i++) {
			lista.add(posicion.valueOf(nombre.charAt(i)));
		}
		Iterator iter = lista.iterator();
		while (iter.hasNext()) {
			System.out.print(iter.next());
			System.out.print("     ");
		}
	}

	public static void main(String[] args) throws IOException {
		Clasesita a = new Clasesita();
		InputStreamReader conversor = new InputStreamReader(System.in);
		BufferedReader teclado = new BufferedReader(conversor);
		System.out.println("Digite una palabra para ponerla vertical: ");
		String nombreS = teclado.readLine();
		a.llenarList(nombreS);
	}

}
