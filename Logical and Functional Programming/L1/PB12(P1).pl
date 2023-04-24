substituie([H|T],A,B,[H|REZ2]):-H=\=A,
	substituie(T,A,B,REZ2).
substituie([H|T],A,B,[B|REZ2]):-H=:=A,
	substituie(T,A,B,REZ2).
substituie([],_,_,[]).

sublista([_|T],M,N,I,REZ):-I<M,
	I1 is I+1,
	sublista(T,M,N,I1,REZ).
sublista([H|T], M, N,I,[H|REZ]):-I>=M,
	I=<N,
	I1 is I+1,
	sublista(T,M,N,I1,REZ).
sublista([],_,_,_,[]):-!.
sublista(_,_,N,I,[]):-I>N,!.

find_sublista(L,M,N,REZ):-sublista(L,M,N,1,REZ).
