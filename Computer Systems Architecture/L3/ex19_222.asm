bits 32 ;asamblare și compilare pentru arhitectura de 32 biți
; definim punctul de intrare in programul principal
global start

; declaram functiile externe necesare programului nostru 
extern exit ; indicam asamblorului ca exit exista, chiar daca noi nu o vom defini
import exit msvcrt.dll  ; exit este o functie care incheie procesul, este definita in msvcrt.dll
        ; msvcrt.dll contine exit, printf si toate celelalte functii C-runtime importante

; segmentul de date in care se vor defini variabilele 
segment data use32 class=data
; a - byte
; b - word
; c - doubleword
; d - quadword
a db -10
b dw -20
c dd 40
d dq 10
e resq 1

; segmentul de cod
segment code use32 class=code
start:
; (d+a)-(c-b)-(b-a)+(c+d) cu semn

;il pun pe d in ecx:ebx 
mov ebx, dword[d]
mov ecx, dword[d+4]

;il extind pe a la edx:eax cu semn
mov al, byte[a]             ; al = a
cbw                         ; ax = al = a
cwde                        ; eax = ax = a
cdq                         ; edx:eax = eax = a  

add eax, ebx
adc edx, ecx                ; edx:eax = d+a 


mov ebx, dword[c]
mov cx, word[b]
sub bx, cx
sbb ebx, 0                  ; ebx = c-b

mov ecx, 0                  ; ecx:ebx = c-b

sub eax, ebx
sbb edx, ecx                ; edx:eax = (d+a) - (c-b)

mov ebx, eax
mov ecx, edx                ; ecx:ebx = edx:eax = (d+a) - (c-b)

mov al, byte[a] 
cbw                         ;ax = al = a  
mov dx, word[b]
sub dx, ax                  ; dx = dx - ax = b-a
mov ax, dx                  ; ax = dx = b-a 
cwde                        ; eax = ax = b-a 

sub ebx, eax 
sbb ecx, 0                  ; ecx:ebx = (d+a) - (c-b) - (b-a)

;il pun pe d in edx:eax 
mov eax, dword[d]
mov edx, dword[e+4]

add eax, [c] 
adc edx, 0                  ; edx:eax = (d+c)

add eax, ebx
adc edx, ecx                ; edx:eax = ecx:ebx + edx:eax = (d+a) - (c-b) - (b-a) + (d+c)

push eax
pop dword[e] 
push edx
pop dword[e+4]              ; e = edx:eax = (d+a) - (c-b) - (b-a) + (d+c)


    ; exit(0)
    push dword 0 ; se pune pe stiva parametrul functiei exit
    call [exit] ; apelul functiei exit pentru terminarea executiei programului