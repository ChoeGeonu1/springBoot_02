package com.example.spring.myapp.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring.myapp.dto.PublicSearchRequestDto;
import com.example.spring.myapp.dto.PublicSearchResultDto;
import com.example.spring.myapp.service.common.CommonItemService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RestController(value = "/aop")
@Slf4j
public class SpringBootAopController {

	// 공통 항목
	@Resource
	private CommonItemService commonItemService;
	
	// 전자공시 관련 화면
	@RequestMapping(value = "/electronicDisclosure")
	public ModelAndView electronicDisclosureView(Map<String, Object> map) throws Exception {
		log.info("전자 공시 화면");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("bank/electronicDisclosure");
		return mav;
	}
	@GetMapping("/electronicDisclosureCall" )
	public ModelAndView electronicDisclosureCall(@RequestParam("corp_name") String corp_name) {
		ModelAndView mav = new ModelAndView();
		log.info("검색 호출 " + corp_name);
		PublicSearchResultDto pDto = new PublicSearchResultDto();
		pDto.setCorp_name(corp_name);
		List<PublicSearchRequestDto> requestList =commonItemService.disclosureInformationCall(pDto); 
		log.info("requestList  " + requestList.toString());
		mav.addObject("requestList",requestList);
		mav.setViewName("bank/electronicDisclosure");
		return mav;
	}
}
