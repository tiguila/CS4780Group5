 
public class SDES {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}

class Operations {

    private byte[] keyOne; // k1
    private byte[] keyTwo; // k2
    
    public byte[] roundTwoK2(byte[] plaintext) {
        // IP
        return plaintext;
    }

    public byte[] roundOneK1(byte[] plaintext) {
        // IP
        return plaintext;
    }

    
    public void keyGeneration(byte[] key) {
        // generate keys
    }

    public byte[] permutateTen(byte[] input) {
        
        return input;
    }

    public byte[] permutateEight(byte[] input) {
    }

    public byte[] permutateFour(byte[] input) {
    }

    public byte[] circularLeftShift(byte[] input, int shift) {
    }
    
    // IP
    public byte[] initialPermutation(byte[] input) {
    }

    // HELP!!! HELP!! HELP!!
    public byte[] initialPermutationInverse(byte[] input) {
    }

    // EP
    public byte[] expandAndPermute(byte[] input) {
    }
    public void print(byte[] byteArray) {
        for (int i = 0; i < byteArray.length; i++) {
            System.out.print(byteArray[i]+ " ");
        }
        System.out.println();
    }

    /**
     * key      eight bit key
     * ep       eight right or left half expanded and permuted */
    public byte[] exclusionOR(byte[] key, byte[] ep) {
    }

    public String SBox0(int x, int y) {
    }

    public String SBox1(int x, int y) {
    }

}