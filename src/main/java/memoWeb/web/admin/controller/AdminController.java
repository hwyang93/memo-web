package memoWeb.web.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.Tuple;

import memoWeb.web.admin.service.AdminService;
import memoWeb.web.main.domain.UserVO;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	private static AdminService adminService;
	
	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@GetMapping("/dashboard")
	public Map<String,Object> getDashboardData(Model model){
		Map<String,Object> map = new HashMap<>();
		long userCnt = adminService.getUserCnt();
		long postCnt = adminService.getPostCnt();
		long groupCnt = adminService.getGroupCnt();

		map.put("userCnt", userCnt);
		map.put("postCnt", postCnt);
		map.put("groupCnt", groupCnt);
		return map;
	}
	
	@GetMapping("/userList")
	public Map<String,Object> getUserList(Model model){
		Map<String,Object> map = new HashMap<>();
		List<UserVO> userList = adminService.getUserList();
		map.put("userList", userList);
		return map;
	}
	
	@GetMapping("/userInfo/{id}")
	public Map<String,Object> getUserInfo(@PathVariable("id") String id, Model model){
		Map<String,Object> map = new HashMap<>();
		Map<String,Object> uGroupCnt = new TreeMap<>();
		UserVO userInfo = adminService.getUserInfo(id);
		List<Tuple> result = adminService.getuGroupCnt(id);
		long uPostCnt = adminService.getuPostCnt(id);
		for(Tuple tuple : result) {
			int sum = 0;
			sum += Integer.parseInt(String.valueOf(tuple.get(1, Integer.class)));
			uGroupCnt.put(tuple.get(0, String.class), sum);
		}
		map.put("userInfo", userInfo);
		map.put("uGroupCnt", uGroupCnt);
		map.put("uPostCnt", uPostCnt);
		return map;
	}
	
	@GetMapping("/deleteUser/{id}")
	public Map<String,Object> deleteUser(@PathVariable("id") String id, Model model){
		Map<String,Object> map = new HashMap<>();
		int result = adminService.deleteUser(id);
		map.put("result", result);
		return map;
	}
	
	@GetMapping("/getMonthData")
	public Map<String,Integer> getMonthData(Model model){
		Map<String,Integer> map = new TreeMap<>();
		List<Tuple> list = adminService.getMonthData();
		for(Tuple tuple : list) {
			map.put(tuple.get(0, String.class), Integer.parseInt(String.valueOf(tuple.get(1, Integer.class))));
		}
		return map;
	}
	
	@GetMapping("/getPostData")
	public Map<String,Integer> getPostData(Model model){
		Map<String,Integer> map = new HashMap<>();
		long memo = adminService.getMemoCnt();
		long schedule = adminService.getScheduleCnt();
		long gSchedule = adminService.getGscheduleCnt();
		map.put("memo", (int) memo);
		map.put("schedule", (int) schedule);
		map.put("gSchedule", (int) gSchedule);
		return map;
	}
}
