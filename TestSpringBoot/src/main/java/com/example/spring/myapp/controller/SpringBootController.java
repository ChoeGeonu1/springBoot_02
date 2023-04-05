package com.example.spring.myapp.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring.BankDto;
import com.example.spring.mumber.dto.MemberDto;
import com.example.spring.myapp.service.BankService;
import com.example.spring.myapp.service.MemberService;
import com.example.spring.paging.Pagination;

@RestController
public class SpringBootController {
	//@Resource
	//private TestTableService testtableService;
	
	@Resource
	private MemberService memberService;

	@Resource
	private BankService bankService;
	
	@RequestMapping(value="index")
	public ModelAndView indexView(Map<String, Object> map) throws Exception{
		System.out.println("#### index");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}
	
	//로그인 화면
	@RequestMapping(value="login")
	public ModelAndView loginView(Map<String, Object> map) throws Exception{
		System.out.println("#### login");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	
	//회원 가입 화면
	@RequestMapping(value="account")
	public ModelAndView accountView(Map<String, Object> map) throws Exception{
		System.out.println("#### account");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("account");
		return mav;
	}
	
	@RequestMapping(value="list")
	public ModelAndView AllListView(Map<String, Object> map ,@RequestParam(value = "page", defaultValue = "1") final int page) throws Exception{
		System.out.println("#### list");
		ModelAndView mav = new ModelAndView();
		
		Pagination paginationVo = new Pagination(bankService.getListCount(), page); // 모든 게시글 개수 구하기.
		paginationVo.setPage(page);
		List<BankDto> listPage = bankService.getListPage(paginationVo);
		
		//List<Map<String, Object>> AllList = testtableService.SelectAllList();
		//System.out.println(AllList);
		
		mav.addObject("Alllist", listPage);
		mav.addObject("page", page);
		mav.addObject("pageVo", paginationVo);
		mav.setViewName("list");
		return mav;
	}
	
	// 아이디 확인
    @PostMapping("/getId")
    public String getId(MemberDto dto) {
    	System.out.println("로그인 아이디 확인");
        boolean b = memberService.getId(dto);
        if(b) {
            return "no";
        }

        return "ok";

    }
    
    // 회원가입 저장
    @PostMapping("/addMember")
    public String addMember(MemberDto dto) {
    	System.out.println("회원 가입 저장");
        boolean b = memberService.addMember(dto);
        if(b) {
            return "ok";
        }
        return "no";
    }
    
    //로그인 하기
    @PostMapping("/login")
    public MemberDto login(MemberDto dto) {
    	System.out.println("로그인 동작");
        return memberService.login(dto);
    }
	
}
