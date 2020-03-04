package tb.spring.encryption;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

public class AesGcmKeys extends AesKeys {

    public static final int GCM_IV_LENGTH = 12;
    private SecretKey key;
    private byte[] IV;

    public AesGcmKeys() {}

    public AesGcmKeys(SecretKey key, byte[] IV) {
        this.key = key;
        this.IV = IV;
    }

    public SecretKey getKey() {
        return key;
    }

    public byte[] getIV() {
        return IV;
    }

    public void generateKeys() throws Exception{
        this.key = generateAesKey();
        this.IV = generateIVkey();
    }

    private SecretKey generateAesKey() throws Exception{
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(AES_KEY_SIZE);
        return keyGenerator.generateKey();
    }

    private byte[] generateIVkey() throws Exception {
        byte[] IV = new byte[GCM_IV_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);
        return IV;
    }
}
