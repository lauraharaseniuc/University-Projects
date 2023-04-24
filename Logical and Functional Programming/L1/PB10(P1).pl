/*
intercalare(A: Lista, P: Integer, I: Integer, E: Element, REZ: Lista)
*/
intercalare([H|T],P,I,E,REZ):-I<P,
	I1 is I+1,
	intercalare(T,P,I1,E,REZ2),
	REZ=[H|REZ2].
intercalare([H|T],P,I,E,REZ):-I=:=P,
	I1 is I+1,
	intercalare(T,P,I1,E,REZ2),
	REZ=[E|[H|REZ2]].
intercalare([H|T],P,I,E,REZ):-I>P,
	I1 is I+1,
	intercalare(T,P,I1,E,REZ2),
	REZ=[H|REZ2].
intercalare([],_,_,_,[]).

/*
intercalare_pe_poz(A: Lista, P: Integer, E: Element, REZ: Lista)
*/
intercalare_pe_poz(A,P,E,REZ):-
	intercalare(A,P,1,E,REZ).

/*
	cmmdc(A: Integer, B: Integer, REZ: Integer)
	*/
cmmdc(A,A,A).
cmmdc(A,B,REZ):-A>B,
	A2 is A-B,
	cmmdc(A2,B,REZ).
cmmdc(A,B,REZ):-B>A,
	B2 is B-A,
	cmmdc(A,B2,REZ).



















