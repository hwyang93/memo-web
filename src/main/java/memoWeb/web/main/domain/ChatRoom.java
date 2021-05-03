package memoWeb.web.main.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@Table(name = "CHAT_ROOM")
@SequenceGenerator(
        name="CHAT_ROOM_SEQ",
        sequenceName="CHAT_ROOM_SEQ",
        initialValue=1,
        allocationSize=1
)
public class ChatRoom {
    @Id
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="CHAT_ROOM_SEQ"
    )
    @Column(name = "CHAT_ROOM_IDX")
    private int chatRoomIdx;
    @Column(name = "CHAT_ROOM_TYPE")
    private String chatRoodType;
    @Column(name = "CHAT_ROOM_TITLE")
    private String chatRoomTitle;
}