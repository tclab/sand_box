/*
 * Utils.cpp
 *
 *  Created on: Aug 19, 2009
 *      Author: juan
 */

#include <iostream>;
#include <stdlib.h>;

using std::cout;
using std::endl;

int numeroAleatorio(int semilla){

	int numero = 1 + rand() % semilla;

	cout << "Numero aleatorio: " << numero << endl;
}


//TODO: Crear menu.
int main(){

	numeroAleatorio(10);

	return 0;
}
