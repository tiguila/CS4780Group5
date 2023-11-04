# SDES and Triple SDES Encryption/Decryption

**Cryptography and Network Security Group Assignments**

## Purpose

- Implement a simplified DES/Triple DES cipher.
- Gain a deep understanding of all the components of a DES cipher and how DES encipher/decipher works step by step.
- Compare SDES and Triple SDES and identify the weaknesses of each cipher, learning how to enhance security in real-life applications.

## Skills
This assignment aims to help you practice the following skills essential to your success in this course:

- **MLO 3.4**: Explain the components of modern block ciphers.
- **MLO 3.6**: Describe the structure of a round in DES.
- **MLO 3.7**: Illustrate the encryption and decryption process in DES.
- **MLO 5.2**: Experiment with attacks to break some security systems.

## Knowledge
- Components of DES
- Structure of DES
- Round key generation in DES
- Triple DES
- Attacks on DES
- Attacks on Triple DES

# Tasks

## Part 1: SDES

Use your implementation to complete the following table:

| Raw Key       | Plaintext    | Ciphertext |
| ------------- | ------------ | ---------- |
| 0000000000    | 00000000     | ?          |
| 1111111111    | 11111111     | ?          |
| 0000011111    | 00000000     | ?          |
| 0000011111    | 11111111     | ?          |
| 1000101110    | ?            | 00011100   |
| 1000101110    | ?            | 11000010   |
| 0010011111    | ?            | 10011101   |
| 0010011111    | ?            | 10010000   |

## Part 2: Triple SDES

Implement TripleSDES and use your implementation to complete the following table:

| Raw Key 1     | Raw Key 2     | Plaintext    | Ciphertext |
| ------------- | ------------- | ------------ | ---------- |
| 0000000000    | 0000000000    | 00000000     | ?          |
| 1000101110    | 0110101110    | 11010111     | ?          |
| 1000101110    | 0110101110    | 10101010     | ?          |
| 1111111111    | 1111111111    | 10101010     | ?          |
| 1000101110    | 0110101110    | ?            | 11100110   |
| 1011101111    | 0110101110    | ?            | 01010000   |
| 0000000000    | 0000000000    | ?            | 10000000   |
| 1111111111    | 1111111111    | ?            | 10010010   |

## Part 3: Cracking SDES and Triple SDES

1. Give the SDES encoding of the following CASCII plaintext using the key 0111001101. (The answer is 64 bits long.)
   - CRYPTOGRAPHY

2. The message in the file `msg1.txt` was encoded using SDES. Decrypt it and find the 10-bit raw key used for its encryption.

3. The message in the file `msg2.txt` was encoded using TripleSDES. Decrypt it and find the two 10-bit raw keys used for its encryption.



# Notes - Solution

## Part 1
1. Run `SDES.java`

## Part 2
1. Run `TripleSDES.java`

## Part 3
### 3.1
Run `PartThreeSDES.java`

### 3.2
Run `SDESBruteForceKey.java`

### 3.3
Run `TripleSDESBruteForce.java`

