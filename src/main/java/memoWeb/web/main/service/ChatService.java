package memoWeb.web.main.service;

import memoWeb.web.main.domain.*;
import memoWeb.web.main.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.HashMap;
import java.util.List;

@Service
public class ChatService {

	private final ChatRepository chatRepository;

	@Autowired
	public ChatService(ChatRepository chatRepository) {
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

	public List<ChatRoomUserDTO> getChatRoomListI(UserDTO user) {
		return chatRepository.getChatRoomListI(user);
	}

	public List<ChatRoomDTO> getChatRoomListG(UserDTO user) {
		return chatRepository.getChatRoomListG(user);
	}

	public ChatRoom createChatRoom(HashMap<String, Object> params) {
		int chatRoomIdx = chatRepository.getMaxChatRoomIdx() + 1;
		ChatRoom chatRoom = new ChatRoom();
		chatRoom.setChatRoomIdx(chatRoomIdx);
		chatRoom.setChatRoodType("I");
		chatRepository.createChatRoom(chatRoom);
		params.forEach((key, val) -> {
			ChatRoomUser chatRoomUser = new ChatRoomUser();
			chatRoomUser.setChatRoomIdx(chatRoomIdx);
			chatRoomUser.setUserId(val.toString());
			chatRepository.joinChatRoom(chatRoomUser);
		});
		return chatRoom;
	}

	public List<ChatRoomMessageDTO> getChatMessage(ChatRoomUserDTO chatRoomUser) {
		return chatRepository.getChatMessage(chatRoomUser);
	}

	public void insertChatMessage(ChatRoomMessage chatRoomMessage) {
		chatRepository.insertChatMessage(chatRoomMessage);
	}
}
