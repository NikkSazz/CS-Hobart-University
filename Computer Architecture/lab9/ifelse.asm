; TYPE YOUR NAME HERE
global _start

; Strings
section .data
  boils:
      db 'Boils', 10, 0
  boils_len: equ $ - boils

  notBoil:
      db 'Does not boil', 10, 0
  notBoil_len: equ $ - notBoil

; Main program
section .text
_start:
  mov edx, 10 ; Set value
  cmp edx, 100
  JG greater
  ; Print message2
  mov rax, 1        ; Prepare to print...
  mov rdi, 1        ; ...to console
  mov rsi, notBoil  ; ...this string
  mov rdx, notBoil_len  ; ...length
  syscall           ; Now print
  mov rax, 60       ; Prepare to exit...
  mov rdi, 0        ; ...with 0 errors
  syscall           ; Now exit

greater:
  mov rax, 1        ; Prepare to print...
  mov rdi, 1        ; ...to console
  mov rsi, boils  ; ...this string
  mov rdx, boils_len  ; ...length
  syscall           ; Now print
  mov rax, 60       ; Prepare to exit...
  mov rdi, 0        ; ...with 0 errors
  syscall           ; Now exit
