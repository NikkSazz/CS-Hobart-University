; Jorgensen p177
; Simple example function to find and return
; the sum and average of an array.
; HLL call:
; stats1(arr, len, sum, ave);
; -----
; Arguments:
; arr, address – rdi
; len, dword value – esi
; sum, address – rdx
; ave, address - rcx

global stats1

section .text
stats1:
    push r12 ; prologue
    mov r12, 0 ; counter/index
    mov rax, 0 ; running sum

sumLoop:
    add eax, dword [rdi+r12*4] ; sum += arr[i]
    inc r12
    cmp r12, rsi
    jl sumLoop
    mov dword [rdx], eax ; return sum
    cdq
    idiv esi ; compute average
    mov dword [rcx], eax ; return ave
    pop r12 ; epilogue
    ret