(defun find_element (l poz index)  
(cond 
    ((null l) l) 
    ((= poz index) (car l)) 
    (t (find_element (cdr l) poz (+ index 1)))
)
)

(defun find_element_wrapper (l poz)
(find_element l poz 1) 
)

(defun apartine (elem l)  
(cond 
    ((null l) l) 
    ((atom (car l))  
        (cond  
            ((eq elem (car l)) t) 
            (t (apartine elem (cdr l)))
        )
    )
    (t (or (apartine elem (car l)) (apartine elem (cdr l)) ))
)
)

(defun subliste (l)  
(cond  
    ((null l) l) 
    ((not (listp (car l))) (subliste (cdr l))) 
    (t (cons (car l) (append (subliste (car l)) (subliste (cdr l))) ))
)
)

(defun apartine2 (l elem)  
(cond 
    ((null l) l) 
    ((eq (car l) elem) t) 
    (t (apartine2 (cdr l) elem))
)
)

(defun multime (l)  
(cond  
    ((null l) l) 
    ((apartine2 (multime (cdr l)) (car l)) (multime (cdr l))) 
    (t (cons (car l) (multime (cdr l))))
)
)

