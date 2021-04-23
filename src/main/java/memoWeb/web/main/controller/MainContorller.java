package memoWeb.web.main.controller;

import memoWeb.common.constant.CommonConstants;
import memoWeb.web.main.domain.*;
import memoWeb.web.main.service.MainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
@RestController
@RequestMapping("/api/main")
public class MainContorller {
	
	private static MainService mainService;
	private static final Logger logger = LoggerFactory.getLogger(MainContorller.class);
	@Autowired
	public MainContorller(MainService mainService) {
		this.mainService = mainService;
	}

	@PostMapping("login.do")
	public ResponseEntity login(Model model, @RequestBody UserVO member, HttpSession session) {
		UserVO result = null;
		result = mainService.login(member);
		if (result != null) {
			session.setAttribute(CommonConstants.SESSION, result);
		}
		model.addAttribute("result", result);
		return ResponseEntity.ok().body(result);
	}

	@PostMapping("signUp.do")
	public ResponseEntity signUp(@RequestBody UserVO member) {

		UserVO result = mainService.signUp(member);
		return ResponseEntity.ok().body(result);
	}

//	@PostMapping("signUp.do")
//	public String signUp(Model model, @RequestBody UserVO member) {
//		UserVO result = mainService.signUp(member);
//
//		model.addAttribute("result", result);
//		return "jsonView";
//	}
	
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

	@PostMapping(value = "/userMemo")
	public String saveUserMemo(Model model, @RequestBody UserMemo userMemo, HttpSession session) {
		UserVO member = (UserVO) session.getAttribute(CommonConstants.SESSION);
		userMemo.setUserId(member.getUserId());
		UserMemo result = mainService.saveUserMemo(userMemo);

		model.addAttribute("result", result);
		return "jsonView";
	}

	@PutMapping(value = "/userMemo/{idx}")
	public String updateUserMemo(Model model, @RequestBody UserMemo userMemo, HttpSession session) {
		UserVO member = (UserVO) session.getAttribute(CommonConstants.SESSION);
		userMemo.setUserId(member.getUserId());
		UserMemo result = mainService.saveUserMemo(userMemo);

		model.addAttribute("result", result);
		return "jsonView";
	}

//	@GetMapping("getSchedule.do")
//	public String getSchedule (Model model, HttpSession session) {
//		UserVO member = (UserVO) session.getAttribute(CommonConstants.SESSION);
//		List<UserScheduleVO> userScheduleList = mainService.getUserScheduleList(member);
//		List<GroupScheduleDTO> groupScheduleList = mainService.getGroupScheduleList(member);
//		model.addAttribute("userScheduleList", userScheduleList);
//		model.addAttribute("groupScheduleList", groupScheduleList);
//		return "jsonView";
//	}

	@GetMapping("/schedule")
	public ResponseEntity<Map<String,Object>> getSchedule (HttpSession session) {
		UserVO member = (UserVO) session.getAttribute(CommonConstants.SESSION);
		Map<String, Object> resultMap = new HashMap<>();
		List<UserScheduleVO> userScheduleList = mainService.getUserScheduleList(member);
		List<GroupScheduleDTO> groupScheduleList = mainService.getGroupScheduleList(member);
		resultMap.put("userScheduleList", userScheduleList);
		resultMap.put("groupScheduleList", groupScheduleList);

		return ResponseEntity.ok(resultMap);
	}
}
