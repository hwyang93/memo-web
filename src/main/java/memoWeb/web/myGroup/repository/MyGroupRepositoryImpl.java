package memoWeb.web.myGroup.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import memoWeb.web.main.domain.QUserVO;
import memoWeb.web.main.domain.UserVO;
import memoWeb.web.myGroup.domain.GroupMemberVO;
import memoWeb.web.myGroup.domain.GroupsVO;
import memoWeb.web.myGroup.domain.QGroupMemberVO;
import memoWeb.web.myGroup.domain.QGroupsVO;
import memoWeb.web.myGroup.domain.QUserRelationVO;
import memoWeb.web.myGroup.domain.UserGroupVO;
import memoWeb.web.myGroup.domain.UserRelationVO;

@Repository
@Transactional
public class MyGroupRepositoryImpl implements MyGroupRepository {

    @PersistenceContext
    private EntityManager em;
    QUserVO qUser = QUserVO.userVO;
    QUserRelationVO qUserRelation = QUserRelationVO.userRelationVO;
    QGroupsVO qGroups = QGroupsVO.groupsVO;
    QGroupMemberVO qGroupMember = QGroupMemberVO.groupMemberVO;

    @Override
    public List<UserVO> getUserList(HashMap<String, Object> params) {
        final JPAQuery<UserVO> query = new JPAQuery<>(em);
        return query.from(qUser)
                .where(qUser.userId.contains((String) params.get("keyword"))
                        .or(qUser.userName.contains((String) params.get("keyword"))
                                .or(qUser.userEmail.contains((String) params.get("keyword")))))
                .fetch();
    }

    @Override
    public UserVO getUserInfo(UserVO user) {
        final JPAQuery<UserVO> query = new JPAQuery<>(em);
        return query.from(qUser)
                .where(qUser.userId.eq(user.getUserId()))
                .fetchOne();
    }

    @Override
    public UserRelationVO joinUserRelation(UserRelationVO userRelation) {
        em.persist(userRelation);
        return userRelation;
    }

    @Override
    public List<UserRelationVO> getFriendList(UserRelationVO userRelation) {
        final JPAQuery<UserRelationVO> query = new JPAQuery<>(em);
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qUserRelation.followingUserId.eq(userRelation.getFollowUserId()));

        if (!userRelation.getRelationStatus().isEmpty() && userRelation.getRelationStatus().equals("ALL")) {
            builder.and(qUserRelation.relationStatus.eq(userRelation.getRelationStatus()));
        }

        return query.from(qUserRelation)
                .where(builder)
                .fetch();
    }

    @Override
    public int getGroupIdx() {
        final JPAQuery<UserGroupVO> query = new JPAQuery<>(em);
        return Optional.ofNullable(query.from(qGroups).select(qGroups.groupIdx.max()).fetchOne()).orElseGet(()->1);
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
    public List<GroupsVO> getGroupList(UserVO user) {
        final JPAQuery<GroupsVO> query = new JPAQuery<>(em);
        return query.from(qGroups)
                .where(qGroupMember.groupUser.eq(user.getUserId())
                .and(qGroups.groupIdx.eq(qGroupMember.groupIdx)))
                .fetch();
    }

    @Override
    public GroupsVO getGroupInfo(GroupsVO group) {
        final JPAQuery<GroupsVO> query = new JPAQuery<>(em);
        return query.from(qGroups)
                .where(qGroups.groupIdx.eq(group.getGroupIdx()))
                .fetchOne();
    }


}
