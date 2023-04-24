%Modele de flux(i,i)
vale([_|[]], STARE):-STARE=:=2.
vale([H1,H2|T], STARE):-H1>H2,
	STARE=:=1,
	vale([H2|T],STARE).
vale([H1,H2|T], STARE):-H1<H2,
	STARE=:=2,
	vale([H2|T], STARE).
vale([H1,H2|T], STARE):-H1>H2,
	STARE=:=0,
	STARE2=1,
	vale([H2|T], STARE2).
vale([H1, H2|T], STARE):-H1<H2,
	STARE=:=1,
	STARE2=2,
	vale([H2|T], STARE2).

e_vale(L):-vale(L,0).


/*
suma(L: Lista, Index: Integer, Suma: Integer)
Modele de flux: (i,i,o),(i,i,i)
*/
suma([],_,0).
suma([H|T],I,SUMA):-mod(I,2)=:=1,
	I2 is I+1,
	suma(T,I2, SUMA2),
	SUMA is SUMA2+H.
suma([H|T],I,SUMA):-mod(I,2)=:=0,
	I2 is I+1,
	suma(T,I2,SUMA2),
	SUMA is SUMA2-H.

suma_elem(L,SUMA):-suma(L,1,SUMA).



















