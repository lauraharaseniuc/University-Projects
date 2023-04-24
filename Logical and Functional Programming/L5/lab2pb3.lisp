(defun stanga (arb)  
    (cadr arb)
)

(defun dreapta (arb) 
    (caddr arb)
)



(defun nr_niveluri (arb)  
(cond
    ((and (null (stanga arb)) (null (dreapta arb))) 1) 
    ((null (dreapta arb)) (+ (nr_niveluri (stanga arb)) 1) ) 
    ((null (stanga arb)) (+ (nr_niveluri (dreapta arb)) 1) ) 
    (t (+ 1 (max (nr_niveluri (stanga arb)) (nr_niveluri (dreapta arb)) )))
)
)