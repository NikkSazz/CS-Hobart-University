; Author: Chris Fietkiewicz. Demonstrates how using printf function
; changes the values in rax and rcx.
; To assemble, use the provided "assemble" utility.

global _start
extern printf    ; Declare external symbol from C library

section .data
    format1: db 'rax = %d, rcx = %d', 10, 0 ; Format for single decimal

section .text
_start:
    mov rax, 123
    mov rcx, 456
    ; Print single decimal integer
    mov rdi, format1  ; printf format string goes in rdi
    mov rsi, rax      ; Value to be printed goes in rsi
    mov rdx, rcx      ; 2nd value to be printed goes in rdx
    call printf       ; Call library function 'printf'
    
    ; Print single decimal integer
    mov rdi, format1  ; printf format string goes in rdi
    mov rsi, rax      ; Value to be printed goes in rsi
    mov rdx, rcx      ; 2nd value to be printed goes in rdx
    call printf       ; Call library function 'printf'
    
    ; Exit
    mov rax, 60  ; Prepare to exit...
    mov rdi, 0   ; ...with no errors
    syscall      ; Execute exit

