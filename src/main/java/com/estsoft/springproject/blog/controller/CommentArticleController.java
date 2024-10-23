package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.Comment;
import com.estsoft.springproject.blog.domain.dto.ArticleResponse;
import com.estsoft.springproject.blog.domain.dto.CommentRequestDTO;
import com.estsoft.springproject.blog.domain.dto.CommentResponseDTO;
import com.estsoft.springproject.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentArticleController {

    private final CommentService commentService;

    public CommentArticleController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 정보 생성 REST API
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentResponseDTO> saveCommentByArticleId(@PathVariable Long articleId, @RequestBody CommentRequestDTO request) {

        Comment comment = commentService.saveComment(articleId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CommentResponseDTO());
    }

    // 댓글 정보조회 REST API
    @GetMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentResponseDTO> selectCommentById(@PathVariable("commentId") Long id) {
        Comment comment = commentService.findComment(id);
        return ResponseEntity.ok(new CommentResponseDTO(comment));
    }

    // 댓글 정보 수정 REST API
    @PutMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateCommentById(@PathVariable Long commentId, @RequestBody CommentRequestDTO request) {
        Comment updatedComment = commentService.update(commentId, request);
        return ResponseEntity.ok(new CommentResponseDTO(updatedComment));
    }

    // 댓글 정보 삭제 REST API
    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }

    //  게시글과 댓글 정보를 한번에 조회하는 REST API
//    @GetMapping("/api/articles/{articleId}/comments")
//    public ResponseEntity<ArticleResponse> viewAll(@PathVariable Long articleId){
//
//    }

}
