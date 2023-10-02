/** 
 * Find the ciphertext
 * Raw key      plaintext       ciphertext
*/


public class TripleSDESBruteForce {
    public static void main(String[] args) {
        // CASCII plaintext
        String plaintext = "CRYPTOGRAPHY";

        // Key for SDES
        byte[] rawKey = {0,1,1,1,0,0,1,1,0,1};

        // Convert CASCII to binary
        byte[] asciiBits = CASCII.Convert(plaintext);
        StringBuilder binaryStringBuilder = new StringBuilder();
        int i=1;
        for (byte b : asciiBits) {
            // Convert each byte to a 5-bit binary string
            String binary = String.format("%5s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            binaryStringBuilder.append(binary);
            i++;
        }

        // Pad the binary representation to make it a multiple of 8 bits
        String paddedBinary = padToMultipleOf8(binaryStringBuilder.toString());

        System.out.println("What is this? " + paddedBinary);
        


        // Perform SDES encoding
        // String sdesEncoded = SDES.encode(paddedBinary, key);

        // Print the ciphertext
        // System.out.println("SDES Encoding: " + sdesEncoded);
    }

    // Pad the binary string to make it a multiple of 8 bits
    private static String padToMultipleOf8(String binary) {
        int remainder = binary.length() % 8;
        if (remainder != 0) {
            int padding = 8 - remainder;
            for (int i = 0; i < padding; i++) {
                binary += "0";
            }
        }
        return binary;
    }
}






