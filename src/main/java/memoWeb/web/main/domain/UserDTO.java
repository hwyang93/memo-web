package memoWeb.web.main.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class UserDTO {
    private String userId;
    private String userPassword;
    private String userEmail;
    private String userName;
    private String regDate;
    private String openFlag;

//    @QueryProjection
//    public UserDTO(String userId, String userPassword, String userEmail, String userName, String regDate, String openFlag) {
//        this.userId = userId;
//        this.userPassword = userPassword;
//        this.userEmail = userEmail;
//        this.userName = userName;
//        this.regDate = regDate;
//        this.openFlag = openFlag;
//    }
}
