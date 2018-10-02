
/**
 * Write a description of class main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class main
{
    public static void main()
    {
        String plainText = "keepgoingnevergiveup";
        
        CeaserCipher c = new CeaserCipher();
        c.setKey("7");
        String caeser_encrpyted = c.encrypt(plainText);
        System.out.println(c.encrypt(plainText));
        System.out.println(c.decrypt(caeser_encrpyted));
    }
}
