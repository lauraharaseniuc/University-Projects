#include "IteratorColectie.h"
#include "Colectie.h"


IteratorColectie::IteratorColectie(const Colectie& c) : col(c) {
	/*
	* Complexitati: 
	* Best Case = Worst Case = Average Case : teta(1)
	* Overall: teta(1) 
	*/
	this->curent = 0;
	this->frecventa = 1;
}

void IteratorColectie::prim() {
	/*
	* Complexitati: 
	* Best Case = Worst Case = Average Case : teta(1)
	* Overall: teta(1) 
	*/
	this->curent = 0; 
	this->frecventa = 1;
}


void IteratorColectie::urmator() {
	/*
	* Complexitati: 
	* Best Case = Worst Case = Average Case : teta(1)
	* Overall: teta(1) 
	*/
	if (this->frecventa < this->col.v[this->curent].frecv)
		this->frecventa++;
	else
	{
		this->curent++;
		this->frecventa = 1;
	}
}


bool IteratorColectie::valid() const {
	/*
	* Complexitati: 
	* Best Case = Worst Case = Average Case : teta(1)
	* Overall: teta(1) 
	*/
	if (this->curent < this->col.n)
		return true;
	return false;
}



TElem IteratorColectie::element() const {
	/*
	* Complexitati: 
	* Best Case = Worst Case = Average Case : teta(1)
	* Overall: teta(1) 
	*/
	if (valid())
		return this->col.v[this->curent].element;
	return -1;
}
