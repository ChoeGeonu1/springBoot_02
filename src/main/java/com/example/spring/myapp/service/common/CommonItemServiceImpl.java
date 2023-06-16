package com.example.spring.myapp.service.common;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.spring.common.CompressUtil;
import com.example.spring.common.ExternalAPIServiceCall;
import com.example.spring.common.UnZip;
import com.example.spring.myapp.dto.BankRackDetailsTableDto;
import com.example.spring.myapp.dto.CommonItemDto;
import com.example.spring.myapp.dto.CorpCdoeDto;
import com.example.spring.myapp.dto.PublicSearchRequestDto;
import com.example.spring.myapp.dto.PublicSearchResultDto;
import com.example.spring.myapp.mapper.CommonItemMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommonItemServiceImpl implements CommonItemService {

	
	// 공통 항목 관련
	@Autowired
	private CommonItemMapper  commonItemMapper;
	
	@Value("${common.crtfc_key}")
	private String crtfc_key;
	
	@Value("${common.spec.xml}")
	private String spec_xml;
	
	@Value("${common.spec.json}")
	private String spec_json;
	
	@Value("${common.outputDir}")
	private String outputDir;

	CompressUtil compressUtil = new CompressUtil();
	
	ExternalAPIServiceCall externalAPIServicecall = new ExternalAPIServiceCall();
	
	/*****
	 * 거래내역 등록 조회 
	 *****/
	@Override
	public int commonItemTableInsert(List<Map<String, Object>> paMap) {
		ObjectMapper objectMapper_01 = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		ObjectMapper objectMapper_02 = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		int cnt = 0;
		if (0 == paMap.size()) {
			return 0;
		}
		Map<String, Object> map = paMap.get(0);
		CommonItemDto commVO = new CommonItemDto(); 
		commVO = objectMapper_01.convertValue(map, CommonItemDto.class);
		log.info("1. " + commVO.toString());
		log.info("2. " + paMap.size());
		BankRackDetailsTableDto bankVO =  new BankRackDetailsTableDto();
		
		for (int i = 0; i < 3; i++) {
			log.info("bankVO  " + paMap.get(i) );
			// [hashmap 객체 선언 및 데이터 삽입]
			Map<String, Object> hashmap = new HashMap<String, Object>(paMap.get(i));
			bankVO = objectMapper_02.convertValue(hashmap, BankRackDetailsTableDto.class);
				if (0 != bankVO.getBreakdown_01().length()) {
					bankVO.setItem_Name(bankVO.getBreakdown_01());
					commVO.setItem_Name(bankVO.getBreakdown_01());
				}
		}
		if (!"".equals(commonItemMapper.selectList(commVO.getItem_Name()))) {
			return 0;
		}
		cnt = commonItemMapper.commonItemTableInsert(commVO);
		return cnt;
	}
	
	/*****
	 * 거래내역 목록 조회 
	 *****/
	@Override
	public CommonItemDto selectList(String item_Name) {
		CommonItemDto  cDto = new CommonItemDto();
		cDto = commonItemMapper.selectList(item_Name);
		/*
		 * if ("".equals(item_Name) || null == item_Name) { item_Name = "테스트"; }
		 */
		/*
		 * if ("".equals(cDto) || null == cDto) { cDto =
		 * commonItemMapper.selectList("테스트"); }
		 */
		log.info("어떤게 나오나 " + commonItemMapper.selectList(item_Name));
		return cDto;
	}
	
	/*****
	 * xml 파일 저장
	 *****/
	@Override
	public int corpCodeInsert() {
		int cnt = 0;
		try {
			  // 현재 날짜 구하기
	        LocalDate now = LocalDate.now();
	        
	        // 포맷 정의
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	        
	        // 포맷 적용
	        String formatedNow = now.format(formatter);
	        
	        outputDir += "/"+formatedNow+"/";
	        
			UnZip unZi = new UnZip();
			//logger.info("시작 ");
			// 1. api 접근 후 파일 다운로드
			String fileDownload = externalAPIServicecall.FileDownload(crtfc_key,spec_xml,outputDir); // 파일명
			//logger.info("1. api 접근 후 파일 다운로드 완료 : : : " + fileDownload);
			// 2. api 접근 후 XML 파일 다운로드 후 압축 해제
			//compressUtil.testZipUnarchive(outputDir, formatedNow);
			String fileName2 =  unZi.unZip(outputDir, fileDownload,outputDir);
			//System.out.println(new File(outputDir+fileName2));
			//File zipFile = compressUtil.unCompressZip(outputDir);
			//logger.info("2. api 접근 후 XML 파일 다운로드 후 압축 해제 : : : " + zipFile);
			// 3. xml 파일 파싱 후 DB 저장
			if (!"".equals(fileName2) || fileName2 != null) {
				List<CorpCdoeDto> oDto = (List<CorpCdoeDto>) (Object) compressUtil.ReadXMLFile2(new File(outputDir+fileName2));
				if (0 != oDto.size()) {
					commonItemMapper.corpCodeDelete();
					for (int i = 0 ; i < oDto.size(); i++) {
						cnt += commonItemMapper.corpCodeInsert(oDto.get(i));
					}
				}
			}
			// object 형변화
			//List<Object> oDto = compressUtil.ReadXMLFile2(zipFile);
			//for (int i =0; i < oDto.size(); i++) {
			//	System.out.println(oDto.get(i).toString());
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public List<PublicSearchRequestDto> disclosureInformationCall(PublicSearchResultDto pDto) {
		PublicSearchResultDto publicDto = commonItemMapper.corpCodeSelect(pDto);
		//System.out.println("어떤 값이 나오나 : : "+publicDto.getCorp_code());
		  // 현재 날짜 구하기
        LocalDate now = LocalDate.now();
        // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        // 포맷 적용
        String formatedNow = now.format(formatter);
        
        if ("".equals(pDto.getBgn_de()) || null == pDto.getBgn_de()) {
        	pDto.setBgn_de("20230401");
		}
        if ("".equals(pDto.getEnd_de()) || null ==pDto.getEnd_de()) {
        	pDto.setEnd_de(formatedNow);
		}
		//https://opendart.fss.or.kr/api/list.json?crtfc_key={발급키}&corp_code={고시번호}&bgn_de={시작일자}&end_de={종료일자}&corp_cls={법인구분}
		//String url =  spec_json+crtfc_key+"&corp_code="+publicDto.getCorp_code()+"&bgn_de="+pDto.getBgn_de()+"&end_de="+pDto.getEnd_de();
        String url = "https://opendart.fss.or.kr/api/list.json?crtfc_key=xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx&corp_code=00434456&bgn_de=20200117&end_de=20200117&corp_cls=Y&page_no=1&page_count=10";
		//String str = externalAPIServicecall.ApiCaller2(url);
		List<PublicSearchRequestDto> list = externalAPIServicecall.ApiCaller2(url);
        //List<PublicSearchRequestDto> list = null;
		//System.out.println("결과 : "+str);
		/*
		 * for (PublicSearchRequestDto str : list) { System.out.println(str.toString());
		 * }
		 */
		return list;
	}

}
