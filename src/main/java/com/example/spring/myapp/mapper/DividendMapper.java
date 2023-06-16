package com.example.spring.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.spring.myapp.dto.DividendDto;
import com.example.spring.myapp.dto.DividendLogDto;

/**
 * @author CafeAlle
 *
 */
@Mapper
public interface DividendMapper {
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
	public int dividendInsert(DividendDto diDto);
	
	/**
	 * @author CafeAlle
	 * 배당금 날짜
	 *
	 */
	public List<DividendDto> yearmonthList(DividendDto diDto);
	
	/**
	 * @author CafeAlle
	 * 배당금 수정
	 *
	 */
	public int dividendUpdate(DividendDto diDto);

	/**
	 * @author CafeAlle
	 * 배당금 등록
	 *
	 */
	public int dividendLogInsert(DividendLogDto diDto);

	/**
	 * @author CafeAlle
	 * 배당금 로그 조회
	 *
	 */
	public List<DividendLogDto> DIVIDEND_LOG_SELECT();
}
