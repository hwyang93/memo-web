package memoWeb.web.post.repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import memoWeb.web.main.domain.*;
import memoWeb.web.myGroup.domain.QGroupsVO;
import memoWeb.web.post.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
//public class PostRepositoryImpl{
public class PostRepositoryImpl implements PostRepository {

	private final JPAQueryFactory queryFactory;
	@PersistenceContext
	private EntityManager em;

	QPost qPost = QPost.post;
	QUserMemo qUserMemo = QUserMemo.userMemo;
	QPostLike qPostLike = QPostLike.postLike;
	QPostReply qPostReply = QPostReply.postReply;

	public PostRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public UserMemo getUserMemo(int idx) {
		return queryFactory.selectFrom(qUserMemo)
				.where(qUserMemo.idx.eq(idx))
				.fetchOne();
	}

	@Override
	public List<Post> getPostList(Post post) {
		return queryFactory.selectFrom(qPost)
				.where(qUserMemo.userId.eq(post.getUserMemo().getUserId()))
				.orderBy(qPost.regDate.desc())
				.fetch();
	}

	@Override
	public void savePost(Post post) {
		em.persist(post);
	}

	@Override
	public void saveLike(PostLike postLike) {
		em.persist(postLike);
	}

	@Override
	public void cancelLike(PostLike postLike) {
		queryFactory.delete(qPostLike)
				.where(qPostLike.postIdx.eq(postLike.getPostIdx())
						.and(qPostLike.userId.eq(postLike.getUserId())))
				.execute();
	}

	@Override
	public List<PostReply> getPostReplyList(Post post) {
		return queryFactory.selectFrom(qPostReply)
				.where(qPostReply.postIdx.eq(post.getPostIdx()))
				.orderBy(qPostReply.originNo.asc(),qPostReply.groupOrd.asc())
				.fetch();
	}

	@Override
	public int getOriginNoMax(PostReply postReply) {
		return Optional.ofNullable(queryFactory.select(qPostReply.originNo.max()).from(qPostReply).where(qPostReply.postIdx.eq(postReply.getPostIdx())).fetchOne()).orElseGet(() -> 0);
	}

	@Override
	public int getGroupOrdMax(PostReply postReply) {
		return Optional.ofNullable(queryFactory.select(qPostReply.groupOrd.max()).from(qPostReply).where(qPostReply.postIdx.eq(postReply.getPostIdx()).and(qPostReply.originNo.eq(postReply.getOriginNo()))).fetchOne()).orElseGet(() -> 0);
	}

	@Override
	public void saveReply(PostReply postReply) {
		em.persist(postReply);
	}

}
