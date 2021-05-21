package memoWeb.web.admin.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;

import memoWeb.web.main.domain.GroupSchedule;
import memoWeb.web.main.domain.QGroupSchedule;
import memoWeb.web.main.domain.QUserMemo;
import memoWeb.web.main.domain.QUserScheduleVO;
import memoWeb.web.main.domain.QUserVO;
import memoWeb.web.main.domain.UserMemo;
import memoWeb.web.main.domain.UserScheduleVO;
import memoWeb.web.main.domain.UserVO;
import memoWeb.web.myGroup.domain.QGroupMemberVO;
import memoWeb.web.myGroup.domain.QGroupsVO;

@Repository
@Transactional
public class AdminRepositoryImpl implements AdminRepository {
	
	
	@PersistenceContext
	private EntityManager em;
	QUserVO quser = QUserVO.userVO;
	QGroupMemberVO qGroupMember = QGroupMemberVO.groupMemberVO;
	QGroupsVO qGroups = QGroupsVO.groupsVO;
	QUserMemo qMemo = QUserMemo.userMemo;
	QUserScheduleVO qSchedule = QUserScheduleVO.userScheduleVO;
	QGroupSchedule qGschedule = QGroupSchedule.groupSchedule;
	
	
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Tuple> getMonthData() {
		final JPAQuery<UserVO> query = new JPAQuery<>(em);
		/*
		 * StringTemplate formattedDate = Expressions.stringTemplate(
		 * "TO_CHAR({0}, {1})" , quser.regDate , ConstantImpl.create("yyyy-mm"));
		 */
		Path<String> regDate = Expressions.stringPath("regDate");
		return query.from(quser)
				.select(((StringExpression) to_char(quser.regDate,("yyyy-mm"))).as(regDate), quser.userId.count().as("cnt"))
				.groupBy(to_char(quser.regDate,("yyyy-mm")))
				.orderBy(((ComparableExpressionBase<String>) regDate).desc())
				.fetch();
	}

	private Expression<?> to_char(StringPath regDate, String string) {
		return Expressions.stringTemplate("to_char({0},'{1s}')", regDate, ConstantImpl.create(string));
	}

	@Override
	public List<Tuple> getuGroupCnt(String id) {
		final JPAQuery<UserVO> query = new JPAQuery<>(em);
		return query
				.select(qGroups.groupTitle, qGroups.count())
				.from(qGroups)
				.leftJoin(qGroupMember)
				.on(qGroups.groupIdx.eq(qGroupMember.groupIdx))
				.where(qGroupMember.groupUser.eq(id))
				.groupBy(qGroups.groupTitle)
				.fetch();
	}

	@Override
	public long getuPostCnt(String id) {
		final JPAQuery<UserVO> query = new JPAQuery<>(em);
		return 0;
	}

	@Override
	public long getMemoCnt() {
		final JPAQuery<UserMemo> query = new JPAQuery<>(em);
		return query.from(qMemo).fetchCount();
	}

	@Override
	public long getScheduleCnt() {
		final JPAQuery<UserScheduleVO> query = new JPAQuery<>(em);
		return query.from(qSchedule).fetchCount();
	}

	@Override
	public long getGscheduleCnt() {
		final JPAQuery<GroupSchedule> query = new JPAQuery<>(em);
		return query.from(qGschedule).fetchCount();
	}
}
