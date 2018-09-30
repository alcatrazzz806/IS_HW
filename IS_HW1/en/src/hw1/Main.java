package hw1;

public class Main {
	
	public static void main(String []args) {
		Cipher cipher = null;
		String plaintext = "KeepGoingNeverGiveUp";
		String key = "";
		String ciphertext = "";
		String decrypted = "";
		System.out.println("Demo...");
		System.out.println("Plaintext: " + plaintext);
		
		cipher = new CaesarCipher();
		key = "7"; 
		cipher.setKey(key);
		System.out.println("\nEncrypt using Caesar Cipher with key of " + key + "...");
		ciphertext = cipher.encrypt(plaintext);
		System.out.println("Ciphertext: " + ciphertext);
		decrypted = cipher.decrypt(ciphertext);
		System.out.println("Decrypted: " + decrypted);
		cipher = null; key = null; ciphertext = null; decrypted = null;
		
		
		key = "QWERTYUIOPASDFGHJKLZXCVBNM";
		cipher = new MonoalphabeticCipher();
		cipher.setKey(key);
		System.out.println("\nEncrypt using Monoalphabetic Cipher with key of");
		System.out.println(key + " ...");
		ciphertext = cipher.encrypt(plaintext);
		System.out.println("Ciphertext: " + ciphertext);
		decrypted = cipher.decrypt(ciphertext);
		System.out.println("Decrypted: " + decrypted);
		cipher = null; key = null; ciphertext = null; decrypted = null;
		
		key = "HIT";
		cipher = new PlayfairCipher();
		cipher.setKey(key);
		System.out.println("\nEncrypt using Playfair Cipher with key of " + key + "...");
		ciphertext = cipher.encrypt(plaintext);
		System.out.println("Ciphertext: " + ciphertext);
		decrypted = cipher.decrypt(ciphertext);
		System.out.println("Decrypted: " + decrypted);
		cipher = null; key = null; ciphertext = null; decrypted = null;
		
		key = "COM";
		cipher = new VernamCipher();
		cipher.setKey(key);
		System.out.println("\nEncrypt using Vernam Cipher with key of " + key + "...");
		ciphertext = cipher.encrypt(plaintext);
		System.out.println("Ciphertext: " + ciphertext);
		decrypted = cipher.decrypt(ciphertext);
		System.out.println("Decrypted: " + decrypted);
		cipher = null; key = null; ciphertext = null; decrypted = null;
		
		key = "31562487";
		cipher = new TranspositionCipher();
		cipher.setKey(key);
		System.out.println("\nEncrypt using Transposition Cipher with key of " + key + "...");
		ciphertext = cipher.encrypt(plaintext);
		System.out.println("Ciphertext: " + ciphertext);
		decrypted = cipher.decrypt(ciphertext);
		System.out.println("Decrypted: " + decrypted);
		cipher = null; key = null; ciphertext = null; decrypted = null;
		
		key = "15 11 19 18 16 3 7 14 2 20 4 12 9 6 1 5 17 13 10 8";
		cipher = new ProductCipher();
		cipher.setKey(key);
		System.out.println("\nEncrypt using Product Cipher with key of\n" + key + "...");
		ciphertext = cipher.encrypt(plaintext);
		System.out.println("Ciphertext: " + ciphertext);
		decrypted = cipher.decrypt(ciphertext);
		System.out.println("Decrypted: " + decrypted);
		cipher = null; key = null; ciphertext = null; decrypted = null;	
	}

}
