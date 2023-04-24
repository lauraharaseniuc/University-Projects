bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, scanf, printf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll  
import printf msvcrt.dll
import scanf msvcrt.dll
extern transformare  ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
;Sa se citeasca un sir s1 (care contine doar litere mici). Folosind un alfabet (definit in segmentul de date), determinati si afisati sirul s2 obtinut prin substituirea fiecarei litere a sirului s1 cu litera corespunzatoare din alfabetul dat.

segment data use32 class=data
    sir1 times 100 db 0
    sir2 times 100 db 0
    format_citire db "%s", 0
    format_afisare db "Dati un cuvant de maxim 100 caractere format numai din litere mici:", 0
    format_afisare2 db "litera alfabet: %d", 0
    alfabet db "OPQRSTUVWXYZABCDEFGHIJKLMN"
    

; our code starts here
segment code use32 class=code

    start:
        push dword format_afisare
        call[printf]
        add esp, 4
        
        push dword sir1 
        push dword format_citire
        call[scanf]
        add esp, 4*2
        
        push dword alfabet
        push dword sir1
        push dword sir2
        call transformare
        add esp, 4*3
        
        push dword sir2
        push dword format_citire
        call[printf] 
        add esp, 4*2
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
