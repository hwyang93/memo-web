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

	@MessageMapping("/receive/{chatRoomIdx}")
	@SendTo("/send/{chatRoomIdx}")
	public ChatRoomMessageDTO SocketHandler(ChatRoomMessageDTO chatRoomMessageDTO, ChatRoomMessage chatRoomMessage) {
		chatServeice.insertChatMessage(chatRoomMessage);

		return chatRoomMessageDTO;
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
		List<ChatRoomMessageDTO> chatRoomMessage = null;
		if (chatRoomInfo != null) {
			chatRoomMessage = chatServeice.getChatMessage(chatRoomInfo);
		}

		resultMap.put("chatRoomInfo", chatRoomInfo);
		resultMap.put("chatUserInfo", chatUserInfo);
		resultMap.put("chatRoomMessage", chatRoomMessage);
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

	@PostMapping("/chatMessage")
	public Map<String, Object> insertChatMessage(HttpSession session, @RequestBody ChatRoomMessage chatRoomMessage) {
		Map<String, Object> resultMap = new HashMap<>();
		UserDTO user = (UserDTO) session.getAttribute(CommonConstants.SESSION);
		chatRoomMessage.setSendUserId(user.getUserId());

		return resultMap;
	}

}
