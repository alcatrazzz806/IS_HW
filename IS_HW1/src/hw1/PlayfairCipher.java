package hw1;

public class PlayfairCipher {
	private String key = "abcdefghiklmnopqrstuvwxyz";
	
	public void setKey(String newKey) {
		key = "";
		int keycount = 0;
		newKey = newKey.toLowerCase();
		String alphabets = "abcdefghiklmnopqrstuvwxyz";
		for (int i = 0; i < newKey.length(); i++) {
			if (newKey.charAt(i) == 'i'|| newKey.charAt(i) == 'j') {
				if (alphabets.contains("i")) {
					key += 'i';
					keycount++;
					alphabets = alphabets.replace("i", "");
				}
			}
			else if (alphabets.contains(newKey.substring(i, i+1))) {
				key += newKey.charAt(i);
				keycount++;
				alphabets = alphabets.replace(newKey.substring(i, i+1), "");
			}
		}
		for (int i = keycount; i < 25; i++) {
			key += alphabets.charAt(0);
			alphabets = alphabets.substring(1);
		}
		System.out.println(key);
	}
	
	public String encrypt(String plaintext) {
		if (plaintext.length() % 2 == 1) {
			plaintext += 'X';
		}
		String remaining = plaintext;
		String ciphertext = "";
		while(!remaining.isEmpty()) {
			char x = remaining.charAt(0);
			if (x == 'j') { x = 'i'; } if (x == 'J') { x = 'I'; }
			char y = remaining.charAt(1);
			if (y == 'j') { y = 'i'; } if (y == 'J') { y = 'I'; }
			
			if (Character.toLowerCase(x) == Character.toLowerCase(y)) {
				int x_index = (Character.isLowerCase(x)) ? key.indexOf(x) : 
					key.indexOf(Character.toLowerCase(x));
				char x_cipher = (Character.isLowerCase(x)) ? key.charAt(((x_index + 1) % 5) + (5 * (x_index / 5))) : 
					Character.toUpperCase(key.charAt(((x_index + 1) % 5) + (5 * (x_index / 5))));
				char y_cipher = (Character.isLowerCase(y)) ? x_cipher : Character.toUpperCase(x_cipher);
				ciphertext += x_cipher; ciphertext += y_cipher;
			}
			
			else {
				int x_index = (Character.isLowerCase(x)) ? key.indexOf(x) : key.indexOf(Character.toLowerCase(x));
				int y_index = (Character.isLowerCase(y)) ? key.indexOf(y) : key.indexOf(Character.toLowerCase(y));
				
				if (x_index/5 == y_index/5) {
					char x_cipher = (Character.isLowerCase(x)) ? key.charAt(((x_index + 1) % 5) + (5 * (x_index / 5))) : 
						Character.toUpperCase(key.charAt(((x_index + 1) % 5) + (5 * (x_index / 5))));
					char y_cipher = (Character.isLowerCase(y)) ? key.charAt(((y_index + 1) % 5) + (5 * (y_index / 5))) :
						Character.toUpperCase(key.charAt(((y_index + 1) % 5) + (5 * (y_index / 5))));
					ciphertext += x_cipher; ciphertext += y_cipher;
				}
				
				else if (x_index%5 == y_index%5) {
					char x_cipher = (Character.isLowerCase(x)) ? key.charAt((y_index % 5) + (5 * (((x_index / 5) + 1) % 5))) : 
						Character.toUpperCase(key.charAt((y_index % 5) + (5 * (((x_index / 5) + 1) % 5))));
					char y_cipher = (Character.isLowerCase(y)) ? key.charAt((x_index % 5) + (5 * (((y_index / 5) + 1) % 5))) :
						Character.toUpperCase(key.charAt((x_index % 5) + (5 * (((y_index / 5) + 1) % 5))));
					ciphertext += x_cipher; ciphertext += y_cipher;
				}
				
				else {
					char x_cipher = (Character.isLowerCase(x)) ? key.charAt((y_index % 5) + (5 * (x_index / 5))) : 
						Character.toUpperCase(key.charAt((y_index % 5) + (5 * (x_index / 5))));
					char y_cipher = (Character.isLowerCase(y)) ? key.charAt((x_index % 5) + (5 * (y_index / 5))) :
						Character.toUpperCase(key.charAt((x_index % 5) + (5 * (y_index / 5))));
					ciphertext += x_cipher; ciphertext += y_cipher;
				}
			}
			remaining = remaining.substring(2);
		}
		return ciphertext;
	}
	
