package hw1;

import hw1.*;

public class CaesarCipher {
	private char getChar(char c, int key, boolean en) {
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
	
	public String encrypt(String plaintext, int key) {
		String ciphertext = "";
		for (int i = 0; i < plaintext.length(); i++) {
			ciphertext += getChar(plaintext.charAt(i), key, true);
		}
		return ciphertext;
	}
	
	public String decrypt(String ciphertext, int key) {
		String plaintext = "";
		for (int i = 0; i < ciphertext.length(); i++) {
			plaintext += getChar(ciphertext.charAt(i), key, false);
		}
		return plaintext;
	}
	
	public static void main(String []args) {
		CaesarCipher caesar = new CaesarCipher();
		String plaintext = "KeepGoingNeverGiveUp";
		String ciphertext = "";
		String decrypted = "";
		System.out.println("Plaintext: " + plaintext);
		
		int caesar_key = 7; 
		System.out.println("\nEncrypt using Caesar Cipher with key of " + caesar_key + "...");
		ciphertext = caesar.encrypt(plaintext, caesar_key);
		System.out.println("Ciphertext: " + ciphertext);
		decrypted = caesar.decrypt(ciphertext, caesar_key);
		System.out.println("Decrypted: " + decrypted);
		caesar = null;
		
		
		String mono_key = "QWERTYUIOPASDFGHJKLZXCVBNM";
		MonoalphabeticCipher mono = new MonoalphabeticCipher();
		mono.setKey(mono_key);
		System.out.println("\nEncrypt using Monoalphabetic Cipher with key of");
		System.out.println(mono_key + " ...");
		ciphertext = mono.encrypt(plaintext);
		System.out.println("Ciphertext: " + ciphertext);
		decrypted = mono.decrypt(ciphertext);
		System.out.println("Decrypted: " + decrypted);
		mono = null; mono_key = null;
		
		String playfair_key = "HIT";
		PlayfairCipher playfair = new PlayfairCipher();
		playfair.setKey(playfair_key);
		System.out.println("\nEncrypt using Playfair Cipher with key of " + playfair_key + "...");
		ciphertext = playfair.encrypt(plaintext);
		System.out.println("Ciphertext: " + ciphertext);
		decrypted = playfair.decrypt(ciphertext);
		System.out.println("Decrypted: " + decrypted);
		playfair = null; playfair_key = null;
		
		String vernam_key = "COM";
		VernamCipher vernam = new VernamCipher();
		vernam.setKey(vernam_key);
		System.out.println("\nEncrypt using Vernam Cipher with key of " + vernam_key + "...");
		ciphertext = vernam.encrypt(plaintext);
		System.out.println("Ciphertext: " + ciphertext);
		decrypted = vernam.decrypt(ciphertext);
		System.out.println("Decrypted: " + decrypted);
		vernam = null; vernam_key = null;
	}
}