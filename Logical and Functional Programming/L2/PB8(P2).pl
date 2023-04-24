/*
succesor(L: Lista, TR: Integer, REZ: Lista)
*/
succesor([H],TR,REZ):-10 is H+1,
	TR=1,
	REZ=[0].
succesor([H],TR,REZ):-H+1 =\= 10,
	TR=0,
	H2 is H+1,
	REZ=[H2].
succesor([H|T],TR,REZ):-succesor(T,TR2,REZ2),
	ADAUG=H+TR2,
	TR is ADAUG div 10,
	ADAUG2 is ADAUG mod 10,
	REZ=[ADAUG2|REZ2].

succesor_a(A,REZ):-succesor(A,TR,REZ),
	TR is 0.
succesor_a(A,[TR|REZ]):-succesor(A, TR,REZ),
	TR > 0.

succesor_b([H|T],[REZ2|REZ]):-is_list(H),
	succesor_b(T,REZ),
	succesor_a(H,REZ2).
succesor_b([H|T],[H|REZ]):-not(is_list(H)),
	succesor_b(T,REZ).
succesor_b([],[]).
