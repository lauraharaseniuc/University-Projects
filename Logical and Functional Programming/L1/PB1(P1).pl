apartine(E,[E|_]):-!.
apartine(E,[H|T]):-E=\=H,
	apartine(E,T).

%diferenta(A: Lista, B: Lista, REZ: Lista)
diferenta([H|T],B,[H|REZ]):-not(apartine(H,B)),
	diferenta(T,B,REZ),!.
diferenta([H|T],B,REZ):-apartine(H,B),
	diferenta(T,B,REZ),!.
diferenta([],_,[]):-!.


adauga1([H|T],[H,1|REZ]):-H mod 2 =:= 0,
	adauga1(T,REZ),!.
adauga1([H|T],[H|REZ]):- H mod 2 =:= 1,
	adauga1(T, REZ),!.
adauga1([],[]):-!.


cmmmc(A,B,REZ):-cmmdc(A,B,DIV_COMUN),
	REZ is div(A*B,DIV_COMUN).

cmmmc_lista([H1,H2|T],REZ):-cmmmc_lista([H2|T],REZ2),
	cmmmc(H1,REZ2,REZ).
cmmmc_lista([H1,H2|[]], REZ):-cmmmc(H1,H2,REZ).

/*adauga_din_2in2(L: Lista, V: Integer, INDEX: Integer, K: Integer, REZ: Lista) */
adauga_din_2in2([H|T],V,INDEX,K,[H|REZ]):-INDEX<K,
	INDEX2 is INDEX+1,
	adauga_din_2in2(T,V,INDEX2,K,REZ).
adauga_din_2in2([H|T],V,INDEX,K,[H,V|REZ]):-INDEX =:= K,
	INDEX2 is INDEX+1,
	K2 is K*2,
	adauga_din_2in2(T,V,INDEX2,K2,REZ).
adauga_din_2in2([],_,_,_,[]):-!.


