bits 32 ;asamblare și compilare pentru arhitectura de 32 biți
; definim punctul de intrare in programul principal
global start

; declaram functiile externe necesare programului nostru 
extern exit, scanf, printf ; indicam asamblorului ca exit exista, chiar daca noi nu o vom defini
import exit msvcrt.dll
import scanf msvcrt.dll
import printf msvcrt.dll  ; exit este o functie care incheie procesul, este definita in msvcrt.dll
        ; msvcrt.dll contine exit, printf si toate celelalte functii C-runtime importante

; segmentul de date in care se vor defini variabilele 
;Sa se citeasca de la tastatura un octet si un cuvant. Sa se afiseze pe ecran daca bitii octetului citit se regasesc consecutiv printre bitii cuvantului. Exemplu:
;a = 10 = 0000 1010b
;b = 256 = 0000 0001 0000 0000b
;Pe ecran se va afisa NU.
;a = 0Ah = 0000 1010b
;b = 6151h = 0110 0001 0101 0001b
;Pe ecran se va afisa DA (bitii se regasesc pe pozitiile 5-12).
segment data use32 class=data
a dd 0
b dd 0
x db 0
y dw 0
format db "%d", 0
format2 db "nr este %d", 0
format3 db "DA", 0
format4 db "NU", 0
ok db 0

; segmentul de cod
segment code use32 class=code
start:
push dword a
push dword format 
call [scanf] 
add esp, 4*2

push dword b
push dword format
call [scanf] 
add esp, 4*2

mov ax, word [b]

; cmp al, byte[a]
; jz egale
; jmp continua2
; egale:
    ; mov bl, 1
    ; mov [ok], bl
; continua2:
    


mov ecx, 8
jecxz final
repeta:
    mov ax, word [b]
    ror ax, cl
    cmp al, byte[a]
    je exista
    jmp continua
    exista: 
        mov bl, 1
        mov [ok], bl
    continua:
loop repeta
final:

mov bl, 1
cmp [ok], bl

je exista2
jmp nu_exista
exista2: 
push dword format3
call[printf] 
add esp, 4*1
jmp final2
nu_exista:
push dword format4
call[printf] 
add esp, 4*1
final2:



    ; exit(0)
    push dword 0 ; se pune pe stiva parametrul functiei exit
    call [exit] ; apelul functiei exit pentru terminarea executiei programului
