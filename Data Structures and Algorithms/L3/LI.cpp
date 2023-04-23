#include <exception>
#include <iostream>
#include "LI.h"
#include "IteratorLI.h"

LI::LI() {
	/* de adaugat */
	this->prim = nullptr;
	this->ultim = nullptr;
}

int LI::dim() const {
	/* de adaugat */
	int dimensiune = 0;
	PNod p=this->prim;
	while (p != nullptr)
	{
		dimensiune++;
		p = (*p).urm;
	}
	return dimensiune;
}


bool LI::vida() const {
	/* de adaugat */
	if (this->prim != nullptr)
		return false;
	return true;
}

TElem LI::element(int i) const {
	/* de adaugat */
	PNod p = this->prim; 
	int poz = 0;
	while (p != nullptr && poz!=i)
	{
		poz++;
		p = p->urm;
	}
	if (poz != i)
		throw std::exception();
	else
		return p->e;
}

TElem LI::modifica(int i, TElem e) {
	/* de adaugat */
	PNod p = this->prim; 
	int poz = 0;
	while (p != nullptr && poz!=i)
	{
		p = p->urm;
		poz++;
	} 
	if (poz == i)
	{
		int vechea_valoare = p->e;
		p->e = e;
		return vechea_valoare;
	}
	else
		throw std::exception();
}

PNod creeaza_nod(TElem e)
{
	PNod nod_nou = new Nod;
	nod_nou->e = e;
	nod_nou->urm = nullptr;
	return nod_nou;
}

void LI::adaugaSfarsit(TElem e) {
	/* de adaugat */
	PNod nod = creeaza_nod(e);
	if (this->prim == nullptr)
	{
		this->prim = nod; 
		this->ultim = nod;
	}
	this->ultim->urm = nod;
	this->ultim = nod;
}

void LI::adauga(int i, TElem e) {           //nod 0(prim) 1 2 3 4 5 6 7 8
	/* de adaugat */
	PNod nod = creeaza_nod(e);
	if (i == 0)
	{
		nod->urm = this->prim;
		this->prim = nod;  
		if (this->prim->urm==nullptr) 
			this->ultim = nod;
	}
	else 
	{
		PNod  p = this->prim;
		int poz = 0;
		while (p->urm != nullptr && poz!=i-1)
		{
			p = p->urm;
			poz++;
		}
		if (poz != i - 1)
			throw std::exception();
		//adaugam intre 2 noduri    0 1 2   ...  3 4 5 6 7 8 9 ... 10        dim=10
		else
		{
			nod->urm = p->urm;
			p->urm = nod;
		}
	}
}

TElem LI::sterge(int i) {
	/* de adaugat */
	int poz = 0; 
	PNod p = this->prim;
	if (this->prim == nullptr)
		throw std::exception();
	if (i == 0)
	{
		PNod nod_sters = this->prim;
		TElem valoare_stearsa = nod_sters->e;
		if (this->prim->urm == nullptr)
			this->ultim = nullptr;
		this->prim = this->prim->urm;
		delete nod_sters;
		return valoare_stearsa;
	}
	else
	{
		while (p->urm != nullptr && poz != i - 1)
		{
			p = p->urm;
			poz++;
		}
		if (poz != i - 1 || (poz == i - 1 && p->urm == nullptr))
			throw std::exception();
		else
		{
			if (p->urm->urm == nullptr)
			{
				this->ultim = p;
			}
			PNod nod_sters = p->urm; 
			TElem valoare_stearsa = nod_sters->e;
			p->urm = (p->urm)->urm;
			delete nod_sters;
			return valoare_stearsa;
		}
	}
}

int LI::cauta(TElem e) const {
	/* de adaugat */
	int poz = 0;
	PNod p = this->prim;
	while (p != nullptr && p->e!=e)
	{
		p = p->urm; 
		poz++;
	}
	if (p->e == e)
		return poz;
	return -1;
}

IteratorLI LI::iterator() const {
	return  IteratorLI(*this);
}

LI::~LI() {
	/* de adaugat */
}
