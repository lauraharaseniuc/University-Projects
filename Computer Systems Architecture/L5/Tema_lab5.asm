bits 32 ;asamblare și compilare pentru arhitectura de 32 biți
; definim punctul de intrare in programul principal
global start

; declaram functiile externe necesare programului nostru 
extern exit ; indicam asamblorului ca exit exista, chiar daca noi nu o vom defini
import exit msvcrt.dll  ; exit este o functie care incheie procesul, este definita in msvcrt.dll
        ; msvcrt.dll contine exit, printf si toate celelalte functii C-runtime importante

; segmentul de date in care se vor defini variabilele 
segment data use32 class=data
;segment de date
;Se da un sir de octeti S. Sa se construiasca sirul D astfel: sa se puna mai intai elementele de pe pozitiile pare din S iar apoi elementele de pe pozitiile impare din S.
s db 1, 2, 3, 4, 5, 6, 7, 8, 9
ls equ $-s
d times ls db 0
a db 0


; segmentul de cod
segment code use32 class=code
start:
mov ecx, ls
mov esi, 0
mov ebx, 0
mov edx, (ls+1)/2
jecxz final  
    repeta: 
        mov al, [s+esi]
        test al, 01h
        jne impar
            mov [d+edx], al
            inc edx
            jmp final_par
        impar:
            mov [d+ebx], al
            inc ebx
        final_par:
        inc esi
    loop repeta
final:

    ; exit(0)
    push dword 0 ; se pune pe stiva parametrul functiei exit
    call [exit] ; apelul functiei exit pentru terminarea executiei programului