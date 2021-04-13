package memoWeb.web.mySchedule.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import memoWeb.web.main.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import memoWeb.common.constant.CommonConstants;
import memoWeb.web.main.domain.UserScheduleVO;
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
		MemberVO member = (MemberVO) session.getAttribute(CommonConstants.SESSION);
		List<UserScheduleVO> userScheduleList = myScheduleService.getUserScheduleListAll(member);
		model.addAttribute("userScheduleList", userScheduleList);
		return "jsonView";
	}

	@GetMapping("getScheduleDetail.do")
	public String getScheduleDetail(ModelAndView model, HttpSession session, int idx) {
		
		return "jsonView";
	}
}
