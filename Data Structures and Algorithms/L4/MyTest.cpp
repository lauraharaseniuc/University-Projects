#include "CP.h"
#include <assert.h> 
#include "MyTest.h"

bool my_rel(TPrioritate p1, TPrioritate p2) {
	if (p1 <= p2) {
		return true;
	}
	else {
		return false;
	}
}


void my_test() {
	CP coada(my_rel);
	assert(coada.cautare(1) == false);
	coada.adauga(2, 2);
	coada.adauga(4, 4); 
	coada.adauga(1, 1); 
	coada.adauga(6, 6); 
	assert(coada.cautare(2) == true); 
	assert(coada.cautare(6) == true); 
	assert(coada.cautare(3) == false);
	assert(coada.cautare(1) == true);
	coada.sterge();
	assert(coada.cautare(1) == false);
}