package memoWeb.web.main.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@Table(name = "USER_MEMO")
@SequenceGenerator(
        name="USER_MEMO_SEQ",
        sequenceName="USER_MEMO_SEQ",
        initialValue=1,
        allocationSize=1
)
public class UserMemo implements Serializable {

    @Id
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="USER_MEMO_SEQ"
    )
    @Column(name = "IDX")
    private int idx;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "REG_DATE")
    private String regDate;

    @Column(name = "PROMISE_PLACE")
    private String promisePlace;

    @Column(name = "LON")
    private String lon;

    @Column(name = "LAT")
    private String lat;

    @Column(name = "MEMO")
    private String memo;
}
