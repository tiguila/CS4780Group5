
public class Operations {

    private byte[] keyOne; // k1
    private byte[] keyTwo; // k2


    public byte[] decrypt(byte[] rawkey, byte[] plaintext) {
        keyGeneration(rawkey);
        return roundTwoK2(roundOneK1(plaintext, getKeyTwo()), getKeyOne());
    }

    // encrypt an 8 bit plaintext given a key.
    public byte[] encrypt(byte[] rawkey, byte[] plaintext) {
        keyGeneration(rawkey);
        return roundTwoK2(roundOneK1(plaintext, getKeyOne()), getKeyTwo());
    }


    // Second round of SDES encryption using K_2 (key 2).
    public byte[] roundTwoK2(byte[] plaintext, byte[] thekey) {
        
        // use this one step before IP^-1.
        byte[] leftHalf = new byte[plaintext.length/2];
        

        byte[] rightHalf = new byte[plaintext.length/2];
        
        for(int i=0; i<(plaintext.length/2); i++) {
            leftHalf[i] = plaintext[i];
        }
        for(int i=0, j=(plaintext.length/2); j<plaintext.length; i++,j++) {
            rightHalf[i] = plaintext[j];
        }
        
        // EP
        byte[] EPRightHalf = this.expandAndPermute(rightHalf);
        
        
        //XOR   EPRightHalf with k1
        byte[] XOROne = exclusionOR(thekey,EPRightHalf);
        
        
        // SO
        byte[] XOROneLefthalf = new byte[plaintext.length/2];
        byte[] XOROneRighthalf = new byte[plaintext.length/2];
        for(int i=0; i<(plaintext.length/2); i++) {
            XOROneLefthalf[i] = XOROne[i];
        }
        for(int i=0, j=(plaintext.length/2); j<plaintext.length; i++,j++) {
            XOROneRighthalf[i] = XOROne[j];
        }
        
        // Combine the first and last bits to create 'x'
        int x1 = (XOROneLefthalf[0] << 1) | XOROneLefthalf[3];
        int y1 = (XOROneLefthalf[1] << 1) | XOROneLefthalf[2];
        
        // Combine the first and last bits to create 'x'
        int x2 = (XOROneRighthalf[0] << 1) | XOROneRighthalf[3];
        int y2 = (XOROneRighthalf[1] << 1) | XOROneRighthalf[2];

        String S0 = SBox0(x1,y1);
        String S1 = SBox1(x2, y2);

        // p4
        byte[] permuteFour = permutateFour(new byte[] {
            (byte) (S0.charAt(0) - '0'),
            (byte) (S0.charAt(1) - '0'),
            (byte) (S1.charAt(0) - '0'),
            (byte) (S1.charAt(1) - '0')
        });
        
        byte[] XORTwo = exclusionOR(leftHalf, permuteFour);

        byte[] ipInverse = new byte[plaintext.length];
        
        // Merge the IP right half the with IP left left half XORed with P4
        for(int i=0; i<(plaintext.length/2); i++) {
            ipInverse[i] = XORTwo[i];
        }
        
        for(int i=0, j=(plaintext.length/2); j<plaintext.length; i++,j++) { // min 40:48
            ipInverse[j] = rightHalf[i];
        }
        ipInverse = initialPermutationInverse(ipInverse);
        return ipInverse;
    }

