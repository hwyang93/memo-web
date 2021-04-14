package memoWeb.web.mySchedule.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import memoWeb.common.constant.CommonConstants;
import memoWeb.web.main.domain.UserScheduleVO;
import memoWeb.web.main.domain.UserVO;
import memoWeb.web.mySchedule.service.MyScheduleService;

@Controller
@RequestMapping("/api/mySchedule")
public class MyScheduleContoller {
	private static MyScheduleService myScheduleService;

	@Autowired
	public MyScheduleContoller(MyScheduleService myScheduleService) {
		this.myScheduleService = myScheduleService;
	}

	@GetMapping("getUserScheduleListAll.do")
	public String getUserScheduleListAll(Model model, HttpSession session) {
		UserVO member = (UserVO) session.getAttribute(CommonConstants.SESSION);
		List<UserScheduleVO> userScheduleList = myScheduleService.getUserScheduleListAll(member);
		model.addAttribute("userScheduleList", userScheduleList);
		return "jsonView";
	}
	
	@GetMapping("getScheduleDetail.do")
	public String getScheduleDetail(Model model, HttpSession session, int idx) {
		UserScheduleVO scheduleDetail = myScheduleService.getScheduleDetail(idx);
		model.addAttribute("scheduleDetail", scheduleDetail);
		return "jsonView";
	}
	
	@PostMapping("updateSchedule.do")
	public String updateSchedule(Model model, @RequestBody UserScheduleVO userScheduleVO, HttpSession session) {
		long result = myScheduleService.updateSchedule(userScheduleVO);
		model.addAttribute("result",result);
		return "jsonView";
	}
	
	@GetMapping("deleteSchedule.do")
	public String deleteSchedule(Model model, HttpSession session, int idx) {
		long result = myScheduleService.deleteSchedule(idx);
		model.addAttribute("result", result);
		return "jsonView";
	}
}
