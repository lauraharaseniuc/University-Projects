#pragma once

#define NULL_TELEM -1
#define INF 99999999;
#define NULL_URM -1;
typedef int TElem;
typedef TElem TCheie;

class IteratorColectie;

class Colectie
{
	friend class IteratorColectie;

private:
	/* aici e reprezentarea */
	int size;
	TCheie* ch;
	int* urm;
	int primLiber;

	void initializare_spatiu_chei() {
		for (int i = 0; i < this->size; i++) {
			this->ch[i] = INF;
		}
	}

	void initializare_spatiu_urm() {
		for (int i = 0; i < this->size; i++) {
			this->urm[i] = NULL_URM;
		}
	}

	void deplasare_prim_liber() {
		int inf = INF;
		this->primLiber += 1;
		while (this->primLiber < this->size && this->ch[this->primLiber] != inf) {
			this->primLiber += 1;
		}
	}

	void redisperasare_elemente() {
		TCheie* copie_chei = new TCheie[this->size];


		for (int i = 0; i < this->size; i++) {
			copie_chei[i] = this->ch[i];
		}

		delete[] this->ch;
		delete[] this->urm;
		this->ch = new TCheie[this->size * 2]; 
		this->urm = new int[this->size * 2];
		initializare_spatiu_chei();
		initializare_spatiu_urm();

		for (int i = 0; i < this->size; i++) {
			this->adauga(copie_chei[i]);
		}
		this->size = this->size * 2;

		delete[] copie_chei;

		this->primLiber = 0;
	}

	int d(TCheie e) const;

public:
	//constructorul implicit
	Colectie();

	//adauga un element in colectie
	void adauga(TElem e);

	//sterge o aparitie a unui element din colectie
	//returneaza adevarat daca s-a putut sterge
	bool sterge(TElem e);

	//verifica daca un element se afla in colectie
	bool cauta(TElem elem) const;

	//returneaza numar de aparitii ale unui element in colectie
	int nrAparitii(TElem elem) const;


	//intoarce numarul de elemente din colectie;
	int dim() const;

	//verifica daca colectia e vida;
	bool vida() const;

	int diferenta() const;

	//returneaza un iterator pe colectie
	IteratorColectie iterator() const;

	// destructorul colectiei
	~Colectie();

};


