; Author: Chris Fietkiewicz. Demonstrates how using printf function
; changes the values in rax and rcx.
; To assemble, use the provided "assemble" utility.

global _start
extern printf    ; Declare external symbol from C library

section .data
    format1: db 'r12 = %d, r13 = %d', 10, 0 ; Format for single decimal

section .text
_start:
    mov r12, 123
    mov r13, 456
    ; Print single decimal integer
    mov rdi, format1  ; printf format string goes in rdi
    mov rsi, r12      ; Value to be printed goes in rsi
    mov rdx, r13      ; 2nd value to be printed goes in rdx
    call printf       ; Call library function 'printf'
    
    ; Print single decimal integer
    mov rdi, format1  ; printf format string goes in rdi
    mov rsi, r12      ; Value to be printed goes in rsi
    mov rdx, r13      ; 2nd value to be printed goes in rdx
    call printf       ; Call library function 'printf'
    
    ; Exit
    mov rax, 60  ; Prepare to exit...
    mov rdi, 0   ; ...with no errors
    syscall      ; Execute exit

