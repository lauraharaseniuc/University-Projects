#pragma once
#include <iostream>

typedef int TElem;


#define NULL_TELEMENT 0

class Nod {
public:
	TElem e;
	int linie;
	int coloana;
	Nod* st;
	Nod* dr;
};

class Matrice {

private:
	/* aici e reprezentarea */
	Nod* radacina;
	int nr_linii;
	int nr_coloane;

	Nod* creeaza_nod(int l, int c, TElem e) {
		Nod* new_node = new Nod;
		new_node->e = e;
		new_node->linie = l;
		new_node->coloana = c;
		new_node->dr = NULL;
		new_node->st = NULL;
		return new_node;
	}

	Nod* adauga_recursiv(Nod* rad, int l, int c, TElem e) {
		if (rad == NULL) {
			rad=this->creeaza_nod(l, c, e);
		}
		else {
			if (e < rad->e) {
				rad->st=this->adauga_recursiv(rad->st, l, c, e);
			}
			else {
				rad->dr=this->adauga_recursiv(rad->dr, l, c, e);
			}
		}
		return rad;
	} 


	Nod* cauta_recursiv(Nod* rad, int linie, int coloana, TElem elem) {
		if (rad == NULL || (rad->linie==linie && rad->coloana==coloana && rad->e==elem)) {
			return rad;
		}
		else if (elem < rad->e) {
			return cauta_recursiv(rad->st, linie, coloana, elem);
		} 
		else {
			return cauta_recursiv(rad->dr, linie, coloana, elem);
		}
	}

public:

	//constructor
	//se arunca exceptie daca nrLinii<=0 sau nrColoane<=0
	Matrice(int nrLinii, int nrColoane);

	//destructor
	~Matrice() {};

	//returnare element de pe o linie si o coloana
	//se arunca exceptie daca (i,j) nu e pozitie valida in Matrice
	//indicii se considera incepand de la 0
	TElem element(int i, int j) const;


	// returnare numar linii
	int nrLinii() const;

	// returnare numar coloane
	int nrColoane() const;


	// modificare element de pe o linie si o coloana si returnarea vechii valori
	// se arunca exceptie daca (i,j) nu e o pozitie valida in Matrice
	TElem modifica(int i, int j, TElem);

	int valoareMaxima() const;

};








