package memoWeb.web.main.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Data
@Entity
@Table(name = "CHAT_ROOM_USER")
public class ChatRoomUser implements Serializable {
	@Id
	@Column(name = "CHAT_ROOM_IDX")
	private int chatRoomIdx;
	@Id
	@Column(name = "USER_ID")
	private String userId;
}