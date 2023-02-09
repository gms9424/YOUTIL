package com.youtil.server.repository.post;

import com.youtil.server.domain.post.Post;
import com.youtil.server.domain.post.PostComment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

    @Transactional
    @Modifying(clearAutomatically = true) // 영속성 컨텍스트 초기화
    @Query("delete from PostComment c where c.post.postId = :postId")
    void deleteByPostId(@Param("postId") Long postId);

    @Query("select c from PostComment c where c.commentId = :commentId")
    Optional<PostComment> findComment(@Param("commentId") Long commnetId);

    @Transactional
    @Modifying
    @Query("delete from PostComment c where c.commentId = :commentId")
    void deleteByCommentId(@Param("commentId") Long commentId);

    @Query("SELECT c FROM PostComment c WHERE c.post.postId = :postId")
    Slice<PostComment> findCommentList(@Param("postId")Long postId, Pageable pageable);

    @Query("SELECT c FROM PostComment c WHERE c.post.postId = :postId and ((:cop = '<' and  c.commentId < :cursor) or (:cop = '>' and  c.commentId > :cursor))")
    Slice<PostComment> findCommentListByCursor(@Param("postId")Long postId, @Param("cursor") Long cursor, @Param("cop")String cop, Pageable pageable);
//
//    @Query("SELECT c FROM PostComment c, User u WHERE c.post.postId = :postId AND c.parentId is not null and ((:cop = '<' and  c.commentId < :cursor) or (:cop = '>' and  c.commentId > :cursor))")
//    Slice<PostComment> findCommentChildrenListByCursor(@Param("postId")Long postId, @Param("cursor") Long cursor, @Param("cop")String cop, Pageable pageable);

    @Query("SELECT c FROM PostComment c WHERE c.post.postId = :postId")
    List<PostComment> findCommentByPostId(@Param("postId")Long postId);
}
