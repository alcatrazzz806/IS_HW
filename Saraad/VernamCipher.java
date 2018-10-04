public class VernamCipher implements Cipher
{
    private String key;

    /**
     * Constructor for objects of class VernamCipher
     */
    public VernamCipher()
    {
        // initialise instance variables
        key = "";
    }

    public void setKey(String k)
    {
        this.key = k;
    }
    
    public String encrypt(String plainText)
    {
        String keyString = "";
        if (this.key.length() >= plainText.length())
        {
            keyString = this.key.substring(0, plainText.length());
            keyString = keyString.toUpperCase();
        }
        else
        {
            keyString = this.key + plainText.substring(0, plainText.length() - this.key.length());
            keyString = keyString.toUpperCase();
        }
        this.key = keyString;
        
        plainText = plainText.toUpperCase();
        
        String cipherText = "";
        
        for (int i = 0; i < plainText.length(); i++)
        {
            int currentLetter = plainText.charAt(i) - 'A';
            int currentKeyStringLetter = keyString.charAt(i) - 'A';
            
            String plainBinary = Integer.toBinaryString(currentLetter);
            String keyStringBinary = Integer.toBinaryString(currentKeyStringLetter);
            while (plainBinary.length() < 5)
            {
                plainBinary = "0" + plainBinary;
            }
            
            while (keyStringBinary.length() < 5)
            {
                keyStringBinary = "0" + keyStringBinary;
            }
            String xorResult = "";
            // Doing XOR
            for (int j = 0; j < 5; j++)
            {
                char pBinaryLetter = plainBinary.charAt(j);
                char kBinaryLetter = keyStringBinary.charAt(j);
                xorResult = xorResult + (pBinaryLetter ^ kBinaryLetter);
            }
            char binToChar = (char) (Integer.parseInt(xorResult, 2) + 65);  // ''65 -> 'A'
            cipherText = cipherText + binToChar;
            
        }
        
        return cipherText;
    }
    
    public String decrypt(String cipherText)
    {        
        cipherText = cipherText.toUpperCase();
        
        String plainText = "";
        
        for (int i = 0; i < cipherText.length(); i++)
        {
            int currentLetter = cipherText.charAt(i) - 'A';
            int currentKeyStringLetter = this.key.charAt(i) - 'A';
                  
            String cipherBinary = Integer.toBinaryString(currentLetter);
            String keyStringBinary = Integer.toBinaryString(currentKeyStringLetter);
            while (cipherBinary.length() < 5)
            {
                cipherBinary = "0" + cipherBinary;
            }
            
            while (keyStringBinary.length() < 5)
            {
                keyStringBinary = "0" + keyStringBinary;
            }
            String xorResult = "";
            // Doing XOR
            for (int j = 0; j < 5; j++)
            {
                char cBinaryLetter = cipherBinary.charAt(j);
                char kBinaryLetter = keyStringBinary.charAt(j);
                xorResult = xorResult + (cBinaryLetter ^ kBinaryLetter);
            }
            char binToChar = (char) (Integer.parseInt(xorResult, 2) + 65);  // ''65 -> 'A'
            plainText = plainText + binToChar;
            
        }
        plainText = plainText.toLowerCase();
        return plainText;
    }
}
