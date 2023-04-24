; (defun apartine (elem l)  
    ; (cond
        ; ((equal elem l) t)
        ; (t (apply #' or (mapcar #'(lambda (l) 
                                        ; (apartine elem l)
                                  ; )
                                  ; l
                        ; )
            ; )
        ; )
    ; )
; )

(defun apartine (elem l) 
(cond
    ((null l) nil)
    ((equal elem (car l)) t) 
    ((and (listp (car l)) (apartine elem (car l))) t) 
    (t (apartine elem (cdr l)))
)
)


(defun cale (arb nod)  
    (cond
        ((equal nod (car arb)) (cons nod nil))
        ((apartine nod (cadr arb)) (cons (car arb) (cale (cadr arb) nod)))
        ((apartine nod (caddr arb)) (cons (car arb) (cale (caddr arb) nod))) 
        (t nil)
    )
)