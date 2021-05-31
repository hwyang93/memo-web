package memoWeb.web.myGroup.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@NoArgsConstructor
@Data
@Entity
@Table(name = "GROUP_MEMBER")
public class GroupMemberVO {
	
    @Column(name = "GROUP_IDX")
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

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "GROUP_IDX")
//    private GroupsVO groups;
    @ManyToOne
    @JoinColumn(name="GROUP_IDX",insertable = false, updatable = false)
    @ToString.Exclude
    @JsonIgnore
    private GroupsVO groups;
}
