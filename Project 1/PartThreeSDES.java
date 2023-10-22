public class PartThreeSDES {
    public static void main(String[] args) {
        partOne();
        
    }

    public static void partOne() {

        String plaintext = "CRYPTOGRAPHY";
        System.out.println("CASCII plaintext: " + plaintext);

        System.out.print("ciphertext(" + plaintext + "): ");
        byte[] key = { 0,1,1,1,0,0,1,1,0,1};
        print(encrypt(plaintextsInBytes(plaintext), key));
        
    }



    /**
     * Encrypt the 2D array of plaintexts using the provided 10-bit key.
     *
     * @param plaintexts the plaintext to encrypt
     * @param key        the key used for encryption
     * @return encryptedBytes the ciphertext of plaintexts.
     */
    public static byte[][] encrypt(byte[][] plaintexts, byte[] key) {
        Operations op = new Operations();
        
        byte[][] encryptedBytes = new byte[plaintexts.length][7];
        for (int i = 0; i < plaintexts.length; i++) {
            encryptedBytes[i] = op.encrypt(key, plaintexts[i]);
        }
        return encryptedBytes;
    }

    /**
     * Convert string plaintext to a 2D array of bytes
     *
     * @param plaintext The string to convert to bytes
     * @return plaintextBytes
     */
    public static byte[][] plaintextsInBytes(String plaintext) {
        byte[][] plaintextBytes = new byte[plaintext.length()][8];
        for (int i = 0; i < plaintext.length(); i++) {
            char letterT = plaintext.charAt(i);
            byte casByte = CASCII.Convert(letterT);

            // For some reason, if you do not reverse the string of casByte,
            // each 8-bit in plaintextBytes will be reversed.
            // To avoid such issues, inverse the process using the reverseString() method.
            String bitString = reverseString(toBitString(casByte)) + "000";

            int j = 7;
            for (int k = 0; k < 8; k++, j--) {
                plaintextBytes[i][k] = (byte) (bitString.charAt(j) - '0');
            }
        }
        return plaintextBytes;
    }

    private static String toBitString(byte b) {
        // Convert the byte to a bit string representation
        return String.format("%5s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
    }

    public static String reverseString(String input) {
        // Convert the string to a char array
        char[] charArray = input.toCharArray();

        // Reverse the char array
        int left = 0;
        int right = charArray.length - 1;
        while (left < right) {
            // Swap characters at left and right indices
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;

            // Move indices towards the center
            left++;
            right--;
        }
        // Convert the reversed char array back to a string
        return new String(charArray);
    }

    public static void print(byte[][] text) {
        for (int i = 0; i < text.length; i++) {
            for (int k = 0; k < text[i].length; k++) {
                System.out.print(text[i][k]);
            }
        }
    }



}
