; Author: Chris Fietkiewicz
; Gets array values from user.

global _start
extern printf ; External symbol from C library

section .data
    prompt: db 'Enter 1-digit number for element #%d: ', 10, 0  ; Print format
    data: dq 0, 0, 0, 0, 0
    output1: db 'You entered:', 10, 0  ; Print format
    output2: db '%d', 10, 0  ; Print format
    character: db 0

section .text
_start:
    mov r12, 0

inputLoop:
    mov rdi, prompt   ; printf format
    mov rsi, r12      ; Value to be printed
    call printf       ; Print
    
    ; Get user input
    mov rax, 0 ; SYS_read
    mov rdi, 0 ; STDIN
    mov rsi, character ; Memory for input character
    mov rdx, 1 ; Number of characters to read
    syscall
    
    ; Store value in array
    mov r13, [character]
    sub r13, 48 ; Convert ASCII to number
    mov [data + r12 * 8], r13

    ; Get newline that was typed
    mov rax, 0 ; SYS_read
    mov rdi, 0 ; STDIN
    mov rsi, character ; Memory for input character
    mov rdx, 1 ; Number of characters to read
    syscall
    
    add r12, 1
    cmp r12, 5
    jl inputLoop

    ; Output array
    mov rdi, output1   ; printf format
    call printf       ; Print
    mov r12, 0

outputLoop:
    mov rdi, output2   ; printf format
    mov rsi, [data + r12 * 8]      ; Value to be printed
    call printf       ; Print
    
    add r12, 1
    cmp r12, 5
    jl outputLoop

   ; Exit
    mov rax, 60  ; Prepare to exit...
    mov rdi, 0   ; ...with no errors
    syscall      ; Execute exit
