package tb.spring.encryption;
import java.util.Base64;

public class DemoApp {
    public static void AES_GCM_Example() throws Exception{
        AesGcm aes = new AesGcm();
        AesGcmKeys aesGcmKeys = new AesGcmKeys();
        aesGcmKeys.generateKeys();

        System.out.println("Original Text : " + Aes.plainText);

        byte[] cipherText = aes.encrypt(AesGcm.plainText.getBytes(), aesGcmKeys.getKey(), aesGcmKeys.getIV());
        System.out.println("Encrypted Text : " +  Base64.getEncoder().encodeToString(cipherText));

        byte[] decryptedText = aes.decrypt(cipherText, aesGcmKeys.getKey(), aesGcmKeys.getIV());
        System.out.println("DeCrypted Text : " + new String(decryptedText));
    }

    public static void AES_CBC_HMAC_Example() throws Exception {
        AesCbcHmac aes = new AesCbcHmac();
        AesCbcHmacKeys aesCbcHmacKeys = new AesCbcHmacKeys();
        aesCbcHmacKeys.generateKeys();

        System.out.println("Original Text : " + Aes.plainText);

        byte[] cipherText = aes.encrypt(Aes.plainText.getBytes(), aesCbcHmacKeys.getEncKey(), aesCbcHmacKeys.getIV());
        System.out.println("Encrypted Text : " +  Base64.getEncoder().encodeToString(cipherText));

        byte[] mac = aesCbcHmacKeys.generateMac(cipherText);

        byte[] decryptedText = aes.decrypt(cipherText, aesCbcHmacKeys.getEncKey(), aesCbcHmacKeys.getIV(), aesCbcHmacKeys.getAuthKey(), mac);
        System.out.println("DeCrypted Text : " + new String(decryptedText));
    }
    public static void main (String[] args) throws Exception {
        //AES_GCM_Example();
        AES_CBC_HMAC_Example();
    }
}
