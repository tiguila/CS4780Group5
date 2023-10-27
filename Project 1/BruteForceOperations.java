import java.util.HashMap;
import java.util.Map;

public class BruteForceOperations {


    /**
     * @return A dictionary that contains the current index and its corresponding key in bytes[]
     */
    public Map<Integer, byte[]> dictionaryKeys() {
        
        // Create a dictionary so that 1-1024 are the keys; the value is the key's equivalent in binary
        Map<Integer, byte[]> dictionary = new HashMap<>();
        byte[][] k = generateAllPossibleKeys();
        
        // Populate dictionary from 1-1024 and its corresponding key
        for(int i=0; i<k.length; i++) {
            dictionary.put(i, k[i]);
        }
        return dictionary;
    }


    
    /**
     * Format the msg1.txt into a matrix
     * @return ciphertext into a 119x8 matrix
     */
    public byte[][] textToMatrix(String s) {
        
        byte[][] plaintext = new byte[s.length()/8][8];
        int x = 0;
        for(int i=0; i<plaintext.length; i++) {
            for(int k=0; k<plaintext[i].length; k++) {
                plaintext[i][k] = (byte) (s.charAt(x) - '0');
                x++;
            }
        }
        return plaintext;
    }


    /**
     * Generate all possible keys, 2^10
     * @return all possible keys
     */
    public byte[][] generateAllPossibleKeys() {
        byte[][] k = new byte[1024][8];
        for (int i = 0; i < k.length; i++) {
            k[i] = key(String.format("%10s", Integer.toBinaryString(i)).replace(' ', '0'));
        }
        return k;
    }


    /**
     * @param k The key in string type
     * @return a key in a byte
     */
    private byte[] key(String k) {
        byte[] key = new byte[k.length()];
        for(int i=0; i<k.length(); i++) {
            key[i] =(byte) (k.charAt(i) - '0');
        }
        return key;
    }

    /*** Create a array, 0 - 2^(10)-1
     * @return An array containing 0-1023
     */
    public int[] OneTo1024() {
        int[] temp = new int[1024];
        for(int i=0; i<temp.length; i++) {
            temp[i] = i;
        }
        return temp;
    }
    
}
