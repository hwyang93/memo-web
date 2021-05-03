package memoWeb.web.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import memoWeb.common.constant.CommonConstants;
import memoWeb.web.admin.service.AdminService;
import memoWeb.web.main.domain.UserDTO;
import memoWeb.web.main.domain.UserVO;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	private static AdminService adminService;
	
	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@GetMapping("/userList")
	public Map<String,Object> getUserList(Model model, UserVO userVo){
		Map<String,Object> map = new HashMap<>();
		List<UserVO> userList = adminService.getUserList();
		long userCount = adminService.getUserCount();
		map.put("userList", userList);
		map.put("userCount", userCount);
		return map;
	}
	
	@GetMapping("/deleteUser/{id}")
	public Map<String,Object> deleteUser(@PathVariable("id") String id, Model model, UserVO userVo){
		Map<String,Object> map = new HashMap<>();
		System.out.println(id);
		int result = adminService.deleteUser(id);
		System.out.println(result);
		map.put("result", result);
		return map;
	}
}
