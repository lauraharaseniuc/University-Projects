(defun substituie (l elem b) 
(cond
    ((null l) l) 
    ((and (atom (car l)) (atom elem) (eq (car l) elem)) (cons b (substituie (cdr l) elem b))) 
    ((and (listp (car l)) (listp elem) (equal (car l) elem)) (cons b (substituie (cdr l) elem b))) 
    (t
        (cond 
            ((listp (car l)) (cons (substituie (car l) elem b) (substituie (cdr l) elem b)))
            (t (cons (car l) (substituie (cdr l) elem b)))
        )
    )
)
)

(defun suma (a b tr) 
(cond
    ((and (null a) (null b)) (list tr)) 
    ((null a) (cons (mod (+ tr (car b)) 10) (suma a (cdr b) (floor (+ tr (car b)) 10)) ))
    ((null b) (cons (mod (+ tr (car a)) 10) (suma (cdr a) b (floor (+ tr (car a)) 10)) ))
    (t (cons (mod (+ (car a) (car b) tr) 10) (suma (cdr a) (cdr b) (floor (+ (car a) (car b) tr) 10))) )
)
)

(defun invers (l col) 
(cond
    ((null l) col) 
    (t (invers (cdr l) (cons (car l) col)))
)
)

(defun invers_wrapper (l) 
(invers l nil)
)

(defun suma_wrapper (a b) 
(invers_wrapper (suma (invers_wrapper a) (invers_wrapper b) 0))
) 

(defun suma_wrapper_wrapper(a b)  
(cond 
    ((= (car (suma_wrapper a b)) 0) (nr_wrapper (cdr (suma_wrapper a b))))
    (t (nr_wrapper(suma_wrapper a b)))
)
)

(defun nr (l x)  
(cond 
    ((null l) x) 
    (t (nr (cdr l) (+ (* x 10) (car l))))
)
)

(defun nr_wrapper (l)  
(nr l 0)
)

(defun cmmdc (a b)  
(cond 
    ((or (<= a 0) (<= b 0)) 1)
    ((= a b) a) 
    ((< a b) (cmmdc a (- b a))) 
    (t (cmmdc (- a b) b))
)
)

(defun cmmdc_lista (l) 
(cond
    ((null l) 1)
    ((null (cdr l)) (car l)) 
    (t (cmmdc (car l) (cmmdc_lista (cdr l))))
)
)