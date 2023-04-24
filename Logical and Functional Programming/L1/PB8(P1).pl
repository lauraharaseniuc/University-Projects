apartine(E,[E|_]).
apartine(E,[H|T]):-E\==H,
	apartine(E,T).

e_multime([]).
e_multime([H|T]):-not(apartine(H,T)),
	e_multime(T).

%elimina(E: Element, A: Lista, I:Integer, REZ: Lista)

elimina(_, [], _ ,[]).
elimina(E, [H|T], I, REZ):-H\==E,
	elimina(E, T, I, REZ2),
	REZ=[H|REZ2].
elimina(E,[H|T], I, REZ):- H==E,
	I<3,
	I1 is I+1,
	elimina(E, T, I1, REZ2),
	REZ=REZ2.
elimina(E, [H|T], I, REZ):-H==E,
	I>=3,
	elimina(E, T, I, REZ2),
	REZ=[H|REZ2].
