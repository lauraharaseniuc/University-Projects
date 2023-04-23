#include "IteratorLI.h"
#include "LI.h"
#include <exception>

IteratorLI::IteratorLI(const LI& li) : lista(li) {
    /* de adaugat */
    this->curent = lista.prim;
}

void IteratorLI::prim() {
    /* de adaugat */
    this->curent = lista.prim;
}

void IteratorLI::urmator() {
    /* de adaugat */
    if (this->curent == nullptr)
        throw std::exception();
    else
        this->curent = this->curent->urm;
}

bool IteratorLI::valid() const {
    /* de adaugat */
    if (this->curent != nullptr)
        return true;
    return false;
}

TElem IteratorLI::element() const {
    /* de adaugat */
    if (this->curent != nullptr)
        return this->curent->e;
    return -1;
}