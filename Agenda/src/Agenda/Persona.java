package Agenda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

public class Persona {

	private String nombre;

	private String apellido;

	private String telefono;

	private String fechanacimiento;

	private String salario;

	private BufferedReader teclado;

	private File agenda;

	private Persona() {
		agenda = new File("agenda.txt");
		teclado = new BufferedReader(new InputStreamReader(System.in));
	}

	public void menu() throws IOException {
		System.out
				.println("********** MENU**********\nDigite la opcion que desea: ");
		System.out
				.print("1. Agregar persona\n2. Mostrar agenda\n4. Salir\nOpcion: ");
		String opc = teclado.readLine();
		System.out.println("");
		int opcion = Integer.parseInt(opc);
		switch (opcion) {
		case 1:
			agregarPersona();
			break;
		case 2:
			mostrarAgenda();
			break;
		case 3:
			buscarPersona();
			break;
		case 4:
			System.exit(0);
			break;
		default:
			System.out.println("Opcion invalida, intente de nuevo...\n");
			menu();
		}
	}

	public void buscarPersona() {

	}

	public void agregarPersona() throws IOException {
		System.out.print("Digite el nombre de la persona: ");
		nombre = teclado.readLine();
		System.out.print("Digite el apellido de la persona: ");
		apellido = teclado.readLine();
		agregarTelefono();
		fechanacimiento = agregarFechaNacimiento();
		System.out.print("Digite el salario devengado por la persona: ");
		salario = teclado.readLine();
		PrintWriter a = new PrintWriter(new FileWriter("agenda.txt", true));
		a.write(nombre);
		a.write(",");
		a.write(apellido);
		a.write(",");
		a.write(telefono);
		a.write(",");
		a.write(fechanacimiento);
		a.write(",");
		a.write(salario);
		a.println();
		a.close();
		menu();
	}

	public void agregarTelefono() throws IOException {
		System.out.print("Digite el telefono de la persona: ");
		telefono = teclado.readLine();
		if (telefono.length() < 7) {
			System.out.println("Deben ser 7 digitos, intente de nuevo...");
			agregarTelefono();
		}
	}

	public String agregarFechaNacimiento() throws IOException {

		String fechaEntrada = new String();
		boolean check = false;
		do {
			try {
				System.out.print("Dia de nacimiento (dd): ");
				fechaEntrada = teclado.readLine();
				System.out.print("Mes de nacimiento (mm): ");
				fechaEntrada = fechaEntrada + "/" + teclado.readLine();
				System.out.print("Año de nacimiento (yyyy): ");
				fechaEntrada = fechaEntrada + "/" + teclado.readLine();
				SimpleDateFormat formateador = new SimpleDateFormat(
						"dd/MM/yyyy");
				formateador.parse(fechaEntrada);
				check = true;

			} catch (java.text.ParseException e) {
				// e.printStackTrace();
				System.out
						.println("Formato de fecha invalido, intente de nuevo...");
				agregarFechaNacimiento();
				check = false;
			}
		} while (check == false);
		return fechaEntrada;
	}

	public void mostrarAgenda() throws IOException {
		int persona = 1;
		BufferedReader agend = new BufferedReader(new FileReader(agenda));
		String linea = agend.readLine();
		while (linea != null) {
			System.out.println("CONTACTO " + persona);
			String[] entrada = linea.split(",");
			for (int i = 0; i < entrada.length; i++) {
				switch (i) {
				case 0:
					System.out.println("Nombre: " + entrada[i]);
					break;
				case 1:
					System.out.println("Apellido: " + entrada[i]);
					break;
				case 2:
					System.out.println("Telefono: " + entrada[i]);
					break;
				case 3:
					System.out.println("Fecha de nacimiento: " + entrada[i]);
					break;
				case 4:
					System.out.println("Salario devengado: " + entrada[i]);
					break;
				}
			}
			System.out.println();
			persona++;
			linea = agend.readLine();
		}
		menu();
	}

	public static void main(String[] args) throws IOException {
		Persona p = new Persona();
		p.menu();
	}

}
