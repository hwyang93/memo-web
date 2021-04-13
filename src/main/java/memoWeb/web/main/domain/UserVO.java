package memoWeb.web.main.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "USERS")
public class UserVO {
	@Id
	@Column(name = "USER_ID")
	private String userId;
	@Column(name = "USER_PASSWORD")
	private String userPassword;
	@Column(name = "USER_EMAIL")
	private String userEmail;
	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "REG_DATE", insertable = false, updatable = false,
			columnDefinition = "Date default sysdate")
	private String regDate;

}
