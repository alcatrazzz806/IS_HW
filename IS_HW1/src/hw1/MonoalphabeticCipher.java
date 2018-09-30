package hw1;

public class MonoalphabeticCipher {
	private String keytable = "abcdefghijklmnopqrstuvwxyz";
	
	private char getChar(char c, boolean en) {
		if (en) {
			if (Character.isLowerCase(c)) {
				return keytable.charAt(c-'a');
			}
			else {
				return Character.toUpperCase(keytable.charAt(c-'A'));
			}
		}
		else {
			if (Character.isLowerCase(c)) {
				return (char)(keytable.indexOf(c) + 'a');
			}
			else {
				return (char)(keytable.indexOf(Character.toLowerCase(c)) + 'A');
			}
		}
	}
	
	public int setKey(String key) {
		if (key.length() != 26) {
			return -1;
		}
		else {
			key = key.toLowerCase();
			keytable = key;
			return 0;
		}
	}
	
	public String encrypt(String plaintext) {
		String ciphertext = "";
		for (int i = 0; i < plaintext.length(); i++) {
			ciphertext += getChar(plaintext.charAt(i), true);
		}
		return ciphertext;
	}
	
	public String decrypt(String ciphertext) {
		String plaintext = "";
		for (int i = 0; i < ciphertext.length(); i++) {
			plaintext += getChar(ciphertext.charAt(i), false);
		}
		return plaintext;
	}
}
