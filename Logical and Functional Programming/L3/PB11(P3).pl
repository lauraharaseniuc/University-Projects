/*
Element=Integer
Lista=Element*

candidat(E: Element, L: Lista)
Modele de flux: (o,i) - nedeterminist
		(i,i) - determinist

E - un element din lista L data ca parametru
L - lista din care se extrage pe rand cate un element care stocheaza in
E
*/

candidat(E,[E|_]).
candidat(E,[_|T]):-candidat(E,T).



/*
Lista=Integer*

subsiruri(N: Integer, L: Lista, REZ: Lista)
Modele de flux: (i,i,o) - nedeterminist

N - intreg ce indica lungimea pe care trebuie sa o aiba lista rezultatul
L - lista din ale carei elemente se compune rezultatul
REZ - lista rezultat, ale carei elemente respecta proprietatea data
*/
subsiruri(N,L,REZ):-subsiruri_aux(N,L,REZ,1,[0]),
	[H|_]=REZ, H=:=0.


/*
	Lista=Integer*

subsiruri_aux(N: Integer, L: Lista, REZ: Lista, LG: Integer, COL: Lista)

N - intreg ce indica lungimea rezultatului asteptat
L - lista din ale carei elemente se construieste rezultatul
REZ - lista in care s-au inserat pe rand 2*N+1 elemente ce respecta o
proprietate data (primul si ultimul element egal cu 0, intre restul
elementelor avand loc relatia |a1-a2|<=2, unde a1 si a2 sunt elemente pe
pozitii consecutive in rezultat)
LG - lungimea listei rezultat REZ
COL - lista in care se face colectarea rezultatului

Modele de flux: (i,i,o,i,i) - nedeterminist

*/

subsiruri_aux(N,_,REZ,LG,REZ):-LG is N*2+1,
	!.
subsiruri_aux(N,L,REZ,LG,[H_COL|T_COL]):-candidat(CAND,L),
	abs(H_COL-CAND)=<2,
	abs(H_COL-CAND)>=1,
	LG2 is LG+1,
	subsiruri_aux(N,L,REZ,LG2,[CAND|[H_COL|T_COL]]).



/*
	Lista=Integer*
	ListaEterogena=Lista*

	toate_subsiruri(N: Integer, REZ: ListaEterogena)

	Modele de flux: (i,o) - determinist

	N - intreg ce indica lungimea unui element din lista de rezultate REZ
	REZ - lista ce contine toate sirurile de lungime 2*n+1 care respecta proprietatea ceruta
	*/
toate_subsiruri(N,REZ):-findall(X,subsiruri(N,[0,-1,1],X),REZ).












