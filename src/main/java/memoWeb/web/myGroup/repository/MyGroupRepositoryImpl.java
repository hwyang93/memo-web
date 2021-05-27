package memoWeb.web.myGroup.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import memoWeb.web.main.domain.QGroupSchedule;
import memoWeb.web.main.domain.QUserVO;
import memoWeb.web.main.domain.UserDTO;
import memoWeb.web.main.domain.UserVO;
import memoWeb.web.myGroup.domain.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class MyGroupRepositoryImpl implements MyGroupRepository {

	private final JPAQueryFactory queryFactory;
	QUserVO qUser = QUserVO.userVO;
	QUserRelationVO qUserRelation = QUserRelationVO.userRelationVO;
	QGroupsVO qGroups = QGroupsVO.groupsVO;
	QGroupMemberVO qGroupMember = QGroupMemberVO.groupMemberVO;
	QGroupSchedule qGroupSchedule = QGroupSchedule.groupSchedule;
	@PersistenceContext
	private EntityManager em;

	public MyGroupRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public List<UserDTO> getUserList(HashMap<String, Object> params) {
		return queryFactory.select(Projections.fields(UserDTO.class, qUser.userId, qUser.userName, qUser.userEmail))
				.from(qUser)
				.where(qUser.userId.contains((String) params.get("keyword"))
						.or(qUser.userName.contains((String) params.get("keyword"))
								.or(qUser.userEmail.contains((String) params.get("keyword")))))
				.fetch();
	}

	@Override
	public UserVO getUserInfo(UserVO user) {
		return queryFactory.select(qUser)
				.where(qUser.userId.eq(user.getUserId()))
				.fetchOne();
	}

	@Override
	public long joinUserRelation(UserRelationVO userRelation) {
		em.persist(userRelation);
		return 1;
	}

	@Override
	public List<UserRelationDTO> getFriendList(UserRelationVO userRelation) {
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qUserRelation.userId.eq(userRelation.getUserId()));

		if (userRelation.getRelationStatus() != null && !userRelation.getRelationStatus().equals("ALL")) {
			builder.and(qUserRelation.relationStatus.eq(userRelation.getRelationStatus()));
		}
		return queryFactory.select(Projections.fields(UserRelationDTO.class, qUserRelation.userId, qUserRelation.followUserId, qUserRelation.relationStatus,
				ExpressionUtils.as(JPAExpressions.select(qUser.userName).from(qUser).where(qUser.userId.eq(qUserRelation.followUserId)), "followUserName")))
				.from(qUserRelation)
				.where(builder)
				.fetch();
	}

	@Override
	public int getGroupIdx() {
		return Optional.ofNullable(queryFactory.select(qGroups.groupIdx.max()).from(qGroups).fetchOne()).orElseGet(() -> 1);
	}

	@Override
	public void createGroup(GroupsVO group) {
		em.persist(group);
	}

	@Override
	public void joinGroupMember(GroupMemberVO groupMember) {
		em.persist(groupMember);
	}

	@Override
	public List<GroupDTO> getGroupList(UserVO user) {
		return queryFactory.select(Projections.fields(GroupDTO.class, qGroups.groupIdx, qGroups.groupTitle, qGroups.groupComment, qGroups.regDate, qGroupMember.memberAuth, qGroupMember.approvalStatus))
				.from(qGroups)
				.innerJoin(qGroupMember)
				.on(qGroups.groupIdx.eq(qGroupMember.groupIdx))
				.where(qGroupMember.groupUser.eq(user.getUserId()), qGroups.delFlag.eq("N"))
				.fetch();
	}

	@Override
	public GroupsVO getGroupInfo(GroupsVO group) {
		return queryFactory.selectFrom(qGroups)
				.where(qGroups.groupIdx.eq(group.getGroupIdx()))
				.fetchOne();
	}

	@Override
	public List<GroupMemberDTO> getGroupMemberList(GroupDTO group) {
		return queryFactory.select(Projections.fields(GroupMemberDTO.class, qGroupMember.groupIdx, qGroupMember.groupUser, qGroupMember.regDate, qGroupMember.memberAuth, qGroupMember.approvalStatus))
				.from(qGroupMember)
				.where(qGroupMember.groupIdx.eq(group.getGroupIdx()))
				.fetch();
	}

	@Override
	public void deleteGroupMembers(GroupDTO group) {
		queryFactory.delete(qGroupMember).where(qGroupMember.groupIdx.eq(group.getGroupIdx())).execute();
	}

	@Override
	public void deleteGroupSchedule(GroupDTO group) {
		queryFactory.delete(qGroupSchedule).where(qGroupSchedule.groupIdx.eq(group.getGroupIdx())).execute();
	}

	@Override
	public void deleteGroup(GroupDTO group) {
		queryFactory.update(qGroups)
					.set(qGroups.delFlag, group.getDelFlag())
					.set(qGroups.delDate, group.getDelDate())
					.where(qGroups.groupIdx.eq(group.getGroupIdx()))
					.execute();
	}

	@Override
	public List<UserRelationDTO> getFriendReqList(UserDTO user) {
		return queryFactory.select(Projections.fields(UserRelationDTO.class, qUserRelation.userId, qUserRelation.followUserId, qUserRelation.relationStatus,
				ExpressionUtils.as(JPAExpressions.select(qUser.userName).from(qUser).where(qUser.userId.eq(qUserRelation.userId)), "followUserName")))
				.from(qUserRelation)
				.where(qUserRelation.followUserId.eq(user.getUserId())
						.and(qUserRelation.relationStatus.eq("W")))
				.fetch();
	}

	@Override
	public long updateUserRelationStatus(UserRelationDTO userRelation) {
		return queryFactory.update(qUserRelation)
				.where(qUserRelation.userId.eq(userRelation.getUserId())
						.and(qUserRelation.followUserId.eq(userRelation.getFollowUserId())))
				.set(qUserRelation.relationStatus, userRelation.getRelationStatus())
				.execute();
	}

	@Override
	public long deleteUserRelation(UserRelationDTO userRelation) {
		return queryFactory.delete(qUserRelation)
				.where(qUserRelation.userId.eq(userRelation.getUserId())
						.and(qUserRelation.followUserId.eq(userRelation.getFollowUserId())))
				.execute();
	}

}
