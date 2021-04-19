package memoWeb.web.myGroup.domain;

import lombok.Data;

@Data
public class GroupDTO {
    private int groupIdx;
    private String groupMasterUser;
    private String groupTitle;
    private String groupComment;
    private String groupRegDate;
    private String groupUser;
    private String memberRegDate;
    private String memberAuth;
    private String approvalStatus;

//    public GroupDTO(int groupIdx, String groupTitle, String groupRegDate, String memberAuth, String approvalStatus) {
//        this.groupIdx = groupIdx;
//        this.groupTitle = groupTitle;
//        this.groupRegDate = groupRegDate;
//        this.memberAuth = memberAuth;
//        this.approvalStatus = approvalStatus;
//    }
}
