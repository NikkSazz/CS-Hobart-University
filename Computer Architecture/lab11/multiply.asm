; Author: Chris Fietkiewicz
; Demonstrates using "mul <src>" which computes rax = rax * <src>.

global _start
extern printf    ; Declare external symbol from C library

section .data
    a: dq 225
    print_result: db 'The product of 255 x 255 is %d.', 10, 0

section .text
_start:
    mov rax, [a]
    mul qword [a]
    mov rdi, print_result   ; printf format string goes in rdi
    mov rsi, qword rax
    call printf       ; Call library function 'printf'

    ; Exit
    mov rax, 60  ; Prepare to exit...
    mov rdi, 0   ; ...with no errors
    syscall      ; Execute exit
