(defun radacina (arb)  
(car arb)
)

(defun stanga (arb)  
    (cadr arb)
)

(defun dreapta (arb) 
    (caddr arb)
)



(defun postordine (arb) 
(cond 
    ((null arb) nil) 
    (t (append (postordine (stanga arb)) (postordine (dreapta arb)) (list (radacina arb))))
)
)