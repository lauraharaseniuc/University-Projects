bits 32 ;asamblare și compilare pentru arhitectura de 32 biți
; definim punctul de intrare in programul principal
global start

; declaram functiile externe necesare programului nostru 
extern exit ; indicam asamblorului ca exit exista, chiar daca noi nu o vom defini
import exit msvcrt.dll  ; exit este o functie care incheie procesul, este definita in msvcrt.dll
        ; msvcrt.dll contine exit, printf si toate celelalte functii C-runtime importante

; segmentul de date in care se vor defini variabilele 
segment data use32 class=data
; a - word
; b - byte
; c - doubleword

a dw 0011101100110010b
b db 11110011b 
c dd 0


; segmentul de cod
segment code use32 class=code
start:
;construim pe c in ebx 
mov ebx, 0 

;bitii 0-3 ai lui C coincid cu bitii 6-9 ai lui A
mov eax, 0 
mov ax, [a]
and eax, 0000001111000000b
ror eax, 6
or ebx, eax

;bitii 4-5 ai lui C au valoarea 1
or ebx, 0000000000110000b

;bitii 6-7 ai lui C coincid cu bitii 1-2 ai lui B
mov eax, 0
mov al, [b]
and eax, 0000000000000110b
rol eax, 5
or ebx, eax

;bitii 8-23 ai lui C coincid cu bitii lui A
mov eax, 0 
mov ax, [a]
and eax, 1111111111111111b
rol eax, 8
or ebx, eax

;bitii 24-31 ai lui C coincid cu bitii lui B
mov eax, 0
mov al, [b]
and eax, 11111111b
rol eax, 24
or ebx, eax

mov [c], ebx


    ; exit(0)
    push dword 0 ; se pune pe stiva parametrul functiei exit
    call [exit] ; apelul functiei exit pentru terminarea executiei programului