package memoWeb.web.myGroup.controller;

import memoWeb.common.constant.CommonConstants;
import memoWeb.web.main.domain.UserVO;
import memoWeb.web.myGroup.domain.GroupMemberVO;
import memoWeb.web.myGroup.domain.GroupsVO;
import memoWeb.web.myGroup.domain.UserRelationVO;
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
public class MyGroupContoller {
    private static MyGroupService myGroupService;

    @Autowired
    public MyGroupContoller(MyGroupService myGroupService) {
        this.myGroupService = myGroupService;
    }

    @GetMapping("getUserList.do")
    public String getUserList(Model model, HttpSession session, @RequestParam HashMap<String, Object> params) {
        List<UserVO> result = myGroupService.getUserList(params);
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
    public String joinUserRelation(Model model, HttpSession session, @RequestBody UserRelationVO userRelation) {
        userRelation.setFollowUserId(((UserVO) session.getAttribute(CommonConstants.SESSION)).getUserId());
        userRelation.setRelationStatus("W");
        UserRelationVO result = myGroupService.joinUserRelation(userRelation);
        model.addAttribute("joinUser", result);
        return "jsonView";
    }

    @GetMapping("getFriendList.do")
    public String getFriendList(Model model, HttpSession session, @ModelAttribute UserRelationVO userRelation) {
        userRelation.setFollowUserId(((UserVO) session.getAttribute(CommonConstants.SESSION)).getUserId());
        List<UserRelationVO> result = myGroupService.getFriendList(userRelation);
        model.addAttribute("friendList", result);
        return "jsonView";
    }

    @GetMapping("getGroupList.do")
    public String getGroupList (Model model, HttpSession session) {
        UserVO user = new UserVO();
        user.setUserId(((UserVO) session.getAttribute(CommonConstants.SESSION)).getUserId());
        List<GroupsVO> result = myGroupService.getGroupList(user);
        model.addAttribute("groupList", result);
        return "jsonView";
    }

    @GetMapping("getGroupInfo.do")
    public String getGroupInfo (Model model, @ModelAttribute GroupsVO group) {
        GroupsVO result = myGroupService.getGroupInfo(group);
        model.addAttribute("groupInfo", result);
        return "jsonView";
    }

    @PostMapping("createGroup.do")
    public String createGroup(Model model, HttpSession session, @RequestBody List<GroupMemberVO> groupMemberList, @RequestBody GroupsVO group){
        int groupIdx = myGroupService.getGroupIdx();

        group.setGroupMasterUser(((UserVO) session.getAttribute(CommonConstants.SESSION)).getUserId());
        group.setGroupIdx(groupIdx);


        GroupMemberVO masterUser = new GroupMemberVO();
        masterUser.setGroupIdx(groupIdx);
        masterUser.setGroupUser(group.getGroupMasterUser());
        masterUser.setApprovalStatus("Y");
        masterUser.setMemberAuth("M");

        groupMemberList.add(masterUser);
        groupMemberList.forEach(groupMember-> {
            groupMember.setGroupIdx(groupIdx);
            groupMember.setMemberAuth("N");
            groupMember.setApprovalStatus("N");
        });

        myGroupService.createGroup(group);
        myGroupService.joinGroupMember(groupMemberList);

        return "jsonView";
    }

    @PostMapping("joinGroup.do")
    public String joinGroup (Model model, @RequestBody List<GroupMemberVO> groupMemberList) {
        groupMemberList.forEach(groupMemeber ->{
            groupMemeber.setMemberAuth("N");
            groupMemeber.setApprovalStatus("N");
        });
        myGroupService.joinGroupMember(groupMemberList);
        return "jsonView";
    }


}
