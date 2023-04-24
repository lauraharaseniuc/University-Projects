(defun radacina (arb)  
(car arb)
)

(defun stanga (arb)  
    (cadr arb)
)

(defun dreapta (arb) 
    (caddr arb)
)



(defun preordine (arb) 
(cond 
    ((null arb) nil) 
    (t (append (list (radacina arb)) (preordine (stanga arb)) (preordine (dreapta arb))))
)
)