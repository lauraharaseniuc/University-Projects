(defun stanga (arb)  
    (cadr arb)
)

(defun dreapta (arb) 
    (caddr arb)
)

(defun nivel (arb k niv_curent) 
(cond
    ((null (car arb)) nil) 
    ((= niv_curent k) ((lambda (arb)
                        (cond
                            ((and (null (car(stanga arb))) (null (car(dreapta arb)))) nil)
                            ((null (stanga arb)) (list (car(dreapta arb))))
                            ((null (dreapta arb)) (list (car(stanga arb))))
                            (t (list (car (stanga arb)) (car (dreapta arb))))
                        )
                      ) arb )
    ) 
    (t (append (nivel (stanga arb) k (+ niv_curent 1)) (nivel (dreapta arb) k (+ niv_curent 1))) )
)
)

(defun nivel_wrapper (arb k) 
(cond 
    ((null arb) nil)
    ((< k 0) nil)
    ((= k 0) (cons (car arb) nil)) 
    (t (nivel arb k 1))
)
)