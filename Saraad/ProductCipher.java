import java.util.ArrayList;

public class ProductCipher implements Cipher
{
    private String key;

    /**
     * Constructor for objects of class ProductCipher
     */
    public ProductCipher()
    {
        // initialise instance variables
        this.key = "";
    }

    public void setKey(String k)
    {
        this.key = k;
    }
    
    public String encrypt(String plainText)
    {
        plainText = plainText.toUpperCase();
        
        ArrayList<String> grid = new ArrayList<String>();
        for (int i = 0; i < plainText.length(); i++)
        {
            grid.add("");
        }
        
        String cipherText = "";
        
        for (int i = 0; i < this.key.length(); i = i + 2)
        {
            String currentIndexString = "" + this.key.charAt(i) + this.key.charAt(i + 1);
            int currentIndex = Integer.parseInt(currentIndexString) - 1;
            cipherText = cipherText + plainText.charAt(currentIndex);
        }
        
        return cipherText;
    }
    
    public String decrypt(String cipherText)
    {
        cipherText = cipherText.toUpperCase();
        
        ArrayList<String> grid = new ArrayList<String>();
        for (int i = 0; i < cipherText.length(); i++)
        {
            grid.add("");
        }
        
        String plainText = "";
        
        for (int i = 0; i < this.key.length(); i = i + 2)
        {
            String currentIndexString = "" + this.key.charAt(i) + this.key.charAt(i + 1);
            int currentIndex = Integer.parseInt(currentIndexString) - 1;
            grid.set(currentIndex, "" + cipherText.charAt(i / 2));
        }
        
        // Add grid letter to plain text
        for (int i = 0; i < grid.size(); i++)
        {
            plainText = plainText + grid.get(i);
        }
        plainText = plainText.toLowerCase();
        
        return plainText;
    }
}
