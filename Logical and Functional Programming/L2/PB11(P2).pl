/*
prim(X: Integer, D: Integer)
*/
prim(X,D):-X>1,HALF_X is X div 2,
	D>HALF_X,!.
prim(X,D):-X>1,D2 is D+1,
	prim(X,D2),
	HALF_X is X div 2,
	D=<HALF_X,
	X mod D =\=0.

e_prim(X):-prim(X,2).

duplica_prime([H|T],REZ):-e_prim(H),
	duplica_prime(T,REZ2),
	REZ=[H,H|REZ2],!.
duplica_prime([H|T],REZ):-not(e_prim(H)),
	duplica_prime(T,REZ2),
	REZ=[H|REZ2],!.
duplica_prime([],[]):-!.

/*
duplica_b (L: Lista, REZ: Lista)
*/
duplica_b([],[]):-!.
duplica_b([H|T],REZ):-is_list(H),
	duplica_prime(H,REZ2),
	duplica_b(T,REZ3),
	REZ=[REZ2|REZ3],!.
duplica_b([H|T],[H|REZ]):-not(is_list(H)),
	duplica_b(T,REZ).
