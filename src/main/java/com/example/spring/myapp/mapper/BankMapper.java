package com.example.spring.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.spring.myapp.dto.BankDto;
import com.example.spring.myapp.dto.BankRackDetailsTableDto;
import com.example.spring.myapp.dto.StockManage;
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
	
	/*****
	 * 은행 거랙 내역 저장 
	 *****/
	public int bankRackDetailsTableInsert(BankRackDetailsTableDto bDto);

	/*****
	 * 목록 조회
	 *****/
	public List<BankRackDetailsTableDto> bankRackDetailsList(Pagination pDto);
	
	/*****
	 * 페이징 처리를 위한 카운트
	 *****/	
	public int getbankRackDetaiCnt(String pDto);
	
	/*****
	 * 주식 등록 
	 *****/
	public int stockManageInsert(StockManage bDto);
	

	/*****
	 * 목록 조회
	 *****/
	public List<StockManage> stockManageSelect();
	
	/*****
	 * 주식 수정
	 *****/
	public int stockManageUpdate(StockManage sDto);
	
}
