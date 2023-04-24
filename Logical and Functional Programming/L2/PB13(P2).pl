/*
divizori(ELEM: Integer, D: Integer, REZ: Lista)
*/
divizori(ELEM,_,[]):-ELEM<2,!.
divizori(ELEM,D,[]):-D> ELEM div 2,!.
divizori(ELEM,D,[D|REZ]):-D=<ELEM div 2,
	ELEM mod D =:=0,
	D2 is D+1,
	divizori(ELEM,D2,REZ),!.
divizori(ELEM,D,REZ):-D=<ELEM div 2,
	ELEM mod D =\=0,
	D2 is D+1,
	divizori(ELEM,D2,REZ),!.

lista_divizori(X,REZ):-divizori(X,2,REZ).


adauga_div([],[]):-!.
adauga_div([H|T],REZ):-lista_divizori(H,REZ2),
	adauga_div(T,REZ3),
	append([H|REZ2],REZ3,REZ),
	!.

adauga_b([H|T],[H|REZ]):-not(is_list(H)),
	adauga_b(T,REZ),!.
adauga_b([H|T],REZ):-is_list(H),
	adauga_div(H,REZ2),
	adauga_b(T,REZ3),
	REZ=[REZ2|REZ3],!.
adauga_b([],[]):-!.
