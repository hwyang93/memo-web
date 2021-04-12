package memoWeb.web.myGroup.repository;

import com.querydsl.jpa.impl.JPAQuery;
import memoWeb.web.main.domain.MemberVO;
import memoWeb.web.main.domain.QMemberVO;
import memoWeb.web.main.domain.QUserScheduleVO;
import memoWeb.web.main.domain.UserScheduleVO;
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
    QMemberVO qMember = QMemberVO.memberVO;


    @Override
    public List<MemberVO> getUserList(String keyword) {
//        final JPAQuery<QMemberVO> query = new JPAQuery<>(em);
//        return query.from(qMember)
//                .where(qMember.userId.contains(keyword))
//                .fetch();
        return null;
    }
}
