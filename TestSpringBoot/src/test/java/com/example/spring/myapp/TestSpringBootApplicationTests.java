package com.example.spring.myapp;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.spring.encoder.Encrypt;
import com.example.spring.encoder.RSACrypto;
import com.example.spring.encoder.WebSecurityConfig;

@SpringBootTest
class TestSpringBootApplicationTests {
	WebSecurityConfig webSecurityConfig = new WebSecurityConfig();
	@Test
	void contextLoads() throws Exception{  // given
		try {
			String key1 ="a45hvn";
			String key2 ="testkey2";
			RSACrypto aes1 = new RSACrypto();
			RSACrypto aes2 = new RSACrypto();
			
			String plain = "1q2w3e4r5t6y7u";
			
			String encAes1 = aes1.encrypt(plain);
			String encAes2 = aes2.encrypt(plain);
			
			System.out.println("암호화 테스트");
			System.out.println("aes1 : "+ encAes1);
			System.out.println("aes2 : "+ encAes2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