    // First round of SDES encryption using K_1 (key 1).
    public byte[] roundOneK1(byte[] plaintext, byte[] thekey) {
        // IP
        plaintext = this.initialPermutation(plaintext);

        byte[] leftHalf = new byte[plaintext.length/2];
        byte[] rightHalf = new byte[plaintext.length/2];
        
        for(int i=0; i<(plaintext.length/2); i++) {
            leftHalf[i] = plaintext[i];
        }
        for(int i=0, j=(plaintext.length/2); j<plaintext.length; i++,j++) {
            rightHalf[i] = plaintext[j];
        }
        
        // EP
        byte[] EPRightHalf = this.expandAndPermute(rightHalf); // EPRightHalf = 1 1 0 0 0 0 1 1
        
        
        //XOR   EPRightHalf with k1
        byte[] XOROne = exclusionOR(thekey,EPRightHalf);
        
        // SO
        byte[] XOROneLefthalf = new byte[plaintext.length/2];
        byte[] XOROneRighthalf = new byte[plaintext.length/2];
        for(int i=0; i<(plaintext.length/2); i++) {
            XOROneLefthalf[i] = XOROne[i];
        }
        for(int i=0, j=(plaintext.length/2); j<plaintext.length; i++,j++) {
            XOROneRighthalf[i] = XOROne[j];
        }
        
        // Combine the first and last bits to create 'x'
        int x1 = (XOROneLefthalf[0] << 1) | XOROneLefthalf[3];
        int y1 = (XOROneLefthalf[1] << 1) | XOROneLefthalf[2];
        
        // Combine the first and last bits to create 'x'
        int x2 = (XOROneRighthalf[0] << 1) | XOROneRighthalf[3];
        int y2 = (XOROneRighthalf[1] << 1) | XOROneRighthalf[2];

        String S0 = SBox0(x1,y1);
        String S1 = SBox1(x2, y2);

        // p4
        byte[] permuteFour = permutateFour(new byte[] {
            (byte) (S0.charAt(0) - '0'),
            (byte) (S0.charAt(1) - '0'),
            (byte) (S1.charAt(0) - '0'),
            (byte) (S1.charAt(1) - '0')
        });

        // The remaining untouched left half
        byte[] XORTwo = exclusionOR(leftHalf,permuteFour);
        byte[] swap = new byte[plaintext.length];
        

        // Merge the IP right half the with IP left left half XORed with P4
        for(int i=0; i<(plaintext.length/2); i++) {
            swap[i] = rightHalf[i];
        }
        for(int i=0, j=(plaintext.length/2); j<plaintext.length; i++,j++) { // min 40:48
            swap[j] = XORTwo[i];
        }
        return swap;
    }
    
    public void keyGeneration(byte[] key) {
        
        // apply permutation 10 on key
        key = this.permutateTen(key);

        byte[] tempLeft = new byte[5];
        for(int i=0; i<tempLeft.length; i++) {
            tempLeft[i] = key[i];
        }

        byte[] tempRight = new byte[5];
        for(int i=0, j=tempLeft.length; i<tempRight.length; i++, j++) {
            tempRight[i] = key[j];
        }
        
        // start of key 1
        // left shift by one position each half.
        tempLeft = this.circularLeftShift(tempLeft, 1);
        tempRight = this.circularLeftShift(tempRight, 1);
        keyOne = new byte[key.length];
        for(int i=0; i<key.length/2; i++) {
            keyOne[i] = tempLeft[i];
        }
        for(int i=0, j=key.length/2; j<key.length; i++,j++) {
            keyOne[j] = tempRight[i];
        }

        // (p8) merge left and right havles and permutate eight it
        // key 1
        keyOne = this.permutateEight(keyOne);

        // start of key 2
        keyTwo = new byte[key.length];
        byte[] leftLSTwo = this.circularLeftShift(tempLeft, 2);
        byte[] rightLSTwo = this.circularLeftShift(tempRight, 2);

        for(int i=0; i<key.length/2; i++) {
            keyTwo[i] = leftLSTwo[i];
        }
        for(int i=0, j=key.length/2; j<key.length; i++,j++) {
            keyTwo[j] = rightLSTwo[i];
        }

        // key 2
        keyTwo = this.permutateEight(keyTwo);
    }


    /** SDES Operations: P10, P8, P4, S0, S1, P10Inverse, and other necessary operations */

