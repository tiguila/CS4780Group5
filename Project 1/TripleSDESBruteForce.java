import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** Note: Do NOT SHARE ANY OF THE CODE HERE WITH OTHERS.
 * Project 1, Part 3.3
 * Triple SDES brute force decryption
 * 
 * 
 * You amy change the range in range() or 
 * bruteForceIterations and bruteForceLowerBound variable values.
 */
public class TripleSDESBruteForce {

    public static BruteForceOperations bfo = new BruteForceOperations();
    public static Map<Integer, byte[]> dictionary = bfo.dictionaryKeys();
    
    /** 
    // left here at index 26563
    **** CONTINUE FROM index 26563 TO 524800
    private static int bruteForceIterations = 0; // limit is 524800, upper bound
    private static int bruteForceLowerBound = 0; // lower bound
    */

    // start: 400,000
    private static int bruteForceIterations = 0; // limit is 524800, upper bound
    private static int bruteForceLowerBound = 0; // lower bound

    public static void main(String[] args) {
        
        long startTime = System.nanoTime();
        System.out.println(bruteForceIterations);

        // String text = "00011111100111111110011111101100111000000011001011110010101010110001011101001101000000110011010111111110000000001010111111000001010010111001111001010101100000110111100011111101011100100100010101000011001100101000000101111011000010011010111100010001001000100001111100100000001000000001101101000000001010111010000001000010011100101111001101111011001001010001100010100000";
        String text  = "0001111110011111111001111110110011100000001100101111001010101011000101110100110100000011001101011111111000000000101011111100000101001011100111100101010110000011";

        
        int[] temp = range();
        for(int i=0; i<temp.length; i++) {
            bruteForceLowerBound = bruteForceIterations;
            bruteForceIterations += temp[i];
            bruteForceDecrypt(text);
        }

        long endTime = System.nanoTime();

        long elapsedTime = endTime - startTime;

        System.out.println("Time taken: " + ((elapsedTime / 1e6)/1000)/60 + " sec"); // Convert to milliseconds

    }

    // do interations in bursts of 5, repeat this for 1000
    public static int[] range() {
        int[] x = new int[1000];
        for(int i=0; i<x.length; i++) {
            x[i] = 5;
        }
        return x;
    }





    /**
     * Display all possible ciphertexts in CASCII
     * @param text The text to decrypt
     */
    public static void bruteForceDecrypt(String text) {
        
        try {
            // Create a BufferedWriter to write to the file, append any previous content of output.txt to the current file.
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));

            for(int i=bruteForceLowerBound; i<bruteForceIterations; i++) {
                String temp = CASCII.toString(tripleSDESBruteForce(text)[i]);
                String value = String.format("\n%dth\n%s", i, temp);
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
    private static byte[][] tripleSDESBruteForce(String text) {

        List<List<Integer>> crossProduct = findCrossProduct();
        PartTwo tripleSDES = new PartTwo();

        byte[][] plaintext = new byte[text.length()/8][8];

        byte[][] pltxt = new byte[bruteForceIterations][text.length()];

        // decrypt the ciphertext, you get plaintext
        for (int i = bruteForceLowerBound; i < pltxt.length; i++) {
            
            int a = crossProduct.get(i).get(0);
            int b = crossProduct.get(i).get(1);
            byte[] key1 = dictionary.get(a);
            byte[] key2 = dictionary.get(b);
            
            int x=0;
            // One possible solution for each completion of this loop.
            for(int k=0; k<plaintext.length; k++) {

                // decrypt the current 8-bit block
                plaintext[k] = tripleSDES.decrypt(key1, key2, key1, bfo.textToMatrix(text)[k]);
                
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
