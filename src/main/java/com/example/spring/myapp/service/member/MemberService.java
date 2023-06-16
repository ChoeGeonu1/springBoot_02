package com.example.spring.myapp.service.member;

import com.example.spring.myapp.dto.MemberDto;

public interface MemberService  {
	// 아이디 중복 확인
	public boolean getId(MemberDto dto);
	// 회원가입
	public boolean addMember(MemberDto dto);
	// 로그인
	public MemberDto login(MemberDto dto);
	
	public String selectList1111();
}
