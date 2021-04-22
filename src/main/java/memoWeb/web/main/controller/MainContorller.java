package memoWeb.web.main.controller;

import javax.servlet.http.HttpSession;

import memoWeb.web.main.domain.*;
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
	public String login(Model model, @RequestBody UserVO member, HttpSession session) {
		UserVO result = null;
		result = mainService.login(member);
		if (result != null) {
			session.setAttribute(CommonConstants.SESSION, result);
		}
		
		model.addAttribute("result", result);
		return "jsonView";
	}
	
	@PostMapping("signUp.do")
	public String signUp(Model model, @RequestBody UserVO member) {
		UserVO result = mainService.signUp(member);

		model.addAttribute("result", result);
		return "jsonView";
	}
	
	@PostMapping("saveUserSchedule.do")
	public String saveUserSchedule(Model model, @RequestBody UserScheduleVO userSchedule, HttpSession session) {
		UserVO member = (UserVO) session.getAttribute(CommonConstants.SESSION);
		userSchedule.setUserId(member.getUserId());
		UserScheduleVO result = mainService.saveUserSchedule(userSchedule);
		
		model.addAttribute("result", result);
		return "jsonView";
	}

	@PostMapping("saveGroupSchedule.do")
	public String saveGroupSchedule(Model model, @RequestBody GroupSchedule groupSchedule, HttpSession session) {
		UserVO member = (UserVO) session.getAttribute(CommonConstants.SESSION);
		groupSchedule.setRegUser(member.getUserId());
		GroupSchedule result = mainService.saveGroupSchedule(groupSchedule);

		model.addAttribute("result", result);
		return "jsonView";
	}

	@PostMapping("saveUserMemo.do")
	public String saveUserMemo(Model model, @RequestBody UserMemo userMemo, HttpSession session) {
		UserVO member = (UserVO) session.getAttribute(CommonConstants.SESSION);
		userMemo.setUserId(member.getUserId());
		UserMemo result = mainService.saveUserMemo(userMemo);

		model.addAttribute("result", result);
		return "jsonView";
	}

	@GetMapping("getSchedule.do")
	public String getSchedule (Model model, HttpSession session) {
		UserVO member = (UserVO) session.getAttribute(CommonConstants.SESSION);
		List<UserScheduleVO> userScheduleList = mainService.getUserScheduleList(member);
		List<GroupScheduleDTO> groupScheduleList = mainService.getGroupScheduleList(member);
		model.addAttribute("userScheduleList", userScheduleList);
		model.addAttribute("groupScheduleList", groupScheduleList);
		return "jsonView";
	}
}
