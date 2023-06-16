/**
 * 
 */
package com.example.spring.myapp.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.example.spring.myapp.dto.MemberDto;

/**
 * @author CafeAlle
 */
@Mapper
public interface MemberMapper {
	
	// 아이디 중복 확인
	public int getId(MemberDto dto);
	
	// 회원가입
	public int addMember(MemberDto dto);
	
	// 로그인
	public MemberDto login(MemberDto dto);
	
	public String selectList1111();
	
}
