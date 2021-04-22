package memoWeb.web.main.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@Table(name = "GROUP_SCHEDULE")
@SequenceGenerator(
        name="GROUP_SCHEDULE_SEQ",
        sequenceName="GROUP_SCHEDULE_SEQ",
        initialValue=1,
        allocationSize=1
)
public class GroupSchedule implements Serializable {

    @Id
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="GROUP_SCHEDULE_SEQ"
    )
    @Column(name = "IDX")
    private int idx;

    @Id
    @Column(name = "GROUP_IDX")
    private int groupIdx;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "REG_USER")
    private String regUser;

    @Column(name = "REG_DATE")
    private String regDate;

    @Column(name = "START_DATE")
    private String startDate;

    @Column(name = "END_DATE")
    private String endDate;

    @Column(name = "PROMISE_PLACE")
    private String promisePlace;

    @Column(name = "LON")
    private String lon;

    @Column(name = "LAT")
    private String lat;

    @Column(name = "MEMO")
    private String memo;
}