	public String decrypt(String ciphertext) {
		String remaining = ciphertext;
		String plaintext = "";
		while(!remaining.isEmpty()) {
			char x = remaining.charAt(0);
			if (x == 'j') { x = 'i'; } if (x == 'J') { x = 'I'; }
			char y = remaining.charAt(1);
			if (y == 'j') { y = 'i'; } if (y == 'J') { y = 'I'; }
			
			if (Character.toLowerCase(x) == Character.toLowerCase(y)) {
				int x_index = (Character.isLowerCase(x)) ? key.indexOf(x) : 
					key.indexOf(Character.toLowerCase(x));
				char x_cipher = (Character.isLowerCase(x)) ? key.charAt(((x_index + 5 - 1) % 5) + (5 * (x_index / 5))) : 
					Character.toUpperCase(key.charAt(((x_index + 5 - 1) % 5) + (5 * (x_index / 5))));
				char y_cipher = (Character.isLowerCase(y)) ? x_cipher : Character.toUpperCase(x_cipher);
				plaintext += x_cipher; plaintext += y_cipher;
			}
			
			else {
				int x_index = (Character.isLowerCase(x)) ? key.indexOf(x) : key.indexOf(Character.toLowerCase(x));
				int y_index = (Character.isLowerCase(y)) ? key.indexOf(y) : key.indexOf(Character.toLowerCase(y));
				
				if (x_index/5 == y_index/5) {
					char x_cipher = (Character.isLowerCase(x)) ? key.charAt(((x_index + 5 - 1) % 5) + (5 * (x_index / 5))) : 
						Character.toUpperCase(key.charAt(((x_index + 5 - 1) % 5) + (5 * (x_index / 5))));
					char y_cipher = (Character.isLowerCase(y)) ? key.charAt(((y_index + 5 - 1) % 5) + (5 * (y_index / 5))) :
						Character.toUpperCase(key.charAt(((y_index + 5 - 1) % 5) + (5 * (y_index / 5))));
					plaintext += x_cipher; plaintext += y_cipher;
				}
				
				else if (x_index%5 == y_index%5) {
					char x_cipher = (Character.isLowerCase(x)) ? key.charAt((y_index % 5) + (5 * (((x_index / 5) + 5 - 1) % 5))) : 
						Character.toUpperCase(key.charAt((y_index % 5) + (5 * (((x_index / 5) + 5 - 1) % 5))));
					char y_cipher = (Character.isLowerCase(y)) ? key.charAt((x_index % 5) + (5 * (((y_index / 5) + 5 - 1) % 5))) :
						Character.toUpperCase(key.charAt((x_index % 5) + (5 * (((y_index / 5) + 5 - 1) % 5))));
					plaintext += x_cipher; plaintext += y_cipher;
				}
				
				else {
					char x_cipher = (Character.isLowerCase(x)) ? key.charAt((y_index % 5) + (5 * (x_index / 5))) : 
						Character.toUpperCase(key.charAt((y_index % 5) + (5 * (x_index / 5))));
					char y_cipher = (Character.isLowerCase(y)) ? key.charAt((x_index % 5) + (5 * (y_index / 5))) :
						Character.toUpperCase(key.charAt((x_index % 5) + (5 * (y_index / 5))));
					plaintext += x_cipher; plaintext += y_cipher;
				}
			}
			remaining = remaining.substring(2);
		}
		return plaintext;
	}
}
