package com.example.spring.myapp;

import com.example.spring.encoder.RSACrypto;

public class encrypt {
    public static void main(String[] args) {
        try {

            RSACrypto rsa = new RSACrypto();
            System.out.println(rsa.encrypt("admin"));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
