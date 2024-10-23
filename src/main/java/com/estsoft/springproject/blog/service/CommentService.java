package com.estsoft.springproject.blog.service;

import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.Comment;
import com.estsoft.springproject.blog.domain.dto.CommentRequestDTO;
import com.estsoft.springproject.blog.repository.BlogRepository;
import com.estsoft.springproject.blog.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    public CommentService(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }

    // 댓글 저장
    public Comment saveComment(Long articleId, CommentRequestDTO requestDTO) {
        Article article = blogRepository.findById(articleId)
                .orElseThrow();
        return commentRepository.save(new Comment(requestDTO.getBody(), article));
    }

    // 댓글 조회(commentid로)
    public Comment findComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow();
    }

    // 댓글 수정
    @Transactional
    public Comment update(Long commentId, CommentRequestDTO request) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        comment.updateCommentBody(request.getBody());
        return commentRepository.save(comment);

    }

    // 댓글 삭제
    public void deleteComment(Long commentId){
        // 나중에 테스트 코드를 작성할 계획이라면 타입 지정을 하기도 함
        commentRepository.deleteById(commentId);
        // delete from comment where id = ${commentid}
    }

    // 게시글과 댓글 전부 조회
//    public List<Comment> findAllComment(Long articleId){
//
//        return commentRepository.findById(articleId);
//
//    }
}
