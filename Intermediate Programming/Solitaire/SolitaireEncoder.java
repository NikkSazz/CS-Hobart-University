

/**
 * handles the actual encryption and decryption processes. 
 * 
 * @author Nikolai Sazonov
 */
public class SolitaireEncoder {
	
	public static String encrypt(String message, String passphrase, int deckSize) {
		
		KeystreamGenerator keystream = new KeystreamGenerator(deckSize, passphrase);
		String encrypted = "";
		for(char c : message.toCharArray()) {
			
			if(!Character.isLetter(c)) { continue; }
			
			int letterVal = Character.toUpperCase(c) - 'A' + 1;
			int keystreamVal = keystream.nextKeystreamValue();
			
			int encryptedVal = (letterVal + keystreamVal) % 26;
			char encryptedChar = (char) ('A' + encryptedVal + 1); // using ascii
			
			encrypted += encryptedChar;
			
		}
		return encrypted;
	}
	
	public static void decrypt(String message, String passphrase, int deckSize) {
		
		var keystream = new KeystreamGenerator(deckSize, passphrase);
		
		
		
	}

}
