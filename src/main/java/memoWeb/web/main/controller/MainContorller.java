package memoWeb.web.main.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import memoWeb.common.constant.CommonConstants;
import memoWeb.web.main.service.MainService;
import memoWeb.web.main.service.MemberService;
import memoWeb.web.main.service.MemberVO;
import memoWeb.web.main.service.UserScheduleVO;

@Controller
@RequestMapping("/api/main")
public class MainContorller {
	
	private static MemberService memberService;
	private static MainService mainService;
	
	@Autowired
	public MainContorller(MemberService memberService, MainService mainService) {
		this.memberService = memberService;
		this.mainService = mainService;
	}
	
	@PostMapping("login.do")
	public String login(Model model, @RequestBody MemberVO member, HttpSession session) {
		MemberVO result = null;
		result = memberService.login(member.getUserId(), member.getUserPassword());
		
		if (result != null) {
			session.setAttribute(CommonConstants.SESSION, result);
		}
		
		model.addAttribute("result", result);
		return "jsonView";
	}
	
	@PostMapping("signUp.do")
	public String signUp(Model model, @RequestBody MemberVO member) {
		MemberVO result = memberService.signUp(member);
		
		model.addAttribute("result", result);
		return "jsonView";
	}
	
	@PostMapping("saveUserSchedule.do")
	public String saveUserSchedule(Model model, @RequestBody UserScheduleVO userSchedule) {
		UserScheduleVO result = mainService.saveUserSchedule(userSchedule);
		
		model.addAttribute("result", result);
		return "jsonView";
	}
}
