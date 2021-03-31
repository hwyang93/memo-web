package memoWeb.web.main.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import memoWeb.web.main.service.MemberService;
import memoWeb.web.main.service.MemberVO;

@Controller
@RequestMapping("/api/main")
public class MainContorller {
	
	private static MemberService memberService;
	
	@Autowired
	public MainContorller(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@PostMapping("login.do")
	public String login(Model model, @RequestBody MemberVO member) {
		MemberVO result = memberService.login(member.getUserId(), member.getUserPassword());
		
		model.addAttribute("result", result);
		return "jsonView";
	}
	
	@PostMapping("signUp.do")
	public String signUp(Model model, @RequestBody MemberVO member) {
		MemberVO result = memberService.signUp(member);
		
		model.addAttribute("result", result);
		return "jsonView";
	}
}
