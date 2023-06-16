package com.example.spring.myapp.dto;

import lombok.Data;

@Data
// 공시 검색 요청
public class PublicSearchResultDto {
	private String crtfc_key; //API 인증키
	private String corp_code; // 고유번호
	private String corp_name; // 고유회사명
	private String bgn_de; //시작일
	private String end_de; // 종료일
	private String last_reprt_at; //최종보고서 검색 여부
	private String pblntf_ty; // 공시유형
	private String pblntf_detail_ty; //공시상세유형
	private String corp_cls; //법인구분
	private String sort; //정렬
	private String sort_mth; //정렬방법
	private String page_no; //페이지 번호
	private String page_count; //페이지 별 건수
}

