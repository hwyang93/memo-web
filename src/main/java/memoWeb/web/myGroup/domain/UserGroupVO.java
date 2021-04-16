package memoWeb.web.myGroup.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@Table(name = "USER_GROUP")
@SequenceGenerator(
        name = "USER_GROUP_SEQ",
        sequenceName = "USER_GROUP_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class UserGroupVO {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "USER_GROUP"
    )
    @Column(name = "GROUP_IDX")
    private int groupIdx;
    @Column(name = "GROUP_MASTER_USER")
    private String groupMasterUser;
    @Column(name = "GROUP_USER")
    private String groupUser;
    @Column(name = "GRUOUP_TITLE")
    private String groupTitle;
    @Column(name = "REG_DATE", insertable = false, updatable = false,
            columnDefinition = "Date default sysdate")
    private String regDate;
    @Column(name = "APPROVAL_STATUS")
    private String approvalStatus;
}
