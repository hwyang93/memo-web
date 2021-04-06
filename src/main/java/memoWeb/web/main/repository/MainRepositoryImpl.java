package memoWeb.web.main.repository;

import com.querydsl.jpa.impl.JPAQuery;
import memoWeb.web.main.domain.MemberVO;
import memoWeb.web.main.domain.QMemberVO;
import memoWeb.web.main.domain.QUserScheduleVO;
import memoWeb.web.main.domain.UserScheduleVO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
@Transactional
public class MainRepositoryImpl implements MainRepository{

    @PersistenceContext
    private EntityManager em;
    QMemberVO qMember = QMemberVO.memberVO;
    QUserScheduleVO qUserSchedule = QUserScheduleVO.userScheduleVO;

    @Override
    public MemberVO getMember(MemberVO member) {
        final JPAQuery<MemberVO> query = new JPAQuery<>(em);

        return query.from(qMember)
                .where(qMember.userId.eq(member.getUserId())
                        .and(qMember.userPassword.eq(member.getUserPassword())))
                .fetchOne();
    }

    @Override
    public MemberVO signUp(MemberVO member) {
        em.persist(member);
        return member;
    }

    @Override
    public UserScheduleVO saveUserSchedule(UserScheduleVO userSchedule) {
        em.persist(userSchedule);
        return userSchedule;
    }

    @Override
    public List<UserScheduleVO> getScheduleList(MemberVO member) {
        final JPAQuery<UserScheduleVO> query = new JPAQuery<>(em);
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return query.from(qUserSchedule)
                .where(qUserSchedule.userId.eq(member.getUserId())
                .and(qUserSchedule.startDate.loe(today))
                .and(qUserSchedule.endDate.goe(today)))
                .fetch();
    }
}
