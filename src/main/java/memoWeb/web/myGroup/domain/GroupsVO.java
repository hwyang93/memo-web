package memoWeb.web.myGroup.domain;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    
    @Column(name="DEL_FLAG")
	private String delFlag;
	@Column(name="DEL_DATE")
	private String delDate;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="GROUP_IDX")
    private List<GroupMemberVO> groupMembers;
    
//    @OneToMany(mappedBy = "groups", cascade = CascadeType.ALL)
//    private List<GroupMemberVO> groupMembers;
    public static GroupsVOBuilder toEntity(GroupDTO groupDTO) {
        return GroupsVOBuilder()
                .groupIdx(groupDTO.getGroupIdx())
                .groupMasterUser(groupDTO.getGroupMasterUser())
                .groupTitle(groupDTO.getGroupTitle())
                .groupComment(groupDTO.getGroupComment())
        		.delFlag(groupDTO.getDelFlag())
        		.delDate(groupDTO.getDelDate());
//                .groupMembers(groupDTO.getGroupMembers());
    }
//    public void addMembers(List<GroupMemberVO> groupMembers) {
//        this.groupMembers.addAll(groupMembers);
//    }
}
