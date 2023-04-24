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
;Se da un sir de octeti reprezentand un text (succesiune de cuvinte separate de spatii). Sa se identifice cuvintele de tip palindrom (ale caror oglindiri sunt similare cu cele de plecare): "cojoc", "capac" etc.
s db 'cojoc e '
ls equ $-s
cuvant times ls db 0
nr_cuv_pal db 0



; segmentul de cod
segment code use32 class=code
start:
mov ecx, ls
jecxz final
mov esi, s 
mov edx, 0
repeta: 
    lodsb
    cmp al, ' '
    jnz e_litera
        ; verifica daca cuvant este palindrom....................
        push ecx
            mov ecx, edx 
            mov ebx, 0
            dec edx
            repeta2:
                mov al, [cuvant+ebx] 
                mov ah, [cuvant+edx]
                dec edx 
                inc ebx
                cmp al, ah
                jnz nu_e_palindrom
            loop repeta2
        inc byte[nr_cuv_pal]
        mov edx, 0 
        nu_e_palindrom:
        mov edx, 0
        pop ecx
        jmp continua
        ;.........................................................
    e_litera:
        ;adauga litera la cuvantul cuvant litera al...............
        mov [cuvant+edx], al
        inc edx 
        ;.........................................................
    continua:
loop repeta
final:
mov eax, [nr_cuv_pal]


    ; exit(0)
    push dword 0 ; se pune pe stiva parametrul functiei exit
    call [exit] ; apelul functiei exit pentru terminarea executiei programului