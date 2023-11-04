import java.util.Arrays;

/**
 * Project 1
 * part 3.1
 * Encrypt ASCII text
*/
public class PartThreeSDES {
    public static void main(String[] args) {
        
        String text = "CRYPTOGRAPHY";
        byte[] key = { 0,1,1,1,0,0,1,1,0,1};
        
        // part 3.1
        System.out.println("Ciphertext: \n" + Arrays.toString(encrypt(key, text)));
        
        /** Output: 
         * [0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1]
         */

    }
    
    public static byte[] encrypt(byte[] key, String text) {
        Operations op = new Operations();
        // byte[] key = { 0,1,1,1,0,0,1,1,0,1};
        byte[] tempPlaintext = CASCII.Convert(text);
        
        byte[][] plaintext = new byte[8][8];
        
        // convert 64-bit plaintext into 8x8 matrix
        int x = 0;
        for(int i=0; i<8; i++) {
            for(int k=0; k<8; k++) {
                plaintext[i][k] = tempPlaintext[x];
                x++;
            }
        }

        // encrypt plaintext
        byte[][] ciphertext = new byte[8][8];
        byte[] ciphertextResult = new byte[64];
        int index = 0;
        for(int i=0; i<8; i++) {
            ciphertext[i] = op.encrypt(key, plaintext[i]);
            for(int j=0; j<8; j++) {
                ciphertextResult[index] = ciphertext[i][j];
                index++;
            }
        }

        return ciphertextResult;

    }

}
