; Author: Chris Fietkiewicz
; Repeatedly gets a menu choice

global _start
extern printf ; External symbol from C library

section .data
    menu: db '0. Quit', 10, '1. Option #1', 10, '2. Option #2', 10, 0
    prompt: db 'Enter your choice: ', 10, 0
    character: dq 0
    option1Text: db 'You chose option #1', 10, 0
    option2Text: db 'You chose option #2', 10, 0

section .text
_start:
    mov rdi, menu   ; printf format
    call printf       ; Print
    mov rdi, prompt   ; printf format
    call printf       ; Print
    
    ; Get user input
    mov rax, 0 ; SYS_read
    mov rdi, 0 ; STDIN
    mov rsi, character ; Memory for input character
    mov rdx, 1 ; Number of characters to read
    syscall
    
    mov r12, [character]

    ; Get newline that was typed
    mov rax, 0 ; SYS_read
    mov rdi, 0 ; STDIN
    mov rsi, character ; Memory for input character
    mov rdx, 1 ; Number of characters to read
    syscall
    
    ; Check user input
    cmp r12, 48 ; Compare to ASCII for 0
    je exit
    cmp r12, 49 ; Compare to ASCII for 1
    je option1
    
    ;;;;;;;;;;;;;;;;;;;;;;;
    ; Option #2
    mov rdi, option2Text  ; printf format
    call printf       ; Print
    jmp _start

    ;;;;;;;;;;;;;;;;;;;;;;;
    ; Option #1
option1:
    mov rdi, option1Text  ; printf format
    call printf       ; Print
    jmp _start

   ; Exit
exit:
    mov rax, 60  ; Prepare to exit...
    mov rdi, 0   ; ...with no errors
    syscall      ; Execute exit
