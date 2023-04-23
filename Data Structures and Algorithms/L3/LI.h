#pragma once

typedef int TElem;
class IteratorLI;

class Nod;

//se defineste tipul PNod ca fiind adresa unui Nod

typedef Nod* PNod;

class Nod {
public:

	friend class Lista;
	TElem e;
	//cele doua legaturi - lista  dublu inlantuita
	PNod urm;

};
/*typedef Nod* PNod;

typedef struct {
	TElem e;
	PNod urm;
}Nod;*/


class LI {
private:
	friend class IteratorLI;
	/* aici e reprezentarea */
	PNod prim;
	PNod ultim;

public:
	// constructor implicit
	LI();

	// returnare dimensiune
	int dim() const;

	// verifica daca lista e vida
	bool vida() const;

	// returnare element
	//arunca exceptie daca i nu e valid
	TElem element(int i) const;

	// modifica element de pe pozitia i si returneaza vechea valoare
	//arunca exceptie daca i nu e valid
	TElem modifica(int i, TElem e);

	// adaugare element la sfarsit
	void adaugaSfarsit(TElem e);

	// adaugare element pe o pozitie i 
	//arunca exceptie daca i nu e valid
	void adauga(int i, TElem e);

	// sterge element de pe o pozitie i si returneaza elementul sters
	//arunca exceptie daca i nu e valid
	TElem sterge(int i);

	// cauta element si returneaza prima pozitie pe care apare (sau -1)
	int cauta(TElem e)  const;

	// returnare iterator
	IteratorLI iterator() const;

	//destructor
	~LI();

};
