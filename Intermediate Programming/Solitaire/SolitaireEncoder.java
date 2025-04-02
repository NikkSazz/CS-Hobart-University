

/**
 * handles the actual encryption and decryption processes. 
 * 
 * @author Nikolai Sazonov
 */
public class SolitaireEncoder {
	
	public static String encrypt(String message, String passphrase, int deckSize) {
		
        KeystreamGenerator keystreamGenerator = new KeystreamGenerator(deckSize, passphrase);
        String encrypted = "";

        for (char c : message.toCharArray()) {
        	
        	c = Character.toUpperCase(c);
        	
        	// Ignore non alphabetical characters
            if (c < 'A' || c > 'Z') { continue; }
            
            int messageVal = (int) c - 64;
            int keytreamVal = keystreamGenerator.nextKeystreamValue();
            
            int encryptedVal = (messageVal + keytreamVal - 1) % 26;
            
            encrypted += (char) (65 + encryptedVal);
            
        }

        return encrypted;
    }
	
	public static String decrypt(String encryptedMessage, String passphrase, int deckSize) {
        KeystreamGenerator generator = new KeystreamGenerator(deckSize, passphrase);
        String decrypted = "";
        
        for (char c : encryptedMessage.toCharArray()) {
        	
        	c = Character.toUpperCase(c);
        	
        	// Ignore non alphabetical characters
        	// Should not be necessary if you are decrypting an already encrypted message
            if (c < 'A' || c > 'Z') { continue; }
            
            int msgVal = c - 64; // ascii A = 65; - 1 = 64
            int keyVal = generator.nextKeystreamValue();
            
            // this makes sure all values stay within 1-26
            int decVal = (msgVal - keyVal + 25) % 26 + 1;
            
            decrypted += (char) (64 + decVal);
        }

        return decrypted;
    }
	
	
}
