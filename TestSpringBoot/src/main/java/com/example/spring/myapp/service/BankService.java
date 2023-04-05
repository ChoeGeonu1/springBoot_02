package com.example.spring.myapp.service;

import java.util.List;

import com.example.spring.BankDto;
import com.example.spring.dividend.dto.DividendDto;
import com.example.spring.paging.Pagination;

public interface BankService {
	// 페이징을 위한 카운트
	public int getListCount();
	
	// 페이징 목록 조회
	public List<BankDto> getListPage(Pagination paginationVo);
}
