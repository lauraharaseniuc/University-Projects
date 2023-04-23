#include "TestScurt.h"
#include <assert.h>
#include "Matrice.h"
#include <iostream>

using namespace std;

void testAll() { //apelam fiecare functie sa vedem daca exista
	Matrice m(4, 4);
	assert(m.nrLinii() == 4);
	assert(m.nrColoane() == 4);
	//adaug niste elemente
	m.modifica(1, 1, 5);
	assert(m.element(1, 1) == 5);
	m.modifica(1, 1, 6);
	assert(m.element(1, 2) == NULL_TELEMENT);
}

void myTest() {
	Matrice m(5, 5); 
	assert(m.valoareMaxima() == NULL_TELEMENT); 
	m.modifica(0, 0, 12);
	assert(m.valoareMaxima() == 12);
	m.modifica(0, 4, 3);
	assert(m.valoareMaxima() == 12);
	m.modifica(1, 0, 100);
	assert(m.valoareMaxima() == 100);
}
