#include <stdio.h>
#include <iostream>

#include "Casos.h"
#include "Utils.h"

using std::cout;
using std::cin;
using std::endl;


void menu(){

	Casos casos;
	Utils utils;
	double area;
	double base;
	double altura;
	double profundidad;
	double dano;
	
	cout << ">>>MENU<<<" << endl;
	cout << "1. Piel de cocodrilo" << endl;
	cout << "2. Hueco" << endl;
	cout << "3. Grieta de borde" << endl;
	cout << "4. Salir" << endl;
	cout << "Opcion: ";

	int opcion;
	cin >> opcion;

	switch(opcion){
		case 1:
			cout << "\n\nOpcion: PIEL DE COCODRILO" << endl;
			cout << "- Ingrese el area: ";
			cin >> area;
			cout << "- Ingrese la base: ";
			cin >> base;
			cout << "- Ingrese la altura: ";
			cin >> altura;
			cout << "\n";

			dano = utils.porcentaje_dano(area, base, altura);

			casos.pielCocodrilo(dano);

			menu();
			break;

		case 2:
			cout << "\n\nOpcion: HUECO" << endl;
			cout << "- Ingrese el area: ";
			cin >> area;
			cout << "- Ingrese la base: ";
			cin >> base;
			cout << "- Ingrese la altura: ";
			cin >> altura;
			cout << "- Ingrese la profundidad: ";
			cin >> profundidad;
			cout << "\n";

			dano = utils.porcentaje_dano(area, base, altura);

			casos.hueco(dano, profundidad);

			menu();
			break;

		case 3:
			cout << "\n\nOpcion: GRIETA DE BORDE" << endl;
			cout << "- Ingrese el area: ";
			cin >> area;
			cout << "- Ingrese la base: ";
			cin >> base;
			cout << "- Ingrese la altura: ";
			cin >> altura;
			cout << "\n";

			dano = utils.porcentaje_dano(area, base, altura);

			casos.grietaBorde(dano);

			menu();
			break;

		case 4:
			system("EXIT");
	}
}

int main(int argc, char **argv){

	/*Casos casos;
	Utils utils;
	double area;
	double base;
	double altura;
	double dano;

	cout << "- Ingrese el area: ";
	cin >> area;
	cout << "- Ingrese la base: ";
	cin >> base;
	cout << "- Ingrese la altura: ";
	cin >> altura;

	dano = utils.porcentaje_dano(area, base, altura);

	cout << "\n" << dano << "\n";*/

	menu();
}

