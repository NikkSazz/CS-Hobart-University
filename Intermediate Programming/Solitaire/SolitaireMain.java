import java.util.Scanner;

/**
 * allow the user to encrypt and decrypt messages as described.
 * 
 * @author Nikolai Sazonov
 */
public class SolitaireMain {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
        System.out.print("enter passphrase: ");
        String passphrase = scanner.nextLine();
        
        if (passphrase.isEmpty()) {
            System.out.println("error: Passphrase cannot be empty.");
            return;
        }
        
		while(true) {
			
			System.out.print("\nencrypt, decrypt, or quit? [e/d/q] ");
            String choice = scanner.nextLine();
            
            if (choice.equals("q")) {
                System.out.println("\nquitting...");
                break;
            }
            
            else if (choice.equals("e")) {
            	System.out.print("\nenter message: ");
                String message = scanner.nextLine();
                
                String encrypted = SolitaireEncoder.encrypt(message, passphrase, 28); // 28 for deck size with two jokers
                System.out.println("\nencrypted: " + encrypted);
            }
            
            else if (choice.equals("d")) {
            	System.out.print("\nenter encrypted message: ");
                String encryptedMessage = scanner.nextLine();
                
                String decrypted = SolitaireEncoder.decrypt(encryptedMessage, passphrase, 28);
                System.out.println("\ndecrypted: " + decrypted);
            }
            
            else {
                System.out.println("please enter 'e' for encrypt, 'd' for decrypt, or 'q' to quit."
                		+ "\nNothing else...");
            }
            
		}
		
	}
	
}
