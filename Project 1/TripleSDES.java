
/**
 * Encrypt and decrypt using Triple SDES
 */
public class TripleSDES {
    static PartTwo tripleSDES = new PartTwo();
    static Operations op = new Operations();
    public static void main(String[] args) {
        tripleSdes();
    }


    public static void tripleSdes() {
        
        // encrypt
        byte[][] keys1Encryption = {
            {0,0,0,0,0,0,0,0,0,0},
            {1,0,0,0,1,0,1,1,1,0},
            {1,0,0,0,1,0,1,1,1,0},
            {1,1,1,1,1,1,1,1,1,1}
        };
        byte[][] keys2Encryption = {
            {0,0,0,0,0,0,0,0,0,0},
            {0,1,1,0,1,0,1,1,1,0},
            {0,1,1,0,1,0,1,1,1,0},
            {1,1,1,1,1,1,1,1,1,1}
        };
        byte[][] plaintexts = {
            {0,0,0,0,0,0,0,0},
            {1,1,0,1,0,1,1,1},
            {1,0,1,0,1,0,1,0},
            {1,0,1,0,1,0,1,0}
        };
        tripleSDESEncrypt(keys1Encryption, keys2Encryption, plaintexts);

        
        // decrypt
        byte[][] keys1decryption = {
            {1,0,0,0,1,0,1,1,1,0},
            {1,0,0,0,1,0,1,1,1,0},
            {0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1}
        };
        byte[][] keys2decryption = {
            {0,1,1,0,1,0,1,1,1,0},
            {0,1,1,0,1,0,1,1,1,0},
            {0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1}
        };
        byte[][] ciphertexts = {
            {1,1,1,0,0,1,1,0},
            {0,1,0,1,0,0,0,0},
            {1,0,0,0,0,0,0,0},
            {1,0,0,1,0,0,1,0}
        };

        // decrypt
        tripleSDESDecrypt(keys1decryption, keys2decryption, ciphertexts);

    }

    /**
     * Encrypt plaintexts using triple SDES
     * @param keys1 The first key set
     * @param keys2 The second key set
     * @param plaintexts plaintext set to encrypt
     */
    public static void tripleSDESEncrypt(byte[][] keys1, byte[][] keys2, byte[][] plaintexts) {
        System.out.println("Encryption");
        System.out.println("key 1\t\tkey 2\t\tPlaintext\tCiphertext");
        for(int i=0; i<keys1.length; i++) {
            op.print(keys1[i]);
            System.out.print("\t");

            op.print(keys2[i]);
            System.out.print("\t");

            op.print(plaintexts[i]);
            System.out.print("\t");
            op.print(tripleSDES.encrypt(keys1[i], keys2[i], keys1[i], plaintexts[i]));
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Decrypt ciphertexts using triple SDES
     * @param keys1 The first key set
     * @param keys2 The second key set
     * @param ciphertexts ciphertexts set to decrypt
     */
    public static void tripleSDESDecrypt(byte[][] keys1, byte[][] keys2, byte[][] ciphertexts) {
        System.out.println("Decryption");
        System.out.println("key 1\t\tkey 2\t\tPlaintext\t Ciphertext");
        for(int i=0; i<keys1.length; i++) {
            op.print(keys1[i]);
            System.out.print("\t");
            op.print(keys2[i]);
            System.out.print("\t");
            op.print(tripleSDES.decrypt(keys1[i], keys2[i], keys1[i], ciphertexts[i]));
            System.out.print("\t");
            op.print(ciphertexts[i]);
            System.out.println();
        }
        System.out.println();
    }






}
