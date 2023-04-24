sterge_aparitii([],_,[]):-!.
sterge_aparitii([H|T],E,REZ):-H=:=E,
	sterge_aparitii(T,E,REZ).
sterge_aparitii([H|T],E,[H|REZ]):-H=\=E,
	sterge_aparitii(T,E,REZ).


nr_aparitii(_,[],0):-!.
nr_aparitii(E,[H|T],REZ):-E=:=H,
	nr_aparitii(E,T,REZ2),
	REZ is REZ2+1.
nr_aparitii(E,[H|T],REZ):-E=\=H,
	nr_aparitii(E,T,REZ).

frecv([],[]):-!.
frecv([H|T],REZ):-nr_aparitii(H,[H|T],NR_AP),
	sterge_aparitii([H|T],H,REZ2),
	frecv(REZ2,REZ3),
	REZ=[[H|[NR_AP]]|REZ3].

sterge_elem_repetate([H|T],REZ):-nr_aparitii(H,T,NR_AP),
	NR_AP=:=0,
	sterge_elem_repetate(T,REZ2),
	REZ=[H|REZ2].
sterge_elem_repetate([H|T],REZ):-nr_aparitii(H,T,NR_AP),
	NR_AP>=1,
	sterge_aparitii(T,H,REZ2),
	sterge_elem_repetate(REZ2,REZ3),
	REZ=REZ3.
sterge_elem_repetate([],[]).

determina_maxim([H|T],MAXIM,REZ):-H>MAXIM,
	determina_maxim(T,H,REZ).
determina_maxim([H|T],MAXIM,REZ):-H=<MAXIM,
	determina_maxim(T,MAXIM,REZ).
determina_maxim([],MAXIM,REZ):-REZ=MAXIM.

determina_maxim_din_lista([H|T],REZ):-determina_maxim([H|T],H,REZ).


sterge_maximul(L,REZ):-determina_maxim_din_lista(L,MAXIM),
	sterge_aparitii(L,MAXIM,REZ).







