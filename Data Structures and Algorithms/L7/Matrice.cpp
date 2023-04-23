#include "Matrice.h"

#include <exception>

using namespace std;


Matrice::Matrice(int m, int n) {
	/*
	* Complexitati: 
	* Best Case = Worst Case = Average Case : teta(1)
	*/
	if (m <= 0 || n <= 0) {
		throw(std::exception());
	}
	this->radacina = NULL;
	this->nr_linii = m; 
	this->nr_coloane = n;
}



int Matrice::nrLinii() const {
	/*
	* Complexitati:
	* Best Case = Worst Case = Average Case : teta(1)
	*/
	return this->nr_linii;
}


int Matrice::nrColoane() const {
	/*
	* Complexitati:
	* Best Case = Worst Case = Average Case : teta(1)
	*/
	return this->nr_coloane;
}

void cauta_nod_preordine(Nod* rad, int l, int c, Nod& nod_gasit, bool& gasit) {
	if (rad != NULL) {
		if (rad->linie == l && rad->coloana == c) {
			nod_gasit = *rad;
			gasit = true;
			return;
		}
		cauta_nod_preordine(rad->st, l, c, nod_gasit, gasit);
		cauta_nod_preordine(rad->dr, l, c, nod_gasit, gasit);
	}
}


TElem Matrice::element(int i, int j) const {
	/*
	*Complexitati:
	* Best Case: teta(1) 
	* Worst Case: teta(h)
	* Average Case: teta(h)
	* Overall: O(h)            ,h din [log(n), n]
	*/
	if (i >= this->nr_linii || j >= nr_coloane) {
		throw(std::exception());
	}
	else {
		Nod nod_cautat;
		bool gasit = false;
		cauta_nod_preordine(this->radacina, i, j, nod_cautat, gasit);
		if (gasit == false) {
			return NULL_TELEMENT;
		}
		else {
			return nod_cautat.e;
		}
	}
}



TElem Matrice::modifica(int i, int j, TElem e) {
	/* 
	* Complexitati: 
	* Best Case: teta(1)
	* Worst Case: teta(h)
	* Average Case: teta(h)
	* Overall: O(h)
	*/
	if (i >= this->nr_linii || j >= nr_coloane) {
		throw(std::exception());
	}
	Nod* nod_cautat = this->cauta_recursiv(this->radacina, i, j, e);
	if (nod_cautat != NULL) {
		TElem valoare = nod_cautat->e;
		nod_cautat->e = e;
		return valoare;
	}
	else {
		this->radacina = this->adauga_recursiv(this->radacina, i, j, e);
		return 0;
	}
} 


int Matrice::valoareMaxima() const {
	/*
	* Complexitati:
	* Best Case = Worst Case = Average Case: teta(h)
	*/
	if (this->radacina == NULL) {
		return NULL_TELEMENT;
	}
	Nod* rad = this->radacina;
	while (rad->dr != NULL) {
		rad = rad->dr;
	}
	return rad->e;
}


