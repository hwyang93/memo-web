package memoWeb.web.main.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
public class ChatRoomDTO {
    private int chatRoomIdx;
    private String chatRoodType;
    private String chatRoomTitle;
}