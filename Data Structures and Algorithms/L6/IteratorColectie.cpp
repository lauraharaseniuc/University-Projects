#include "IteratorColectie.h"
#include "Colectie.h"
#include <exception>
#define INF 99999999;


IteratorColectie::IteratorColectie(const Colectie& c) : col(c) {
	/* de adaugat */
	/*
	* Complexitati: Best Case = Worst Case = Average Case: teta(1)
	*/
	this->curent = 0;
}

void IteratorColectie::prim() {
	/* de adaugat */
	/*
	* Complexitati: Best Case = Worst Case = Average Case: teta(1)
	*/
	this->curent = 0;
}


void IteratorColectie::urmator() {
	/* de adaugat */
	/*
	* Complexitati: 
	* Best Case : teta(1) 
	* Worst Case :teta(n) 
	* Average Case: teta(n) 
	* Overall: O(n)
	*/
	int inf = INF;
	if (this->curent >= col.size || col.ch[this->curent] == inf) {
		throw std::exception();
	}
	this->curent++;
	while (this->curent < col.size && col.ch[this->curent] == inf)
		this->curent++;
}


bool IteratorColectie::valid() const {
	/* de adaugat */
	/*
	* Complexitati: Best Case = Worst Case = Average Case: teta(1)
	*/
	int inf = INF;
	return this->curent<col.size && col.ch[this->curent]!=inf;
}



TElem IteratorColectie::element() const {
	/* de adaugat */
	/*
	* Complexitati: Best Case = Worst Case = Average Case: teta(1)
	*/
	int inf = INF;
	if (this->curent >= col.size || col.ch[this->curent] == inf)
		throw std::exception();
	return col.ch[this->curent];
}
