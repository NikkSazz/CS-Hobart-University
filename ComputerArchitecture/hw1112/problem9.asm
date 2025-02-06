; Author: ENTER YOUR NAME HERE
; Gets size and quantity from user and calls "triangle" function.

global _start
extern printf ; External symbol from C library
extern triangle ; External symbol

section .data
    promptSize: db 'Enter size: ', 10, 0  ; Print format
    promptQuantity: db 'Enter quantity: ', 10, 0  ; Print format
    character: dq 0

section .text
_start:
    ; Get size and put it in r14
    mov rdi, promptSize   ; printf format
    call printf       ; Print
    mov rax, 0 ; SYS_read
    mov rdi, 0 ; STDIN
    mov rsi, character ; Memory for input character
    mov rdx, 1 ; Number of characters to read
    syscall
    ; Convert input
    mov r14, [character]
    sub r14, 48 ; Convert ASCII to number

    ; Get newline that was typed
    mov rax, 0 ; SYS_read
    mov rdi, 0 ; STDIN
    mov rsi, character ; Memory for input character
    mov rdx, 1 ; Number of characters to read
    syscall

    ; Get quantity and put it in r15
    mov rdi, promptQuantity   ; printf format
    call printf       ; Print
    mov rax, 0 ; SYS_read
    mov rdi, 0 ; STDIN
    mov rsi, character ; Memory for input character
    mov rdx, 1 ; Number of characters to read
    syscall
    ; Convert input
    mov r15, [character]
    sub r15, 48 ; Convert ASCII to number
	; r14 is size, r15 quantity
    ;----------------
    ; ADD CODE HERE
    mov r12, 0
quantity:
	inc r12
    	cmp r12, r15
    	jg exit
    	
    	mov rdi, r14
    	call triangle
    	jmp quantity
exit:
    ;----------------

    ; Exit
    mov rax, 60  ; Prepare to exit...
    mov rdi, 0   ; ...with no errors
    syscall      ; Execute exit
