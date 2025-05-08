; Author: Chris Fietkiewicz
; Demonstrates:
;   * Printing a string using syscall (like hello.asm in Lab #9).
;   * Reading a character from the keyboard.
;   * Printing the ASCII code that was received.
;   * Reading the newline character (10) that is also typed.
;   * Printing the ASCII code of the newline character.

global _start
extern printf ; External symbol from C library

section .data
    promptFormat: db 'Enter a character: ', 0  ; Print format
    promptLen: equ $ - promptFormat
    outputFormat: db 'Got ASCII code = %d', 10, 0  ; Print format
    character: dq 0 ; Store character here 

section .text
_start:
    ; Print prompt
    mov rax, 1        ; write(
    mov rdi, 1        ;   STDOUT_FILENO,
    mov rsi, promptFormat ; string address
    mov rdx, promptLen   ;   sizeof(string)
    syscall           ; );

    ; Get character that was typed
    mov rax, 0 ; SYS_read
    mov rdi, 0 ; STDIN
    mov rsi, character ; Memory for input character
    mov rdx, 1 ; Number of characters to read
    syscall
    
    ; Print ASCII code that was stored
    mov rdi, outputFormat   ; printf format
    mov rsi, [character] ; Value to be printed
    call printf       ; Print

    ; Get newline that was typed
    mov rax, 0 ; SYS_read
    mov rdi, 0 ; STDIN
    mov rsi, character ; Memory for input character
    mov rdx, 1 ; Number of characters to read
    syscall
    
    ; Print ASCII code that was stored
    mov rdi, outputFormat   ; printf format
    mov rsi, [character] ; Value to be printed
    call printf       ; Print

    ; Exit
    mov rax, 60  ; Prepare to exit...
    mov rdi, 0   ; ...with no errors
    syscall      ; Execute exit
