package com.estsoft.springproject.blog.service;

import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.Comment;
import com.estsoft.springproject.blog.domain.dto.ArticleResponse;
import com.estsoft.springproject.blog.domain.dto.CommentResponseDTO;
import com.estsoft.springproject.blog.repository.BlogRepository;
import com.estsoft.springproject.blog.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ExternalApiService {
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;
    private final RestTemplate restTemplate;


    public ExternalApiService(CommentRepository commentRepository, BlogRepository blogRepository, RestTemplate restTemplate) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
        this.restTemplate = restTemplate;
    }

    // Get - article 받아오기
    public void fetchExternalRequest() {
        // 요청을 보낼 URL
        String articleUrl = "https://jsonplaceholder.typicode.com/posts";
        String commentUrl = "https://jsonplaceholder.typicode.com/comments";

        ArticleResponse[] articleResponse = new ArticleResponse[]{restTemplate.getForObject(articleUrl, ArticleResponse.class)};
        CommentResponseDTO[] commentResponse = new CommentResponseDTO[]{restTemplate.getForObject(commentUrl, CommentResponseDTO.class)};

        if (articleResponse != null) {
            List<ArticleResponse> articleResponseList = Arrays.asList(articleResponse);
            for (ArticleResponse response : articleResponse) {
                Article article = new Article();
                article.setId(response.getId());
                article.setTitle(response.getTitle());
                article.setContent(response.getContent());
                blogRepository.save(article);
            }
        }
        if (commentResponse != null) {
            List<CommentResponseDTO> commentResponseList = Arrays.asList(commentResponse);
            for (CommentResponseDTO response : commentResponse) {
                Comment comment = new Comment();
                comment.setId(response.getCommentId());
                comment.setBody(response.getBody());
                comment.setArticle(new Article(response.getArticle()));
                commentRepository.save(comment);
            }
        }

    }

    // GET - comment

}
