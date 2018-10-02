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
