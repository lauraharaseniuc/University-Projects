(defpackage :hangman 
    (:use :cl)
)

(in-package :hangman) 

; Set random state
(setf *random-state* (make-random-state t))

(defun choose-gallows (n)  
    (let ((gallowses  
            #(  
            " 
    ||--------===+ 
    ||           
    ||          
    ||          
    || 
    -------------------
   /===================\\
  /=====================\\
 /=======================\\
/=========================\\
---------------------------

"
                " 
    ||--------===+ 
    ||           o
    ||         
    ||      
    || 
    -------------------
   /===================\\
  /=====================\\
 /=======================\\
/=========================\\
---------------------------

"
" 
    ||--------===+ 
    ||           o
    ||           |
    ||          
    || 
    -------------------
   /===================\\
  /=====================\\
 /=======================\\
/=========================\\
---------------------------

"
" 
    ||--------===+ 
    ||           o
    ||          /|
    ||          
    || 
    -------------------
   /===================\\
  /=====================\\
 /=======================\\
/=========================\\
---------------------------

"
" 
    ||--------===+ 
    ||           o
    ||          /|\\
    ||          
    || 
    -------------------
   /===================\\
  /=====================\\
 /=======================\\
/=========================\\
---------------------------

"
" 
    ||--------===+ 
    ||           o
    ||          /|\\
    ||          / 
    || 
    -------------------
   /===================\\
  /=====================\\
 /=======================\\
/=========================\\
---------------------------

"
" 
    ||--------===+ 
    ||           o
    ||          /|\\
    ||          / \\
    || 
    -------------------
   /===================\\
  /=====================\\
 /=======================\\
/=========================\\
---------------------------

"
            )
          ) 
         )
         (elt gallowses n)
    )
)

(defun choose-random-word ()  
(let ((words '( randomized state queen number
                careful attentive maniac american
                pineapple fabulous exceptional
                camera photo website assembly graph
              )  
      )  
     )
     (string (nth (random (length words)) words))
)
)


(defun choose-random-symbol-from-package (&optional (pkg :cl)) 
(let ((package-count (if (eql pkg :cl) 978  
                         (loop for c being each symbol of pkg count c)
                     ) ))
                     (loop for i from 0 to (random package-count) 
                     for sym being each symbol of pkg
                     for chosen_symbol=sym
                     finally (return (string sym)))
)
)

(defun generate-secret-word (&optional (difficulty 'hard)) 
(if (eql difficulty 'hard) (choose-random-symbol-from-package) 
(choose-random-word) 
)
)

(defun mask-secret-word (secret_word)  
(make-string (length secret_word) :initial-element #\-)
)

(defun display-spaced-mistaked-characters (mistaked_characters)
(map 'string (lambda (mistake) (princ mistake) (princ #\ )) mistaked_characters) 
)

(defun unmask-guessed-characters (secret_word guessed_characters masked_word) 
(loop for position below (length secret_word) 
    if (find (elt secret_word position) guessed_characters :test #'char-equal) 
        do (setf (elt masked_word position) (elt secret_word position))
    finally (return masked_word)
)
)

(defun get-user-guess ()
(let ((user_input (read-line))) 
(return-from get-user-guess user_input)
) 
)

(defun guess-character (characters_already_guessed)  
(loop  
    (format t "~&Your guess: ") 
    (finish-output)
    (let ((guessed_character (get-user-guess))) 
        (cond
            ((not (= (length guessed_character) 1)) (format t "~&Please enter a sigle character!~%")) 
            ((search guessed_character characters_already_guessed) (format t "~&You already guessed that character!~%")) 
            (t (return guessed_character))
        )
    )
)
)

(defun check-if-all-characters-are-guessed (secret_word characters_guessed_correctly)  
(loop  
    for character across secret_word 
    if (not (search (string character) characters_guessed_correctly :test #'char-equal)) return nil
    finally (return t)
)
)

(defun display-hanging-scene (secret_word characters_guessed_correctly mistakes) 
(format t "~&Your mistakes: ") 
(display-spaced-mistaked-characters mistakes) 
(terpri) 
; Choose appropriate gallow
(format t (choose-gallows (length mistakes)))
(format t (unmask-guessed-characters secret_word characters_guessed_correctly (mask-secret-word secret_word)))
)

(defun choose-set-free-prompt ()
(let* ((freedom_blurbs '("You're free!" 
                       "We could not get you this time!" 
                       "Maybe next time your enemies wil succeed..."
                       "Congratulations you have been set free!"))  
      (freedom_blurb (nth (random (length freedom_blurbs)) freedom_blurbs))
     )
     freedom_blurb
)
)

(defun choose-game-lost-prompt ()  
(let* ((loser_prompts '("Try again next time!" "Ooops! Looks like there are no more trials available." "Too many mistakes were made..." "We will have to hang poor Perp now..."))
       (loser_prompt (nth (random (length loser_prompts)) loser_prompts))
      ) 
      loser_prompt
)
)

(defun choose-cheater-prompt ()  
(let* ((cheater_prompts '("Still chetaing after death..." "Cheaters never win!" "Once a cheater always a cheater!"))  
       (cheater_prompt (nth (random (length cheater_prompts)) cheater_prompts))
      ) 
      cheater_prompt
)
)


(defun run-the-game (&key (difficulty 'hard) (cheat_mode_on nil) 
                     &aux (secret_word (generate-secret-word difficulty)) 
                          (characters_guessed_correctly (make-array (length secret_word) :fill-pointer 0 :adjustable t :element-type 'character))
                          (mistakes (make-array 7 :fill-pointer 0 :adjustable t :element-type 'character))
                          (gallowses_count 7)
                    ) 
(loop  
(display-hanging-scene secret_word characters_guessed_correctly mistakes)
(let ((guess (guess-character (concatenate 'string characters_guessed_correctly mistakes) )))
     (cond 
        ((search guess secret_word :test #'string-equal) (vector-push (coerce guess 'character) characters_guessed_correctly))
        (t (vector-push (coerce guess 'character) mistakes))
     )
     
     (cond 
        ((= (length mistakes) (- gallowses_count 1)) (return (progn 
                                                                    (format t "~2&~a~2%" (choose-gallows  (- gallowses_count 1)))  
                                                                    (format t "~a~%" (choose-game-lost-prompt))
                                                                    (if cheat_mode_on 
                                                                    (format t "~a ~a~%" (choose-cheater-prompt) secret_word)))
                                                     )
        )
        ((check-if-all-characters-are-guessed secret_word characters_guessed_correctly) (return (progn  
                                                                                                    (format t "~&You got the word: ~a~%"secret_word) 
                                                                                                    (format t "~&~a~%" (choose-set-free-prompt))) 
                                                                                        ) 
        )
)
)
)
)

(defun run-interactive-game (&key (difficulty 'hard) (cheat_mode_on t))  
(run-the-game :difficulty difficulty :cheat_mode_on cheat_mode_on)
(format t "~2&Play again? [y\\n]?")
(finish-output)
(let ((play_again (read-line))) 
(if (or (string-equal play_again "y") 
        (string-equal play_again "yes")) 
        (run-interactive-game :difficulty difficulty :cheat_mode_on cheat_mode_on)) 
(return-from run-interactive-game "Game ended.")
)
)