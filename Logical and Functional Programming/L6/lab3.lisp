; l - parametrul din care se extrag atomii aflati pe toate nivelurile
; output - lista atomilor din parametrul dat aflati pe toate nivelurile

(defun atomi (l)
(cond
    ((atom l) (list l)) 
    (t (apply #' append (mapcar #' atomi l)))
    
)
)