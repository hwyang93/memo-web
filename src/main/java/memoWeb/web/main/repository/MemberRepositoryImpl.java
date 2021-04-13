package memoWeb.web.main.repository;

import com.querydsl.jpa.impl.JPAQuery;
import memoWeb.web.main.domain.QUserVO;
import memoWeb.web.main.domain.UserVO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepositoryImpl implements MemberRepository{

    @PersistenceContext
    private EntityManager em;
    QUserVO qUser = QUserVO.userVO;

    @Override
    public UserVO findAllByUserIdAndUserPassword(UserVO member) {
        final JPAQuery<UserVO> query = new JPAQuery<>(em);
        return query.from(qUser)
                .where(qUser.userId.eq(member.getUserId())
                .and(qUser.userPassword.eq(member.getUserPassword())))
                .fetchOne();
    }

    @Override
    public UserVO save(UserVO member) {
        em.persist(member);
        return member;
    }
}
