package memoWeb.web.main.repository;

import memoWeb.web.main.domain.*;

import java.util.HashMap;
import java.util.List;

public interface ChatRepository {
	List<ChatRoomDTO> getChatList(UserDTO user);

	ChatRoomUserDTO getChatRoomUser(HashMap<String, Object> params);

	UserDTO getUserInfo(String userId);

	List<ChatRoomUserDTO> getChatRoomListI(UserDTO user);

	List<ChatRoomDTO> getChatRoomListG(UserDTO user);

	int getMaxChatRoomIdx();

	void createChatRoom(ChatRoom chatRoom);

	void joinChatRoom(ChatRoomUser chatRoomUser);
}
