package memoWeb.web.main.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
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
	@Column(name = "OPEN_FLAG")
	private String openFlag;
	@Column(name="DEL_DATE")
	private String delDate;
}
