package com.example.spring.myapp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.spring.myapp.dto.DividendDto;
import com.example.spring.myapp.dto.DividendLogDto;
import com.example.spring.myapp.service.bank.BankService;
import com.example.spring.myapp.service.common.CommonItemService;
import com.example.spring.myapp.service.dividend.DividendService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class TestSpringBootApplicationTests {
	
	@Autowired
	public CommonItemService CommonItemService;
	

	// 은행 관련
	@Resource
	private BankService bankService;
	
	@Autowired
	private DividendService dividendService;

	
	@Test
	void contextLoads() throws Exception{  // given
		DividendLogDto diDto = new DividendLogDto();
		try {
			diDto.setYearmonth("999912");
			diDto.setClsfc_Code("D");
			diDto.setCategory("테스트항목");
			diDto.setJanuary("99");
			log.info("결과 : " + dividendService.DIVIDEND_LOG_SELECT());
			//log.info("결과 : : " + bankService.stockManageSelect());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
