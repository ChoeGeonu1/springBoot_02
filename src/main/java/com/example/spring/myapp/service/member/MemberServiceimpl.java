package com.example.spring.myapp.service.member;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring.encoder.Encrypt;
import com.example.spring.encoder.RSACrypto;
import com.example.spring.myapp.dto.MemberDto;
import com.example.spring.myapp.mapper.MemberMapper;

@Log
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
			log.info("pwd  " + dto.getPwd());
			dto.setPwd(rsa.encrypt(dto.getPwd()));
			log.info("pwd  " + rsa.encrypt(dto.getPwd()));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return memberMapper.login(dto);
		//return  null;
	}
	
	@Override
	public String selectList1111() {
		//System.out.println("테스트셑스트트트트트트트트");
		return memberMapper.selectList1111();
	}

}
