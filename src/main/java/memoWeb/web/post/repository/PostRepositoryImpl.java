package memoWeb.web.post.repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import memoWeb.web.main.domain.*;
import memoWeb.web.myGroup.domain.QGroupsVO;
import memoWeb.web.post.domain.Post;
import memoWeb.web.post.domain.QPost;
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
	@Autowired
	private final JPAQueryFactory queryFactory;
	@PersistenceContext
	private EntityManager em;

	QPost qPost = QPost.post;

	public PostRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public List<Post> getPostList(Post post) {
		return queryFactory.selectFrom(qPost).fetch();
	}
}
