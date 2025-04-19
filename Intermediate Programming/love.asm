section .data
    # prompt db 'Who is the salt to my tomato? <3 ', 0
    prompt db '*Insert romantic Question here* <3 ', 0
    response db 'I love you  <3 !', 0
    spacer db 10, 0  ; Spacer for newlines (newline character)

section .bss
    input resb 10

section .text
    global _start

_start:
    ; Display prompt message
    mov rax, 1          ; sys_write
    mov rdi, 1          ; file descriptor 1 (stdout)
    mov rsi, prompt     ; address of prompt
    mov rdx, 31         ; length of prompt
    syscall

    ; Read user input (maximum 10 characters)
    mov rax, 0          ; sys_read
    mov rdi, 0          ; file descriptor 0 (stdin)
    lea rsi, [input]    ; address of input buffer
    mov rdx, 12         ; maximum bytes to read (10 characters + metadata)
    syscall

    ; Skip first two bytes of metadata (maximum size and bytes read)
    lea rsi, [input]  ; rsi points to the actual input (skip metadata)
    
    ; Display the input
    mov rdi, 1          ; file descriptor 1 (stdout)
    mov rdx, rax        ; length of input string (bytes read)
    mov rax, 1          ; sys_write
    syscall

    ; Display "I love you" message
    mov rax, 1          ; sys_write
    mov rdi, 1          ; file descriptor 1 (stdout)
    mov rsi, response   ; address of response
    mov rdx, 19         ; length of response
    syscall
    
      ; Add a newline after the first input display
    mov rax, 1          ; sys_write
    mov rdi, 1          ; file descriptor 1 (stdout)
    lea rsi, [spacer]   ; address of newline
    mov rdx, 1          ; newline length
    syscall



    ; Exit program
    mov rax, 60         ; sys_exit
    xor rdi, rdi        ; exit status 0
    syscall
