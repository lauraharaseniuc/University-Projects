(defun radacina (arb)  
(car arb)
)

(defun stanga (arb)  
    (cadr arb)
)

(defun dreapta (arb) 
    (caddr arb)
)

(defun nr_subarbori (arb) 
(cond 
    ((and (null (stanga arb)) (null (dreapta arb))) 0) 
    ((null (stanga arb)) 1)
    ((null (dreapta arb)) 1)
    (t 2)
)
)

(defun transforma (arb)  
(cond
    ((null arb) nil)
    ((and (null (stanga arb)) (null (dreapta arb))) (list (radacina arb) 0))
    (t (cons (radacina arb) (cons (nr_subarbori arb) (append (transforma (stanga arb)) (transforma (dreapta arb)) ))))
)
)