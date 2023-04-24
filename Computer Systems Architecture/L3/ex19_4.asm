bits 32 ;asamblare și compilare pentru arhitectura de 32 biți
; definim punctul de intrare in programul principal
global start

; declaram functiile externe necesare programului nostru 
extern exit ; indicam asamblorului ca exit exista, chiar daca noi nu o vom defini
import exit msvcrt.dll  ; exit este o functie care incheie procesul, este definita in msvcrt.dll
        ; msvcrt.dll contine exit, printf si toate celelalte functii C-runtime importante

; segmentul de date in care se vor defini variabilele 
segment data use32 class=data
; a,b,c-byte 
; e-doubleword
;x-qword
a db 5
b db 2
c db 1
e dd 10
x dq 10

; segmentul de cod
segment code use32 class=code
start:
;(a+a+b*c*100+x)/(a+10)+e*a fara semn
; rez=64
mov al, byte[b]
mul byte[c]                 ; ax = al*c = b*c
mov bx, 100
mul bx                      ; dx:ax = ax*bx = b*c*100 

mov bl, byte[a] 
add bl, [a]                 ; bl = a+a 
mov bh, 0                   ; bx = bl = a+a
mov cx, 0                   ; cx:bx = bx = a+a

add ax, bx
adc dx, cx                  ; dx:ax = b*c*100+a+a 
push dx
push ax
pop eax                     ; eax = dx:ax = b*c*100+a+a
mov edx, 0                  ; edx:eax =  b*c*100+a+a

mov ebx, dword[x]
mov ecx, dword[x+4]         ;ecx:ebx = x

add eax, ebx
adc edx, ecx                ; edx:eax = x+ b*c*100+a+a

mov ebx, 0
mov bl, byte[a]             
mov bh, 0                   ; ebx = bx = a 
add bl, 10                  ; bl = bl +10 = a+10 si ebx = bx = a+10
div ebx                     ; eax = edx:eax/eb x= (x+ b*c*100+a+a)/(a+10)

mov ebx, eax                ; ebx = (x+ b*c*100+a+a)/(a+10)

mov eax, 0
mov al, byte[a]
mov ah, 0                   ; eax = ax = al = a 
mul dword[e]                ; edx:eax = eax*e = a*e

add eax, ebx
adc edx, 0                  ; edx:eax = edx:eax + ebx = a*e + (x+ b*c*100+a+a)/(a+10)
            



    ; exit(0)
    push dword 0 ; se pune pe stiva parametrul functiei exit
    call [exit] ; apelul functiei exit pentru terminarea executiei programului