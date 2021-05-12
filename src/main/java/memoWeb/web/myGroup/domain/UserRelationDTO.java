package memoWeb.web.myGroup.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
public class UserRelationDTO {
	private String userId;
	private String followUserId;
	private String regDate;
	private String relationStatus;

	private String keyword;

}
