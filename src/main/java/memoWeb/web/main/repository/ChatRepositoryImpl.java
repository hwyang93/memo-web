package memoWeb.web.main.repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import memoWeb.web.main.domain.*;
import memoWeb.web.myGroup.domain.QGroupsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class ChatRepositoryImpl implements ChatRepository {
	@Autowired
	private final JPAQueryFactory queryFactory;
	QChatRoom qChatRoom = QChatRoom.chatRoom;
	QChatRoomUser qChatRoomUser = QChatRoomUser.chatRoomUser;
	QChatRoomUser qChatRoomUser2 = new QChatRoomUser("qChatRoomUser2");
	QUserVO qUser = QUserVO.userVO;
	QGroupsVO qGroup = QGroupsVO.groupsVO;
	@PersistenceContext
	private EntityManager em;

	public ChatRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public List<ChatRoomDTO> getChatList(UserDTO user) {
//		return queryFactory.select(Projections.fields(ChatRoomDTO.class, qChatRoom.chatRoomIdx, qChatRoom.chatRoodType, qChatRoom.chatRoomTitle))
//				.from(qChatRoom)
//				.innerJoin(qChatRoomUser)
//				.on(qChatRoom.chatRoomIdx.eq(qChatRoomUser.chatRoomIdx))
//				.where(qChatRoomUser.userId.eq(user.getUserId()))
//				.fetch();
		return null;
	}

	@Override
	public ChatRoomUserDTO getChatRoomUser(HashMap<String, Object> params) {
		List<Integer> chatIdxList = queryFactory.select(qChatRoom.chatRoomIdx)
				.from(qChatRoom)
				.where(qChatRoom.chatRoomIdx.in(JPAExpressions.select(qChatRoomUser.chatRoomIdx).from(qChatRoomUser).where(qChatRoomUser.userId.eq(params.get("user1").toString())))
						.and(qChatRoom.chatRoodType.eq("I")))
				.fetch();
		return queryFactory.select(Projections.fields(ChatRoomUserDTO.class, qChatRoomUser.chatRoomIdx, qChatRoomUser.userId,
				ExpressionUtils.as(JPAExpressions.select(qUser.userName).from(qUser).where(qUser.userId.eq(qChatRoomUser.userId)), "chatRoomTitle")))
				.from(qChatRoomUser)
				.where(qChatRoomUser.chatRoomIdx.in(chatIdxList)
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

	@Override
	public List<ChatRoomUserDTO> getChatRoomListI(UserDTO user) {
		List<Integer> chatIdxList = queryFactory.select(qChatRoom.chatRoomIdx)
				.from(qChatRoom)
				.where(qChatRoom.chatRoomIdx.in(JPAExpressions.select(qChatRoomUser.chatRoomIdx).from(qChatRoomUser).where(qChatRoomUser.userId.eq(user.getUserId())))
						.and(qChatRoom.chatRoodType.eq("I")))
				.fetch();
		return queryFactory.select(Projections.fields(ChatRoomUserDTO.class, qChatRoomUser.chatRoomIdx, qChatRoomUser.userId,
				ExpressionUtils.as(JPAExpressions.select(qUser.userName).from(qUser).where(qUser.userId.eq(qChatRoomUser.userId)), "chatRoomTitle")))
				.from(qChatRoomUser)
				.where(qChatRoomUser.chatRoomIdx.in(chatIdxList)
						.and(qChatRoomUser.userId.ne(user.getUserId())))
				.fetch();
	}

	@Override
	public List<ChatRoomDTO> getChatRoomListG(UserDTO user) {
		return queryFactory.select(Projections.fields(ChatRoomDTO.class, qChatRoom.chatRoomIdx, qChatRoom.chatRoodType, qChatRoom.groupIdx,
				ExpressionUtils.as(JPAExpressions.select(qGroup.groupTitle).from(qGroup).where(qGroup.groupIdx.eq(qChatRoom.groupIdx)), "chatRoomTitle")))
				.from(qChatRoom)
				.where(qChatRoom.chatRoomIdx.in(JPAExpressions.select(qChatRoomUser.chatRoomIdx).from(qChatRoomUser).where(qChatRoomUser.userId.eq(user.getUserId())))
						.and(qChatRoom.chatRoodType.eq("G")))
				.fetch();
	}

	@Override
	public int getMaxChatRoomIdx() {
		return Optional.ofNullable(queryFactory.select(qChatRoom.chatRoomIdx.max()).from(qChatRoom).fetchOne()).orElseGet(() -> 0);
	}

	@Override
	public void createChatRoom(ChatRoom chatRoom) {
		em.persist(chatRoom);
	}

	@Override
	public void joinChatRoom(ChatRoomUser chatRoomUser) {
		em.persist(chatRoomUser);
	}

}
