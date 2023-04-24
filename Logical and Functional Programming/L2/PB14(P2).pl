/*
predecesor(L: Lista, TR: Integer, REZ: Lista)
*/
predecesor([H],TR,REZ):-H2 is H-1,
	H2>=0,TR=0,
	REZ=[H2],!.
predecesor([H],TR,REZ):-H2 is H-1,
	H2<0,
	TR is -1,
	H3 is 10+H2,
	REZ=[H3],!.
predecesor([H|T],TR,REZ):-predecesor(T,TR2,REZ2),
	ADAUG is H+TR2,
	ADAUG<0,
	TR is -1,
	ADAUG2 is 10+ADAUG,
	REZ=[ADAUG2|REZ2],!.
predecesor([H|T],TR,REZ):-predecesor(T,TR2,REZ2),
	ADAUG is H+TR2,
	ADAUG>=0,
	TR is 0,
	REZ=[ADAUG|REZ2],!.

predecesor_wrapper(L,REZ):-predecesor(L,_,REZ2),
	[H|T]=REZ2,
	H=:=0,
	REZ=T.
predecesor_wrapper(L,REZ):-predecesor(L,_,REZ2),
	[H|_]=REZ2,
	H=\=0,
	REZ=REZ2.

predecesor_lista([],[]):-!.
predecesor_lista([H|T],[H|REZ]):-not(is_list(H)),
	predecesor_lista(T,REZ),!.
predecesor_lista([H|T],REZ):-is_list(H),
	predecesor_wrapper(H,REZ2),
	predecesor_lista(T,REZ3),
	REZ=[REZ2|REZ3],!.

