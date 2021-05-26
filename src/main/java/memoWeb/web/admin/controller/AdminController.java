package memoWeb.web.admin.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.Tuple;

import memoWeb.web.admin.service.AdminService;
import memoWeb.web.main.domain.UserVO;
import memoWeb.web.myGroup.domain.GroupsVO;
import memoWeb.web.post.domain.Post;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	private static AdminService adminService;
	
	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	// dashboard
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
		long post = adminService.getPostCnt();
		long memo = adminService.getMemoCnt();
		long schedule = adminService.getScheduleCnt();
		long gSchedule = adminService.getGscheduleCnt();
		map.put("post", (int) post);
		map.put("memo", (int) memo);
		map.put("schedule", (int) schedule);
		map.put("gSchedule", (int) gSchedule);
		return map;
	}
	
	// users
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
		List<Tuple> result = adminService.getuGroupCnt(id);
		Map<String,Object> uGroupCnt = new TreeMap<>();
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
	
	@GetMapping(value = "/deleteUser/{id}")
	public Map<String,Object> deleteUser(@PathVariable("id") String id, Model model, UserVO member){
		Map<String,Object> map = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date day = new Date();
		String today = sdf.format(day);

		member.setUserId(id);
		member.setOpenFlag("N");
		member.setDelDate(today);
		long result = adminService.deleteUser(member);
		map.put("result", result);
		return map;
	}
	
	//board
	@GetMapping("/postList")
	public Map<String,Object> getPostList(Model model){
		Map<String,Object> map = new HashMap<>();
		List<Post> postList = adminService.getPostList();
		System.out.println(postList);
		map.put("postList", postList);
		return map;
	}
	
	@GetMapping("/postDetail/{idx}")
	public Map<String,Object> getPostDetail(@PathVariable("idx") int idx, Model model){
		Map<String,Object> map = new HashMap<>();
		Post postDetail = adminService.getPostDetail(idx);
		map.put("postDetail", postDetail);
		return map;
	}
	
	@GetMapping("/deletePost/{idx}")
	public Map<String,Object> deletePost(@PathVariable("idx") int idx, Model model, Post post){
		Map<String,Object> map = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date day = new Date();
		String today = sdf.format(day);
		
        post.setPostIdx(idx);
		post.setDelFlag("Y");
		post.setDelDate(today);
		long result = adminService.deletePost(post);
		map.put("result", result);
		return map;
	}
	
	
	/* groups */
	
	@GetMapping("/groupList")
	public Map<String,Object> getGroupList(Model model){
		Map<String,Object> map = new HashMap<>();
		List<GroupsVO> groupList = adminService.getGroupList();
		map.put("groupList", groupList);
		return map;
	}
	
	@GetMapping("/groupDetail/{idx}")
	public Map<String,Object> getGroupDetail(@PathVariable("idx") int idx, Model model){
		Map<String,Object> map = new HashMap<>();
		GroupsVO groupDetail = adminService.getGroupDetail(idx);
		System.out.println(groupDetail);
		map.put("groupDetail", groupDetail);
		return map;
	}
}
