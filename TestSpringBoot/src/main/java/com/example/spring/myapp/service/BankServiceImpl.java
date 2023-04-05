package com.example.spring.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.BankDto;
import com.example.spring.dividend.dto.DividendDto;
import com.example.spring.myapp.model.mapper.BankMapper;
import com.example.spring.myapp.model.mapper.DividendMapper;
import com.example.spring.paging.Pagination;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	BankMapper  bankMapper;
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

}
