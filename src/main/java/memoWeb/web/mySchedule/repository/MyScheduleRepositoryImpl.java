package memoWeb.web.mySchedule.repository;

import com.querydsl.jpa.impl.JPAQuery;
import memoWeb.web.main.domain.MemberVO;
import memoWeb.web.main.domain.QUserScheduleVO;
import memoWeb.web.main.domain.UserScheduleVO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class MyScheduleRepositoryImpl implements MyScheduleRepository{

    @PersistenceContext
    private EntityManager em;
    QUserScheduleVO qUserSchedule = QUserScheduleVO.userScheduleVO;


    @Override
    public List<UserScheduleVO> getUserScheduleListAll(MemberVO member) {
        final JPAQuery<UserScheduleVO> query = new JPAQuery<>(em);
        return query.from(qUserSchedule)
                .where(qUserSchedule.userId.eq(member.getUserId()))
                .orderBy(qUserSchedule.idx.desc())
                .fetch();
    }
}
