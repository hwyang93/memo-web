package memoWeb.web.main.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Data
@Entity
@Table(name = "CHAT_ROOM")
public class ChatRoom implements Serializable {
	@Id
	@Column(name = "CHAT_ROOM_IDX")
	private int chatRoomIdx;
	@Column(name = "CHAT_ROOM_TYPE")
	private String chatRoodType;
	@Column(name = "GROUP_IDX")
	private int groupIdx;
}