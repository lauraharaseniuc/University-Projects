(defun radacina (arb)  
(car arb)
)

(defun stanga (arb)  
    (cadr arb)
)

(defun dreapta (arb) 
    (caddr arb)
)


(defun inordine (arb)
(cond
    ((null arb) nil) 
    (t (append (inordine (stanga arb)) (list (radacina arb)) (inordine (dreapta arb))) )
)
)