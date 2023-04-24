/*
suma_lista(L: Lista, S: Integer)
*/
suma_lista([],0).
suma_lista([H|T],S):-suma_lista(T,S2),
	S is H+S2.

/*
	suma(A: Lista, B: Lista, S: Integer)
	*/
suma(A,B,REZ):-formeaza_nr(A,_,NR1),
	formeaza_nr(B,_,NR2),
	REZ is NR1+NR2.

/*
	formeaza_nr(L: Lista, P: Integer, REZ: Integer)
	*/
formeaza_nr([H|[]],1,H).
formeaza_nr([H|T],P, REZ):-
	formeaza_nr(T, P1,REZ2),
	P is P1*10,
	REZ is H*P+REZ2.

calculeaza_suma_subliste([],0).
calculeaza_suma_subliste([H|T],S):-is_list(H),
	calculeaza_suma_subliste(T,S2),
	formeaza_nr(H,_,NR),
	S is S2+NR.
calculeaza_suma_subliste([H|T],S):-not(is_list(H)),
	calculeaza_suma_subliste(T,S).
