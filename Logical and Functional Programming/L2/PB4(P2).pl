/*
interclasare(A: Lista, B: Lista, REZ: Lista)
*/
interclasare([HA|TA], [HB|TB], REZ):-HA<HB,
	interclasare(TA, [HB|TB], REZ2),
	REZ=[HA|REZ2],!.
interclasare([HA|TA], [HB|TB], REZ):-HA>HB,
	interclasare([HA|TA], TB, REZ2),
	REZ=[HB|REZ2],!.
interclasare([HA|TA], [HB|TB], REZ):-HA=:=HB,
	interclasare(TA, TB, REZ2),
	REZ=[HA|REZ2],!.
interclasare([],A,A):-!.
interclasare(A,[],A):-!.
interclasare([],[],[]):-!.


/*
interclasare_lista(A: Lista, REZ: Lista)
*/
interclasare_lista([H|T], REZ):-is_list(H),
	interclasare_lista(T,REZ2),
	interclasare(H,REZ2,REZ).
interclasare_lista([H|T], REZ):- not(is_list(H)),
	interclasare_lista(T, REZ2),
	interclasare([H],REZ2,REZ).
interclasare_lista([],[]).
