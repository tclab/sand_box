#include "Utils.h"

const double PI = 3.1415926;

Utils::Utils(void){}

double Utils::porcentaje_dano(double area, double base, double altura){
	return area/(base*altura);
}

double Utils::diametroHueco(double area){
	return sqrt((4*area)/PI);
}
