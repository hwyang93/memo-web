package memoWeb.web.myGroup.domain;

import lombok.*;
import memoWeb.web.post.domain.PostFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "GROUPS")
@Builder(builderMethodName = "GroupsVOBuilder")
public class GroupsVO {
    @Id
    @Column(name = "GROUP_IDX")
    private int groupIdx;
    @Column(name = "GROUP_MASTER_USER")
    private String groupMasterUser;
    @Column(name = "GROUP_TITLE")
    private String groupTitle;
    @Column(name = "REG_DATE", insertable = false, updatable = false, columnDefinition = "Date default sysdate")
    private String regDate;
    @Column(name = "GROUP_COMMENT")
    private String groupComment;

    @OneToMany(mappedBy = "groups", cascade = CascadeType.ALL)
    @Singular
    private List<GroupMemberVO> groupMembers;

    public static GroupsVOBuilder toEntity(GroupDTO groupDTO) {
        return GroupsVOBuilder()
                .groupIdx(groupDTO.getGroupIdx())
                .groupMasterUser(groupDTO.getGroupMasterUser())
                .groupTitle(groupDTO.getGroupTitle())
                .groupComment(groupDTO.getGroupComment())
                .groupMembers(groupDTO.getGroupMembers());
    }

    public void addMembers(List<GroupMemberVO> groupMembers) {
        this.groupMembers.addAll(groupMembers);
    }

}
