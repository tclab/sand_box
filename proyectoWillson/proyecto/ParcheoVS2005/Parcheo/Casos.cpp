#include <iostream>

#include "Casos.h"

using std::cout;
using std::cin;
using std::endl;

Casos::Casos(void){}

void Casos::pielCocodrilo(double dano){
	
	if(dano < 10){
		cout << "Dano: " << dano << endl;
		cout << "No hacer nada." << endl;
	}else if ((dano > 10) && (dano < 20)){
		cout << "Dano: " << dano << endl;
		cout << "Parcheo parcial" << endl;
	}else if ((dano > 20) && (dano < 30)){
		cout << "Dano: " << dano << endl;
		cout << "Parcheo full" << endl;
	}else if (dano > 30){
		cout << "Indefinido" << endl;
	}
	cout << "\n\n";
}



void Casos::hueco(double dano, double profundidad){
	
	if(dano < 10){
		cout << "Dano: " << dano << endl;
		cout << "No hacer nada." << endl;
	}else if ((dano > 10) && (dano < 20)){
		cout << "Dano: " << dano << endl;
		if (profundidad <= 25){
			cout << "Profundidad: " << profundidad << endl;
			cout << "Parcheo parcial" << endl;
		} else if (profundidad > 25){
			cout << "Profundidad: " << profundidad << endl;
			cout << "Parcheo profundo" << endl;
		}
	}
	cout << "\n\n";
}



void Casos::grietaBorde(double dano){
	
	if(dano < 10){
		cout << "Dano: " << dano << endl;
		cout << "No hacer nada." << endl;
	}else if ((dano > 10) && (dano < 20)){
		cout << "Dano: " << dano << endl;
		cout << "Parcheo parcial" << endl;
	}else if ((dano > 20) && (dano < 30)){
		cout << "Dano: " << dano << endl;
		cout << "Parcheo full" << endl;
	}else if (dano > 30){
		cout << "Indefinido" << endl;
	}
	cout << "\n\n";
}
