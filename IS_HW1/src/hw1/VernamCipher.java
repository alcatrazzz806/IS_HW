package hw1;

public class VernamCipher {
	private String key = "";
	
	public void setKey(String newKey) {
		key = newKey.toLowerCase();
	}
	
	private char getChar(char c, char key, boolean en) {
		if (en) {
			return (Character.isLowerCase(c)) ? (char)((c-'a' + key-'a') % 26 + 'a') : (char)((c-'A' + key-'a') % 26 + 'A');
		} 
		else {
			return (Character.isLowerCase(c)) ? (char)((c - key + 26) % 26 + 'a') : (char)((c-'A' - key+'a' + 26) % 26 + 'A');
		}
	}

	public String encrypt(String plaintext) {
		String ciphertext = "";
		int keycount = 0;
		for (int i = 0; i < plaintext.length(); i++) {
			ciphertext += getChar(plaintext.charAt(i), key.charAt(keycount), true);
			keycount = (keycount + 1) % key.length();
		}
		return ciphertext;
	}
	
	public String decrypt(String ciphertext) {
		String plaintext = "";
		int keycount = 0;
		for (int i = 0; i < ciphertext.length(); i++) {
			plaintext += getChar(ciphertext.charAt(i), key.charAt(keycount), false);
			keycount = (keycount + 1) % key.length();
		}
		return plaintext;
	}
}
