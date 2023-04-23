#include "Colectie.h"
#include "IteratorColectie.h"
#include <exception>
#include <iostream>

using namespace std;


Colectie::Colectie() {
	/*
	* Complexitati: 
	* Best Case = Worst Case = Average Case : teta(1)
	* Overall: teta(1) 
	*/
	this->cp = 4;
	this->n = 0;
	this->v = new Pereche [this->cp]; 

}


void Colectie::adauga(TElem elem) {
	/* 
	* Complexitati: 
	* Best Case: teta(1)
	* Averge Case: teta(n) 
	* Worst Case: teta(n) 
	* Overall: O(n)
	* teta(1) amortizat
	*/
	int poz = 0; 
	bool found = false;
	while (poz < this->n && !found)
	{
		if (elem == this->v[poz].element)
		{
			this->v[poz].frecv++;
			found = true;
		} 
		poz++;
	}
	if (!found)
	{
		if (this->cp == this->n)
		{
			Pereche* v_aux;
			v_aux = new Pereche [2 * this->cp];
			for (int i = 0; i < this->n; i++)
				v_aux[i] = this->v[i]; 
			delete[] this->v;
			this->v = v_aux;
			this->cp = this->cp * 2;
		}
		this->v[this->n].element = elem; 
		this->v[this->n].frecv = 1;
		this->n++;
	} 
}


bool Colectie::sterge(TElem elem) {
	/*
	* Complexitati: 
	* Best Case: teta(1)
	* Averge Case: teta(n)
	* Worst Case: teta(n) 
	* Overall: O(n)
	* teta(1) amortizat
	*/
	int poz = 0;
	bool found = false;
	while (poz < this->n && !found)
	{
		if (elem == this->v[poz].element)
		{
			found = true;
		}
		poz++;
	} 
	if (found)
	{
		if (this->v[poz - 1].frecv > 1)  
			this->v[poz - 1].frecv--; 
		else
		{
			for (int i = poz - 1; i < this->n-1; i++)
				this->v[i] = this->v[i + 1];
			this->n--;
			if (this->n < this->cp / 4)
			{
				Pereche* v_aux;
				v_aux = new Pereche[this->cp/2];
				for (int i = 0; i < this->n; i++)
					v_aux[i] = this->v[i];
				delete[] this->v;
				this->v = v_aux;
				this->cp = this->cp / 2;
			}
		}
		return true;
	}
	return false;
}


bool Colectie::cauta(TElem elem) const {
	/*
	* Complexitati: 
	* Best Case: teta(1)
	* Averge Case: teta(n) 
	* Worst Case: teta(n) 
	* Overall: O(n) 
	*/
	int poz = 0;
	bool found = false;
	while (poz < this->n && !found)
	{
		if (elem == this->v[poz].element)
		{
			found = true;
		}
		poz++;
	}
	return found;
}

int Colectie::nrAparitii(TElem elem) const {
	/*
	* Complexitati: 
	* Best Case: teta(1)
	* Averge Case: teta(n) 
	* Worst Case: teta(n) 
	* Overall: O(n) 
	*/
	for (int poz = 0; poz < this->n; poz++)
		if (this->v[poz].element == elem)
			return this->v[poz].frecv;
	return 0;
}


int Colectie::dim() const {
	/*
	* Complexitati: 
	* Best Case = Worst Case = Average Case : teta(n)
	* Overall: teta(n) 
	*/
	int size = 0; 
	for (int i = 0; i < this->n; i++)
		size += this->v[i].frecv; 
	return size;
}


bool Colectie::vida() const {
	/*
	* Complexitati: 
	* Best Case = Worst Case = Average Case : teta(1)
	* Overall: teta(1) 
	*/
	if (this->n == 0)
		return true;
	return false;
}

IteratorColectie Colectie::iterator() const {
	/* 
	* Complexitati: 
	* Best Case = Worst Case = Average Case : teta(1)
	* Overall: teta(1) 
	*/
	return  IteratorColectie(*this);
}


Colectie::~Colectie() {
	/*
	* Complexitati: 
	* Best Case = Worst Case = Average Case : teta(1)
	* Overall: teta(1) 
	*/
	delete[] this->v;
}
