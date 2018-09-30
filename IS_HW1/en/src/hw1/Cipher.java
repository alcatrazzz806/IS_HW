package hw1;

public interface Cipher {
	public void setKey(String newKey);
	public String encrypt(String plaintext);
	public String decrypt(String ciphertext);
}
