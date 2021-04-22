package memoWeb.web.myGroup.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Data
@Entity
@Table(name = "GROUP_MEMBER")
public class GroupMemberVO {

    @Id
    @Column(name = "GROUP_IDX")
    private int groupIdx;

    @Column(name = "GROUP_USER")
    private String groupUser;
    @Column(name = "REG_DATE", insertable = false, updatable = false,
            columnDefinition = "Date default sysdate")
    private String regDate;
    @Column(name = "MEMBER_AUTH")
    private String memberAuth;
    @Column(name = "APPROVAL_STATUS")
    private String approvalStatus;
}
