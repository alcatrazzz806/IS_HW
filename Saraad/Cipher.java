
/**
 * An implementation including basic methods of cipher.
 */
public interface Cipher
{
    /**
     * Set the key of the cipher.
     */
    void setKey(String k);
    
    /**
     * Encrypt the plain text to cipher text.
     * 
     * @param   plainText    Text wanted to be encrypted
     * @return  The encrypted text 
     */
    String encrypt(String plainText);
    
    /**
     * Decrypt the cipher text to plain text.
     * 
     * @param   cypherText    Text wanted to be encrypted
     * @return  The decrpyted text 
     */
    String decrypt(String cypherText);
}
