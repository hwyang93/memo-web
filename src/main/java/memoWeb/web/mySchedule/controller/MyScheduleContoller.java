package memoWeb.web.mySchedule.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import memoWeb.common.constant.CommonConstants;
import memoWeb.web.main.domain.UserDTO;
import memoWeb.web.main.domain.UserScheduleVO;
import memoWeb.web.mySchedule.service.MyScheduleService;

@RestController
@RequestMapping("/api/mySchedule")
public class MyScheduleContoller {
	private static MyScheduleService myScheduleService;

	@Autowired
	public MyScheduleContoller(MyScheduleService myScheduleService) {
		this.myScheduleService = myScheduleService;
	}

	@GetMapping("getUserScheduleListAll")
	public Map<String, Object> getUserScheduleListAll(Model model, UserScheduleVO userScheduleVo, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		UserDTO user = (UserDTO) session.getAttribute(CommonConstants.SESSION);
		List<UserScheduleVO> userScheduleList = myScheduleService.getUserScheduleListAll(user);
		map.put("userScheduleList", userScheduleList);
		return map;
	}
	
	@GetMapping("getScheduleDetail/{idx}")
	public Map<String, Object> getScheduleDetail(@PathVariable("idx") int idx, Model model, HttpSession session) {
		System.out.println(idx);
		Map<String, Object> map = new HashMap<>();
		UserScheduleVO scheduleDetail = myScheduleService.getScheduleDetail(idx);
		map.put("scheduleDetail", scheduleDetail);
		return map;
	}
	
	@PutMapping("updateSchedule")
	public Map<String, Object> updateSchedule(@RequestBody UserScheduleVO userScheduleVO, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		long result = myScheduleService.updateSchedule(userScheduleVO);
		map.put("result",result);
		return map;
	}
	
	@GetMapping("deleteSchedule/{idx}")
	public Map<String, Object> deleteSchedule(Model model, HttpSession session, @PathVariable("idx") int idx) {
		Map<String, Object> map = new HashMap<>();
		long result = myScheduleService.deleteSchedule(idx);
		map.put("result", result);
		return map;
	}
}
