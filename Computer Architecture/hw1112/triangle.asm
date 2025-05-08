; Author: ENTER YOUR NAME HERE
; Print a triangle. Size is 1st argument (rdi).
; Calls "row" function

global triangle
extern row ; External symbol

section .text
triangle:
    push r12 ; prologue
    mov r14, rdi ; Size

    ;----------------
    ; ADD CODE HERE
    mov r12, 1
loop: 
    cmp r12, r14
    jg exit
    mov rdi, r12   
    call row
    inc r12
    jmp loop
exit:
    ;----------------
    ; Exit
    pop r12 ; epilogue
    ret
