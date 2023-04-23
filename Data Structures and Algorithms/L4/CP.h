#pragma once
#pragma once
#include <vector>
#include <utility>
#include <iostream>

using namespace std;

typedef int TElem;
typedef int TPrioritate;

typedef std::pair<TElem, TPrioritate> Element;

typedef bool (*Relatie)(TPrioritate p1, TPrioritate p2);

class CP {
private:
	/* aici reprezentarea */
	int cp;
	int prim;
	int primLiber;
	Element* e;
	int* urm;
	int* prec;
	Relatie r;

	void initializare_spatiu_liber(int cp)
	{
		for (int i = 0; i < cp-1; i++)
			this->urm[i] = i + 1;
		this->prec[0] = -1;
		for (int i = 1; i < cp; i++)
			this->prec[i] = i - 1;
		this->urm[cp - 1] = -1;
		this->primLiber = 0;
	} 

	void aloca(int& i) {
		i = this->primLiber;
		this->primLiber = this->urm[this->primLiber];
	}

	void dealoca(int i) {
		this->urm[i] = this->primLiber;
		this->primLiber = i;
	}

	void redimensionare() {
		Element* e_aux = new Element[2 * this->cp]; 
		int* urm_aux = new int[2 * this->cp]; 
		int* prec_aux = new int[2 * this->cp]; 
		for (int i = 0; i < this->cp; i++)
		{
			e_aux[i] = this->e[i];
			urm_aux[i] = this->urm[i]; 
			prec_aux[i] = this->prec[i];
		}
		delete[] this->e;
		delete[] this->urm;
		delete[] this->prec;
		this->e = e_aux;
		this->urm = urm_aux;
		this->prec = prec_aux;
		this->cp = this->cp * 2;
	}

	void reinitializare_spatiu_liber() {
		int old_capacity = this->cp / 2;
		//this->urm[old_capacity - 1] = old_capacity;
		for (int i = old_capacity; i < this->cp - 1; i++)
			this->urm[i] = i + 1; 
		this->urm[this->cp - 1] = -1;
		this->prec[old_capacity] = -1;
		for (int i = old_capacity + 1; i < this->cp; i++) ////  0 1 2 3 ... 4 5 6 7
			this->prec[i] = i - 1;
		this->primLiber = old_capacity;
	}

	int creeaza_nod(TElem e, TPrioritate p) {
		if (this->primLiber == -1) {
			redimensionare();
			reinitializare_spatiu_liber();
		}
		int i;
		aloca(i);
		this->e[i] = Element(e, p);
		this->urm[i] = -1;
		this->prec[i] = -1;
		return i;
	}

public:
	//constructorul implicit
	CP(Relatie r);

	//adauga un element in CP
	void adauga(TElem e, TPrioritate p);

	//acceseaza elementul cel mai prioritar in raport cu relatia de ordine
	//arunca exceptie daca CP e vida
	Element element()  const;

	//sterge elementul cel mai prioritar si il returneaza
	//arunca exceptie daca CP e vida
	Element sterge();

	//verifica daca CP e vida;
	bool vida() const;

	bool cautare(TElem elem) const;

	// destructorul cozii
	~CP();

};
