package memoWeb.web.myGroup.controller;

import memoWeb.web.main.domain.UserVO;
import memoWeb.web.myGroup.service.MyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        List<UserVO> memberList = myGroupService.getUserList((String) params.get("keyword"));
        return "jsonView";
    }

    @GetMapping("getUserInfo.do")
    public String getUserInfo(Model model, HttpSession session) {
        return "jsonView";
    }
}
