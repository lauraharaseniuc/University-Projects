#include "Colectie.h"
#include "IteratorColectie.h"
#include <exception>
#include <iostream>

#define INITIAL_CAPACITY 182659

using namespace std;

int hash_code(TCheie c) {
	return abs(c);
}

int Colectie::d(TCheie c) const{
	return hash_code(c) % this->size;
}


Colectie::Colectie() {
	/* de adaugat */
	/* 
	* Complexitati: 
	* Best Case = Average Case= Worst Case : teta(n)
	*/
	this->size = INITIAL_CAPACITY;
	this->ch = new TCheie[INITIAL_CAPACITY];
	this->urm = new int[INITIAL_CAPACITY];
	this->primLiber = 0;

	initializare_spatiu_chei();
	initializare_spatiu_urm();
}


void Colectie::adauga(TElem elem) {
	/* de adaugat */
	/* 
	* Complexitati: 
	* Best Case : teta(1)
	* Average Case : teta(n)
	* Worst Case : teta(n)
	* Overall: O(n)
	*/
	int i = d(elem);
	int inf = INF;
	if (this->ch[i] == inf) {
		this->ch[i] = elem;
		if (this->primLiber == i) {
			deplasare_prim_liber();
		}
	}
	else {
		int anterior=0;
		while (i!= -1) {
			anterior = i;
			i = this->urm[i];
		}
		if (this->primLiber >= this->size) {
			redisperasare_elemente();
		}
		//adauga
		this->ch[this->primLiber] = elem; 
		this->urm[anterior] = this->primLiber;
		deplasare_prim_liber();
	}
}


bool Colectie::sterge(TElem elem) {
	/* de adaugat */ 
	/* 
	* Complexitati: 
	* Worst Case :  teta(n)
	* Best Case: teta(1)
	* Average Case: teta(n) 
	* Overall: O(n)
	*/
	int valoare_dispersie = this->d(elem);
	int i= 0;
	int precedent = -1;
	bool gata = false;

	while (i < this->size && precedent==-1) {
		if (this->urm[i] == valoare_dispersie) {
			precedent = i;
		}
		else {
			i++;
		}
	} 

	i = valoare_dispersie;
	int j = precedent;

	while (i != -1 && this->ch[i]!=elem) {
		j = i;
		i = this->urm[i];
	}
	if (i == -1) {
		//cheia nu exista
		return false;
	}

	int val = this->ch[i];
	gata = false;
	do {
		int p = this->urm[i];
		int pp = i; 
		
		while (p != -1 && this->d(this->ch[p]) != i) {
			pp = p;
			p = this->urm[p];
		}
		if (p == -1)
			gata = true;
		else {
			this->ch[i] = this->ch[p];
			i = p;
			j = pp;
		}
		
	} while (gata == false);

	if (j != -1) {
		this->urm[j] = this->urm[i];
	} 
	this->ch[i] = INF;
	this->urm[i] = -1;

	if (i < this->primLiber)
		this->primLiber = i;
	return true;
}


bool Colectie::cauta(TElem elem) const{
	/* de adaugat */
	/*
	* Complexitati: 
	* Best Case: teta(1)
	* Worst Case: teta(n) 
	* Average Case: teta(n) 
	* Overall: O(n)
	*/
	int poz = this->d(elem);
	while (poz != -1 && this->ch[poz]!=elem) {
		poz = this->urm[poz];
	}
	if (poz==-1) 
		return false;
	return true;
}

int Colectie::nrAparitii(TElem elem) const {
	/* de adaugat */
	/* 
	* Complexitati: 
	* Best Case = Worst Case = Average Case: teta(n)
	*/
	int poz = this->d(elem);
	int nr_aparitii = 0; 
	while (poz != -1) { 
		if (this->ch[poz] == elem) {
			nr_aparitii++;
		}
		poz = this->urm[poz];
	}
	return nr_aparitii;
} 

int Colectie::diferenta() const { 
	/*
	* Complexitati:
	* Best Case = Worst Case = Average Case: teta(n)
	*/
	if (this->primLiber == 0)
		return -1;
	int inf = INF;
	int mx = (-1)*INF;
	int mm = INF;
	for (int i = 0; i < this->size; i++) {
		if (this->ch[i] != inf) {
			if (this->ch[i] > mx)
				mx = this->ch[i];
			if (this->ch[i] < mm)
				mm = this->ch[i];
		}
	} 
	return mx - mm;
}


int Colectie::dim() const {
	/* de adaugat */
	/* 
	* Complexitati:
	* Best Case = Worst Case = Average Case: teta(n)
	*/
	int inf = INF;
	int dimensiune = 0;
	for (int i = 0; i < this->size; i++) {
		if (this->ch[i] != inf) {
			dimensiune++;
		}
	}
	return dimensiune;
}


bool Colectie::vida() const {
	/* de adaugat */
	/* 
	* Complexitati: Best Case = Worst Case = Average Case: teta(1)
	*/
	return this->primLiber == 0;
}

IteratorColectie Colectie::iterator() const {
	/*
	* Complexitati: Best Case = Worst Case = Average Case: teta(1)
	*/
	return  IteratorColectie(*this);
}


Colectie::~Colectie() {
	/* de adaugat */ 
	/*
	* Complexitati: Best Case = Worst Case = Average Case: teta(n)
	*/
	delete[] this->ch;
	delete[] this->urm;
}


