import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


/** Project 1, 3.2
 * Brute force SDES
 * You are given n bits ciphertext. Decrypt it and find the CASCII text and its corresponding key.
 */
public class SDESBruteForceKey {


    public static BruteForceOperations tsdesbf = new BruteForceOperations();
    public static void main(String[] args) {
        
        String txt = "1011011001111001001011101111110000111110100000000001110111010001111011111101101100010011000000101101011010101000101111100011101011010111100011101001010111101100101110000010010101110001110111011111010101010100001100011000011010101111011111010011110111001001011100101101001000011011111011000010010001011101100011011110000000110010111111010000011100011111111000010111010100001100001010011001010101010000110101101111111010010110001001000001111000000011110000011110110010010101010100001000011010000100011010101100000010111000000010101110100001000111010010010101110111010010111100011111010101111011101111000101001010001101100101100111001110111001100101100011111001100000110100001001100010000100011100000000001001010011101011100101000111011100010001111101011111100000010111110101010000000100110110111111000000111110111010100110000010110000111010001111000101011111101011101101010010100010111100011100000001010101110111111101101100101010011100111011110101011011";
        bruteForceDecrypt(txt);

        // printResults();
        
    }


    public static void printResults() {

        int index = 756;
        byte[][] keys = tsdesbf.generateAllPossibleKeys();
        String plaintext = "WHOEVER THINKS HIS PROBLEM CAN BE SOLVED USING CRYPTOGRAPHY, DOESN'T UNDERSTAND HIS PROBLEM AND DOESN'T UNDERSTAND CRYPTOGRAPHY.  ATTRIBUTED BY ROGER NEEDHAM AND BUTLER LAMPSON TO EACH OTHER";

        System.out.println("Key: " + Arrays.toString(keys[index]));
        System.out.println("Plaintext: "+ plaintext);
    }



    /** Try 1024 possible keys and print CASCII plaintext
     * @param n Ciphertext length
     */
    public static void bruteForceDecrypt(String text) {
        // int i= 756;
        try {
            // Create a BufferedWriter to write to the file, append any previous content of output.txt to the current file.
            BufferedWriter writer = new BufferedWriter(new FileWriter("SDESbruteforce.txt", true));

            for(int i=752; i<763; i++) {
                String temp = CASCII.toString(decryptBinary(text)[i]);
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
     * @param n Length of the ciphertext
     * @return 1024 possible plaintext solutions in bytes;
     * Each solution contains 119 bytes
     */
    private static byte[][] decryptBinary(String text) {
        int n = text.length();
        Operations op = new Operations();
        
        
        byte[][] keys = tsdesbf.generateAllPossibleKeys(); // 1024x8
        byte[][] pltxt = new byte[keys.length][n];
        byte[][] plaintext = new byte[n/8][8];
        
        // decrypt the ciphertext, you get plaintext
        for (int i = 0; i < keys.length; i++) {
            
            // current solution index, 0-952
            int x=0;

            /** Current solution, 1/1024
            * 119x8 ---> 952 bits --> one iteration equates to one possible solution. */
            for(int k=0; k<plaintext.length; k++) {
                
                // decrypt the current 8-bit block
                plaintext[k] = op.decrypt(keys[i], tsdesbf.textToMatrix(text)[k]);
                
                // Append the current byte to current nth/1024 possible solutions
                for(int y=0; y<8; y++) {
                    pltxt[i][x] = plaintext[k][y];
                    x++;
                }
            }
            x = 0;

        }

        return pltxt;
    }


}


