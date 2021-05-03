package memoWeb.web.main.controller;

import memoWeb.common.constant.CommonConstants;
import memoWeb.web.main.domain.*;
import memoWeb.web.main.service.ChatServeice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatServeice chatServeice;

    @Autowired
    public ChatController(ChatServeice chatServeice) {
        this.chatServeice = chatServeice;
    }

    @MessageMapping("/receive/{chatRoomIdx}")
    @SendTo("/send/{chatRoomIdx}")
    public ChatVO SocketHandler(ChatVO chatVO) {
        String userName = chatVO.getUserName();
        String content = chatVO.getContent();

        ChatVO result = new ChatVO(userName, content);
        return result;
    }

    @GetMapping("/chat")
    public Map<String, Object> getChatList(HttpSession session) {
        Map<String, Object> resultMap = new HashMap<>();
        UserDTO user = (UserDTO) session.getAttribute(CommonConstants.SESSION);
        List<ChatRoomDTO> chatRoomList = chatServeice.getChatList(user);
        resultMap.put("chatList", chatRoomList);
        return resultMap;
    }

    @GetMapping("/chatRoom/{userId}")
    public Map<String, Object> getChatRoom(HttpSession session, @PathVariable String userId) {
        Map<String, Object> resultMap = new HashMap<>();
        HashMap<String, Object> params = new HashMap<>();
        UserDTO user = (UserDTO) session.getAttribute(CommonConstants.SESSION);
        params.put("user1", user.getUserId());
        params.put("user2", userId);
        ChatRoomUserDTO chatRoomInfo = chatServeice.getChatRoom(params);
        UserDTO chatUserInfo = chatServeice.getUserInfo(userId);

        resultMap.put("chatRoomInfo", chatRoomInfo);
        resultMap.put("chatUserInfo", chatUserInfo);
        return resultMap;
    }
}
