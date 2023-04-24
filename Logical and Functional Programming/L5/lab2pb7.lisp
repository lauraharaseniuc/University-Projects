(defun radacina (arb)  
(car arb)
)

(defun stanga (arb)  
    (cadr arb)
)

(defun dreapta (arb) 
    (caddr arb)
)


(defun cauta (l elem k)  
(cond 
    ((null l) nil)
    ((equal elem (car l)) k) 
    ((and (listp (car l))(cauta (car l) elem (+ k 1))) )
    ((cauta (cdr l) elem k))
)
)


(defun nivel (arb nod) 
(cond
    ((null arb) nil) 
    ((equal (radacina arb) nod) 0) 
    ((cauta (stanga arb) nod 1)) 
    ((cauta (dreapta arb) nod 1))
)
)