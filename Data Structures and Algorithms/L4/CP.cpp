
#include "CP.h"
#include <exception>
#include <stdio.h>
#include <iostream>

using namespace std;


CP::CP(Relatie r) {
	/*
	* Complexitati:  
	* Best case = Worst case = Average case : teta(1)
	*/
	this->cp = 4;
	this->prim = -1;
	this->e = new Element[this->cp];
	this->urm = new int[this->cp]; 
	this->prec = new int[this->cp];
	this->initializare_spatiu_liber(this->cp);
	this->r = r;
}


void CP::adauga(TElem e, TPrioritate p) {
	/*
	* Complexitati:
	* Best Case: teta(1)
	* Worst Case: teta(n)
	* Average Case: teta(n)
	* Overall: O(n)
	*/
	int i = creeaza_nod(e, p);
	if (this->prim == -1)
	{
		this->prim = i;
	} 
	else if (r(p, (this->e[this->prim]).second)) {
		//adaug inaintea primului element
		this->prec[this->prim] = i;
		this->urm[i] = this->prim;
		this->prim = i;
	}
	else {
		int poz = this->prim;
		while (urm[poz] != -1 && r(this->e[urm[poz]].second, p)) {
			poz = this->urm[poz];
		}
		if (urm[poz] == -1) {
			//adaug la final
			this->urm[poz] = i;
			this->prec[i] = poz;
		}
		else {
			this->prec[this->urm[poz]] = i;
			this->urm[i] = this->urm[poz];
			this->urm[poz] = i;
			this->prec[i] = poz;
		}
	}
}

//arunca exceptie daca coada e vida
Element CP::element() const {
	/*
	* Complexitati: 
	* Best Case = Worst Case = Average Case: teta(1)
	*/
	if (this->prim == -1)
		throw std::exception();       
	else
		return this->e[this->prim];
}

Element CP::sterge() {
	/*
	* Complexitati: 
	* Best Case = Worst Case = Average Case: teta(1)
	*/
	if (this->prim==-1) 
		throw std::exception();
	else {
		Element elem_sters = this->e[this->prim];
		int poz_primului_elem = this->prim;
		this->prim = this->urm[this->prim];
		dealoca(poz_primului_elem);
		return elem_sters;
	}
}

bool CP::vida() const {
	/* 
	* Complexitati:
	* Best Case = Worst Case = Average Case: teta(1)
	*/
	if (this->prim==-1) 
		return true;
	return false;
}


bool CP::cautare(TElem elem) const {
	/*
	* Complexitati: 
	* Best Case: teta(1) 
	* Worst Case: teta(n)
	* Average Case: teta(n)
	* Overall: O(n)
	*/
	int poz = this->prim;
	while (poz != -1) {
		if (this->e[poz].first == elem) {
			return true;
		}
		poz = this->urm[poz];
	}
	return false;
}

CP::~CP() {
	/*
	* Complexitati: 
	* Best Case = Worst Case = Average Case: teta(n)
	*/
	delete[] this->e; 
	delete[] this->urm;
	delete[] this->prec;
};