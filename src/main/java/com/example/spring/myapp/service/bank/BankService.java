package com.example.spring.myapp.service.bank;

import java.util.List;
import java.util.Map;

import com.example.spring.myapp.dto.BankDto;
import com.example.spring.myapp.dto.BankRackDetailsTableDto;
import com.example.spring.myapp.dto.DividendDto;
import com.example.spring.myapp.dto.StockManage;
import com.example.spring.paging.Pagination;

public interface BankService {
	// 페이징을 위한 카운트
	public int getListCount();
	
	// 페이징 목록 조회
	public List<BankDto> getListPage(Pagination paginationVo);
	
	/*****
	 * 은행 거랙 내역 저장 
	 *****/
	public int bankRackDetailsTableInsert(List<Map<String, Object>> param);
	
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
	public String stockManageInsert(StockManage bDto);

	/*****
	 * 목록 조회
	 *****/
	public List<StockManage> stockManageSelect();

	/*****
	 * 주식 수정
	 *****/
	public String stockManageUpdate(StockManage map);
}
