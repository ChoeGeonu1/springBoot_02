package com.example.spring.myapp.dto;


import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.ToString;

// 공시 검색 응답
@Data
@ToString
public class PublicSearchRequestDto {
	
	@SerializedName("status")
	private String status;	//에러 및 정보 코드
	@SerializedName("message")
	private String message;	//	에러 및 정보 메시지
	@SerializedName("page_no")
	private String page_no;	//	페이지 번호
	@SerializedName("page_count")
	private String page_count;	//	페이지 별 건수
	@SerializedName("total_count")
	private String total_count;	//	총 건수
	@SerializedName("total_page")
	private String total_page;	//	총 페이지 수
	@SerializedName("corp_cls")
	private String corp_cls;	//	법인구분 : Y(유가), K(코스닥), N(코넥스), E(기타)
	@SerializedName("corp_name")
	private String corp_name;	//	공시대상회사의 종목명(상장사) 또는 법인명(기타법인)
	@SerializedName("corp_code")
	private String corp_code;	//	고유번호	공시대상회사의 고유번호(8자리)
	@SerializedName("stock_code")
	private String stock_code;	//	종목코드	상장회사의 종목코드(6자리)
	@SerializedName("report_nm")
	private String report_nm;	//	보고서명
	@SerializedName("rcept_no")
	private String rcept_no;	//	접수번호 접수번호(14자리)
	@SerializedName("flr_nm")
	private String flr_nm;	//	공시 공시 제출인명
	@SerializedName("rcept_dt") 
	private String rcept_dt;	//	접수일자 공시 접수일자(YYYYMMDD)
	@SerializedName("rm")
	private String rm;	//	비고 조합된 문자로 각각은 아래와 같은 의미가 있음

}

