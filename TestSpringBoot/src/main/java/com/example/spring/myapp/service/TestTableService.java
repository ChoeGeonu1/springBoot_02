package com.example.spring.myapp.service;

import java.util.List;
import java.util.Map;

import com.example.spring.dividend.dto.DividendDto;
import com.example.spring.paging.Pagination;

public interface TestTableService {
	
	//select * from Test_Table
	public List<Map<String, Object>> SelectAllList() throws Exception;
	

}
