package memoWeb.web.main.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import memoWeb.web.main.domain.*;
import memoWeb.web.myGroup.domain.QGroupMemberVO;
import memoWeb.web.myGroup.domain.QGroupsVO;
import memoWeb.web.myGroup.domain.QUserRelationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class MainRepositoryImpl implements MainRepository {

	@Autowired
	private final JPAQueryFactory queryFactory;
	QUserVO qUser = QUserVO.userVO;
	QUserScheduleVO qUserSchedule = QUserScheduleVO.userScheduleVO;
	QGroupSchedule qGroupSchedule = QGroupSchedule.groupSchedule;
	QGroupsVO qGroups = QGroupsVO.groupsVO;
	QGroupMemberVO qGroupMember = QGroupMemberVO.groupMemberVO;
	QUserMemo qUserMemo = QUserMemo.userMemo;
	QUserRelationVO qUserRelation = QUserRelationVO.userRelationVO;
	@PersistenceContext
	private EntityManager em;
	public MainRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public UserDTO getMember(UserDTO user) {
		final JPAQuery<UserDTO> query = new JPAQuery<>(em);
		return queryFactory.select(Projections.fields(UserDTO.class,
				qUser.userId, qUser.userEmail, qUser.userName))
				.from(qUser)
				.where(qUser.userId.eq(user.getUserId())
						.and(qUser.userPassword.eq(user.getUserPassword())))
				.fetchOne();
	}

	@Override
	public UserVO signUp(UserVO member) {
		em.persist(member);
		return member;
	}

	@Override
	public UserScheduleVO saveUserSchedule(UserScheduleVO userSchedule) {
		em.persist(userSchedule);
		return userSchedule;
	}

	@Override
	public List<UserScheduleVO> getUserScheduleList(UserDTO user) {
		final JPAQuery<UserScheduleVO> query = new JPAQuery<>(em);
		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return query.from(qUserSchedule)
				.where(qUserSchedule.userId.eq(user.getUserId())
						.and(qUserSchedule.startDate.loe(today))
						.and(qUserSchedule.endDate.goe(today)))
				.fetch();
	}

	@Override
	public List<GroupScheduleDTO> getGroupScheduleList(UserDTO user) {
		return queryFactory.select(Projections.fields(GroupScheduleDTO.class,
				qGroupSchedule.idx, qGroupSchedule.groupIdx, qGroupSchedule.title, qGroupSchedule.startDate, qGroupSchedule.endDate, qGroupSchedule.promisePlace, qGroupSchedule.lon, qGroupSchedule.lat,
				qGroupSchedule.memo))
				.from(qGroupSchedule)
				.innerJoin(qGroups)
				.on(qGroupSchedule.groupIdx.eq(qGroups.groupIdx))
				.innerJoin(qGroupMember)
				.on(qGroupSchedule.groupIdx.eq(qGroupMember.groupIdx))
				.where(qGroupMember.groupUser.eq(user.getUserId()))
				.fetch();
	}

	@Override
	public GroupSchedule saveGroupSchedule(GroupSchedule groupSchedule) {
		em.persist(groupSchedule);
		return groupSchedule;
	}

	@Override
	public UserMemo saveUserMemo(UserMemo userMemo) {
		em.persist(userMemo);
		return userMemo;
	}

	@Override
	public List<UserMemoDTO> getUserMemoList(UserDTO userDTO) {
		return queryFactory.select(Projections.fields(UserMemoDTO.class, qUserMemo.title, qUserMemo.memoPlace, qUserMemo.lon, qUserMemo.lat, qUserMemo.regDate, qUserMemo.memo))
				.from(qUserMemo)
				.where(qUserMemo.userId.eq(userDTO.getUserId()))
				.fetch();
	}

	@Override
	public List<UserDTO> getFriendList(UserDTO user) {
		return queryFactory.select(Projections.fields(UserDTO.class, qUser.userId, qUser.userName))
				.from(qUser)
				.innerJoin(qUserRelation)
				.on(qUserRelation.followUserId.eq(user.getUserId()))
				.where(qUser.userId.eq(qUserRelation.followingUserId)
						.and(qUserRelation.relationStatus.eq("I"))
						.and((qUser.userId.contains(user.getKeyword())
								.or(qUser.userName.contains(user.getKeyword())
										.or(qUser.userEmail.contains(user.getKeyword()))))))
				.fetch();
	}
}
