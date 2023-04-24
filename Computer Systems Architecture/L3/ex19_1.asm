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
a db 10
b dw 20
c dd 30
d dq 100
e resq 1

; segmentul de cod
segment code use32 class=code
start:
; e = (d+d) - (a+a) - (b+b) - (c+c) fara semn
;il pun pe d+d in edx:eax
mov eax, dword[d]
mov edx, dword[d+4]
add eax, eax 
adc edx, edx        ; edx:eax = d+d 

mov ebx, 0
mov bl, byte[a] 
add bl, [a]         ; bl = bl+a = a+a si ebx = bl = a+a

mov ecx, 0          ; ecx:ebx = ebx = a+a 

;fac (d+d) - (a+a) 
sub eax, ebx 
sbb edx, ecx        ; eax:edx = (d+d) - (a+a)

;il pun pe  b+b in ecx:ebx 
mov ecx, 0 
mov ebx, 0
mov bx, word[b]
add bx, [b]

sub eax, ebx 
sbb edx, ecx        ; eax:edx = (d+d) - (a+a) - (b+b) 

;il pun pe c+c in ecx:ebx
mov ecx, 0 
mov ebx, dword[c]
add ebx, [c]

sub eax, ebx
sbb edx, ecx        ; eax:edx = (d+d) - (a+a) - (b+b) - (c+c)

push eax
pop dword[e]             ; e = eax:edx = (d+d) - (a+a) - (b+b) - (c+c)
push edx 
pop dword[e+4]
          

    ; exit(0)
    push dword 0 ; se pune pe stiva parametrul functiei exit
    call [exit] ; apelul functiei exit pentru terminarea executiei programului
