apartine(E,[E|_]).
apartine(E,[H|T]):-E\==H,
	apartine(E,T).

%intersectie(A: Lista, B: Lista, REZ: Lista)
intersectie([],_,[]).
intersectie(_,[],[]).
intersectie([H|T], B, [H|REZ2]):-apartine(H, B),
	intersectie(T,B,REZ2).
intersectie([H|T],B,REZ):-not(apartine(H,B)),
	intersectie(T,B,REZ).

%multime(M: Integer, N: Integer, REZ: Lista)
multime(M,N,[]):-M>N.
multime(M,N,[M|REZ2]):-M=<N,
	M1 is M+1,
	multime(M1,N,REZ2).
