/*
adauga(L: Lista, E: Integer, INDEX: Integer, K:Integer, REZ: Lista)
Modele de flux:(i,i,i,i,o)
*/
adauga([],_,_,_,[]):-!.
adauga([H|T],E,INDEX,K,REZ):-INDEX<K,
	INDEX2 is INDEX+1,
	adauga(T,E,INDEX2,K,REZ2),
	REZ=[H|REZ2].
adauga([H|T],E,INDEX,K,REZ):-INDEX=:=K,
	INDEX2 is INDEX+1,
	K2 is 2*K+1,
	adauga(T,E,INDEX2,K2,REZ2),
	REZ=[H,E|REZ2].

adauga_wrapper(L,E,REZ):-adauga(L,E,1,1,REZ).


/*
adauga_sublista(L: Lista, ANTERIOR: Integer, REZ: Lista)
*/
adauga_sublista([],_,[]):-!.
adauga_sublista([H|T],_,REZ):-not(is_list(H)),
	adauga_sublista(T,H,REZ2),
	REZ=[H|REZ2],!.
adauga_sublista([H|T],ANT,REZ):-is_list(H),
	adauga_sublista(T,ANT,REZ2),
	adauga_wrapper(H,ANT,REZ3),
	REZ=[REZ3|REZ2].

adauga_sublista_wrapper(L,REZ):-adauga_sublista(L,_,REZ).
