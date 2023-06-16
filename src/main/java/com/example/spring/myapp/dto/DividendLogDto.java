package com.example.spring.myapp.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 배당금 로그 테이블
 */
@Data
@ToString
public class DividendLogDto {
	private String registration_Date = "";  //등록일자
	private String registered_Time = "";    //등록시간
	private String clsfc_Code = "";    		//구분코드
	private String yearmonth = "";    		//등록년월
	private String category = "";    		//항목
	private String january = "";    		//1월
	private String february = "";    		//2월
	private String march = "";    			//3월
	private String april = "";    			//4월
	private String may = "";    			//5월
	private String june = "";    			//6월
	private String july = "";    			//7월
	private String august = "";    			//8월
	private String september = "";    		//9월
	private String october = "";    		//10월
	private String november = "";    		//11월
	private String december = "";    		//12월
	private String id = "";    				//등록자
}
