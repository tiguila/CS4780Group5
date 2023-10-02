public class TripleDES {
    static PartTwo p = new PartTwo();
    static Operations op = new Operations();
    public static void main(String[] args) {
        tripleSdes();
    }


    public static void tripleSdes() {
        
        // encrypt
        byte[][] keys1 = {
            {0,0,0,0,0,0,0,0,0,0},
            {1,0,0,0,1,0,1,1,1,0},
            {1,0,0,0,1,0,1,1,1,0},
            {1,1,1,1,1,1,1,1,1,1}
        };
        byte[][] keys2 = {
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
        tripleSDESEncrypt(keys1, keys2, plaintexts);

        
        // decrypt
        byte[][] keys1d = {
            {1,0,0,0,1,0,1,1,1,0},
            {1,0,0,0,1,0,1,1,1,0},
            {0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1}
        };
        byte[][] keys2d = {
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
        tripleSDESDecrypt(keys1d, keys2d, ciphertexts);

    }


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
            op.print(op.encrypt(keys1[i], plaintexts[i]));
            System.out.println();
        }
        System.out.println();
    }

    public static void tripleSDESDecrypt(byte[][] keys1, byte[][] keys2, byte[][] ciphertexts) {
        System.out.println("Decryption");
        System.out.println("key 1\t\tkey 2\t\tPlaintext\t Ciphertext");
        for(int i=0; i<keys1.length; i++) {
            op.print(keys1[i]);
            System.out.print("\t");
            op.print(keys2[i]);
            System.out.print("\t");
            op.print(op.decrypt(keys1[i], ciphertexts[i]));
            System.out.print("\t");
            op.print(ciphertexts[i]);
            System.out.println();
        }
        System.out.println();
    }






}
