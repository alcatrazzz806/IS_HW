public class TranspositionCipher implements Cipher {
	private String key = "";

	public void setKey(String newKey) {
		key = newKey;
	}
	
	public String encrypt(String plaintext) {
		String ciphertext = "";
		int cipher_len = (plaintext.length() / key.length() + 1) * key.length();
		for (int i = plaintext.length()-1; i < cipher_len-1; i++) {
			plaintext += 'x';
		}
		for (int i = 0; i < key.length(); i++) {
			int pos = Character.getNumericValue(key.charAt(i) - 1);
			while(pos < plaintext.length()) {
				ciphertext += plaintext.charAt(pos);
				pos += key.length();
			}
		}
		return ciphertext;
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
