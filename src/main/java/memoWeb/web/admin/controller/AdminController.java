package memoWeb.web.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.QueryResults;
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
		UserVO userInfo = adminService.getUserInfo(id);
		map.put("userInfo", userInfo);
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
	public Map<String,Object> getMonthData(Model model){
		Map<String,Object> map = new HashMap<>();
		List<Tuple> list = adminService.getMonthData();
		// tuple인걸로 못보냄 바꿔줘야함
		map.put("list", list);
		return map;
	}
}
