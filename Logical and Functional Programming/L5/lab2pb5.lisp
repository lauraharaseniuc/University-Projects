; arb - reprezentarea unui arbore binar sub forma de lista liniara de atomi
; nrv - numarul curent de noduri din parcurgere
; nrm - numarul curent de muchii din parcurgere
; col_st - lista liniara de atomi in care se colecteaza partea stanga a arborelui dat ca parametru

;output - lista ce are ca ekemente componente 2 liste: prima lista reprezinta descendenta stanga a arborelui dat ca parametru
;                                                      a doua lista reprezinta descendenta dreapta a arborelui dat ca parametru
(defun st_dr_arbore_aux (arb nrv nrm col_st)
    (cond 
    ( (equal (- nrv nrm) 1) (cons col_st (list arb) ) )
    (t (st_dr_arbore_aux (cddr arb) (+ nrv 1) (+ nrm (cadr arb)) (append col_st (list (car arb) (cadr arb))) ) )
    )
)


; arb - reprezentarea unui arbore binar sub forma de lista liniara de atomi
;output - lista ce are ca ekemente componente 2 liste: prima lista reprezinta descendenta stanga a arborelui dat ca parametru
;                                                      a doua lista reprezinta descendenta dreapta a arborelui dat ca parametru
(defun st_dr_arbore (arb)
    (cond
    ((null arb) (list () ()))
    (t (st_dr_arbore_aux arb 0 0 ()))
    )
)



; arb - reprezentarea unui arbore binar sub forma de lista liniara de atomi
; elem - nodul din arbore a carui nivel trebuie determinat
; nivel - nivelul la care s-a ajuns in parcurgerea in latime a arborelui
; output - nivelul nodului dat ca parametru in cadrul arborelui
(defun adancime (arb elem nivel)
    (cond 
    ((null arb) nil)
    ( (equal (caar (st_dr_arbore (cddr arb) )) elem)  nivel) 
    ( (equal (caadr (st_dr_arbore(cddr arb) )) elem)  nivel)
    ( (adancime (car (st_dr_arbore (cddr arb) )) elem (+ nivel 1)))
    ( (adancime (cadr (st_dr_arbore(cddr arb) )) elem (+ nivel 1)))
    )
)

; arb - reprezentarea unui arbore binar sub forma de lista liniara de atomi
; output - nivelul nodului dat ca parametru in cadrul arborelui
(defun wrapper_adancime (arb elem)  
    (cond  
    ((equal (car arb) elem) 0) 
    (t (adancime arb elem 1))
    )
)