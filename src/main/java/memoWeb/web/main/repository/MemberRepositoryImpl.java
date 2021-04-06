package memoWeb.web.main.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import memoWeb.web.main.domain.MemberVO;
import memoWeb.web.main.domain.QMemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class MemberRepositoryImpl implements MemberRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public MemberVO findAllByUserIdAndUserPassword(MemberVO member) {
        final JPAQuery<MemberVO> query = new JPAQuery<>(em);
        QMemberVO qMember = QMemberVO.memberVO;
        return query.from(qMember)
                .where(qMember.userId.eq(member.getUserId())
                .and(qMember.userPassword.eq(member.getUserPassword())))
                .fetchOne();
    }

    @Override
    public MemberVO save(MemberVO member) {
        em.persist(member);
        return member;
    }
}
