package memoWeb.web.myGroup.domain;

import lombok.Data;

@Data
public class GroupMemberDTO {
    private int groupIdx;
    private String groupUser;
    private String regDate;
    private String memberAuth;
    private String approvalStatus;

}
