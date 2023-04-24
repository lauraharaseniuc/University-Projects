/*
secv_pare(L: Lista, LG: Integer, LG_MAX: Integer, REZ: Lista)
*/

secv_pare([H1,H2|T], LGP, LGF, REZP, REZF,_):-H1+2=:=H2,
	LGP2 is LGP+1,
	append(REZP,[H1],REZP2),
	%REZP2=[H1|REZP],
	secv_pare([H2|T], LGP2, LGF3, REZP2, _,1),
	LGP2>=LGF3,
	LGF is LGP2,
	REZF=REZP2,!.
secv_pare([H1,H2|T], LGP, LGF, REZP, REZF,_):-H1+2=:=H2,
	LGP2 is LGP+1,
	append(REZP,[H1],REZP2),
	%REZP2=[H1|REZP],
	secv_pare([H2|T], LGP2, LGF3, REZP2, REZF3,1),
	LGP2<LGF3,
	LGF is LGF3,
	REZF=REZF3,!.
secv_pare([H1,H2|T], LGP, LGF, REZP, REZF,FLAG):-H1+2=\=H2,
	FLAG=:=1,
	LGP2 is LGP+1,
	append(REZP,[H1],REZP2),
	%REZP2=[H1|REZP],
	secv_pare([H2|T],LGP2, LGF3, REZP2, _, 0),
	LGP2>=LGF3,
	LGF is LGP2,
	REZF=REZP2,!.
secv_pare([H1,H2|T], LGP, LGF, REZP, REZF,FLAG):-H1+2=\=H2,
	FLAG=:=1,
	LGP2 is LGP+1,
	append(REZP,[H1],REZP2),
	%REZP2=[H1|REZP],
	secv_pare([H2|T],LGP2, LGF3, REZP2, REZF3, 0),
	LGP2<LGF3,
	LGF is LGF3,
	REZF=REZF3,!.
/*secv_pare([H1,H2|T],LGP,LGF, REZP,REZF,FLAG):-H1+2=\=H2,
	FLAG=:=0,
	secv_pare([H2|T],LGP,LGF3,REZP,_,0),
	LGP>=LGF3,
	LGF is LGP,
	REZF=REZP.
secv_pare([H1,H2|T],LGP,LGF, REZP,REZF,FLAG):-H1+2=\=H2,
	FLAG=:=0,
	secv_pare([H2|T],LGP,LGF3,REZP,REZF3,0),
	LGP<LGF3,
	LGF is LGF3,
	REZF=REZF3.*/
secv_pare([H1,H2|T],_, LGF, _, REZF, FLAG):-H1+2=\=H2,
	FLAG=:=0,
	LGP=0,
	REZP=[],
	secv_pare([H2|T],LGP,LGF2,REZP,REZF2,0),
	LGF=LGF2,
	REZF=REZF2,!.
secv_pare([H],LGP,LGF,REZP,REZF,1):-LGF is LGP+1,
	append(REZP,[H],REZF),!.
	%REZF=[H|REZP].
secv_pare([_],_,LGF,_,REZF,0):-LGF is 0,
	REZF=[],!.


secv_pare_wrapper(L,REZ):-secv_pare(L,0,_,[],REZ,0).

inlocuieste([],[]):-!.
inlocuieste([H|T],[H|REZ]):-not(is_list(H)),
	inlocuieste(T,REZ).
inlocuieste([H|T],REZ):-is_list(H),
	inlocuieste(T,REZ2),
	secv_pare_wrapper(H,REZ3),
	REZ=[REZ3|REZ2].














