; Author: Chris Fietkiewicz. Demonstrates console input.
global _start
extern printf ; External symbol from C library

section .data
    format: db 'You entered %d', 10, 0  ; Print format
    input: dq 0

section .text
_start:
    mov rax, 0 ; SYS_read
    mov rdi, 0 ; STDIN
    mov rsi, input ; Memory for input character
    mov rdx, 1 ; Number of characters to read
    syscall

    mov rax, [input]
    sub rax, 48 ; Subtract 48 to convert ASCII to number

    mov rdi, format   ; printf format
    mov rsi, rax ; Value to be printed
    call printf       ; Print

    ; Exit
    mov rax, 60  ; Prepare to exit...
    mov rdi, 0   ; ...with no errors
    syscall      ; Execute exit
