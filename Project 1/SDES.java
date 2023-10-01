public class SDES {
    static Operations op = new Operations();

    public static void main(String[] args) {
        sdes();
        testCase();
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
        System.out.println("Encryption");
        System.out.println("Raw key\t\t Plaintext\t Ciphertext");
        for(int i=0; i<keys.length; i++) {
            op.print(keys[i]);
            System.out.print("\t");
            op.print(plaintexts[i]);
            System.out.print("\t");
            op.print(op.encrypt(keys[i], plaintexts[i]));
            System.out.println();
        }
        System.out.println();
    }

    public static void SDESDecrypt(byte[][] keys, byte[][] ciphertexts) {
        System.out.println("Decryption");
        System.out.println("Raw key\t\t Plaintext\t Ciphertext");
        for(int i=0; i<keys.length; i++) {
            op.print(keys[i]);
            System.out.print("\t");
            op.print(op.decrypt(keys[i], ciphertexts[i]));
            System.out.print("\t");
            op.print(ciphertexts[i]);
            System.out.println();
        }
        System.out.println();
    }

    public static void testCase() {
        byte[][] encryptKeys = {
            {0,0,0,0,0,0,0,0,0,0},
            {1,1,1,0,0,0,1,1,1,0},
            {1,1,1,0,0,0,1,1,1,0},
            {1,1,1,1,1,1,1,1,1,1}
        };
        byte[][] plaintexts = {
            {1,0,1,0,1,0,1,0},
            {1,0,1,0,1,0,1,0},
            {0,1,0,1,0,1,0,1},
            {1,0,1,0,1,0,1,0}
        };

        SDESEncrypt(encryptKeys, plaintexts);
        
    }    

}
