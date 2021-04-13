package memoWeb.web.main.repository;

import com.querydsl.jpa.impl.JPAQuery;
import memoWeb.web.main.domain.QUserScheduleVO;
import memoWeb.web.main.domain.QUserVO;
import memoWeb.web.main.domain.UserScheduleVO;
import memoWeb.web.main.domain.UserVO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class MainRepositoryImpl implements MainRepository{

    @PersistenceContext
    private EntityManager em;
    QUserVO qUser = QUserVO.userVO;
    QUserScheduleVO qUserSchedule = QUserScheduleVO.userScheduleVO;

    @Override
    public UserVO getMember(UserVO member) {
        final JPAQuery<UserVO> query = new JPAQuery<>(em);

        return query.from(qUser)
                .where(qUser.userId.eq(member.getUserId())
                        .and(qUser.userPassword.eq(member.getUserPassword())))
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
    public List<UserScheduleVO> getScheduleList(UserVO member) {
        final JPAQuery<UserScheduleVO> query = new JPAQuery<>(em);
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return query.from(qUserSchedule)
                .where(qUserSchedule.userId.eq(member.getUserId())
                .and(qUserSchedule.startDate.loe(today))
                .and(qUserSchedule.endDate.goe(today)))
                .fetch();
    }
}
