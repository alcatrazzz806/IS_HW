public class CaesarCipher implements Cipher {
	private int key = 0;
	private char getChar(char c,  boolean en) {
		if (en) {
			if (Character.isLowerCase(c)) {
				return (char)((c - 'a' + key) % 26 + 'a');
			}
			else {
				return (char)((c - 'A' + key) % 26 + 'A');
			}
		}
		else {
			if (Character.isLowerCase(c)) {
				return (char)((((c - 'a' - key) % 26) + 26) % 26 + 'a');
			}
			else {
				return (char)((((c - 'A' - key) % 26) + 26) % 26 + 'A');
			}
		}
	}
	
	public void setKey(String newKey) {
		key = Integer.valueOf(newKey);
	}
	
	public String encrypt(String plaintext) { 
		String ciphertext = "";
		for (int i = 0; i < plaintext.length(); i++) {
			ciphertext += getChar(plaintext.charAt(i), true);
		}
		return ciphertext;
	}
	
	/*
	public String decrypt(String ciphertext) {
		String plaintext = "";
		for (int i = 0; i < ciphertext.length(); i++) {
			plaintext += getChar(ciphertext.charAt(i), false);
		}
		return plaintext;
	}
	*/
}