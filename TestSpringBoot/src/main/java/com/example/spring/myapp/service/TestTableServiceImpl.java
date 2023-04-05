package com.example.spring.myapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.dividend.dto.DividendDto;
import com.example.spring.myapp.model.mapper.DividendMapper;
import com.example.spring.myapp.model.mapper.TestTableMapper;
import com.example.spring.paging.Pagination;

@Service
public class TestTableServiceImpl implements TestTableService{

	@Autowired
	TestTableMapper testtableMapper;
	
	@Autowired
	DividendMapper dividendMapper;
	
	@Override
	public List<Map<String, Object>> SelectAllList() throws Exception {
		// TODO Auto-generated method stub
		return testtableMapper.SelectAllList();
	}
	

}
