public class MonoAlphabeticCipher implements Cipher
{
    private String key;
    private char[] orderedAlphabet = new char[26];

    /**
     * Constructor for objects of class MonoAlphabaticCipher
     */
    public MonoAlphabeticCipher()
    {
        // initialise instance variables
        this.key = "";
        int index = 0;
        for (char c = 'z'; c >= 'a'; c--) 
        {
            this.orderedAlphabet[index++] = c;
        }
    }

    public void setKey(String k)
    {
        k = k.toUpperCase();
        this.key = k;
    }
    
    public String encrypt(String plainText)
    {
        plainText = plainText.toLowerCase();
        String cipherText = "";
        for (int i = 0; i < plainText.length(); i++)
        {
            char currentLetter = plainText.charAt(i);
            int index = new String (this.orderedAlphabet).indexOf(currentLetter);
            cipherText = cipherText + this.key.charAt(index);
        }
        
        return cipherText;
    }
    
    public String decrypt(String cipherText)
    {
        cipherText = cipherText.toUpperCase();
        String plainText = "";
        for (int i = 0; i < cipherText.length(); i++)
        {
            char currentLetter = cipherText.charAt(i);
            int index = new String (this.key).indexOf(currentLetter);
            plainText = plainText + this.orderedAlphabet[index];
        }
        
        return plainText;
    }
}
