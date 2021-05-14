package memoWeb.web.myGroup.controller;

import memoWeb.common.constant.CommonConstants;
import memoWeb.web.main.domain.UserDTO;
import memoWeb.web.main.domain.UserVO;
import memoWeb.web.myGroup.domain.*;
import memoWeb.web.myGroup.service.MyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/api/myGroup")
public class MyGroupController {
	@Autowired
	private static MyGroupService myGroupService;

	public MyGroupController(MyGroupService myGroupService) {
		this.myGroupService = myGroupService;
	}

	@GetMapping("getUserList.do")
	public String getUserList(Model model, HttpSession session, @RequestParam HashMap<String, Object> params) {
		List<UserDTO> result = myGroupService.getUserList(params);
		model.addAttribute("userList", result);
		return "jsonView";
	}

	@GetMapping("getUserInfo.do")
	public String getUserInfo(Model model, @ModelAttribute UserVO user) {
		UserVO result = myGroupService.getUserInfo(user);
		model.addAttribute("userInfo", result);
		return "jsonView";
	}

	@PostMapping("joinUserRelation.do")
	public String joinUserRelation(Model model, HttpSession session, @RequestBody UserRelationDTO userRelation) {
		userRelation.setUserId(((UserDTO) session.getAttribute(CommonConstants.SESSION)).getUserId());
		userRelation.setRelationStatus("W");
		long result = myGroupService.joinUserRelation(userRelation);
		model.addAttribute("result", result);
		return "jsonView";
	}

	@GetMapping("getFriendList.do")
	public String getFriendList(Model model, HttpSession session, @ModelAttribute UserRelationVO userRelation) {
		userRelation.setUserId(((UserDTO) session.getAttribute(CommonConstants.SESSION)).getUserId());
		List<UserRelationDTO> result = myGroupService.getFriendList(userRelation);
		model.addAttribute("friendsList", result);
		return "jsonView";
	}

	@GetMapping("getGroupList.do")
	public String getGroupList(Model model, HttpSession session) {
		UserVO user = new UserVO();
		user.setUserId(((UserDTO) session.getAttribute(CommonConstants.SESSION)).getUserId());
		List<GroupDTO> result = myGroupService.getGroupList(user);
		model.addAttribute("groupList", result);
		return "jsonView";
	}

	@GetMapping("getGroupInfo.do")
	public String getGroupInfo(Model model, @ModelAttribute GroupsVO group, @ModelAttribute GroupDTO group2) {
		GroupsVO result = myGroupService.getGroupInfo(group);
		List<GroupMemberDTO> result2 = myGroupService.getGroupMemberList(group2);
		model.addAttribute("groupInfo", result);
		model.addAttribute("groupMemberList", result2);
		return "jsonView";
	}

	@PostMapping("createGroup.do")
	public String createGroup(Model model, HttpSession session, @RequestBody List<GroupMemberVO> groupMemberList, @RequestBody GroupsVO group) {
		int groupIdx = myGroupService.getGroupIdx();

		group.setGroupMasterUser(((UserDTO) session.getAttribute(CommonConstants.SESSION)).getUserId());
		group.setGroupIdx(groupIdx);


		GroupMemberVO masterUser = new GroupMemberVO();
		masterUser.setGroupIdx(groupIdx);
		masterUser.setGroupUser(group.getGroupMasterUser());
		masterUser.setApprovalStatus("Y");
		masterUser.setMemberAuth("M");

		groupMemberList.add(masterUser);
		groupMemberList.forEach(groupMember -> {
			groupMember.setGroupIdx(groupIdx);
			groupMember.setMemberAuth("N");
			groupMember.setApprovalStatus("N");
		});

		myGroupService.createGroup(group);
		myGroupService.joinGroupMember(groupMemberList);

		return "jsonView";
	}

	@PostMapping("joinGroup.do")
	public String joinGroup(Model model, @RequestBody List<GroupMemberVO> groupMemberList) {
		groupMemberList.forEach(groupMember -> {
			groupMember.setMemberAuth("N");
			groupMember.setApprovalStatus("N");
		});
		myGroupService.joinGroupMember(groupMemberList);
		return "jsonView";
	}

	@PostMapping("deleteGroup.do")
	public String deleteGroup(Model model, @RequestBody GroupDTO group) {
		myGroupService.deleteGroup(group);
		return "jsonView";
	}

	@GetMapping("getFriendReqList.do")
	public String getFriendReqList(Model model, HttpSession session, @ModelAttribute GroupDTO group) {
		UserDTO user = (UserDTO) session.getAttribute(CommonConstants.SESSION);
		List<UserRelationDTO> result = myGroupService.getFriendReqList(user);
		model.addAttribute("friendReqList", result);
		return "jsonView";

	}

	@PostMapping("friendConsent.do")
	public String friendConsent(Model model, @RequestBody UserRelationDTO userRelation) {
		myGroupService.friendConsent(userRelation);
		return "jsonView";
	}
}
