package com.example.spring.myapp.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.spring.BankDto;
import com.example.spring.paging.Pagination;
/**
 * @author CafeAlle
 *
 */
@Mapper
public interface BankMapper {
	/*****
	 * 페이징 처리를 위한 카운트
	 *****/	
	public int getListCount();
	
	/*****
	 * 페이징 목록 조회 
	 *****/
	public List<BankDto> getListPage(Pagination paginationVo);
}
