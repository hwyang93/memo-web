package memoWeb.web.main.service;

import memoWeb.web.main.domain.ChatRoomDTO;
import memoWeb.web.main.domain.ChatRoomUserDTO;
import memoWeb.web.main.domain.UserDTO;
import memoWeb.web.main.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ChatServeice {

	private final ChatRepository chatRepository;

	@Autowired
	public ChatServeice(ChatRepository chatRepository) {
		this.chatRepository = chatRepository;
	}

	public List<ChatRoomDTO> getChatList(UserDTO user) {
		return chatRepository.getChatList(user);
	}

	public ChatRoomUserDTO getChatRoom(HashMap<String, Object> params) {
		return chatRepository.getChatRoomUser(params);
	}

	public UserDTO getUserInfo(String userId) {
		return chatRepository.getUserInfo(userId);
	}

}
