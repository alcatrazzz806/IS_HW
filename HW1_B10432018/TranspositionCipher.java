public class TranspositionCipher implements Cipher {
	private String key = "";

	public void setKey(String newKey) {
		key = newKey;
	}
	
	public String encrypt(String plaintext) {
		int cipher_len = 0;
		if (plaintext.length() % key.length() == 0) {
			cipher_len = (plaintext.length() / key.length()) * key.length();
		}
		else {
			cipher_len = (plaintext.length() / key.length() + 1) * key.length();
		}
		char[] ciphertext = new char[cipher_len]; 
		for (int i = plaintext.length()-1; i < cipher_len-1; i++) {
			plaintext += 'X';
		}
		for (int i = 0; i < key.length(); i++) {
			int x = i;
			int pos = Character.getNumericValue(key.charAt(i) - 1) * (cipher_len / key.length());
			while(x < plaintext.length()) {
				ciphertext[pos] = plaintext.charAt(x);
				x += key.length(); pos++;
			}
		}
		String cipherReturn = new String(ciphertext);
		return cipherReturn;
	}
	/*
	public String decrypt(String ciphertext) {
		char[] plaintext = new char[ciphertext.length()];
		int cipher_pos = 0;
		for (int i = 0; i < key.length(); i++) {
			int pos = Character.getNumericValue(key.charAt(i) - 1);
			while(pos < ciphertext.length()) {
				plaintext[pos] = ciphertext.charAt(cipher_pos);
				pos += key.length();
				cipher_pos++;
			}
		}
		String plaintext_str = new String(plaintext);
		return plaintext_str;
	}
	*/
}
