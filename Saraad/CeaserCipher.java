public class CeaserCipher implements Cipher
{
    private int key;
    private char[] orderedAlphabet = new char[26];
    
    /**
     * Constructor for objects of class CeaserCipher
     */
    public CeaserCipher()
    {
        // initialise instance variables
        this.key = 0;
        int index = 0;
        for (char c = 'a'; c <= 'z'; c++) 
        {
            this.orderedAlphabet[index++] = c;
        }
    }
    
    public void setKey(String k)
    {
        this.key = Integer.parseInt(k);
    }
    
    public String encrypt(String plainText)
    {
        plainText = plainText.toLowerCase();
        
        String cipherText = "";
        for (int i = 0; i < plainText.length(); i++)
        {
            char currentLetter = plainText.charAt(i);
            int index = new String (orderedAlphabet).indexOf(currentLetter);
            index = (index + key) % 26;
            cipherText = cipherText + orderedAlphabet[index];
        }
        
        return cipherText;
    }
    
    public String decrypt(String cipherText)
    {
        cipherText = cipherText.toLowerCase();
        String plainText = "";
        for (int i = 0; i < cipherText.length(); i++)
        {
            char currentLetter = cipherText.charAt(i);
            int index = new String (orderedAlphabet).indexOf(currentLetter);
            index = (index - key + 26) % 26;
            plainText = plainText + orderedAlphabet[index];
        }
        
        return plainText;
    }
    
}


