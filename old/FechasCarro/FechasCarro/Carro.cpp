#include <iostream>
#include <string>

using namespace std;

float opercion(string op, int n1, int n2){
	float resultado;
	
	if(strcmp(op, "+") == 0){
		resultado = n1 + n2;
	} else if(strcmp(op,"-") == 0){
		resultado = n1-n2;
	} else if (strcmp(op,"*") == 0){
		resultado = n1*n2;
	} else if (strcmp(op,"/") == 0){
		resultado = n1/n2;
	}
	return resultado;
}


int main(){
	int n1;
	int n2;
	string op;
	float res;

	cout << "n1: ";
	cin >> n1;

	cout << "n2: ";
	cin >> n2;

	cout << "Op: ":
	cin >> op;

	res = operacion(op, n1, n2);

	cout << "Resultado: " << res << endl;

	cin >> res;

	return 0;
}
