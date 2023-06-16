package com.example.spring.myapp.service.dividend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.myapp.dto.DividendDto;
import com.example.spring.myapp.dto.DividendLogDto;
import com.example.spring.myapp.mapper.DividendMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DividendServiceImpl implements DividendService {

	@Autowired
	private DividendMapper dividendMapper;

	/**
	 * @author CafeAlle 배당금 조회
	 *
	 */
	@Override
	public List<DividendDto> dividendList(DividendDto diDto) {
		return dividendMapper.dividendList(diDto);
	}

	/**
	 * @author CafeAlle 배당금 등록
	 *
	 */
	@Override
	public String dividendInsert(DividendDto diDto) {
		log.info("배당금 등록/수정");
		String str = "실패";
		try {
			 if (dividendMapper.dividendInsert(diDto) != 0) {
				log.info("배당금 등록 인듯");
				return "성공";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * @author CafeAlle 배당금 날짜 조회
	 *
	 */
	@Override
	public List<DividendDto> yearmonthList(DividendDto diDto) {
		return dividendMapper.yearmonthList(diDto);
	}

	/**
	 * @author CafeAlle 배당금 등록,수정,삭제, 로그 적재
	 *
	 */
	@Override
	public String dividendLogInsert(DividendDto diDto) {
		DividendLogDto dLogDto = new DividendLogDto();
				
		String str = "실패";
		dLogDto.setClsfc_Code(diDto.getClsfc_Code());// 구분코드
		dLogDto.setYearmonth(diDto.getYearmonth());// 구분코드
		dLogDto.setCategory(diDto.getCategory());// 항목
		dLogDto.setJanuary(diDto.getJanuary());// 1월
		dLogDto.setFebruary(diDto.getFebruary());// 2월
		dLogDto.setMarch(diDto.getMarch());// 3월
		dLogDto.setApril(diDto.getApril());// 5월
		dLogDto.setMay(diDto.getMay());// 6월
		dLogDto.setJune(diDto.getJune());// 7월
		dLogDto.setJuly(diDto.getJuly());// 8월
		dLogDto.setAugust(diDto.getAugust());// 9월
		dLogDto.setSeptember(diDto.getSeptember());// 10월
		dLogDto.setNovember(diDto.getNovember());// 11월
		dLogDto.setDecember(diDto.getDecember());// 12월
		dLogDto.setId(diDto.getId());// 등록자
		
		try {
			switch (dLogDto.getClsfc_Code()) {
			case "I":
				dividendMapper.dividendInsert(diDto);
				str = "등록";
				break;
			case "U":
				str = "수정";
				dividendMapper.dividendUpdate(diDto);
				break;
			default:
				break;
			}
			// 로그 적재
			dividendMapper.dividendLogInsert(dLogDto);
		} catch (Exception e) {
			log.error(""+e.toString());
		}
		return str;
	}

	/**
	 * @author CafeAlle
	 * 배당금 로그 조회
	 *
	 */
	@Override
	public List<DividendLogDto> DIVIDEND_LOG_SELECT() {
		return dividendMapper.DIVIDEND_LOG_SELECT();
	}

}
