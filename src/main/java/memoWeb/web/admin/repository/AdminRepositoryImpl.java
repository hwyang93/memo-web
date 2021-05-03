package memoWeb.web.admin.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;

import memoWeb.web.main.domain.QUserVO;
import memoWeb.web.main.domain.UserVO;

@Repository
@Transactional
public class AdminRepositoryImpl implements AdminRepository {
	
	@PersistenceContext
	private EntityManager em;
	QUserVO quser = QUserVO.userVO;
	
	@Override
	public List<UserVO> getUserList() {
		final JPAQuery<UserVO> query = new JPAQuery<>(em);
		return query.from(quser)
				.orderBy(quser.regDate.desc())
				.fetch();
	}

	@Override
	public long getUserCount() {
		final JPAQuery<UserVO> query = new JPAQuery<>(em);
		return query.from(quser)
			.fetchCount();
	}

	@Override
	public int deleteUser(String id) {
		final JPADeleteClause deleteClause = new JPADeleteClause(em,quser);
		return (int) deleteClause.where(quser.userId.eq(id)).execute();
	}

}
