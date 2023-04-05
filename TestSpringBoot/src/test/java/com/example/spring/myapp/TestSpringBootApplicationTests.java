package com.example.spring.myapp;


import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.spring.BankDto;
import com.example.spring.encoder.Encrypt;
import com.example.spring.encoder.RSACrypto;
import com.example.spring.encoder.WebSecurityConfig;
import com.example.spring.myapp.service.BankService;
import com.example.spring.paging.Pagination;

@SpringBootTest
class TestSpringBootApplicationTests {

	@Resource
	private BankService bankService;
	
	@Test
	void contextLoads() throws Exception{  // given
		int page = 2;
		try {
			Pagination paginationVo = new Pagination(bankService.getListCount(), page);
			paginationVo.setPage(page);
			System.out.println(paginationVo.toString());
			/*
			 * List<BankDto> listPage = bankService.getListPage(paginationVo);
			 * System.out.println(listPage.size()); for (int i =0; i < listPage.size(); i++)
			 * { System.out.println(listPage.get(i)); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
