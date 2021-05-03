package memoWeb.web.main.repository;

import memoWeb.web.main.domain.ChatRoomDTO;
import memoWeb.web.main.domain.ChatRoomUser;
import memoWeb.web.main.domain.ChatRoomUserDTO;
import memoWeb.web.main.domain.UserDTO;

import java.util.HashMap;
import java.util.List;

public interface ChatRepository {
    List<ChatRoomDTO> getChatList(UserDTO user);

    ChatRoomUserDTO getChatRoomUser(HashMap<String, Object> params);

    UserDTO getUserInfo(String userId);
}
