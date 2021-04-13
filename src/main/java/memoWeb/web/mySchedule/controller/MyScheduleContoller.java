package memoWeb.web.mySchedule.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import memoWeb.web.main.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import memoWeb.common.constant.CommonConstants;
import memoWeb.web.main.domain.UserScheduleVO;
import memoWeb.web.mySchedule.service.MyScheduleService;

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
        UserVO member = (UserVO) session.getAttribute(CommonConstants.SESSION);
        List<UserScheduleVO> userScheduleList = myScheduleService.getUserScheduleListAll(member);
        model.addAttribute("userScheduleList", userScheduleList);
        return "jsonView";
    }
    
    @GetMapping("getScheduleDetail.do")
    public ModelAndView getScheduleDetail(ModelAndView model, HttpSession session) {
    	String userId = session.getId();
    	System.out.println(userId);
		/*
		 * UserScheduleVO usVO = myScheduleService.getUserScheduleDetail();
		 * model.addObject("myScheduleDetail",usVO);
		 * model.setViewName("myPage/myScheduleDetail");
		 */
    	return model;
    }
}
