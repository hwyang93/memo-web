package memoWeb.web.myGroup.controller;

import memoWeb.common.constant.CommonConstants;
import memoWeb.web.main.domain.UserVO;
import memoWeb.web.myGroup.domain.UserGroupVO;
import memoWeb.web.myGroup.domain.UserRelationVO;
import memoWeb.web.myGroup.service.MyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String getUserList(Model model, HttpSession session, HashMap<String, Object> params) {
        List<UserVO> result = myGroupService.getUserList((String) params.get("keyword"));
        model.addAttribute("userList", result);
        return "jsonView";
    }

    @GetMapping("getUserInfo.do")
    public String getUserInfo(Model model, UserVO user) {
        UserVO result = myGroupService.getUserInfo(user);
        model.addAttribute("userInfo", result);
        return "jsonView";
    }

    @PostMapping("joinUserRelation")
    public String joinUserRelation(Model model, HttpSession session, UserRelationVO userRelation) {
        userRelation.setFollowUserId(((UserVO) session.getAttribute(CommonConstants.SESSION)).getUserId());
        userRelation.setRelationStatus("W");
        UserRelationVO result = myGroupService.joinUserRelation(userRelation);
        model.addAttribute("joinUser", result);
        return "jsonView";
    }

    @GetMapping("getFriendList.do")
    public String getFriendList(Model model, HttpSession session, UserRelationVO userRelation) {
        userRelation.setFollowUserId(((UserVO) session.getAttribute(CommonConstants.SESSION)).getUserId());
        List<UserRelationVO> result = myGroupService.getFriendList(userRelation);
        model.addAttribute("friendList", result);
        return "jsonView";
    }

    @PostMapping("createGroup.do")
    public String createGroup(Model model, HttpSession session, @RequestBody  List<UserGroupVO> userGrouupList){

        userGrouupList.forEach(usergroup -> {
            usergroup.setGroupMasterUser(((UserVO) session.getAttribute(CommonConstants.SESSION)).getUserId());
        });
        System.out.println(userGrouupList.toString());
        return "jsonView";
    }



}
