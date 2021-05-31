package memoWeb.web.main.controller;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
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
import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
@RestController
@RequestMapping("/api/main")
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	private static MainService mainService;

	@Autowired
	public MainController(MainService mainService) {
		this.mainService = mainService;
	}

	@PostMapping("login.do")
	public ResponseEntity login(Model model, @RequestBody UserDTO user, HttpSession session) {
		UserDTO result = null;
		result = mainService.login(user);
		if (result != null) {
			String token = createToken(user.getUserId());
			result.setToken(token);
			session.setAttribute(CommonConstants.SESSION, result);
		}
		model.addAttribute("result", result);
		return ResponseEntity.ok().body(result);
	}

	@PostMapping("logout")
	public Map<String, Object> logout(HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		session.setAttribute(CommonConstants.SESSION, null);
		resultMap.put("result", "logout");
		return resultMap;
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
		UserDTO user = (UserDTO) session.getAttribute(CommonConstants.SESSION);
		userSchedule.setUserId(user.getUserId());
		UserScheduleVO result = mainService.saveUserSchedule(userSchedule);

		model.addAttribute("result", result);
		return "jsonView";
	}

	@PutMapping("saveGroupSchedule.do")
	public String saveGroupSchedule(Model model, @RequestBody GroupSchedule groupSchedule, HttpSession session) {
		UserDTO user = (UserDTO) session.getAttribute(CommonConstants.SESSION);
		groupSchedule.setRegUser(user.getUserId());
		System.out.println(groupSchedule);
		GroupSchedule result = mainService.saveGroupSchedule(groupSchedule);

		model.addAttribute("result", result);
		return "jsonView";
	}

	@PostMapping(value = "/memo")
	public String saveUserMemo(Model model, @RequestBody UserMemo userMemo, HttpSession session) {
		UserDTO user = (UserDTO) session.getAttribute(CommonConstants.SESSION);
		UserVO userVO = new UserVO();
		userVO.setUserId(user.getUserId());
 		userMemo.setUser(userVO);

//		userMemo.setUserId(user.getUserId());
		UserMemo result = mainService.saveUserMemo(userMemo);

		model.addAttribute("result", result);
		return "jsonView";
	}

	@PutMapping(value = "/userMemo/{idx}")
	public String updateUserMemo(Model model, @RequestBody UserMemo userMemo, HttpSession session) {
		UserDTO user = (UserDTO) session.getAttribute(CommonConstants.SESSION);
		userMemo.setUserId(user.getUserId());
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
	public Map<String, Object> getSchedule(HttpSession session) {
		UserDTO user = (UserDTO) session.getAttribute(CommonConstants.SESSION);
		Map<String, Object> resultMap = new HashMap<>();
		List<UserScheduleVO> userScheduleList = mainService.getUserScheduleList(user);
		List<GroupScheduleDTO> groupScheduleList = mainService.getGroupScheduleList(user);
		List<UserMemoDTO> userMemoList = mainService.getUserMemoList(user);
		resultMap.put("userScheduleList", userScheduleList);
		resultMap.put("groupScheduleList", groupScheduleList);
		resultMap.put("userMemoList", userMemoList);

		return resultMap;
	}

	@GetMapping("/friend")
	public Map<String, Object> getFriendList(HttpSession session, @RequestParam HashMap<String, Object> params) {
		UserDTO user = (UserDTO) session.getAttribute(CommonConstants.SESSION);
		user.setKeyword((String) params.get("keyword"));
		Map<String, Object> resultMap = new HashMap<>();
		List<UserDTO> friendList = mainService.getFriendList(user);
		resultMap.put("friendList", friendList);

		return resultMap;
	}

	public String createToken(String subject) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		Key signingKey = Keys.secretKeyFor(signatureAlgorithm);
		JwtBuilder builder = Jwts.builder()
				.setSubject(subject)
				.signWith(signingKey);
		return builder.compact();
	}

}
