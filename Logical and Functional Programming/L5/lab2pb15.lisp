(defun radacina (arb)  
(car arb)
)

(defun stanga (arb)  
    (cadr arb)
)

(defun dreapta (arb) 
    (caddr arb)
)

(defun adancime (arb) 
(cond
    ((and (null (stanga arb)) (null (dreapta arb))) 0) 
    (t (+ (max (adancime (stanga arb)) (adancime (dreapta arb))) 1))
)
)


(defun echilibrat (arb)  
(cond  
    ((null arb) t)
    ((and (echilibrat (stanga arb)) (echilibrat (dreapta arb)) (<= (abs (- (adancime (stanga arb)) (adancime (dreapta arb)))) 1) ) t)
)
)