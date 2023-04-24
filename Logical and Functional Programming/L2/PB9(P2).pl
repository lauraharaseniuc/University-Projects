/*
sterge_consec(A: Lista, REZ: Lista)
*/
sterge_consec([H1,H2,H3|T],[H1|REZ],_):-not(H1+1=:=H2),
	H2+1=\=H3,sterge_consec([H2,H3|T],REZ,0),!.
sterge_consec([H1,H2,H3|T],REZ,_):-H1+1=:=H2, H2+1=:=H3,
	sterge_consec([H3|T],REZ,1),!.
sterge_consec([H1,H2,H3|T],REZ,_):-H1+1=:=H2, H2+1=\=H3,
	sterge_consec([H3|T],REZ,0),!.
sterge_consec([H1,H2,H3|T],[H1|REZ],0):-H1=\=H2, H2+1=:=H3,
	sterge_consec([H2,H3|T],REZ,1),!.
sterge_consec([H1,H2,H3|T],REZ,1):-H1=\=H2, H2+1=:=H3,
	sterge_consec([H2,H3|T],REZ,0),!.
sterge_consec([H1,H2|[]],[],_):-H1+1=:=H2.
sterge_consec([H1,H2|[]],[H1,H2],0):-H1+1=\=H2.
sterge_consec([H1,H2|[]],[H2],1):-H1=\=H2.
sterge_consec([H],[H],0).
sterge_consec([_],[],1).

/*sterge_consec([H1,H2,H3|T],REZ):-H1+1=:=H2,
	sterge_consec([H2,H3|T],REZ),!.
sterge_consec([H1,H2,H3|T],[H1|REZ]):- not(H1+1=:=H2),
	sterge_consec([H2,H3|T],REZ),!.
*/

sterge([H|T],[REZ2|REZ]):-is_list(H),
	sterge_consec(H,REZ2,_),
	sterge(T,REZ).
sterge([H|T],[H|REZ]):-not(is_list(H)),
	sterge(T,REZ).
sterge([],[]).
