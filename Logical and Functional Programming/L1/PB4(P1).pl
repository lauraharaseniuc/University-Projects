%substituie(L: Lista, E:Integer, A: Lista, REZ: Lista)
substituie([],_,_,[]).
substituie([H|T],E,A,[A|REZ]):-H=:=E,
	substituie(T,E,A,REZ).
substituie([H|T],E,A,[H|REZ]):-H=\=E,
	substituie(T,E,A,REZ).

elem_pe_poz([H|_],K,INDEX,REZ):-INDEX=:=K,
	REZ is H.
elem_pe_poz([_|T],K,INDEX,REZ):-INDEX<K,
	INDEX2 is INDEX+1,
	elem_pe_poz(T,K,INDEX2,REZ).

det_elem_pe_poz(L,K,REZ):-elem_pe_poz(L,K,1,REZ).
