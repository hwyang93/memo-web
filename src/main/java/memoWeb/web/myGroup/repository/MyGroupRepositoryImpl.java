package memoWeb.web.myGroup.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import memoWeb.web.main.domain.QUserVO;
import memoWeb.web.main.domain.UserVO;
import memoWeb.web.myGroup.domain.QUserRelationVO;
import memoWeb.web.myGroup.domain.UserRelationVO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class MyGroupRepositoryImpl implements MyGroupRepository {

    @PersistenceContext
    private EntityManager em;
    QUserVO qUser = QUserVO.userVO;
    QUserRelationVO qUserRelation = QUserRelationVO.userRelationVO;


    @Override
    public List<UserVO> getUserList(String keyword) {
        final JPAQuery<UserVO> query = new JPAQuery<>(em);
        return query.from(qUser)
                .where(qUser.userId.contains(keyword)
                .or(qUser.userName.contains(keyword)
                .or(qUser.userEmail.contains(keyword))))
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
}
