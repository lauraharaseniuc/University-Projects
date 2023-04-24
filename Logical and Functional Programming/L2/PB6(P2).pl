/*
inlocuieste_a(A: Lista, E: Integer, B:Lista, REZ: Lista)
*/
inlocuieste_a([HA|TA],E,B,REZ):-HA=:=E,
	inlocuieste_a(TA,E,B,REZ2),
	append(B,REZ2,REZ),!.
inlocuieste_a([HA|TA],E,B,REZ):-HA=\=E,
	inlocuieste_a(TA,E,B,REZ2),
	append([HA],REZ2,REZ),!.
inlocuieste_a([],_,_,[]):-!.


/*
inlocuieste_b(A: Lista, REZ: Lista)
*/
inlocuieste_b([HA|TA],B,REZ):-is_list(HA),
	inlocuieste_b(TA,B,REZ2),
	[H|_]=HA,
	inlocuieste_a(HA,H,B,REZ3),
	REZ=[REZ3|REZ2],!.
inlocuieste_b([HA|TA],B,REZ):-not(is_list(HA)),
	inlocuieste_b(TA,B,REZ2),
	REZ=[HA|REZ2],!.
inlocuieste_b([],_,[]):-!.
