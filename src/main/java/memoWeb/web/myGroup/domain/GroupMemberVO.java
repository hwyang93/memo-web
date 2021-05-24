package memoWeb.web.myGroup.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "GROUP_MEMBER")
public class GroupMemberVO {

    @Column(name = "GROUP_IDX",insertable = false, updatable = false)
    private int groupIdx;
    @Id
    @Column(name = "GROUP_USER")
    private String groupUser;
    @Column(name = "REG_DATE", insertable = false, updatable = false,
            columnDefinition = "Date default sysdate")
    private String regDate;
    @Column(name = "MEMBER_AUTH")
    private String memberAuth;
    @Column(name = "APPROVAL_STATUS")
    private String approvalStatus;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "GROUP_IDX")
    private GroupsVO groups;
}
