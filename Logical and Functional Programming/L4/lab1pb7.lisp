(defun liniara (l)  
(cond
    ((null l) t) 
    ((listp (car l)) nil) 
    (t (liniara (cdr l)))
)
)

(defun substituie (l elem inloc gata)  
(cond
    ((null l) l) 
    ((equal (car l) elem)  
        (cond 
            ((= gata 0) (cons inloc (substituie (cdr l) elem inloc 1))) 
            (t (cons (car l) (substituie (cdr l) elem inloc gata)))
        )
    ) 
    (t (cons (car l) (substituie (cdr l) elem inloc gata)))
)
)

(defun substituie_wrapper (l elem inloc)  
(substituie l elem inloc 0)
)

(defun ultim (l) 
(cond 
    ((null (cdr l))
        (cond 
            ((listp (car l)) (ultim (car l))) 
            (t (car l))
        )
    )
    ((null l) l) 
    (t (ultim (cdr l)))
)
)

(defun inloc_ultim (l)
    (cond 
        ((null l) l) 
        ((listp (car l)) (cons (ultim (car l)) (inloc_ultim (cdr l)))) 
        (t (cons (car l) (inloc_ultim (cdr l))))
    )
)

(defun invers_aux (l col) 
(cond 
    ((null l) col) 
    (t (invers_aux (cdr l) (cons (car l) col)))
)
)

(defun invers (l) 
(invers_aux l nil)
)

(defun succesor (l tr)  
(cond
    ((null l)
    (cond
        ((= tr 0) nil)
        (t (cons tr nil))
    )
    )
    (t (cons (mod (+ (car l) tr) 10) (succesor (cdr l) (floor (+ (car l) tr) 10))))
)
)

(defun succesor_wrapper (l) 
(invers (succesor (invers l) 1))
)



(DEFUN TEST (L)
(COND 
    ((NULL L) T)
    ((LABELS ((TEST1 (L)
        (COND 
            ((NULL L) T)
            ((NUMBERP (CAR L)) (TEST1 (CDR L)))
            (T NIL)
        )))(TEST1 (CAR L))) (TEST (CDR L)))
    (T NIL)
)
)


(defun curs10_ex3(l) 
(cond
    ((null l) t) 
    (   
        (labels((cont_atomi(l) 
            (cond
                ((null l) t)
                ((numberp (car l)) (cont_atomi (cdr l)))
                (t nil)
            )
            ))
            (cont_atomi (car l)))
        )
    )
    (curs10_ex3 (cdr l))
    (t nil)
)
)