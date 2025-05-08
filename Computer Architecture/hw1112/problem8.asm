; Author: ENTER YOUR NAME HERE
; Gets size from user and calls "row" function.

global _start
extern printf ; External symbol from C library
extern row ; External symbol

section .data
    prompt: db 'Enter size: ', 10, 0  ; Print format
    character: dq 0

section .text
_start:
    mov rdi, prompt   ; printf format
    call printf       ; Print
    
    ; Get user input
    mov rax, 0 ; SYS_read
    mov rdi, 0 ; STDIN
    mov rsi, character ; Memory for input character
    mov rdx, 1 ; Number of characters to read
    syscall
    
    ; Convert input
    mov r14, [character]
    sub r14, 48 ; Convert ASCII to number

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
    mov rax, 60  ; Prepare to exit...
    mov rdi, 0   ; ...with no errors
    syscall      ; Execute exit
