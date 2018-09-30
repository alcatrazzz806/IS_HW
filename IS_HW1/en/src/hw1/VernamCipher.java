package hw1;

public class VernamCipher implements Cipher {
	private String key = "";
	
	public void setKey(String newKey) {
		key = newKey.toLowerCase();
	}
	
	private char getChar(char c, char key) {
		String c_bin = (Character.isLowerCase(c)) ? Integer.toBinaryString(c-'a') :
			Integer.toBinaryString(c-'A');
		String key_bin = Integer.toBinaryString(key-'a');
		StringBuilder xor_bin_sb = new StringBuilder();
		int i = c_bin.length() - 1; int j = key_bin.length() - 1;
		while(i >= 0 || j >= 0) {
			if (i < 0) { xor_bin_sb.insert(0, '0' ^ key_bin.charAt(j));	}
			else if (j < 0) { xor_bin_sb.insert(0, '0' ^ c_bin.charAt(i)); }
			else { xor_bin_sb.insert(0, c_bin.charAt(i) ^ key_bin.charAt(j)); }
			i--; j--;
		}
		String xor_bin = xor_bin_sb.toString();
		return (Character.isLowerCase(c)) ? (char)(Integer.parseInt(xor_bin, 2)%26 + 'a') :
			(char)(Integer.parseInt(xor_bin, 2)%26 + 'A');
	}

	public String encrypt(String plaintext) {
		String ciphertext = "";
		int keycount = 0;
		for (int i = 0; i < plaintext.length(); i++) {
			ciphertext += getChar(plaintext.charAt(i), key.charAt(keycount));
			keycount = (keycount + 1) % key.length();
		}
		return ciphertext;
	}
	
	public String decrypt(String ciphertext) {
		String plaintext = "";
		int keycount = 0;
		for (int i = 0; i < ciphertext.length(); i++) {
			plaintext += getChar(ciphertext.charAt(i), key.charAt(keycount));
			keycount = (keycount + 1) % key.length();
		}
		return plaintext;
	}
}
