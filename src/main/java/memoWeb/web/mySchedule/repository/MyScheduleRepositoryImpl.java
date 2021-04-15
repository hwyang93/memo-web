package memoWeb.web.mySchedule.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;

import memoWeb.web.main.domain.QUserScheduleVO;
import memoWeb.web.main.domain.UserScheduleVO;
import memoWeb.web.main.domain.UserVO;

@Repository
@Transactional
public class MyScheduleRepositoryImpl implements MyScheduleRepository{

    @PersistenceContext
    private EntityManager em;
    QUserScheduleVO qUserSchedule = QUserScheduleVO.userScheduleVO;


    @Override
    public List<UserScheduleVO> getUserScheduleListAll(UserVO member) {
        final JPAQuery<UserScheduleVO> query = new JPAQuery<>(em);
        return query.from(qUserSchedule)
                .where(qUserSchedule.userId.eq(member.getUserId()))
                .orderBy(qUserSchedule.idx.desc())
                .fetch();
    }

	@Override
	public UserScheduleVO getScheduleDetail(int idx) {
		final JPAQuery<UserScheduleVO> query = new JPAQuery<>(em);
		return query.from(qUserSchedule)
				.where(qUserSchedule.idx.eq(idx))
				.fetchOne();
	}

	@Override
	public long updateSchedule(UserScheduleVO userScheduleVO) {
		final JPAUpdateClause updateClause = new JPAUpdateClause(em, qUserSchedule);
		return updateClause.set(qUserSchedule.idx, userScheduleVO.getIdx())
							.set(qUserSchedule.title, userScheduleVO.getTitle())
							.set(qUserSchedule.promisePlace, userScheduleVO.getPromisePlace())
							.set(qUserSchedule.memo, userScheduleVO.getMemo())
							.set(qUserSchedule.endDate, userScheduleVO.getEndDate())
							.set(qUserSchedule.startDate, userScheduleVO.getStartDate())
							.set(qUserSchedule.lat, userScheduleVO.getLat())
							.set(qUserSchedule.lon, userScheduleVO.getLon())
							.execute();
	}

	@Override
	public long deleteSchedule(int param) {
		final JPADeleteClause deleteClause = new JPADeleteClause(em,qUserSchedule);
		return deleteClause.where(qUserSchedule.idx.eq(param)).execute();
	}
}
