package com.example.spring.myapp.service.dividend;

import java.util.List;

import com.example.spring.myapp.dto.DividendDto;
import com.example.spring.myapp.dto.DividendLogDto;


public interface DividendService {
	/**
	 * @author CafeAlle
	 * 배당금 조회
	 *
	 */
	public List<DividendDto> dividendList(DividendDto paginationVo);

	/**
	 * @author CafeAlle
	 * 배당금 등록
	 *
	 */
	public String dividendInsert(DividendDto diDto);
	
	/**
	 * @author CafeAlle
	 * 배당금 날짜
	 *
	 */
	public List<DividendDto> yearmonthList(DividendDto diDto);

	/**
	 * @author CafeAlle
	 * 배당금 등록
	 *
	 */
	public String dividendLogInsert(DividendDto diDto);
	/**
	 * @author CafeAlle
	 * 배당금 로그 조회
	 *
	 */
	public List<DividendLogDto> DIVIDEND_LOG_SELECT();
}
