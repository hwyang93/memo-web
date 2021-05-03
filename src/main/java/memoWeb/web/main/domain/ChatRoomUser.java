package memoWeb.web.main.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@Table(name = "CHAT_ROOM_USER")
public class ChatRoomUser {
    @Id
    @Column(name = "CHAT_ROOM_IDX")
    private int chatRoomIdx;
    @Column(name = "USER_ID")
    private String userId;
}