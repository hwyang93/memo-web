package memoWeb.web.main.domain;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@Table(name = "USER_SCHEDULE")
@SequenceGenerator(
        name="USER_SCHEDULE_SEQ",
        sequenceName="USER_SCHEDULE_SEQ",
        initialValue=1,
        allocationSize=1
        )
public class UserScheduleVO {
	
	@Id
	@GeneratedValue(
		strategy=GenerationType.SEQUENCE,
		generator="USER_SCHEDULE_SEQ"
	)
	@Column(name = "IDX")
	private int idx;

	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "REG_DATE", insertable = false, updatable = false,
			columnDefinition = "Date default sysdate")
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
