package tb.spring.encryption;
import at.favre.lib.crypto.HKDF;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class AesCbcHmacKeys extends AesKeys {
    public static final int CBC_IV_LENGTH = 16;
    public static final int CBC_TAG_LENGTH = 16;
    public static final int HMAC_SHA256_LENGTH = 32;
    private byte[] encKey;
    private byte[] authKey;
    private byte[] IV;
    //private byte[] mac;

    public AesCbcHmacKeys() {}

    public AesCbcHmacKeys(byte[] encKey, byte[] authKey, byte[] IV, byte[] mac) {
        this.encKey = encKey;
        this.authKey = authKey;
        this.IV = IV;
        //this.mac = mac;
    }

    public byte[] getEncKey() {
        return encKey;
    }

    public byte[] getAuthKey() {
        return authKey;
    }

    public byte[] getIV() {
        return IV;
    }

/*    public byte[] getMac() {
        return mac;
    }*/

    public void generateKeys() throws Exception{
        SecretKey key = generateAesKey();
        this.authKey = generateAuthKey(key);
        this.encKey = generateEncKey(key);
        this.IV = generateIV();
        //this.mac = generateMac();
    }

    private byte[] generateEncKey(SecretKey key) {
        return HKDF.fromHmacSha256().expand(key.getEncoded(),
                "encKey".getBytes(StandardCharsets.UTF_8),
                CBC_TAG_LENGTH);
    }

    private byte[] generateAuthKey(SecretKey key) {
        return HKDF.fromHmacSha256().expand(key.getEncoded(),
                "authKey".getBytes(StandardCharsets.UTF_8),
                HMAC_SHA256_LENGTH);
    }

    private byte[] generateIV() {
        byte[] IV = new byte[CBC_IV_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);
        return IV;
    }

    public byte[] generateMac(byte[] cipherText) throws Exception {
        SecretKey macKey = new SecretKeySpec(authKey, "HmacSHA256");
        Mac hmac = Mac.getInstance("HmacSHA256");
        hmac.init(macKey);
        hmac.update(IV);
        hmac.update(cipherText);
        return hmac.doFinal();
    }

    private SecretKey generateAesKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(AES_KEY_SIZE);
        return keyGenerator.generateKey();
    }
}
