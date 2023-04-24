;l - lista asupra careia se face extragerea atomilor de la fiecare nivel intr-o unica lista cu un singur nivel
;output: lista ce contine toti atomii de la toate nivelurile listei date ca parametru
(defun labb(l)
    (cond 
        ( (atom l) l )
        ( (atom(car l) ) ( append  (list(car l)) (labb(cdr l))  ))
        ( (listp (car l)) ( append (labb(car l)) (labb(cdr l)) ) )
    )
)


;l - lista in care se inverseaza toate secventele continue de atomi
;col - lista in care se colecteaza inversa unei secvente continue de atomi la un moment dat
;output: lista ce contine inversele tuturor secventelor continue de atomi ale listei date ca parametru
(defun formeaza(l col) 
    (cond
    ( (null l) col )
    ( (atom (car l)) (formeaza (cdr l) (cons (car l) col) ) ) 
    ( (listp (car l)) (append col ( cons (formeaza(car l) ()) (formeaza (cdr l) ()) ) ) )
    )
)

;l - lista in care se inverseaza toate secventele continue de atomi
;output: lista ce contine inversele tuturor secventelor continue de atomi ale listei date ca parametru
(defun formeaza_wrapper(l)  
    (formeaza l ())
)


;l - lista pentru care se determina maximul numeric de la nivel superficial
;output: valoarea numerica maxima de la nivel superficial din lista primita ca parametru
(defun maxim(l)  
    (cond 
    ((null l) -9999)
    ((numberp (car l)) (max (car l) (maxim (cdr l)) ))
    ( (not (numberp (car l))) (maxim (cdr l)))
    )
)

;a - lista de atomi numerici
;b - lista de atomi numerici
;output - lista de atomi numerici in care un element de pe pozitia i are ca valoare suma dintre a(i) si b(i)
(defun suma(a b)  
    (cond  
    ( (null a) b)
    ( (null b) a)
    ( t (cons (+ (car a) (car b)) (suma (cdr a) (cdr b) )) )
    )
)