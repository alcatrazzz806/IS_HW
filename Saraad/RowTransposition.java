import java.util.ArrayList;

public class RowTransposition implements Cipher
{
    private String key;

    /**
     * Constructor for objects of class RowTransposition
     */
    public RowTransposition()
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
        String grid = plainText;
        String cipherText = "";
        
        for (int i = 0; i < this.key.length(); i++)
        {
            String keyChar = "" + (i + 1);
            int keyIndex = this.key.indexOf(keyChar);
            int gridIndex = keyIndex;
            while (gridIndex < grid.length())
            {
                cipherText = cipherText + grid.charAt(gridIndex);
                gridIndex = gridIndex + this.key.length();
            }
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
        // Counting numbers added to grid
        int count = 0;
        for (int i = 0; i < this.key.length(); i++)
        {
            String keyChar = "" + (i + 1);
            int keyIndex = this.key.indexOf(keyChar);
            int gridIndex = keyIndex;
            
            while (gridIndex < grid.size())
            {
                grid.set(gridIndex, "" + cipherText.charAt(count));
                count = count + 1;
                gridIndex = gridIndex + this.key.length();
            }
        }
        
        // Print out grid
        for (int i = 0; i < grid.size(); i++)
        {
            plainText = plainText + grid.get(i);
        }
        plainText = plainText.toLowerCase();
        
        return plainText;
    }
}
