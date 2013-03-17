package Agenda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Prueba {

	File prueba;

	public Prueba() {
		prueba = new File("prueba.txt");
	}

	public static void main(String[] args) throws IOException {
		Prueba a = new Prueba();
		a.archivo();
	}

	public void archivo() throws IOException {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(
				System.in));
		BufferedReader archivo = new BufferedReader(new FileReader("prueba.txt"));
		PrintWriter escribir = new PrintWriter(new FileWriter("prueba.txt"));

		System.out.print("nombre?");
		String nombre = teclado.readLine();
		escribir.write(nombre);
		System.out.print("en archivo: ");
		System.out.println(archivo.readLine());
	}

}
