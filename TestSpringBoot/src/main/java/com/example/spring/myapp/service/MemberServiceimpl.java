package com.example.spring.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring.encoder.Encrypt;
import com.example.spring.encoder.RSACrypto;
import com.example.spring.mumber.dto.MemberDto;
import com.example.spring.myapp.model.mapper.MemberMapper;

@Service
public class MemberServiceimpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;

	Encrypt en = new Encrypt();
	

//	WebSecurityConfig webSecurityConfig = new WebSecurityConfig();

	// 아이디 확인
	@Override
	public boolean getId(MemberDto dto) {
		int n = memberMapper.getId(dto);
		return n > 0;
	}
	// 회원가입
	@Override
	public boolean addMember(MemberDto dto) {
		int n = 0;
		try {
			RSACrypto rsa = new RSACrypto();
			dto.setPwd(rsa.encrypt(dto.getPwd()));
			/*
			 * System.out.println("dto " + dto.getAuth()); System.out.println("dto " +
			 * dto.getEmail()); System.out.println("dto " + dto.getId());
			 * System.out.println("dto " + dto.getPwd());
			 */
			n = memberMapper.addMember(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	// 로그인
	@Override
	public MemberDto login(MemberDto dto) {

		try {
			RSACrypto rsa = new RSACrypto();
			dto.setPwd(rsa.encrypt(dto.getPwd()));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return memberMapper.login(dto);
	}

}
