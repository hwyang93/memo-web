package memoWeb.web.main.domain;

import lombok.Data;

@Data
public class ChatRoomUserDTO {
	private int chatRoomIdx;
	private String userId;
	private String chatRoomTitle;
}