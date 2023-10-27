
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Triple SDES brute force decryption
 * This is enhanced version of 3.3, not fully implemented yet.
 */
public class TestCopy {

    public static BruteForceOperations bfo = new BruteForceOperations();
    public static Map<Integer, byte[]> dictionary = bfo.dictionaryKeys();
    public static PartTwo tripleSDES = new PartTwo();
    private static int bruteForceIterations = 3000; // limit is 524800

    public static void main(String[] args) {
        
        String text = "00011111100111111110011111101100111000000011001011110010101010110001011101001101000000110011010111111110000000001010111111000001010010111001111001010101100000110111100011111101011100100100010101000011001100101000000101111011000010011010111100010001001000100001111100100000001000000001101101000000001010111010000001000010011100101111001101111011001001010001100010100000";
        // bruteForceDecrypt(text);
        byte[] key1 = {1, 1,0,1,1,1,1,1,1,1};
        byte[] key2 = {1, 1,0,1,1,1,1,0,1,0};
        Map<byte[], byte[]> tempMap = decryptAndReturnMap(text, key1, key2);

        int x = 0;
        for (byte[] value : tempMap.values()) {
            System.out.println(Arrays.toString(value) + ", "+ x);
            x++;
        }




    }


    /**
     * Display all possible ciphertexts in CASCII
     * @param text The text to decrypt
     */
    public static void bruteForceDecrypt(String text) {
        
        try {
            // Create a BufferedWriter to write to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter("output2.txt"));
            System.out.println("in progrss...");
        
            for(int i=0; i<bruteForceIterations; i++) {
                String temp = CASCII.toString(TripleSDESBruteForce(text)[i]);
                String value = String.format("\n%dth iteration\n%s", i, temp);
                writer.write(value);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("Process Completed!");
        }
    }

    /**
     * @param text Text to decrypt using brute force
     * @return All possible solutions; ~ 500k
     */
    private static byte[][] TripleSDESBruteForce(String text) {
        
        List<List<Integer>> crossProduct = findCrossProduct();
        

        byte[][] plaintext = new byte[46][8];
        byte[][] pltxt = new byte[bruteForceIterations][368];

        // decrypt the ciphertext, you get plaintext
        for (int i = 0; i < pltxt.length; i++) {
            
            int a = crossProduct.get(i).get(0);
            int b = crossProduct.get(i).get(1);
            byte[] key1 = dictionary.get(a);
            byte[] key2 = dictionary.get(b);
            Map<byte[], byte[]> tempMap = decryptAndReturnMap(text, key1, key2);

            int x=0;
            // One possible solution for each completion of this loop.
            for(int k=0; k<plaintext.length; k++) {

                plaintext[k] = tempMap.get(bfo.textToMatrix(text)[k]);
                
                // Append the current byte to current nth possible solutions
                for(int y=0; y<8; y++) {
                    pltxt[i][x] = plaintext[k][y];
                    x++;
                }
            }
            x = 0;
        }
        return pltxt;
    }

    /**
     * @param Ciphertext to decrypt
     * @return A dictionary/Map that contains the plaintext of the ciphertext
     */
    public static Map<byte[], byte[]> decryptAndReturnMap(String text, byte[] key1, byte[] key2) {
        Map<byte[], byte[]> tempMap = new HashMap<>();

        byte[][] x = bfo.textToMatrix(text);
        for (int i = 0; i < x.length; i++) {
            byte[] value = tripleSDES.decrypt(key1, key2, key1, x[i]);
            byte[] key = x[i];

            // Check if the key is already in the map before adding it
            if (!tempMap.containsKey(key)) {
                tempMap.put(key,value);
            }
        }
        return tempMap;
    }

    /**
     * @return The cross product of 1024 with itself
     */
    public static List<List<Integer>> findCrossProduct() {
        int[] a = bfo.OneTo1024();

        List<List<Integer>> crossProduct = new ArrayList<>();
        
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                List<Integer> pair = new ArrayList<>();
                pair.add(a[i]);
                pair.add(a[j]);
                crossProduct.add(pair);
            }
        }
        return crossProduct;
    }

}
