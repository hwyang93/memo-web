package memoWeb.web.myMemo.controller;

import memoWeb.common.constant.CommonConstants;
import memoWeb.web.main.domain.UserDTO;
import memoWeb.web.main.domain.UserMemoDTO;
import memoWeb.web.myMemo.service.MyMemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/myMemo")
public class MyMemoController {
    private final MyMemoService myMemoService;

    @Autowired
    public MyMemoController(MyMemoService myMemoService) {
        this.myMemoService = myMemoService;
    }

    @GetMapping("/myMemo")
    public Map<String, Object> getMymemoListAll(HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute(CommonConstants.SESSION);
        Map<String, Object> resultMap = new HashMap<>();
        List<UserMemoDTO> result = myMemoService.getUserMemoListAll(user);
        resultMap.put("result", result);

        return resultMap;
    }

    @GetMapping("/myMemo/{idx}")
    public Map<String, Object> getMyMemoInfo(@PathVariable int idx) {
        Map<String, Object> resultMap = new HashMap<>();
        UserMemoDTO resutl = myMemoService.getUserMemoInfo(idx);
        resultMap.put("result", resutl);
        return resultMap;
    }


}
