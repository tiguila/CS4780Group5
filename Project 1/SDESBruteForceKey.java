public class SDESBruteForceKey {

    public static void main(String[] args) {
        Operations op = new Operations();
        // Read ciphertext from file
        String ciphertext = readFromFile();

        // Perform key search

        for (int i = 0; i < 1024; i++) {
            String key = String.format("%10s", Integer.toBinaryString(i)).replace(' ', '0');
            
            
            byte[] decryptedMessage = op.decrypt(null, null);


            /**
             * Here's my thought process:
             * 
             * 
             * step 1: 1024 possible keys, iterations
             * step 2: apply decrypt() 119 times
             * Step 3: remove 3 bits from the right most of the decrypted ciphertext
             * Step 4: convert step 3 result into CASCII character
             * Step 5: Manually check each iteration result, find the one that makes the most sense and its corresponding key.
             */

            System.out.println("Key found: " + key);
            System.out.println("Decrypted Message: " + decryptedMessage);
            
        }
    }

    private static String readFromFile() {
        // return content of msg1.txt. The following is the content of msg1.txt
        // create and return a 119x8 matrix representing the contnet of msg1.txt
        return "1011011001111001001011101111110000111110100000000001110111010001111011111101101100010011000000101101011010101000101111100011101011010111100011101001010111101100101110000010010101110001110111011111010101010100001100011000011010101111011111010011110111001001011100101101001000011011111011000010010001011101100011011110000000110010111111010000011100011111111000010111010100001100001010011001010101010000110101101111111010010110001001000001111000000011110000011110110010010101010100001000011010000100011010101100000010111000000010101110100001000111010010010101110111010010111100011111010101111011101111000101001010001101100101100111001110111001100101100011111001100000110100001001100010000100011100000000001001010011101011100101000111011100010001111101011111100000010111110101010000000100110110111111000000111110111010100110000010110000111010001111000101011111101011101101010010100010111100011100000001010101110111111101101100101010011100111011110101011011";
    }
}


