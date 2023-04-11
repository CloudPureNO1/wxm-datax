package com.wxm.util.my.code;
// Import necessary packages
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
/**
 * TODO
 *
 * @author wangsm
 * @version 1.0.0
 * @date 2023-04-07 17:31:45
 */
public class AES128 {
    public static byte[] encrypt(byte[] plaintext, byte[] key) throws Exception {

        // Create a new SecretKeySpec object with the given key and "AES" algorithm
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");

        // Create a new Cipher object with "AES/ECB/PKCS5Padding" transformation
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        // Initialize the cipher in encryption mode with the given secret key
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Return the encrypted plaintext
        return cipher.doFinal(plaintext);
    }

    // Define the decrypt method that takes in a ciphertext and a key and returns the plaintext
    public static byte[] decrypt(byte[] ciphertext, byte[] key) throws Exception {

        // Create a new SecretKeySpec object with the given key and "AES" algorithm
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");

        // Create a new Cipher object with "AES/ECB/PKCS5Padding" transformation
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        // Initialize the cipher in decryption mode with the given secret key
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Return the decrypted ciphertext
        return cipher.doFinal(ciphertext);
    }
}
