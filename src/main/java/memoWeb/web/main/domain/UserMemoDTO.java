package memoWeb.web.main.domain;

import lombok.Data;

@Data
public class UserMemoDTO {
    private int idx;
    private String title;
    private String userId;
    private String regDate;
    private String promisePlace;
    private String lon;
    private String lat;
    private String memo;
}
