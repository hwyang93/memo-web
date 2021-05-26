package memoWeb.web.myGroup.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {
    private int groupIdx;
    private String groupMasterUser;
    private String groupTitle;
    private String groupComment;
    private String groupRegDate;
	private String delFlag;
	private String delDate;
    private String groupUser;
    private String memberRegDate;
    private String memberAuth;
    private String approvalStatus;

    private List<GroupMemberVO> groupMembers;

//    private GroupMemberDTO groupMember;

//    public GroupDTO(int groupIdx, String groupTitle, String groupRegDate, String memberAuth, String approvalStatus) {
//        this.groupIdx = groupIdx;
//        this.groupTitle = groupTitle;
//        this.groupRegDate = groupRegDate;
//        this.memberAuth = memberAuth;
//        this.approvalStatus = approvalStatus;
//    }
}
