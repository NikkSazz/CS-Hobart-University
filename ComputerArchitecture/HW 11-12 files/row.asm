; Author: Chris Fietkiewicz
; Print a row of dots. Size is 1st argument (rdi)
global row
extern printf ; External symbol from C library

section .data
    dot: db '*', 0
    newline: db 10, 0

section .text
row:
    push r12 ; prologue
    mov r12, 0 ; Counter
    mov r13, rdi ; Number of dots

loop:
    cmp r12, r13
    je exit
    mov rdi, dot   ; printf format
    call printf       ; Print
    add r12, 1
    jmp loop
    
exit:
    mov rdi, newline   ; printf format
    call printf       ; Print
    pop r12 ; epilogue
    ret