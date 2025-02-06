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
    ;----------------

    ; Exit
    pop r12 ; epilogue
    ret