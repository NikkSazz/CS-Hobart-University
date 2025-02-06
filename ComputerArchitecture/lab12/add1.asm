; Author: Chris Fietkiewicz. Adds 2 + 3 and prints the result.
global _start
extern printf    ; Declare external symbol from C library

section .data
    format: db 'The sum is %d.', 10, 0 ; Format for single decimal

section .text
_start:
    ; Compute sum
    mov rax, 2
    add rax, 3

    ; Print single decimal integer
    mov rdi, format  ; printf format string goes in rdi
    mov rsi, rax      ; Value to be printed goes in rsi
    call printf       ; Call library function 'printf'
    
    ; Exit
    mov rax, 60  ; Prepare to exit...
    mov rdi, 0   ; ...with no errors
    syscall      ; Execute exit

