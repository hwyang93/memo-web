package memoWeb.web.main.domain;

import lombok.Data;

@Data
public class GroupScheduleDTO {
    private int idx;
    private int groupIdx;
    private String title;
    private String regUser;
    private String regDate;
    private String startDate;
    private String endDate;
    private String promisePlace;
    private String lon;
    private String lat;
    private String memo;
}
