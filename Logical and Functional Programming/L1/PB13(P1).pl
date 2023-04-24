adauga_final([],E,[E]):-!.
adauga_final([H|T],E,[H|REZ]):-adauga_final(T,E,REZ).

apartine([E|_],E):-!.
apartine([H|T],E):-H=\=E,
	apartine(T,E).

multime([],[]):-!.
multime([H|T],REZ):-not(apartine(T,H)),
	multime(T,REZ2),
	REZ=[H|REZ2].
multime([H|T],REZ2):-apartine(T,H),
	multime(T,REZ),
	REZ2=REZ.

cmmdc(A,A,A):-!.
cmmdc(A,B,CMMDC):-A>B,
	A2 is A-B,
	cmmdc(A2,B,CMMDC).
cmmdc(A,B,CMMDC):-A<B,
	B2 is B-A,
	cmmdc(A, B2, CMMDC).

cmmdc_lista([H1,H2|[]],REZ):-cmmdc(H1,H2,REZ).
cmmdc_lista([H1,H2|T],REZ):-cmmdc_lista([H2|T],REZ2),
	cmmdc(H1,REZ2,REZ3),
	REZ=REZ3.
