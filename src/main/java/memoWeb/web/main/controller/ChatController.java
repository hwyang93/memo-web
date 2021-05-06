package memoWeb.web.main.controller;

import memoWeb.common.constant.CommonConstants;
import memoWeb.web.main.domain.*;
import memoWeb.web.main.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

	private final ChatService chatServeice;

	@Autowired
	public ChatController(ChatService chatServeice) {
		this.chatServeice = chatServeice;
	}

//	@MessageMapping("/receive/{chatRoomIdx}")
//	@SendTo("/send/{chatRoomIdx}")
//	public ChatVO SocketHandler(ChatVO chatVO) {
//		String userName = chatVO.getUserName();
//		String content = chatVO.getContent();
//
//		ChatVO result = new ChatVO(userName, content);
//		return result;
//	}

	@MessageMapping("/receive/{chatRoomIdx}")
	@SendTo("/send/{chatRoomIdx}")
	public ChatRoomMessageDTO SocketHandler(ChatRoomMessageDTO chatRoomMessage) {
		int chatRoomIdx = chatRoomMessage.getChatRoomIdx();
		String userName = chatRoomMessage.getSendUserId();
		String sendDate = chatRoomMessage.getSendDate();
		String content = chatRoomMessage.getSendMessage();

		ChatRoomMessageDTO result = new ChatRoomMessageDTO(chatRoomIdx, userName, sendDate, content);
		return result;
	}

	@GetMapping("/chat")
	public Map<String, Object> getChatList(HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		UserDTO user = (UserDTO) session.getAttribute(CommonConstants.SESSION);
		List<ChatRoomUserDTO> chatListI = chatServeice.getChatRoomListI(user);
		List<ChatRoomDTO> chatListG = chatServeice.getChatRoomListG(user);
		resultMap.put("chatListI", chatListI);
		resultMap.put("chatListG", chatListG);
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

	@PostMapping("/chat/{userId}")
	public Map<String, Object> createChatRoom(HttpSession session, @PathVariable String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HashMap<String, Object> params = new HashMap<>();
		UserDTO user = (UserDTO) session.getAttribute(CommonConstants.SESSION);
		params.put("user1", user.getUserId());
		params.put("user2", userId);
		resultMap.put("result", chatServeice.createChatRoom(params));
		return resultMap;
	}
}
