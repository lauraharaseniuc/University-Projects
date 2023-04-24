apartine(E,[E|_]):-!.
apartine(E,[H|T]):-H=\=E,
	apartine(E,T).

egalitate_multimi([],_).
egalitate_multimi([H|T],B):-apartine(H,B),
	egalitate_multimi(T,B).

selecteaza_element([_|T],K,INDEX,REZ):-INDEX<K,
	INDEX2 is INDEX+1,
	selecteaza_element(T,K,INDEX2,REZ).
selecteaza_element([H|_],K,INDEX,H):-INDEX=:=K.

selecteaza_el(L,K,REZ):-selecteaza_element(L,K,1,REZ).
