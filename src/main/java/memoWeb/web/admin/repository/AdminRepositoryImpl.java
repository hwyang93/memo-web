package memoWeb.web.admin.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.core.types.dsl.StringTemplate;
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
				.where(quser.userId.notLike("admin"))
				.orderBy(quser.regDate.desc())
				.fetch();
	}

	@Override
	public int deleteUser(String id) {
		final JPADeleteClause deleteClause = new JPADeleteClause(em,quser);
		return (int) deleteClause.where(quser.userId.eq(id)).execute();
	}

	@Override
	public UserVO getUserInfo(String id) {
		final JPAQuery<UserVO> query = new JPAQuery<>(em);
		return query.from(quser)
				.where(quser.userId.eq(id))
				.fetchOne();
	}

	@Override
	public long getUserCnt() {
		final JPAQuery<UserVO> query = new JPAQuery<>(em);
		return query.from(quser)
				.fetchCount();
	}

	@Override
	public List<Tuple> getMonthData() {
		final JPAQuery<UserVO> query = new JPAQuery<>(em);
		/*
		 * StringTemplate formattedDate = Expressions.stringTemplate(
		 * "TO_CHAR({0}, {1})" , quser.regDate , ConstantImpl.create("yyyy-mm"));
		 */
		return query.from(quser)
				.select(to_char(quser.regDate,("yyyy-mm")), quser.userId.count().as("cnt"))
				.groupBy(to_char(quser.regDate,("yyyy-mm")))
				.fetch();
	}

	private Expression<?> to_char(StringPath regDate, String string) {
		return Expressions.stringTemplate("to_char({0},'{1s}')", regDate, ConstantImpl.create(string));
	}
}
