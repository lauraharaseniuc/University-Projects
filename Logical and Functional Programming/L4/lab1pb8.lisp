(defun elimina (l n index)
(cond  
    ((null l) nil) 
    ((or (< index n) (> index n)) (cons (car l) (elimina (cdr l) n (+ index 1)))) 
    (t (elimina (cdr l) n (+ index 1)))
)
)

(defun elimina_wrapper (l n)  
(elimina l n 1)
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

(defun succesor (l tr col)
(cond
    ((null l)
    (cond 
        ((= tr 0) col) 
        (t (cons tr col))
    ) 
    )
    (t (succesor (cdr l) (floor (+ (car l) tr) 10) (cons (mod (+ (car l) tr) 10) col) ))
)
)

(defun succesor_wrapper (l)  
(succesor (invers l) 1 nil)
)

(defun apartine (elem l)  
(cond
    ((null l) nil) 
    ((equal elem (car l)) t) 
    (t (apartine elem (cdr l)))
)
)

(defun multime (l col) 
(cond
    ((null l) col) 
    ((atom (car l))
    (cond
        ((apartine (car l) col) (multime (cdr l) col)) 
        (t (multime (cdr l) (cons (car l) col)))
    )
    )
    (t (multime (cdr l) (multime (car l) col)))
)
)

(defun multime_wrapper (l)  
(multime l nil)
)

(defun e_multime (l col)  
(cond 
    ((null l) t) 
    ((apartine (car l) col) nil) 
    (t (e_multime (cdr l) (cons (car l) col)))
)
)

(defun e_multime_wrapper (l) 
(e_multime l nil)
)