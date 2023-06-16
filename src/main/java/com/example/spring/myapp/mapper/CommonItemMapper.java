package com.example.spring.myapp.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.spring.myapp.dto.CommonItemDto;
import com.example.spring.myapp.dto.CorpCdoeDto;
import com.example.spring.myapp.dto.PublicSearchResultDto;


/**
 * @author CafeAlle
 *
 */
@Mapper
public interface CommonItemMapper {
	/*****
	 * 거래내역 목록 저장
	 *****/
	public int commonItemTableInsert(CommonItemDto cDto);
	
	
	/*****
	 * 거래내역 목록 조회 
	 *****/
	public CommonItemDto selectList(String name);
	

	/*****
	 * xml 파일 저장
	 *****/
	public int corpCodeInsert(CorpCdoeDto cDto);
	
	/*****
	 * 데이터 삭제
	 *****/
	public int corpCodeDelete();
	
	/*****
	 * 회사명으로 고유 번호 조회 
	 *****/
	public PublicSearchResultDto corpCodeSelect(PublicSearchResultDto pDto);
	
}