    public byte[] permutateTen(byte[] input) {
        int size = 10;
        byte[] byteArray = new byte[size];
        byteArray[0] = input[2];
        byteArray[1] = input[4];
        byteArray[2] = input[1];
        byteArray[3] = input[6];
        byteArray[4] = input[3];
        byteArray[5] = input[9];
        byteArray[6] = input[0];
        byteArray[7] = input[8];
        byteArray[8] = input[7];
        byteArray[9] = input[5];
        return byteArray;
    }

    public byte[] permutateEight(byte[] input) {
        int size = 8;
        byte[] byteArray = new byte[size];
        byteArray[0] = input[5];
        byteArray[1] = input[2];
        byteArray[2] = input[6];
        byteArray[3] = input[3];
        byteArray[4] = input[7];
        byteArray[5] = input[4];
        byteArray[6] = input[9];
        byteArray[7] = input[8];
        return byteArray;
    }

    public byte[] permutateFour(byte[] input) {
        int size = 4;
        byte[] byteArray = new byte[size];
        byteArray[0] = input[1];
        byteArray[1] = input[3];
        byteArray[2] = input[2];
        byteArray[3] = input[0];
        return byteArray;
    }

    public byte[] circularLeftShift(byte[] input, int shift) {

        // Calculate the effective shift (taking into account wrapping)
        int effectiveShift = shift % input.length;
        
        // Perform the circular left shift
        byte[] result = new byte[input.length];
        
        for (int i = 0; i < input.length; i++) {
            int newIndex = (i + effectiveShift) % input.length;
            result[i] = input[newIndex];
        }
        return result;
    }
    
    // IP
    public byte[] initialPermutation(byte[] input) {
        int size = 8;
        byte[] byteArray = new byte[size];
        byteArray[0] = input[1];
        byteArray[1] = input[5];
        byteArray[2] = input[2];
        byteArray[3] = input[0];
        byteArray[4] = input[3];
        byteArray[5] = input[7];
        byteArray[6] = input[4];
        byteArray[7] = input[6];
        return byteArray;
    }

    public byte[] initialPermutationInverse(byte[] input) {
        int size = 8;
        byte[] byteArray = new byte[size];
        byteArray[0] = input[3];
        byteArray[1] = input[0];
        byteArray[2] = input[2];
        byteArray[3] = input[4];
        byteArray[4] = input[6];
        byteArray[5] = input[1];
        byteArray[6] = input[7];
        byteArray[7] = input[5];
        return byteArray;
    }

    // EP
    public byte[] expandAndPermute(byte[] input) {
        int size = 8;
        byte[] byteArray = new byte[size];
        byteArray[0] = input[3];
        byteArray[1] = input[0];
        byteArray[2] = input[1];
        byteArray[3] = input[2];
        byteArray[4] = input[1];
        byteArray[5] = input[2];
        byteArray[6] = input[3];
        byteArray[7] = input[0];
        return byteArray;
    }

    /**
     * key      eight bit key
     * ep       eight right or left half expanded and permuted */
    public byte[] exclusionOR(byte[] key, byte[] ep) {
        byte[] result = new byte[key.length];
        for (int i = 0; i < key.length; i++) {
            result[i] = (byte) (key[i] ^ ep[i]);
        }
        return result;
    }

    public String SBox0(int x, int y) {
        String[][] box = {
            {"01", "00", "11", "10"},
            {"11", "10", "01", "00"},
            {"00", "10", "01", "11"},
            {"11", "01", "11", "10"}
        };
        return String.valueOf(box[x][y]);
    }

    public String SBox1(int x, int y) {
        String[][] box = {
            {"00", "01", "10", "11"},
            {"10", "00", "01", "11"},
            {"11", "00", "01", "00"},
            {"10", "01", "00", "11"}
        };
        return String.valueOf(box[x][y]);
    }


    /** Encrypt the plaintexts */
    public void print(byte[] byteArray) {
        for (int i = 0; i < byteArray.length; i++) {
            System.out.print(byteArray[i]);
        }
        // System.out.println();
    }


    

    // Getters
    public byte[] getKeyOne() {
        return keyOne;
    }
    public byte[] getKeyTwo() {
        return keyTwo;
    }

}