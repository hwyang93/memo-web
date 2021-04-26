package memoWeb.web.myMemo.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import memoWeb.web.main.domain.QUserMemo;
import memoWeb.web.main.domain.UserDTO;
import memoWeb.web.main.domain.UserMemoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MyMemoRepositoryImpl implements MyMemoRepository {

    @Autowired
    private final JPAQueryFactory queryFactory;
    QUserMemo qUserMemo = QUserMemo.userMemo;

    public MyMemoRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<UserMemoDTO> getUserMemoListAll(UserDTO user) {
        return queryFactory.select(Projections.fields(UserMemoDTO.class,
                qUserMemo.idx, qUserMemo.title, qUserMemo.userId, qUserMemo.memoPlace, qUserMemo.regDate, qUserMemo.memo))
                .from(qUserMemo)
                .where(qUserMemo.userId.eq(user.getUserId()))
                .fetch();
    }

    @Override
    public UserMemoDTO getUserMemoInfo(int idx) {
        return queryFactory.select(Projections.fields(UserMemoDTO.class,
                qUserMemo.idx, qUserMemo.title, qUserMemo.userId, qUserMemo.memoPlace, qUserMemo.regDate, qUserMemo.memo, qUserMemo.lon, qUserMemo.lat))
                .from(qUserMemo)
                .where(qUserMemo.idx.eq(idx))
                .fetchOne();
    }

}
