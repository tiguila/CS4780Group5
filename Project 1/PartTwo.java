/**
 * Project 1, Part 2
 * Triple SDES encryption and decryption methods.
 */
public class PartTwo {

    Operations op = new Operations();
    
    /** Encrypt an 8-bit block using Triple SDES
     * @param key1 the first key
     * @param key2 the second key
     * @param plaintext plaintext to encrypt
     * @return ciphertext
     */
    public byte[] encrypt(byte[] key1, byte[] key2, byte[] key3, byte[] plaintext) {
        byte[] temp1 = op.encrypt(key1, plaintext);
        byte[] temp2 = op.decrypt(key2, temp1);
        byte[] temp3 = op.encrypt(key3, temp2);
        return temp3;
    }

    /**
     * Decrypt an 8-bit block using Triple SDES
     * @param key1 the first key
     * @param key2 the second key
     * @param ciphertext ciphertext to decrypt
     * @return plaintext
     */
    public byte[] decrypt(byte[] key1, byte[] key2, byte[] key3, byte[] ciphertext) {
        byte[] temp1 = op.decrypt(key1, ciphertext);
        byte[] temp2 = op.encrypt(key2, temp1);
        byte[] temp3 = op.decrypt(key3, temp2);
        return temp3;
    }

}
