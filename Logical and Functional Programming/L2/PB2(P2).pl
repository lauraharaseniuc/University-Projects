/*
imparte(A: Lista, REZ1: Lista, REZ2: Lista)
*/
imparte([],[],[]).
imparte([A],[A],[]).
imparte([H1,H2|T], REZ1, REZ2):-imparte(T, REZ3, REZ4),
	REZ1=[H1|REZ3],
	REZ2=[H2|REZ4].

/*
	merge_sort(A: Lista, B: Lista, REZ: Lista)
	*/
merge_sort([HA|TA], [HB|TB], REZ):-HA<HB,
	merge_sort(TA, [HB|TB], REZ2),
	REZ=[HA|REZ2].
merge_sort([HA|TA], [HB|TB], REZ):-HA>=HB,
	merge_sort([HA|TA], TB, REZ2),
	REZ=[HB|REZ2].
merge_sort([],B,B).
merge_sort(A,[],A).

/*
sorteaza_lista(A: Lista, REZ: Lista)
*/

sorteaza_lista([],[]).
sorteaza_lista([A],[A]).
sorteaza_lista([H1,H2|T],REZ):-imparte([H1,H2|T],P1,P2),
	sorteaza_lista(P1, S1),
	sorteaza_lista(P2, S2),
	merge_sort(S1, S2, REZ).




/*
	sorteaza_b(A: Lista, REZ: Lista)
	*/
sorteaza_b([H|T],REZ):-is_list(H),
	sorteaza_b(T,REZ2),
	sorteaza_lista(H, REZ1),
	REZ=[REZ1|REZ2].
sorteaza_b([H|T],REZ):-not(is_list(H)),
	sorteaza_b(T,REZ2),
	REZ=[H|REZ2].
sorteaza_b([],[]).








