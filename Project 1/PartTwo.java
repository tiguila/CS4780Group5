public class PartTwo {

    Operations op = new Operations();
    

    public byte[] encrypt(byte[] key1, byte[] key2, byte[] plaintext) {
        byte[] temp1 = op.encrypt(key1, plaintext);
        byte[] temp2 = op.decrypt(key2, temp1);
        byte[] temp3 = op.encrypt(key1, temp2);
        return temp3;
    }

    public byte[] decrypt(byte[] key1, byte[] key2, byte[] plaintext) {
        byte[] temp1 = op.decrypt(key1, plaintext);
        byte[] temp2 = op.encrypt(key2, temp1);
        byte[] temp3 = op.decrypt(key1, temp2);
        return temp3;
    }

}
