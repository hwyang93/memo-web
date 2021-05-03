package memoWeb.web.main.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import memoWeb.web.main.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class ChatRepositoryImpl implements ChatRepository{
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private final JPAQueryFactory queryFactory;

    public ChatRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    QChatRoom qChatRoom = QChatRoom.chatRoom;
    QChatRoomUser qChatRoomUser = QChatRoomUser.chatRoomUser;
    QChatRoomUser qChatRoomUser2 = new QChatRoomUser("qChatRoomUser2");
    QUserVO qUser = QUserVO.userVO;

    @Override
    public List<ChatRoomDTO> getChatList(UserDTO user) {
        return queryFactory.select(Projections.fields(ChatRoomDTO.class,qChatRoom.chatRoomIdx, qChatRoom.chatRoodType, qChatRoom.chatRoomTitle))
                .from(qChatRoom)
                .innerJoin(qChatRoomUser)
                .on(qChatRoom.chatRoomIdx.eq(qChatRoomUser.chatRoomIdx))
                .where(qChatRoomUser.userId.eq(user.getUserId()))
                .fetch();
    }

    @Override
    public ChatRoomUserDTO getChatRoomUser(HashMap<String, Object> params) {
        return queryFactory.selectDistinct(Projections.fields(ChatRoomUserDTO.class, qChatRoomUser.chatRoomIdx, qChatRoomUser.userId))
                .from(qChatRoomUser)
                .innerJoin(qChatRoomUser2)
                .on(qChatRoomUser2.userId.eq(params.get("user1").toString())
                        .and(qChatRoomUser.chatRoomIdx.eq(qChatRoomUser2.chatRoomIdx)))
                .where(qChatRoomUser.userId.ne(params.get("user1").toString())
                        .and(qChatRoomUser.userId.eq(params.get("user2").toString())))
                .fetchOne();
    }

    @Override
    public UserDTO getUserInfo(String userId) {
        return queryFactory.select(Projections.fields(UserDTO.class, qUser.userId, qUser.userName))
                .from(qUser)
                .where(qUser.userId.eq(userId))
                .fetchOne();
    }

}
