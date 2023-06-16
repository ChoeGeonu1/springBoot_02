package com.example.spring.myapp.service.bank;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.myapp.dto.BankDto;
import com.example.spring.myapp.dto.BankRackDetailsTableDto;
import com.example.spring.myapp.dto.DividendDto;
import com.example.spring.myapp.dto.StockManage;
import com.example.spring.myapp.mapper.BankMapper;
import com.example.spring.paging.Pagination;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BankServiceImpl implements BankService {

	
	@Autowired
	private BankMapper  bankMapper;
	/****
	* 페이징을 위한 카운트
	***/
	@Override
	public int getListCount() {
		return bankMapper.getListCount();
	}
	/****
	* 페이징 목록 조회
	***/
	@Override
	public List<BankDto> getListPage(Pagination paginationVo) {
		return bankMapper.getListPage(paginationVo);
	}
	
	/*****
	 * 은행 거랙 내역 저장 
	 *****/
	@Override
	public int bankRackDetailsTableInsert(List<Map<String, Object>> param) {
		ObjectMapper objectMapper_01 = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		int cnt = 0;
		BankRackDetailsTableDto bankVO =  new BankRackDetailsTableDto();
		if (0 == param.size()) {
			return 0;
		}
		try {
			for (int i = 0; i < param.size(); i++) {
				Map<String, Object> hashmap = new HashMap<String, Object>(param.get(i));
				bankVO = objectMapper_01.convertValue(hashmap, BankRackDetailsTableDto.class);
					bankVO.setItem_Name(bankVO.getBreakdown_01());
						if (0 != bankVO.getBreakdown_01().length()) {
						cnt = bankMapper.bankRackDetailsTableInsert(bankVO);
						}
					
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return cnt;
	}
	
	/*****
	 * 목록 조회
	 *****/
	
	@Override
	public List<BankRackDetailsTableDto> bankRackDetailsList(Pagination pDto) {
		//logger.info("모가 나오나 " + pDto.toString());
		List<BankRackDetailsTableDto> listBank = bankMapper.bankRackDetailsList(pDto);
		
		/*
		 * if (0 == listBank.size()) { pDto.setItem_Name("테스트"); listBank =
		 * bankMapper.bankRackDetailsList(pDto); }
		 */
		
		/*
		 * if ("".equals(pDto.getItem_Name()) || null == pDto.getItem_Name()) {
		 * pDto.setItem_Name("테스트"); }
		 */
		return listBank;
	}
	
	/*****
	 * 목록 조회
	 *****/
	
	@Override
	public int getbankRackDetaiCnt(String item_Name) {
		/*
		 * if ("".equals(item_Name) || null == item_Name) { item_Name = "테스트"; }
		 */
		//logger.info("어디서 오류 나나 2 " + bankMapper.getbankRackDetaiCnt(item_Name));
		return bankMapper.getbankRackDetaiCnt(item_Name);
	}

	/*****
	 * 주식 등록 
	 *****/
	@Override
	public String stockManageInsert(StockManage bDto) {
		String str = "실패";
		try {
			if (0 != bankMapper.stockManageInsert(bDto)) {
				str = "성공";
			}
		} catch (Exception e) {
			e.printStackTrace();
			str = "실패";
		}
		return str;
	}
	
	/*****
	 * 목록 조회
	 *****/
	@Override
	public List<StockManage> stockManageSelect() {
		return bankMapper.stockManageSelect();
	}

	/*****
	 * 주식 수정
	 *****/
	@Override
	public String stockManageUpdate(StockManage map) {
		int cnt = bankMapper.stockManageUpdate(map);
		if (0 != cnt) {
			return "성공";
		}
		return "실패";
	}

}
