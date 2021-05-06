package memoWeb.web.main.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class ChatRoomMessageDTO implements Serializable {
	private int chatRoomIdx;
	private String sendUserId;
	private String sendDate;
	private String sendMessage;
}