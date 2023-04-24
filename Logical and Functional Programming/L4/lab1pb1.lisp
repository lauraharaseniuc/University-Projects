(defun inserare (elem l index)
(cond
    ((null l) nil)
    ( (= (mod index 2) 0) ( cons (car l) (cons elem (inserare elem (cdr l) (+ index 1) ) )) )
    (t (cons (car l) (inserare elem (cdr l) (+ index 1)) ))
)
)

(defun inserare_wrapper (elem l)
(inserare elem l 1)
)

(defun atomi (l col) 
(cond
    ((null l) col)
    ((atom (car l)) (atomi (cdr l) (cons (car l) col))) 
    ((listp (car l)) (atomi (cdr l) (atomi (car l) col)))
)
)

(defun atomi_wrapper (l)
(atomi l nil) 
)

(defun cmmdc (a b) 
(cond
    ((or (< a 0) (< b 0)) 1)
    ((= a b) a)
    ((< a b) (cmmdc a (- b a)))
    (t (cmmdc (- a b) b))
)
) 

(defun cmmdc_lista (l ultim) 
(cond
    ((null l) ultim)
    ((and (= ultim -1) (numberp (car l))) (cmmdc_lista (cdr l) (car l))) 
    ((and (numberp (car l)) (not(= ultim -1))) (cmmdc_lista (cdr l) (cmmdc (car l) ultim)))
    ((and (atom (car l)) (not (numberp (car l))) ) (cmmdc_lista (cdr l) ultim))
    ((and (listp (car l)) (not(= (cmmdc_lista (car l) -1) -1))) (cmmdc_lista (cdr l) (cmmdc (cmmdc_lista (car l) -1) ultim))) 
    ((and (listp (car l)) (= (cmmdc_lista (car l) -1) -1)) (cmmdc_lista (cdr l) ultim))
    
)
)

(defun cmmdc_lista_wrapper (l)  
(cmmdc_lista l -1)
)

(defun nr_aparitii (l elem)  
(cond
    ((null l) 0) 
    ((atom (car l))  
        (cond 
            ((eq (car l) elem) (+ 1 (nr_aparitii (cdr l) elem))) 
            (t (nr_aparitii (cdr l) elem))
        )
    )
    (t (+ (nr_aparitii (car l) elem) (nr_aparitii (cdr l) elem)))
)
)