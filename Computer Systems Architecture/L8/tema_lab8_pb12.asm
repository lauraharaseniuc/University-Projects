bits 32 ;asamblare și compilare pentru arhitectura de 32 biți
; definim punctul de intrare in programul principal
global start

; declaram functiile externe necesare programului nostru 
extern exit, fprintf, fopen, fclose, scanf, printf ; indicam asamblorului ca exit exista, chiar daca noi nu o vom defini
import exit msvcrt.dll
import fprintf msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import scanf msvcrt.dll 
import printf msvcrt.dll ; exit este o functie care incheie procesul, este definita in msvcrt.dll
        ; msvcrt.dll contine exit, printf si toate celelalte functii C-runtime importante

; segmentul de date in care se vor defini variabilele 
;Se da un nume de fisier (definit in segmentul de date). Sa se creeze un fisier cu numele dat, apoi sa se citeasca de la tastatura numere si sa se scrie valorile citite in fisier pana cand se citeste de la tastatura valoarea 0.
segment data use32 class=data
nume_fisier db "p12.txt", 0
descriptor dd 0
mod_acces db "w", 0
nr dd -1
format db "%d", 0
format_afisare db "%d ", 0
text db "astazi", 0

; segmentul de cod
segment code use32 class=code
start:
push dword mod_acces
push dword nume_fisier
call [fopen]
add esp, 4*2

mov [descriptor], eax
cmp eax, 0
je final

;se citesc numere de la tastatura pana la intalnirea lui 0
incepe_citirea:
push dword nr
push dword format
call [scanf]
add esp, 4*2

;se compara nr citit cu 0
mov eax, [nr]
cmp eax, 0
je final
;.........................
;se adauga numarul in fisier
push dword [nr]
push dword format_afisare
push dword [descriptor]
call [fprintf]
add esp, 4*3
;.............................
jmp incepe_citirea
;............................................................

push dword[descriptor]
call[fclose]
add esp, 4*1


final:
    ; exit(0)
    push dword 0 ; se pune pe stiva parametrul functiei exit
    call [exit] ; apelul functiei exit pentru terminarea executiei programului