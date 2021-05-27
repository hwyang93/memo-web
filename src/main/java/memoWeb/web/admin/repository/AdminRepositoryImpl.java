package memoWeb.web.admin.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.From;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;

import memoWeb.web.main.domain.QGroupSchedule;
import memoWeb.web.main.domain.QUserMemo;
import memoWeb.web.main.domain.QUserScheduleVO;
import memoWeb.web.main.domain.QUserVO;
import memoWeb.web.main.domain.UserDTO;
import memoWeb.web.main.domain.UserVO;
import memoWeb.web.myGroup.domain.GroupDTO;
import memoWeb.web.myGroup.domain.GroupsVO;
import memoWeb.web.myGroup.domain.QGroupMemberVO;
import memoWeb.web.myGroup.domain.QGroupsVO;
import memoWeb.web.post.domain.Post;
import memoWeb.web.post.domain.PostDTO;
import memoWeb.web.post.domain.QPost;

@Repository
@Transactional
public class AdminRepositoryImpl implements AdminRepository {
	
	

	@PersistenceContext
	private EntityManager em;
	
	private final JPAQueryFactory queryFactory;
	QUserVO quser = QUserVO.userVO;
	QGroupMemberVO qGroupMember = QGroupMemberVO.groupMemberVO;
	QGroupsVO qGroups = QGroupsVO.groupsVO;
	QPost qPost = QPost.post;
	QUserMemo qMemo = QUserMemo.userMemo;
	QUserScheduleVO qSchedule = QUserScheduleVO.userScheduleVO;
	QGroupSchedule qGschedule = QGroupSchedule.groupSchedule;
	
	public AdminRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	// ******** dashboard
	@Override
	public List<Tuple> getMonthData() {
		Path<String> regDate = Expressions.stringPath("regDate");
		return queryFactory.selectFrom(quser)
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
		return queryFactory.selectFrom(qPost)
				.where(qPost.delFlag.eq("N")).fetchCount();
	}
	
	@Override
	public long getMemoCnt() {
		return queryFactory.selectFrom(qMemo).fetchCount();
	}

	@Override
	public long getScheduleCnt() {
		return queryFactory.selectFrom(qSchedule).fetchCount();
	}

	@Override
	public long getGscheduleCnt() {
		return queryFactory.selectFrom(qGschedule).fetchCount();
	}
	
	// ******** users
	@Override
	public List<UserDTO> getUserList() {
		return queryFactory.select(Projections.fields(UserDTO.class, quser.userId))
				.from(quser)
				.where(quser.userId.notLike("admin"), quser.openFlag.eq("Y"))
				.orderBy(quser.regDate.desc())
				.fetch();
	}
	
	@Override
	public UserVO getUserInfo(String id) {
		return queryFactory.selectFrom(quser)
				.where(quser.userId.eq(id))
				.fetchOne();
	}

	@Override
	public long getuPostCnt(String id) {
		return queryFactory.selectFrom(qPost)
				.leftJoin(qMemo)
				.on(qPost.userMemo.idx.eq(qMemo.idx))
				.fetchJoin()
				.where(qPost.userMemo.userId.eq(id), qPost.delFlag.eq("N"))
				.fetchCount();
	}
	

	@Override
	public List<Tuple> getuGroupCnt(String id) {
		return queryFactory
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
	public long deleteUser(UserDTO member) {
		return queryFactory.update(quser)
				.set(quser.openFlag, member.getOpenFlag())
				.set(quser.delDate, member.getDelDate())
				.where(quser.userId.eq(member.getUserId()))
				.execute();
	}


	// ******** board
	@Override
	public List<PostDTO> getPostList() {
		return queryFactory.select(Projections.fields(PostDTO.class, qPost.postIdx, qMemo.title))
				.from(qPost)
				.leftJoin(qMemo)
				.on(qPost.userMemo.idx.eq(qMemo.idx))
				.fetchJoin()
				.where(qPost.delFlag.eq("N"))
				.fetch();
	}

	@Override
	public Post getPostDetail(int idx) {
		return queryFactory.selectFrom(qPost)
				.leftJoin(qMemo)
				.on(qPost.userMemo.idx.eq(qMemo.idx))
				.fetchJoin()
				.where(qPost.postIdx.eq(idx))
				.fetchOne();
	}

	@Override
	public long deletePost(PostDTO post) {
		return queryFactory.update(qPost)
				.set(qPost.delFlag, post.getDelFlag())
				.set(qPost.delDate, post.getDelDate())
				.where(qPost.postIdx.eq(post.getPostIdx()))
				.execute();
	}

	@Override
	public List<GroupDTO> getGroupList() {
		return queryFactory.select(Projections.fields(GroupDTO.class, qGroups.groupIdx, qGroups.groupTitle))
				.from(qGroups)
				.where(qGroups.delFlag.eq("N"))
				.fetch();
	}

	@Override
	public GroupsVO getGroupDetail(int idx) {
		return queryFactory.selectFrom(qGroups)
				.where(qGroups.groupIdx.eq(idx))
				.fetchOne();
	}

	@Override
	public long getGroupActive() {
		return queryFactory.from(qGroups)
				.where(qGroups.delFlag.eq("N"))
				.fetchCount();
	}

	@Override
	public long getGroupOther() {
		return queryFactory.from(qGroups)
				.where(qGroups.delFlag.eq("Y"))
				.fetchCount();
	}

	@Override
	public long deleteGroup(GroupDTO groups) {
		return queryFactory.update(qGroups)
				.set(qGroups.delFlag, groups.getDelFlag())
				.set(qGroups.delDate, groups.getDelDate())
				.where(qGroups.groupIdx.eq(groups.getGroupIdx()))
				.execute();
	}

}
