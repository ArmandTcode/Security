package tb.spring.encryption;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class AesCbcHmac extends Aes {
    public static final int CBC_IV_LENGTH = 16;

    public AesCbcHmac() {}

    public byte[] encrypt(byte[] plaintext, byte[] encKey, byte[] IV) throws Exception {

        final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); //actually uses PKCS#7
        cipher.init(Cipher.ENCRYPT_MODE,
                new SecretKeySpec(encKey, "AES"),
                new IvParameterSpec(IV)
        );
        return cipher.doFinal(plaintext);
    }

    public byte[] decrypt(byte[] ciphertext, byte[] encKey, byte[] IV, byte[] authKey, byte[] mac) throws Exception {
        // verify mac - construct it as before
        SecretKey macKey = new SecretKeySpec(authKey, "HmacSHA256");
        Mac hmac = Mac.getInstance("HmacSHA256");
        hmac.init(macKey);
        hmac.update(IV);
        hmac.update(ciphertext);
        byte[] refMac = hmac.doFinal();

        //check mac from time to time to avoid attacks
        if (!MessageDigest.isEqual(refMac, mac)) {
            throw new SecurityException("could not authenticate");
        }

        final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(encKey, "AES"), new IvParameterSpec(IV));
        return cipher.doFinal(ciphertext);
    }


    public static byte[] generateIV() {
        byte[] IV = new byte[CBC_IV_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);
        return IV;
    }

    private byte[] concatenate(byte[] IV, byte[] mac, byte[] cipherText) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1 + IV.length + 1 + mac.length + cipherText.length);
        byteBuffer.put((byte) IV.length);
        byteBuffer.put(IV);
        byteBuffer.put((byte) mac.length);
        byteBuffer.put(mac);
        byteBuffer.put(cipherText);
        return byteBuffer.array();
    }
}
