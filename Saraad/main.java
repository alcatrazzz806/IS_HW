public class main
{
    public static void main()
    {
        String plainText = "keepgoingnevergiveup";
        System.out.println("Plain text: " + plainText);
        
        CeaserCipher c = new CeaserCipher();
        c.setKey("7");
        String caeser_encrpyted = c.encrypt(plainText);
        System.out.println("Caeser cipher encrypted result: " + caeser_encrpyted);
        System.out.println("Caeser cipher decrypted result: " + c.decrypt(caeser_encrpyted));
        
        MonoAlphabeticCipher m = new MonoAlphabeticCipher();
        m.setKey("MNBVCXZLKJHGFDSAPOIUYTREWQ");
        String monoalphabetic_encrpyted = m.encrypt(plainText);
        System.out.println("Monoalphabatic cipher encrypted result: " + monoalphabetic_encrpyted);
        System.out.println("Monoalphabatic cipher decrypted result: " + m.decrypt(monoalphabetic_encrpyted));
        
        PlayfairCipher p = new PlayfairCipher();
        p.setKey("HIT");
        String playfair_encrypted = p.encrypt(plainText);
        System.out.println("Playfair cipher encrypted result: " + playfair_encrypted);
        System.out.println("Playfair cipher decrypted result: " + p.decrypt(playfair_encrypted));
        
        VernamCipher v = new VernamCipher();
        v.setKey("CON");
        String vernam_encrypted = v.encrypt(plainText);
        System.out.println("Vernam cipher encrypted result: " + vernam_encrypted);
        System.out.println("Vernam cipher decrypted result: " + v.decrypt(vernam_encrypted));
        
        RowTransposition r = new RowTransposition();
        r.setKey("31562487");
        String rowTransposition_encrypted = r.encrypt(plainText);
        System.out.println("Row transposition cipher encrypted result: " + rowTransposition_encrypted);
        System.out.println("Row transposition cipher decrypted result: " + r.decrypt(rowTransposition_encrypted));

    }
}
