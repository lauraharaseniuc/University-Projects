%are_nr_par_de_elemente(L: Lista, FLAG: Integer)
are_nr_par_de_elemente([],0):-!.
are_nr_par_de_elemente([_|T],FLAG):-FLAG=:=0,
	are_nr_par_de_elemente(T,1).
are_nr_par_de_elemente([_|T],FLAG):-FLAG=:=1,
	are_nr_par_de_elemente(T,0).

verifica_lista(L):-are_nr_par_de_elemente(L,0).


poz_elem_minim([],_,POZ,_,POZ).
poz_elem_minim([H|T],MINIM,_,INDEX,REZ):-H<MINIM,
	INDEX2 is INDEX+1,
	poz_elem_minim(T,H,INDEX,INDEX2,REZ).
poz_elem_minim([H|T],MINIM,POZ,INDEX,REZ):-H>=MINIM,
	INDEX2 is INDEX+1,
	poz_elem_minim(T,MINIM,POZ,INDEX2,REZ).

determina_poz_minim([H|T],POZ):-poz_elem_minim([H|T],H,1,1,POZ).





