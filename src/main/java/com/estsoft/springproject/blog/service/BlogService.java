package com.estsoft.springproject.blog.service;

import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.ArticleWithCommentDTO;
import com.estsoft.springproject.blog.domain.dto.UpdateArticleRequest;
import com.estsoft.springproject.blog.repository.BlogRepository;
import com.estsoft.springproject.blog.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {


    private static final Logger log = LoggerFactory.getLogger(BlogService.class);
    private final BlogRepository repository;
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository repository, CommentRepository commentRepository, BlogRepository blogRepository) {
        this.repository = repository;
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    } // 생성자 매개변수 추가 방식으로 DI 주입

    // blog 게시글 저장
    public Article saveArticle(AddArticleRequest request) {
        log.warn("반드시 서버에서 확인해야 하는 값: {}", 1);
        return repository.save(request.toEntity());
    }

    // blog 게시글 전체 조회
    public List<Article> findAll() {
        return repository.findAll();
    }

    // blog 게시글 단건 조회
    public Article findBy(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found id : " + id));
    }

    // blog 게시글 삭제(id)
    public void deleteBy(Long id) {
        repository.deleteById(id);
    }

    @Transactional  // 여기서 커밋까지 해줘야됨
    public Article update(Long id, UpdateArticleRequest request) {
        Article article = findBy(id);
        article.update(request.getTitle(), request.getContent());
        return article;
    }

    public ArticleWithCommentDTO getArticleWithComment(Long id) {
        Article article = blogRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found id : " + id));
        return new ArticleWithCommentDTO(article);
    }


}
