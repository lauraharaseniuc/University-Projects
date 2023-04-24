bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start      
global transformare  

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...

; our code starts here
segment code use32 class=code
 
transformare: 
;transformare(sir2, sir1, alfabet)
;[ESP]-adresa de revenire
;[ESP+4]-adresa lui sir2
;[ESP+8]-adresa lui sir1
;[ESP+12]-adresa lui alfabet
mov esi, [esp+8]
mov edi, [esp+4]
mov ebx, [esp+12] ; mut in eax adresa de inceput a alfabetului
mov edx, 0;pe edx in folosesc ca si contor in interiorul lui ebx

repeta:
lodsb 
cmp al, 0
je out_repeta
sub al, 'a'
mov dl,al
mov al, [ebx+edx]
stosb
jmp repeta
out_repeta:

mov [esp+4], edi

ret
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
