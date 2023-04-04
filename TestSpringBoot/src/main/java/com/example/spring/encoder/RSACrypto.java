package com.example.spring.encoder;

import java.util.HashMap;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class RSACrypto {
	
	// 암호화알고리즘/운용 모드/ 패딩
    // 아래 포스팅에 cipher에 사용되는 arg들에 대해 상세하게 설명되어 있다.
    // http://happinessoncode.com/2019/04/06/java-cipher-algorithm-mode-padding/
	private String arg = "AES/CBC/PKCS5Padding";
	
	private Cipher cipher;
	private SecretKeySpec keySpec;
	private IvParameterSpec ivParameterSpec;
	
	  /* PKCS#5와 PKCS#7 */
    public static final String PADDING = "AES/CBC/PKCS5Padding";

    /* 256비트(32바이트)의 키 */
    private static final String KEY = "aes256encrypt123aes256encrypt123567";

    /* initialization vector */
    private static byte[] getIv() {
        return new byte[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }
	
	
	public RSACrypto() throws NoSuchAlgorithmException, NoSuchPaddingException {
		byte[] specBytes = new byte[16];
		byte[] keyBytes = KEY.getBytes();
		int len = keyBytes.length;
		
        if (len > specBytes.length) {
            len = specBytes.length;
        }
		System.arraycopy(keyBytes, 0, specBytes, 0, len);
		
		this.cipher=Cipher.getInstance(arg);		
		this.keySpec=new SecretKeySpec(specBytes, "AES");
		this.ivParameterSpec=new IvParameterSpec(specBytes);
	}
	public String encrypt(String plain) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		cipher.init(Cipher.ENCRYPT_MODE, keySpec,ivParameterSpec);
		
		byte[] encrypted = cipher.doFinal(plain.getBytes());
		return Base64.getEncoder().encodeToString(encrypted);
		
	}
	
	public String decrypt(String encoded) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		cipher.init(Cipher.DECRYPT_MODE, keySpec,ivParameterSpec);
		
		byte[] base64Decoded = Base64.getDecoder().decode(encoded);
		byte[] decrypted = cipher.doFinal(base64Decoded);
		
		return new String(decrypted);
	}
	
	
    public static String new_encrypt(String plainText) throws Exception {
        
        /* 32byte IV initialization */
        byte[] iv = getIv();
        /* KEY String -> byte */
        byte[] keyData = KEY.getBytes();

        /* keyData를 key로 지정, AES algorithm사용 */
        SecretKey secureKey = new SecretKeySpec(keyData, "AES");

        /* CBC PKCS5Padding 방식 사용 */
        Cipher cipher = Cipher.getInstance(PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(iv));

        /* 암호화 */
        byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }
	
}
