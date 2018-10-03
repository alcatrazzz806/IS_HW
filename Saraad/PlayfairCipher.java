public class PlayfairCipher implements Cipher
{
    private String keyString;

    /**
     * Constructor for objects of class PlayfairCipher
     */
    public PlayfairCipher()
    {
        this.keyString = "";
    }
    
    public void setKey(String k)
    {
        k = k.toUpperCase();
               
        for (int i = 0; i < k.length(); i++)
        {
            char currentLetter = k.charAt(i);
            // 'I' and 'J' will be in same space
            if ((currentLetter == 'I') || (currentLetter == 'J'))
            {
                if (!keyString.contains("IJ"))
                {
                    keyString = keyString + "IJ";
                }
            }
            // Letter not yet appeared
            else if (keyString.indexOf(currentLetter) == -1)
            {
                keyString = keyString + currentLetter;
            }        
        }
        
        for (char c = 'A'; c <= 'Z'; c++)
        {            
            // 'I' and 'J' will be in same space
            if ((c == 'I') || (c == 'J'))
            {
                if (!keyString.contains("IJ"))
                {
                    keyString = keyString + "IJ";
                }
            }
            // Letter not yet appeared
            else if (keyString.indexOf(c) == -1)
            {
                keyString = keyString + c;
            }        
        }
        // Remove 'J'
        int j_index = keyString.indexOf('J');
        keyString = keyString.substring(0, j_index) + keyString.substring(j_index + 1);
        
        //System.out.println("key string = " + keyString);
    }
    
    public String encrypt(String plainText)
    {  
        plainText = plainText.toUpperCase();
        if (plainText.length() % 2 == 1)
        {
            plainText = plainText + "Z";
        }
        
        String cipherText = "";
        for (int i = 0; i < plainText.length(); i = i + 2)
        {
            char firstLetter = plainText.charAt(i);
            char secondLetter = plainText.charAt(i + 1);
            int firstLetterRow = (new String (this.keyString).indexOf(firstLetter)) / 5;
            int firstLetterCol = (new String (this.keyString).indexOf(firstLetter)) % 5;
            int secondLetterRow = (new String (this.keyString).indexOf(secondLetter)) / 5;
            int secondLetterCol = (new String (this.keyString).indexOf(secondLetter)) % 5;
            
            // Same row and col
            if ((firstLetterRow == secondLetterRow) && (firstLetterCol == secondLetterCol))
            {
                int index = 5 * firstLetterRow + (firstLetterCol + 1) % 5;    // Choose right letter
                cipherText = cipherText + keyString.charAt(index) + keyString.charAt(index);
            }
            // Same row, different col
            else if (firstLetterRow == secondLetterRow)
            {
                int letterOneIndex = 5 * firstLetterRow + (firstLetterCol + 1) % 5;
                int letterTwoIndex = 5 * secondLetterRow + (secondLetterCol + 1) % 5;
                cipherText = cipherText + keyString.charAt(letterOneIndex) + keyString.charAt(letterTwoIndex);
            }
            // Same col, different row
            else if (firstLetterCol == secondLetterCol)
            {
                int letterOneIndex = 5 * ((firstLetterRow + 1) % 5) + firstLetterCol;
                int letterTwoIndex = 5 * ((secondLetterRow + 1) % 5) + secondLetterCol;
                cipherText = cipherText + keyString.charAt(letterOneIndex) + keyString.charAt(letterTwoIndex);
            }
            // Different row and col
            else
            {
                int letterOneIndex = 5 * firstLetterRow + secondLetterCol;
                int letterTwoIndex = 5 * secondLetterRow + firstLetterCol;
                cipherText = cipherText + keyString.charAt(letterOneIndex) + keyString.charAt(letterTwoIndex);
            }
        }
        
        return cipherText;
    }
    
    public String decrypt(String cipherText)
    {        
        String plainText = "";
        for (int i = 0; i < cipherText.length(); i = i + 2)
        {
            char firstLetter = cipherText.charAt(i);
            char secondLetter = cipherText.charAt(i + 1);
            int firstLetterRow = (new String (this.keyString).indexOf(firstLetter)) / 5;
            int firstLetterCol = (new String (this.keyString).indexOf(firstLetter)) % 5;
            int secondLetterRow = (new String (this.keyString).indexOf(secondLetter)) / 5;
            int secondLetterCol = (new String (this.keyString).indexOf(secondLetter)) % 5;
            
            // Same row and col
            if ((firstLetterRow == secondLetterRow) && (firstLetterCol == secondLetterCol))
            {
                int index = 5 * firstLetterRow + (firstLetterCol - 1 + 5) % 5;    // Choose right letter
                plainText = plainText + keyString.charAt(index) + keyString.charAt(index);
            }
            // Same row, different col
            else if (firstLetterRow == secondLetterRow)
            {
                int letterOneIndex = 5 * firstLetterRow + (firstLetterCol - 1 + 5) % 5;
                int letterTwoIndex = 5 * secondLetterRow + (secondLetterCol - 1 + 5) % 5;
                plainText = plainText + keyString.charAt(letterOneIndex) + keyString.charAt(letterTwoIndex);
            }
            // Same col, different row
            else if (firstLetterCol == secondLetterCol)
            {
                int letterOneIndex = 5 * ((firstLetterRow - 1 + 5) % 5) + firstLetterCol;
                int letterTwoIndex = 5 * ((secondLetterRow - 1 + 5) % 5) + secondLetterCol;
                plainText = plainText + keyString.charAt(letterOneIndex) + keyString.charAt(letterTwoIndex);
            }
            // Different row and col
            else
            {
                int letterOneIndex = 5 * firstLetterRow + secondLetterCol;
                int letterTwoIndex = 5 * secondLetterRow + firstLetterCol;
                plainText = plainText + keyString.charAt(letterOneIndex) + keyString.charAt(letterTwoIndex);
            }
        }
        
        plainText = plainText.toLowerCase();
        return plainText;
    }
}
