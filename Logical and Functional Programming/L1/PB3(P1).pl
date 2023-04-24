apartine(E,[E|_]):-!.
apartine(E,[H|T]):-E=\=H,
	apartine(E,T).


%multime(L: Lista, MULTIME: Lista, REZ: Lista)
multime([],MULTIME,REZ):-REZ=MULTIME.
multime([H|T],MULTIME,REZ):-not(apartine(H,MULTIME)),
	append(MULTIME,[H],MULTIME2),
	multime(T,MULTIME2,REZ).
multime([H|T], MULTIME, REZ):-apartine(H,MULTIME),
	multime(T,MULTIME,REZ).

/*desparte_lista(L: Lista, LP: Lista, LI: Lista, NRP: Integer, NRI: Integer)
*/
desparte_lista([],[],[],NRP,NRI,NRPF,NRIF):-NRPF is NRP,
	NRIF is NRI.
desparte_lista([H|T], [H|LP],LI, NRP, NRI,NRPF, NRIF):- H mod 2 =:=0,
	NRP2 is NRP+1,
	desparte_lista(T,LP,LI,NRP2,NRI,NRPF,NRIF).
desparte_lista([H|T],LP,[H|LI],NRP, NRI,NRPF,NRIF):- H mod 2 =:=1,
	       NRI2 is NRI+1,
	       desparte_lista(T,LP,LI,NRP,NRI2,NRPF,NRIF).

desparte_lista_wrapper(L,LP,LI,NRP,NRI):-
	desparte_lista(L,LP,LI,0,0,NRP, NRI).
