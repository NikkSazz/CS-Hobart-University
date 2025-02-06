; Jorgensen p177
; Calls stats1
global _start
extern stats1 ; External symbol
extern printf ; External symbol from C library

section .data
    arr: dd 10, 20, 30, 40
    formatSum: db 'Sum is %d.', 10, 0  ; Print format
    formatAve: db 'Average is %d.', 10, 0  ; Print format
    ave: dq 0
    sum: dq 0
    len: dd 4

section .text
_start:
    mov rcx, ave ; 4th arg, addr of ave
    mov rdx, sum ; 3rd arg, addr of sum
    mov esi, dword [len] ; 2nd arg, value of len
    mov rdi, arr ; 1st arg, addr of arr
    call stats1

    mov rdi, formatSum   ; printf format
    mov rsi, [sum] ; Value to be printed
    call printf       ; Print

    mov rdi, formatAve   ; printf format
    mov rsi, [ave] ; Value to be printed
    call printf       ; Print

    ; Exit
    mov rax, 60  ; Prepare to exit...
    mov rdi, 0   ; ...with no errors
    syscall      ; Execute exit
