(defun dubleaza (l n index)
    (cond 
    ( (null l) l)
    ( (= index n) (cons (car l) (cons (car l) (dubleaza (cdr l) n (+ index 1) )) )) 
    (t (cons (car l) (dubleaza (cdr l) n (+ index 1))) )
    )
)

(defun dubleaza_wrapper (li n)  
(dubleaza li n 1)
)

(defun asociere (a b)
    (cond 
    ((null a) b) 
    ((null b) a) 
    (t (cons (cons (car a) (car b)) (asociere (cdr a) (cdr b))))
    ()
    )
)

(defun nr_subliste(l nr)
    (cond
    ( (null l) (+ nr 1))
    ( (listp (car l)) (nr_subliste (cdr l) (+ nr (nr_subliste (car l) 0) )))
    (t (nr_subliste (cdr l) nr))
    )
)

(defun nr_subliste_wrapper(l) 
(nr_subliste l 0)
)

(defun nr_atomi(l)
    (cond
    ((null l) 0) 
    ((atom (car l)) (+ (nr_atomi (cdr l)) 1))
    (t (nr_atomi (cdr l)))
    )
)