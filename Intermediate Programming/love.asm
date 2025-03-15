.model small
.stack 100h

.data
    prompt db 'Who is the salt to my tomato? <3 $'
    input_buffer db 10, 0, 10 dup('$')  ; Reserve space for 10 characters and a null terminator
    response db 'I love you  ^ <3 ^ !$'

.code
main:
    ; Initialize data segment
    mov ax, @data
    mov ds, ax

    ; Display the prompt message
    mov ah, 09h      ; DOS function to display string
    lea dx, prompt   ; Load address of the prompt string
    int 21h          ; Call DOS interrupt

    ; Get user input (string)
    lea dx, input_buffer ; Load address of input buffer
    mov ah, 0Ah      ; DOS function to get input string (buffered input)
    int 21h          ; Call DOS interrupt


    xor dx, dx      ; Clear
    lea dx, response
    mov ah, 09h      ; DOS function to display string
    int 21h

    lea dx, input_buffer + 2 ; skip first 2 bytes
    int 21h          ; Call DOS interrupt

    ; Exit the program
    mov ah, 4Ch      ; DOS function to terminate the program
    int 21h          ; Call DOS interrupt

end main
