package com.example.spring.myapp.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring.myapp.dto.BankRackDetailsTableDto;
import com.example.spring.myapp.dto.CommonItemDto;
import com.example.spring.myapp.dto.DividendDto;
import com.example.spring.myapp.dto.MemberDto;
import com.example.spring.myapp.dto.StockManage;
import com.example.spring.myapp.service.bank.BankService;
import com.example.spring.myapp.service.common.CommonItemService;
import com.example.spring.myapp.service.dividend.DividendService;
import com.example.spring.paging.Pagination;
import com.example.spring.session.SessionConst;

import lombok.extern.slf4j.Slf4j;

@Controller
@RestController(value = "/bank")
@Slf4j
public class SpringBootBankController {

	// 배당금 관련
	@Resource
	private DividendService dividendService;
	
	// 은행 관련
	@Resource
	private BankService bankService;
	
	// 공통 항목 조회
	@Resource
	private CommonItemService commonItemService;
	
	// 배당금 조회
	@RequestMapping(value = "/dividend")
	public ModelAndView dividendView(
			@RequestParam(value = "yearmonth", defaultValue = "") final String yearmonth,
			Map<String, Object> map, DividendDto dividendDto , HttpServletRequest request,
			@SessionAttribute(name = SessionConst.LOGIN_MEMBER_ID, required = false) Object loginMember) throws Exception {
		//log.info("#### dividend 조회  " + yearmonth);
		ModelAndView mav = new ModelAndView();
		dividendDto.setYearmonth(yearmonth);
		dividendDto.setId(String.valueOf(loginMember));
		List<DividendDto> divList = dividendService.dividendList(dividendDto);
		List<DividendDto> yearmonthList = dividendService.yearmonthList(dividendDto);
		mav.addObject("dAllList", divList);
		mav.addObject("yearmonthList", yearmonthList);
		mav.setViewName("bank/dividend");
		return mav;
	}
	
	// 배당금 등록
	@PostMapping("/dividendLogInsert.do")
	public String dividendLogInsert(DividendDto ddto, 
			@SessionAttribute(name = SessionConst.LOGIN_MEMBER_ID, required = false) Object loginMember) {
		ddto.setId(String.valueOf(loginMember));
		log.info("결과 : : : : : : ; "  + ddto.toString());
		//return "";
		return dividendService.dividendLogInsert(ddto);
	}
	
	// 배당금 차트 목록 조회
	@GetMapping(value = "/dividendChart.do")
	public List<DividendDto> dividendChartSelete(
			@RequestParam(value = "yearmonth", defaultValue = "") final String yearmonth,
			@SessionAttribute(name = SessionConst.LOGIN_MEMBER_ID, required = false) Object loginMember) throws Exception {
		//log.info("#### dividendChart.do 조회  ");
		DividendDto dividendDto = new DividendDto();
		dividendDto.setYearmonth(yearmonth);
		dividendDto.setYearmonth(String.valueOf(loginMember));
		List<DividendDto> divList = dividendService.dividendList(dividendDto);
		divList.add(dividendDto);
		return divList;
	}
	
	// 은행 거래 내역
		@GetMapping(value = "bankRackDetailsList")
		public ModelAndView bankRackDetailView(Map<String, Object> map,
				@RequestParam(value = "page", defaultValue = "1") final int page, 
				@RequestParam(value = "item_Name", defaultValue = "테스트")  String item_Name,
				@SessionAttribute(name = SessionConst.LOGIN_MEMBER_ID, required = false) Object loginMember,
				@RequestParam(value = "yearmonth", defaultValue = "null") final String yearmonth) throws Exception {
			ModelAndView mav = new ModelAndView();
			Pagination paginationVo = new Pagination(bankService.getbankRackDetaiCnt(item_Name), page); // 모든 게시글 개수 구하기.
			paginationVo.setPage(page);
			paginationVo.setItem_Name(item_Name);// 검색 조건
			List<BankRackDetailsTableDto> listPage = bankService.bankRackDetailsList(paginationVo);
			CommonItemDto comDto = commonItemService.selectList(item_Name);
			mav.addObject("Alllist", listPage);
			mav.addObject("comVo", comDto);
			mav.addObject("page", page);
			mav.addObject("pageVo", paginationVo);
			
			mav.setViewName("bank/bankRackDetailsList");
			return mav;
		}
		
		// 엑셀 업로드 화면
		@RequestMapping(value = "/excelUpload")
		public ModelAndView excelUploadView(Map<String, Object> map , HttpServletRequest requst) throws Exception {
			//log.info("엑셀 업로드 화면");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("bank/excelUpload");
			return mav;
		}
		
		// 엑셀 거래내역 등록
		@ResponseBody
		@PostMapping(value = "/excelUpload.do")
		public String excelUploadInsert(@RequestBody List<Map<String, Object>> param) throws Exception {
			//log.info("은행 거랙 내역 등록 " );
			for (Map<String, Object> str : param) {
				log.info(": : :  " + str.toString());
			}
			int commCnt = commonItemService.commonItemTableInsert(param);
			int bankCnt = bankService.bankRackDetailsTableInsert(param);
			return String.valueOf(commCnt+bankCnt);
		}
		
		// 주식 수 화면
		@GetMapping("/stockManage")
		public ModelAndView stockManageView(Model model , StockManage stockManage,
				@SessionAttribute(name = SessionConst.LOGIN_MEMBER_ID, required = false) Object loginMember) {
			log.info("값 : : : " + loginMember);
			ModelAndView mv = new ModelAndView();
			mv.addObject("stockManage", stockManage);
			//mv.addObject(SessionConst.LOGIN_MEMBER_ID, loginMember);
			mv.addObject("stockManageDto", bankService.stockManageSelect());
			mv.setViewName("bank/stockManage");
			return mv;
		}
		// 주식 수 수정		
		@PostMapping("/stockManageInsert.do")
		public ModelAndView stockManageInsert(@ModelAttribute("stockManage") StockManage stockManage) {
			ModelAndView mv = new ModelAndView();
			log.info("결과 : : : " + toString());
			mv.addObject("msg",bankService.stockManageInsert(stockManage));
			mv.addObject("resultUrl","/stockManage");
			mv.setViewName("msg/message");
			//mav.addObject("msg", dividendService.dividendInsert(ddto));
			//mav.addObject("resultUrl", "/dividend");
			//mav.setViewName("msg/message");
			return mv;
		}
		// 주식 수정
		@PostMapping("/stockManageUpdate.do")
		public String stockManageUpdate(StockManage map) {
			log.info("주식 수정    " + map.toString());
			//mv.addObject("msg",bankService.stockManageUpdate(map));
			//mv.addObject("resultUrl","/stockManage");
			//mv.setViewName("msg/message");
			return bankService.stockManageUpdate(map);
		}
		
		
		
	
}
