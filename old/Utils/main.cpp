// SIGUIENTE UTILIDAD: 2

/* 
 * File:   main.cpp
 * Author: Juan Diego Toro Cano
 *
 * Created on August 9, 2009, 10:32 PM
 */

#include <stdlib.h>
#include <iostream>
#include <cstdlib> //tiene la definicion de la funcion RAND (random)
using std::cout;
using std::endl;
using std::cin;

//Utilidad 1: Generador de numeros aleatorios
int numeroRandom(){

    cout  << "+++NUMEROS ALEATORIOS+++" <<  endl;
    int semilla;
    int cantidad;

    cout << "Semilla: ";
    cin >> semilla;

    cout << "Cuantos números: ";
    cin >> cantidad;
    cout << endl;

    for (int contador =1 ; contador <= cantidad ; contador++){
          cout << (1 + rand() % semilla) << endl; //mostramos por pantalla los numeros generados del 1 a la semilla seleccionada
    }
}

//Genera el menu
char menu(){

    cout << endl;
    int opcion = 0;

    //Vista
    cout << ".:UTILIDADES:." << endl;
    cout << "1. Generador de números aleatorios" << endl;
    //... NUEVA OPCION AQUI!!...
    cout << endl;
    cout << "... cero para terminar..." << endl;
    cout << "Opcion: ";
    cin >> opcion;
    cout << endl;

    //Control
    switch(opcion){
        case 0: return 0;

        case 1: numeroRandom();
        break;
    }
    menu();
}

int main(int argc, char** argv) {
    menu();
}

