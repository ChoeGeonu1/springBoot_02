package com.example.spring.myapp.dto;

import lombok.Data;

// 고유번호 검색 테이블
@Data
public class CorpCdoeDto {
	private String corp_code; //고유번호
	private String corp_name; // 정식명칭
	private String stock_code; //종목코드
	private String modify_date; //최종변경일자
}
