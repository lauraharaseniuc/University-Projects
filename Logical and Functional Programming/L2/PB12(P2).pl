/*
inlocuieste(L: Lista, A: Integer, B: Integer, REZ: Lista)
*/
inlocuieste([],_,_,[]):-!.
inlocuieste([H|T],A,B,[B|REZ]):-H=:=A,
	inlocuieste(T,A,B,REZ),!.
inlocuieste([H|T],A,B,[H|REZ]):-H=\=A,
	inlocuieste(T,A,B,REZ),!.


/*
	maxim (L: Lista, MX: InIteger)
	*/
maxim([H],H):-number(H),!.
maxim([H|T],MX):-number(H),
	maxim(T,MX2),
	H>MX2,
	MX=H,!.
maxim([H|T],MX):-number(H),
	maxim(T,MX2),
	H=<MX2,
	MX=MX2,!.
maxim([H|T],MX):-not(number(H)),
	maxim(T,MX),!.


inlocuieste_in_subliste([H|T],MX,[H|REZ]):-not(is_list(H)),
	inlocuieste_in_subliste(T,MX,REZ),!.
inlocuieste_in_subliste([H|T],MX,REZ):-is_list(H),
	inlocuieste_in_subliste(T,MX,REZ2),
	maxim(H,MX_SUBLISTA),
	inlocuieste(H,MX,MX_SUBLISTA,REZ3),
	REZ=[REZ3|REZ2],!.
inlocuieste_in_subliste([],_,[]):-!.

inlocuieste_in_subliste_wrapper(L,REZ):-maxim(L,MX),
	inlocuieste_in_subliste(L,MX,REZ).









