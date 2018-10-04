import java.util.Scanner;

public class Main {
	
	public static void main(String []args) {
		Cipher cipher = null;
		String plaintext = "keepgoingnevergiveup";
		String key = "";
		String ciphertext = "";
		//String decrypted = "";
		System.out.println("Demo...");
		System.out.println("Plaintext: " + plaintext);
		
		cipher = new CaesarCipher();
		key = "7"; 
		cipher.setKey(key);
		System.out.println("\nEncrypt using Caesar Cipher with key of " + key + "...");
		ciphertext = cipher.encrypt(plaintext);
		System.out.println("Ciphertext: " + ciphertext);
		//decrypted = cipher.decrypt(ciphertext);
		//System.out.println("Decrypted: " + decrypted);
		cipher = null; key = null; ciphertext = null; //decrypted = null;
		
		
		key = "MNBVCXZLKJHGFDSAPOIUYTREWQ";
		cipher = new MonoalphabeticCipher();
		cipher.setKey(key);
		System.out.println("\nEncrypt using Monoalphabetic Cipher with key of");
		System.out.println(key + " ...");
		ciphertext = cipher.encrypt(plaintext);
		System.out.println("Ciphertext: " + ciphertext);
		//decrypted = cipher.decrypt(ciphertext);
		//System.out.println("Decrypted: " + decrypted);
		cipher = null; key = null; ciphertext = null; //decrypted = null;
		
		key = "HIT";
		cipher = new PlayfairCipher();
		cipher.setKey(key);
		System.out.println("\nEncrypt using Playfair Cipher with key of " + key + "...");
		ciphertext = cipher.encrypt(plaintext);
		System.out.println("Ciphertext: " + ciphertext);
		//decrypted = cipher.decrypt(ciphertext);
		//System.out.println("Decrypted: " + decrypted);
		cipher = null; key = null; ciphertext = null; //decrypted = null;
		
		key = "COM";
		cipher = new VernamCipher();
		cipher.setKey(key);
		System.out.println("\nEncrypt using Vernam Cipher with key of " + key + "...");
		ciphertext = cipher.encrypt(plaintext);
		System.out.println("Ciphertext: " + ciphertext);
		//decrypted = cipher.decrypt(ciphertext);
		//System.out.println("Decrypted: " + decrypted);
		cipher = null; key = null; ciphertext = null; //decrypted = null;
		
		key = "31562487";
		cipher = new TranspositionCipher();
		cipher.setKey(key);
		System.out.println("\nEncrypt using Transposition Cipher with key of " + key + "...");
		ciphertext = cipher.encrypt(plaintext);
		System.out.println("Ciphertext: " + ciphertext);
		//decrypted = cipher.decrypt(ciphertext);
		//System.out.println("Decrypted: " + decrypted);
		cipher = null; key = null; ciphertext = null; //decrypted = null;
		
		key = "15 11 19 18 16 3 7 14 2 20 4 12 9 6 1 5 17 13 10 8";
		cipher = new ProductCipher();
		cipher.setKey(key);
		System.out.println("\nEncrypt using Product Cipher with key of\n" + key + "...");
		ciphertext = cipher.encrypt(plaintext);
		System.out.println("Ciphertext: " + ciphertext);
		//decrypted = cipher.decrypt(ciphertext);
		//System.out.println("Decrypted: " + decrypted);
		cipher = null; key = null; ciphertext = null; //decrypted = null;
		plaintext = null;
		
		while (true) {
			System.out.println("\n\nChoose the cipher, -1 to end...");
			System.out.println("1.Caesar Cipher\n2.Monoalphabetic Cipher");
			System.out.println("3.Playfair Cipher\n4.Vernam Cipher");
			System.out.println("5.Transposition Cipher\n6.Product Cipher");
			
			Scanner scanner = new Scanner(System.in);
			int choice = scanner.nextInt();
			while (choice < -1 || choice > 6) {
				System.out.println("Wrong Selection...");
				choice = scanner.nextInt();
			}
			switch (choice) {
			case -1:
				return;
			case 1:
				cipher = new CaesarCipher();
				System.out.println("Key Format: Integer");
				break;
			case 2:
				cipher = new MonoalphabeticCipher();
				System.out.println("Key Format: 26 alphabets for zyx...cba(reverse order)");
				break;
			case 3:
				cipher = new PlayfairCipher();
				System.out.println("Key Format: String");
				break;
			case 4:
				cipher = new VernamCipher();
				System.out.println("Key Format: String");
				break;
			case 5:
				cipher = new TranspositionCipher();
				System.out.println("Key Format: Numbers from 1 to n");
				break;
			case 6:
				cipher = new ProductCipher();
				System.out.println("Key Format: Numbers from 1 to n, use blank between numbers");
				System.out.println("ex.: 10 1 9 2 8 3 7 4 6 5");
				break;
			default:
				break;
			}
			System.out.println("Input Key...");
			scanner.nextLine();
			key = scanner.nextLine();
			cipher.setKey(key);
			
			System.out.println("Input Plaintext...");
			plaintext = scanner.next();
			ciphertext = cipher.encrypt(plaintext);
			System.out.println("Ciphertext: " + ciphertext);
			cipher = null; key = null; ciphertext = null;
		}
	}
}
