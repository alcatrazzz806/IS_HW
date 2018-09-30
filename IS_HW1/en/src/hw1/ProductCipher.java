package hw1;

public class ProductCipher implements Cipher{
	private int[] key = null;
	
	public void setKey(String newKey) {
		String[] nums = newKey.split(" ");
		key = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			key[i] = Integer.parseInt(nums[i]);
		}
	}
	
	public String encrypt(String plaintext) {
		StringBuilder ciphertext = new StringBuilder();
		for (int i = 0; i < plaintext.length(); i++) {
			ciphertext.append(plaintext.charAt(key[(i % key.length)]-1));
		}
		return ciphertext.toString();
	}
	
	public String decrypt(String ciphertext) {
		char[] plaintext = new char[ciphertext.length()];
		for (int i = 0; i < plaintext.length; i++) {
			plaintext[key[i]-1] = ciphertext.charAt(i);
		}
		String plaintext_str = new String(plaintext);
		return plaintext_str;
	}
	
}
