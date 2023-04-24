(defun radacina (arb)  
(car arb)
)

(defun stanga (arb)  
    (cadr arb)
)

(defun dreapta (arb) 
    (caddr arb)
)

(defun reuneste (arb) 
(cond
    ((null arb) nil) 
    ((listp (car arb)) (append (car arb) (reuneste (cdr arb))) )
    (t (reuneste (cdr arb)))
)
)

(defun nr_noduri (arb)  
(cond 
    ((null arb) 0) 
    ((atom (car arb)) (+ 1 (nr_noduri (cdr arb))))
    (t (nr_noduri (cdr arb)))
)
)

(defun noduri (arb) 
(cond 
    ((null arb) nil) 
    ((atom (car arb)) (cons (car arb) (noduri (cdr arb)))) 
    (t (noduri (cdr arb)))
)
)


(defun nivel_max (arb nivel)  
(cond
    ((null arb) (list 0 nivel nil) )
    ((> (nr_noduri arb) (car(nivel_max (reuneste arb) (+ nivel 1)))) (list (nr_noduri arb) nivel (noduri arb)) )
    (t (nivel_max (reuneste arb) (+ nivel 1) ))
)
)

(defun nivel_max_wrapper (arb)  
(nivel_max (reuneste arb) 1)
)