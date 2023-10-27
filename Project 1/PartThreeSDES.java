
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
        System.out.println(encrypt(key, text));
        
    }
    
    public static String encrypt(byte[] key, String text) {
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
        for(int i=0; i<8; i++) {
            ciphertext[i] = op.encrypt(key, plaintext[i]);
        }

        StringBuilder s = new StringBuilder();
        // print ciphertext
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                s.append(ciphertext[i][j]);                
            }
        }

        return s.toString();

    }

    
   


}
