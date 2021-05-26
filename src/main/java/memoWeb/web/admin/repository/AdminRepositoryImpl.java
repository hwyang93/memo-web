package memoWeb.web.admin.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
import com.querydsl.jpa.impl.JPAUpdateClause;

import memoWeb.web.main.domain.GroupSchedule;
import memoWeb.web.main.domain.QGroupSchedule;
import memoWeb.web.main.domain.QUserMemo;
import memoWeb.web.main.domain.QUserScheduleVO;
import memoWeb.web.main.domain.QUserVO;
import memoWeb.web.main.domain.UserMemo;
import memoWeb.web.main.domain.UserScheduleVO;
import memoWeb.web.main.domain.UserVO;
import memoWeb.web.myGroup.domain.GroupsVO;
import memoWeb.web.myGroup.domain.QGroupMemberVO;
import memoWeb.web.myGroup.domain.QGroupsVO;
import memoWeb.web.post.domain.Post;
import memoWeb.web.post.domain.QPost;

@Repository
@Transactional
public class AdminRepositoryImpl implements AdminRepository {
	
	
	private static final Object SYSDATE = null;
	@PersistenceContext
	private EntityManager em;
	QUserVO quser = QUserVO.userVO;
	QGroupMemberVO qGroupMember = QGroupMemberVO.groupMemberVO;
	QGroupsVO qGroups = QGroupsVO.groupsVO;
	QPost qPost = QPost.post;
	QUserMemo qMemo = QUserMemo.userMemo;
	QUserScheduleVO qSchedule = QUserScheduleVO.userScheduleVO;
	QGroupSchedule qGschedule = QGroupSchedule.groupSchedule;
	
	// ******** dashboard
	@Override
	public List<Tuple> getMonthData() {
		final JPAQuery<UserVO> query = new JPAQuery<>(em);
		Path<String> regDate = Expressions.stringPath("regDate");
		return query.from(quser)
				.select(((StringExpression) to_char(quser.regDate,("yyyy-mm"))).as(regDate), quser.userId.count().as("cnt"))
				.where(quser.openFlag.eq("Y"))
				.groupBy(to_char(quser.regDate,("yyyy-mm")))
				.orderBy(((ComparableExpressionBase<String>) regDate).desc())
				.fetch();
	}

	private Expression<?> to_char(StringPath regDate, String string) {
		return Expressions.stringTemplate("to_char({0},'{1s}')", regDate, ConstantImpl.create(string));
	}

	@Override
	public long getPostCnt() {
		final JPAQuery<Post> query = new JPAQuery<>(em);
		return query.from(qPost)
				.where(qPost.delFlag.eq("N")).fetchCount();
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
	
	// ******** users
	@Override
	public List<UserVO> getUserList() {
		final JPAQuery<UserVO> query = new JPAQuery<>(em);
		return query.from(quser)
				.where(quser.userId.notLike("admin"), quser.openFlag.eq("Y"))
				.orderBy(quser.regDate.desc())
				.fetch();
	}
	
	@Override
	public UserVO getUserInfo(String id) {
		final JPAQuery<UserVO> query = new JPAQuery<>(em);
		return query.from(quser)
				.where(quser.userId.eq(id))
				.fetchOne();
	}

	@Override
	public long getuPostCnt(String id) {
		final JPAQuery<Post> query = new JPAQuery<>(em);
		return query.from(qPost)
				.leftJoin(qMemo)
				.on(qPost.userMemo.idx.eq(qMemo.idx))
				.fetchJoin()
				.where(qPost.userMemo.userId.eq(id), qPost.delFlag.eq("N"))
				.fetchCount();
	}
	

	@Override
	public List<Tuple> getuGroupCnt(String id) {
		final JPAQuery<UserVO> query = new JPAQuery<>(em);
		return query
				.select(qGroups.groupTitle, qGroups.count())
				.from(qGroups)
				.leftJoin(qGroupMember)
				.on(qGroups.groupIdx.eq(qGroupMember.groupIdx))
				.fetchJoin()
				.where(qGroupMember.groupUser.eq(id), qGroups.delFlag.eq("N"))
				.groupBy(qGroups.groupTitle)
				.fetch();
	}
	
	@Override
	public long deleteUser(UserVO member) {
		final JPAUpdateClause updateClause = new JPAUpdateClause(em, quser);
		return updateClause
				.set(quser.openFlag, member.getOpenFlag())
				.set(quser.delDate, member.getDelDate())
				.where(quser.userId.eq(member.getUserId()))
				.execute();
	}


	// ******** board
	@Override
	public List<Post> getPostList() {
		final JPAQuery<Post> query = new JPAQuery<>(em);
		return query.from(qPost)
				.leftJoin(qMemo)
				.on(qPost.userMemo.idx.eq(qMemo.idx))
				.fetchJoin()
				.where(qPost.delFlag.eq("N"))
				.fetch();
	}

	@Override
	public Post getPostDetail(int idx) {
		final JPAQuery<Post> query = new JPAQuery<>(em);
		return query.from(qPost)
				.leftJoin(qMemo)
				.on(qPost.userMemo.idx.eq(qMemo.idx))
				.fetchJoin()
				.where(qPost.postIdx.eq(idx))
				.fetchOne();
	}

	@Override
	public long deletePost(Post post) {
		final JPAUpdateClause updateClause = new JPAUpdateClause(em, qPost);
		return updateClause
				.set(qPost.delFlag, post.getDelFlag())
				.set(qPost.delDate, post.getDelDate())
				.where(qPost.postIdx.eq(post.getPostIdx()))
				.execute();
	}

	@Override
	public List<GroupsVO> getGroupList() {
		final JPAQuery<GroupsVO> query = new JPAQuery<>(em);
		return query.from(qGroups)
				.where(qGroups.delFlag.eq("N")).fetch();
	}

	@Override
	public GroupsVO getGroupDetail(int idx) {
		final JPAQuery<GroupsVO> query = new JPAQuery<>(em);
		return query.from(qGroups)
				.where(qGroups.groupIdx.eq(idx))
				.fetchOne();
	}

}
