package memoWeb.web.myGroup.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "GROUPS")
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
}
