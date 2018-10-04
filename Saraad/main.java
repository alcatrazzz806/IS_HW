import java.util.Scanner; 

public class main
{
    public static void main()
    {
        System.out.println("Please type the plain text:");
        Scanner scan = new Scanner(System.in);
        String plainText = scan.next();
        //String plainText = "keepgoingnevergiveup";
        System.out.println("Plain text: " + plainText);
        
        System.out.println("Please type the key for CAESER cipher (0~25):");
        String cKey = scan.next();
        CeaserCipher c = new CeaserCipher();
        //c.setKey("7");
        c.setKey(cKey);
        String caeser_encrpyted = c.encrypt(plainText);
        System.out.println("Caeser cipher encrypted result: " + caeser_encrpyted);
        System.out.println("Caeser cipher decrypted result: " + c.decrypt(caeser_encrpyted) + "\n");
        
        System.out.println("Please type the key for MONOALPHABETIC cipher (MNBVCXZLKJHGFDSAPOIUYTREWQ) :");
        String mKey = scan.next();
        MonoAlphabeticCipher m = new MonoAlphabeticCipher();
        //m.setKey("MNBVCXZLKJHGFDSAPOIUYTREWQ");
        m.setKey(mKey);
        String monoalphabetic_encrpyted = m.encrypt(plainText);
        System.out.println("Monoalphabatic cipher encrypted result: " + monoalphabetic_encrpyted);
        System.out.println("Monoalphabatic cipher decrypted result: " + m.decrypt(monoalphabetic_encrpyted) + "\n");
        
        System.out.println("Please type the key for PLAYFAIR cipher (HIT):");
        String pKey = scan.next();
        PlayfairCipher p = new PlayfairCipher();
        //p.setKey("HIT");
        p.setKey(pKey);
        String playfair_encrypted = p.encrypt(plainText);
        System.out.println("Playfair cipher encrypted result: " + playfair_encrypted);
        System.out.println("Playfair cipher decrypted result: " + p.decrypt(playfair_encrypted) + "\n");
        
        System.out.println("Please type the key for VERNAM cipher (CON):");
        String vKey = scan.next();
        VernamCipher v = new VernamCipher();
        //v.setKey("CON");
        v.setKey(vKey);
        String vernam_encrypted = v.encrypt(plainText);
        System.out.println("Vernam cipher encrypted result: " + vernam_encrypted);
        System.out.println("Vernam cipher decrypted result: " + v.decrypt(vernam_encrypted) + "\n");
        
        System.out.println("Please type the key for ROW TRANSPOSITION cipher (31562487):");
        String rKey = scan.next();
        RowTransposition r = new RowTransposition();
        //r.setKey("31562487");
        r.setKey(rKey);
        String rowTransposition_encrypted = r.encrypt(plainText);
        System.out.println("Row transposition cipher encrypted result: " + rowTransposition_encrypted);
        System.out.println("Row transposition cipher decrypted result: " + r.decrypt(rowTransposition_encrypted) + "\n");
        
        System.out.println("Please type the key for PRODUCT cipher (1511191816030714022004120906010517131008):");
        String prKey = scan.next();
        ProductCipher pr = new ProductCipher();
        //pr.setKey("1511191816030714022004120906010517131008");
        pr.setKey(prKey);
        String product_encrypted = pr.encrypt(plainText);
        System.out.println("Product cipher encrypted result: " + product_encrypted);
        System.out.println("Product cipher decrypted result: " + pr.decrypt(product_encrypted) + "\n");
    }
}
