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
a db 40
b db 1
c db -1
e dd 1
x dq 120
aux resd 1
; pt. valorile date rez=42

; segmentul de cod
segment code use32 class=code
start:
;(a+a+b*c*100+x)/(a+10)+e*a cu semn
mov al, byte[b]
mov bl, byte[c] 
imul bl                 ; ax = al*bl = b*c 
mov bx, 100
imul bx                 ; dx:ax = ax*bx = b*c*100 

mov cx, dx
mov bx, ax              ; cx:bx = dx:ax = b*c*100 

mov al, byte[a]
add al, [a]
cbw                     ; ax = a+a 
add bx, ax
adc cx, 0               ; cx:bx = b*c*100 + a + a

push cx
push bx
pop eax
cdq                     ; edx:eax = eax = cx:bx =  b*c*100 + a + a

mov ebx, dword[x] 
mov ecx, dword[x+4]     ; ecx:ebx = x

add ebx, eax
adc ecx, edx            ; ecx:ebx = x + b*c*100 + a + a

mov al, byte[a] 
add al, 10
cbw                     ; ax = al = a+10
cwde                    ; eax = ax = a+10
mov [aux], eax

mov eax, ebx
mov edx, ecx            ; edx:eax = x + b*c*100 + a + a
idiv dword[aux]         ; eax = edx:eax/aux = (x + b*c*100 + a + a)/(a+10)

mov ebx, eax
mov ecx, edx            ;ecx:ebx = edx:eax = (x + b*c*100 + a + a)/(a+10)

mov al, byte[a]
cbw                     ; ax = al = a 
cwde                    ; eax = ax = a
imul dword[e]           ; edx:eax = eax*e = a*e 

add eax, ebx
adc edx, ecx            ;edx:eax = (x + b*c*100 + a + a)/(a+10) + a*e


    ; exit(0)
    push dword 0 ; se pune pe stiva parametrul functiei exit
    call [exit] ; apelul functiei exit pentru terminarea executiei programului