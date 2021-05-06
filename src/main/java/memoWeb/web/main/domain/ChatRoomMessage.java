package memoWeb.web.main.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@Data
@Entity
@Table(name = "CHAT_ROOM_MESSAGE")
public class ChatRoomMessage implements Serializable {
	@Id
	@Column(name = "CHAT_ROOM_IDX")
	private int chatRoomIdx;
	@Column(name = "SEND_USER_ID")
	private String sendUserId;
	@Column(name = "SEND_DATE", insertable = false, updatable = false,
			columnDefinition = "Date default sysdate")
	private String sendDate;
	@Column(name = "SEND_MESSAGE")
	private String sendMessage;

}