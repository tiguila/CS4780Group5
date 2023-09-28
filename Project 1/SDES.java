public class SDES {
    static Operations op = new Operations();

    public static void main(String[] args) {
        
        
        // call the methods that do encryption and decryption
        SDES sdes = new SDES();
        sdes.sdes();
    }

    /**
     * Print all the encrypted plaintexts.
     * Print all the decrypted ciphertexts.
    */
    public static void sdes() {
        byte[][] encryptKeys = {
            {0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1},
            {0,0,0,0,0,1,1,1,1,1},
            {0,0,0,0,0,1,1,1,1,1}
        };
        byte[][] plaintexts = {
            {0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1},
            {0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1}
        };
        byte[][] decryptKeys = {
            {1,0,0,0,1,0,1,1,1,0},
            {1,0,0,0,1,0,1,1,1,0},
            {0,0,1,0,0,1,1,1,1,1},
            {0,0,1,0,0,1,1,1,1,1}
        };
        byte[][] ciphertext = {
            {0,0,0,1,1,1,0,0},
            {1,1,0,0,0,0,1,0},
            {1,0,0,1,1,1,0,1},
            {1,0,0,1,0,0,0,0}
        };

        SDESEncrypt(encryptKeys, plaintexts);
        SDESDecrypt(decryptKeys, ciphertext);
    }


    /**
     * ------------------ Encryption and decryption
     * */
    public static void SDESEncrypt(byte[][] keys, byte[][] plaintexts) {
        System.out.println("Encrypted plaintexts: ");
        for(int i=0; i<keys.length; i++) {
            op.print(op.encrypt(keys[i], plaintexts[i]));
        }
        System.out.println();
    }

    /** Decrypt the ciphertexts */
    public static void SDESDecrypt(byte[][] keys, byte[][] ciphertexts) {
        System.out.println("Decrypted ciphertexts: ");
        for(int i=0; i<keys.length; i++) {
           op.print(op.decrypt(keys[i], ciphertexts[i]));
        }
        System.out.println();
    }


}
