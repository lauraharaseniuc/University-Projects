/*
determina_maxim(L: Lista, MX: Integer, INDEX: Integer, POZ: Lista)
*/
determina_maxim([H],H,INDEX,[INDEX]):-!.
determina_maxim([H1|T],MX,INDEX, POZ):-INDEX2 is INDEX+1,
	determina_maxim(T,MX2,INDEX2,_),
	H1>MX2,
	MX=H1,
	POZ=[INDEX],!.
determina_maxim([H1|T],MX,INDEX, POZ):-INDEX2 is INDEX+1,
	determina_maxim(T,MX2, INDEX2,POZ2),
	H1<MX2,
	MX=MX2,I
	POZ=POZ2,
	!.
determina_maxim([H|T],MX,INDEX,POZ):-INDEX2 is INDEX+1,
	determina_maxim(T,MX2,INDEX2, POZ2),
	H=:=MX2,
	MX=MX2,
	append(POZ2,[INDEX],POZ),
	!.

determina_poz_max(L,REZ):-determina_maxim(L,_,1,REZ).


inlocuieste_in_lista([H|T],REZ):-is_list(H),
	inlocuieste_in_lista(T,REZ2),
	determina_poz_max(H,POZ),
	REZ=[POZ|REZ2],!.
inlocuieste_in_lista([H|T],REZ):-not(is_list(H)),
	inlocuieste_in_lista(T,REZ2),
	REZ=[H|REZ2],!.
inlocuieste_in_lista([],[]):-!.
