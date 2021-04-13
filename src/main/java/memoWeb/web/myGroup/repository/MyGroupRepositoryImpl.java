package memoWeb.web.myGroup.repository;

import com.querydsl.jpa.impl.JPAQuery;
import memoWeb.web.main.domain.QUserVO;
import memoWeb.web.main.domain.UserVO;
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
    public UserRelationVO joinUserRelation(UserRelationVO userRelation) {
        final JPAQuery<UserVO> query = new JPAQuery<>(em);
        return null;
    }
}
