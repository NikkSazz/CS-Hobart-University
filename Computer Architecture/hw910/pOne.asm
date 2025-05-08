global _start
extern printf

section .data

	byteFormat: db 'byte adress #%d: %d', 10, 0
	wordFormat: db 'word address #%d: %d', 10, 0
 	doubleFormat: db 'double address #%d: %d', 10, 0
 	quadFormat: db 'quad address #%d: %d', 10, 0
bytee db 4
byteee db 7
wordd dw 18
worddd dw 400
doublee dd 420
doubleee dd 489
quadd dq 523
quaddd dq 9412
 	 	
section .text

	mov rdi, byteFormat   ; printf format
  	mov rsi, [bytee] ; Value to be printed
    	call printf       ; Print
  	mov rsi, [byteee] ; Value to be printed
    	call printf       ; Print
    	
	mov rdi, wordFormat   ; printf format
  	mov rsi, [wordd] ; Value to be printed
    	call printf       ; Print
  	mov rsi, [worddd] ; Value to be printed
    	call printf       ; Print
    	
    	mov rdi, doubleFormat   ; printf format
  	mov rsi, [doublee] ; Value to be printed
    	call printf       ; Print
  	mov rsi, [doubleee] ; Value to be printed
    	call printf       ; Print
    	
    	mov rdi, quadFormat   ; printf format
  	mov rsi, [quadd] ; Value to be printed
    	call printf       ; Print
  	mov rsi, [quaddd] ; Value to be printed
    	call printf       ; Print
    	
    	; Exit
    	mov rax, 60  ; Prepare to exit...
  	mov rdi, 0   ; ...with no errors
   	syscall      ; Execute exit
