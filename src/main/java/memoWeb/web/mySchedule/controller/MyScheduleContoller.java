package memoWeb.web.mySchedule.controller;

import memoWeb.common.constant.CommonConstants;
import memoWeb.web.main.domain.MemberVO;
import memoWeb.web.main.domain.UserScheduleVO;
import memoWeb.web.mySchedule.service.MyScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/api/mySchedule")
public class MyScheduleContoller {
    private static MyScheduleService myScheduleService;

    @Autowired
    public MyScheduleContoller(MyScheduleService myScheduleService) {
        this.myScheduleService = myScheduleService;
    }

    @GetMapping("getUserScheduleListAll.do")
    public String getUserScheduleListAll(Model model, HttpSession session) {
        MemberVO member = (MemberVO) session.getAttribute(CommonConstants.SESSION);
        List<UserScheduleVO> result = myScheduleService.getUserScheduleListAll(member);
        model.addAttribute("result", result);
        return "jsonView";
    }
}
