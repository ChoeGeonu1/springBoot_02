package com.example.spring.myapp.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 배당금 테이블
 */
@Data
@ToString
public class DividendDto extends MemberDto {
	private int turn; 
	private String yearmonth; // 년도
	private String clsfc_Code = "";    //구분코드
	private String category; //항목
	private String january; //1월
	private String february; //2월
	private String march; //3월
	private String april; //4월
	private String may; //5월
	private String june; //6월
	private String july; //7월
	private String august; //8월 
	private String september; //9월
	private String october; //10월
	private String november; //11월
	private String december; //12월
	
	

	
	
}
