package memoWeb.web.main.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity(name = "userSchedule")
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
	@Column(name = "idx")
	private String idx;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "reg_date")
	private String regDate;

	@Column(name = "start_data")
	private String startData;

	@Column(name = "end_date")
	private String endDate;

	@Column(name = "promise_place")
	private String promisePlace;

	@Column(name = "lon")
	private String lon;

	@Column(name = "lat")
	private String lat;

	@Column(name = "memo")
	private String memo;

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getStartData() {
		return startData;
	}

	public void setStartData(String startData) {
		this.startData = startData;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPromisePlace() {
		return promisePlace;
	}

	public void setPromisePlace(String promisePlace) {
		this.promisePlace = promisePlace;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
