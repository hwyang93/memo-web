package memoWeb.web.main.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import memoWeb.web.main.domain.*;
import memoWeb.web.myGroup.domain.QGroupMemberVO;
import memoWeb.web.myGroup.domain.QGroupsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class MainRepositoryImpl implements MainRepository {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private final JPAQueryFactory queryFactory;

    public MainRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    QUserVO qUser = QUserVO.userVO;
    QUserScheduleVO qUserSchedule = QUserScheduleVO.userScheduleVO;
    QGroupSchedule qGroupSchedule = QGroupSchedule.groupSchedule;
    QGroupsVO qGroups = QGroupsVO.groupsVO;
    QGroupMemberVO qGroupMember = QGroupMemberVO.groupMemberVO;

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
    public List<UserScheduleVO> getUserScheduleList(UserVO member) {
        final JPAQuery<UserScheduleVO> query = new JPAQuery<>(em);
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return query.from(qUserSchedule)
                .where(qUserSchedule.userId.eq(member.getUserId())
                .and(qUserSchedule.startDate.loe(today))
                .and(qUserSchedule.endDate.goe(today)))
                .fetch();
    }

    @Override
    public List<GroupScheduleDTO> getGroupScheduleList(UserVO user) {
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
}
