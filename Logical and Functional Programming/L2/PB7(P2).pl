/*
produs(L: Lista, C: Integer, TR: Integer, REZ: Lista)
*/
produs([],_,0,[]):-!.
produs([H|T],C,TR,REZ):-produs(T,C,TR2,REZ2),
	ADAUG=H*C+TR2,
	TR is ADAUG div 10,
	ADAUG2 is ADAUG mod 10,
	REZ=[ADAUG2|REZ2],!.

produs_a(A,C,REZ):-produs(A,C,TR,REZ),
	TR=:=0.
produs_a(A,C,[TR|REZ]):-produs(A,C,TR,REZ),
	TR=\=0.

inlocuieste([HA|TA], NR_L, [REZ3|REZ2]):-is_list(HA),
	NR_L2 is NR_L+1,
	inlocuieste(TA, NR_L2, REZ2),
	produs_a(HA,NR_L,REZ3),!.
inlocuieste([HA|TA],NR_L,[HA|REZ]):-not(is_list(HA)),
	inlocuieste(TA, NR_L, REZ),!.
inlocuieste([],_,[]):-!.

inlocuieste_wrapper(A,REZ):-inlocuieste(A,1,REZ).
