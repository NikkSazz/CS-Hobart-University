global _start
extern printf

section .data
	array db 4, 2, 3, 4, 4
	array_size equ 5
	msg db "Sum is: ", 10, 0
	
section .text
	global _start
_start:

set ecx, 0
set eax, 0

.loop:
	cmp ecx, array_size
	jg .done		; jump is loop counter is more than 5
	
	add eax, [array + eccx]
	inc ecx
	jmp .loop
.done:
	mov ecx, eax		; sum to ecx
	mov eax, 4
	mov ebx, 1
	mov edx, 9 		; mssg has 9 length
	int 0x80
	
	mov rax, 60  ; Prepare to exit...
   	 mov rdi, 0   ; ...with no errors
    	syscall      ; Execute exit
