; Author: Chris Fietkiewicz. Prints integers using printf function.
; To assemble, use the provided "assemble" utility. First, copy the
; "assemble" script file to your current directory. Then execute the
; script to assemble *and* link to an executable. Finally, execute
; your program. Note that you only need to copy the "assemble" file
; once, and then it will be in your directory later. Type this:
;      cp /home/fietkiewicz/220/assemble .
;      ./assemble printNumbers
;      ./printNumbers

global _start
extern printf    ; Declare external symbol from C library

section .data
    format1: db 'One number is %d.', 10, 0 ; Format for single decimal
    format2: db 'Two more numbers are %d and %d.', 10, 0 ; Format for two decimals

section .text
_start:
    ; Print single decimal integer
    mov rdi, format1  ; printf format string goes in rdi
    mov rsi, 123      ; Value to be printed goes in rsi
    call printf       ; Call library function 'printf'
    
    ; Print two decimal integers
    mov rdi, format2  ; printf format string goes in rdi
    mov rsi, 456      ; 1st value to be printed goes in rsi
    mov rdx, 789      ; 2nd value to be printed goes in rdx
    call printf       ; Call library function 'printf'
    
    ; Exit
    mov rax, 60  ; Prepare to exit...
    mov rdi, 0   ; ...with no errors
    syscall      ; Execute exit

