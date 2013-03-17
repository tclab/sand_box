/*
 * Ensayo.cpp
 *
 *  Created on: Aug 19, 2009
 *      Author: juan
 */

#include <iostream>;
#include <stdlib.h>; //Tiene la funcion rand para # aleatorios

using std::cout;
using std::endl;

//Apuntadores
int apuntadores(){
	int numero = 12;
		int *refNumero = &numero;

		cout << "Direccion de numero: " << &numero
			 << "\nValor de refNumero: " << refNumero
			 << "\n\nValor de numero: " << numero
			 << "\nValor de *refNumero: " << *refNumero
			 << "\n\nPrueba de que * y & son inversos uno del otro..."
			 << "\n&*refNumero: " << &*refNumero
			 << "\n*&refNumero: " << *&refNumero
			 <<endl;
}

//Generacion de numeros aleatorios
int aleatorio(){

	int numeroAleatorio = rand();

	cout << "Aleatorio del 0 a RAND_MAX: " << numeroAleatorio
		 << "\nAleatorio de 1 a RAND_MAX: " << numeroAleatorio +1
		 << endl;
	numeroAleatorio = rand() % 5;

	cout << "Aleatorio con semilla 5 (0 a 5): " << numeroAleatorio
		 << endl;

	numeroAleatorio = 1 + rand() % 5;

	cout << "Aleatorio del 0 al 5: " << numeroAleatorio
		 << endl;

	return 0;
}

int main(){

	aleatorio();

	return 0;
}
