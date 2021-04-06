package memoWeb.web.main.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import memoWeb.common.constant.CommonConstants;
import memoWeb.web.main.service.MainService;
import memoWeb.web.main.service.MemberService;
import memoWeb.web.main.domain.MemberVO;
import memoWeb.web.main.domain.UserScheduleVO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/api/main")
public class MainContorller {
	
	private static MainService mainService;
	private static final Logger logger = LoggerFactory.getLogger(MainContorller.class);
	@Autowired
	public MainContorller(MainService mainService) {
		this.mainService = mainService;
	}

	@PostMapping("login.do")
	public String login(Model model, @RequestBody MemberVO member, HttpSession session) {
		MemberVO result = null;
		result = mainService.login(member);
		if (result != null) {
			session.setAttribute(CommonConstants.SESSION, result);
		}
		
		model.addAttribute("result", result);
		return "jsonView";
	}
	
	@PostMapping("signUp.do")
	public String signUp(Model model, @RequestBody MemberVO member) {
		MemberVO result = mainService.signUp(member);

		model.addAttribute("result", result);
		return "jsonView";
	}
	
	@PostMapping("saveUserSchedule.do")
	public String saveUserSchedule(Model model, @RequestBody UserScheduleVO userSchedule, HttpSession session) {
		MemberVO member = (MemberVO) session.getAttribute(CommonConstants.SESSION);
		userSchedule.setUserId(member.getUserId());
		UserScheduleVO result = mainService.saveUserSchedule(userSchedule);
		
		model.addAttribute("result", result);
		return "jsonView";
	}

	@GetMapping("getUserSchedule.do")
	public String getUserSchedule (Model model, HttpSession session) {
		MemberVO member = (MemberVO) session.getAttribute(CommonConstants.SESSION);
		List<UserScheduleVO> userScheduleList = null;
		userScheduleList = mainService.getScheduleList(member);
		model.addAttribute("userScheduleList", userScheduleList);
		return "jsonView";
	}

}
