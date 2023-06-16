package com.example.spring.myapp.service.common;

import java.util.List;
import java.util.Map;

import com.example.spring.myapp.dto.CommonItemDto;
import com.example.spring.myapp.dto.PublicSearchRequestDto;
import com.example.spring.myapp.dto.PublicSearchResultDto;


public interface CommonItemService {
	/*****
	 * 거래내역 목록 조회 
	 *****/
	public int commonItemTableInsert(List<Map<String, Object>> paMap);
	
	/*****
	 * 거래내역 목록 조회 
	 *****/
	public CommonItemDto selectList(String name);
	
	/*****
	 * xml 파일 저장
	 *****/
	public int corpCodeInsert();
	
	/*****
	 * 공시 정보 요청/응답
	 *****/
	public List<PublicSearchRequestDto> disclosureInformationCall(PublicSearchResultDto pDto) ;
}
