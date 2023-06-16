package com.example.spring.myapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring.myapp.dto.DividendDto;
import com.example.spring.myapp.dto.MemberDto;
import com.example.spring.myapp.service.dividend.DividendService;
import com.example.spring.myapp.service.member.MemberService;
import com.example.spring.session.SessionConst;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class SpringBootController {

	// 배당금 관련
	@Resource
	private DividendService dividendService;

	// 회원 관련
	@Resource
	private MemberService memberService;
	
	// 메인 화면
		@RequestMapping(value = "/")
		public ModelAndView main(Map<String, Object> map) throws Exception {
			log.info("#### main : : : ");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("member/login");
			return mav;
		}

		// 로그인 화면
		@RequestMapping(value = "login")
		public ModelAndView loginView(Map<String, Object> map) throws Exception {
			log.info("#### login");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("member/login");
			return mav;
		}

		// 회원 가입 화면
		@RequestMapping(value = "account")
		public ModelAndView accountView(Map<String, Object> map) throws Exception {
			log.info("#### account");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("member/account");
			return mav;
		}
		
	
		// 로그인 하기
		@PostMapping("/login111")
		public MemberDto login111(MemberDto dto, HttpServletRequest request) {
			log.info("로그인 동작 중 " + dto.getId() + " : : : " + dto.getPwd());
			MemberDto login = memberService.login(dto);
			HttpSession session = request.getSession();
			log.info("로그인 정보 : : : "+ login.getId());
			session.setAttribute(SessionConst.LOGIN_MEMBER_ID, login.getId());
			return login;
		}
	// 로그인 하기
	@PostMapping("/login")
	public ModelAndView login(MemberDto dto, HttpServletRequest request) {
		log.info("로그인 동작 중 " + dto.getId() + " : : : " + dto.getPwd());
		ModelAndView mav = new ModelAndView();
		MemberDto login = memberService.login(dto);
		HttpSession session = request.getSession();
		log.info("로그인 정보 : : : "+ login.getId());
		session.setAttribute(SessionConst.LOGIN_MEMBER_ID, login.getId());
		mav.setViewName("redirect:/dividend");
		return mav;
	}
		//로그아웃
		@GetMapping("/logout")
		public ModelAndView logout(HttpServletRequest request) throws Exception{
			System.out.println("로그아웃");
			 HttpSession session = request.getSession();
			 session.invalidate();
			 ModelAndView mav = new ModelAndView();
			 mav.setViewName("redirect:/");
			 return mav;
		}

	
}
