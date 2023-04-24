(defun produs (a b)  
(cond 
    ((null a) b) 
    ((null b) a) 
    (t (cons (* (car a) (car b)) (produs (cdr a) (cdr b))))
)
)

(defun adancime (l nivel)  
(cond 
    ((null l) nivel) 
    ((listp (car l)) (max (adancime (cdr l) nivel) (adancime (car l) (+ nivel 1)) ))
    (t (adancime (cdr l) nivel))
)
)

(defun partition (l) 
(cond
    ((null l) (list nil nil)) 
    ((null (cdr l)) (cons (cons (car l) nil) (cons nil nil)))
    (t (list (cons (car l) (car (partition (cddr l)))) (cons (cadr l) (cadr (partition(cddr l))) ) ))
) 
)

(defun mymerge (a b) 
(cond 
    ((null a) b)
    ((null b) a) 
    ((< (car a) (car b)) (cons (car a) (mymerge (cdr a) b))) 
    ((< (car b) (car a)) (cons (car b) (mymerge a (cdr b))))
    (t (cons (car a) (mymerge (cdr a) (cdr b))))
)
)



(defun merge_sort (l)  
(cond 
    ((null l) l) 
    ((null (cdr l)) l)
    (t (mymerge (merge_sort (car (partition l))) (merge_sort (cadr (partition l)))))
)
)

(defun apartine (l elem) 
(cond
    ((null l) l) 
    ((eq (car l) elem) t) 
    (t (apartine (cdr l) elem))
)
)

(defun intersectie (a b) 
(cond 
    ((null a) a) 
    ((apartine b (car a)) (cons (car a) (intersectie (cdr a) b))) 
    (t (intersectie (cdr a) b))
)
)