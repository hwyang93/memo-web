package memoWeb.web.myGroup.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USER_RELATION")
public class UserRelationVO {
	@Id
	@Column(name = "FOLLOW_USER_ID")
	private String followUserId;
	@Column(name = "FOLLOWING_USER_ID")
	private String followingUserId;
	@Column(name = "REG_DATE", insertable = false, updatable = false,
            columnDefinition = "Date default sysdate")
	private String regDate;
	@Column(name = "RELATION_STATUS")
	private String relationStatus;

}
