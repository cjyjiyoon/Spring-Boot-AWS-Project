package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.Comment;
import com.estsoft.springproject.blog.domain.dto.*;
import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Tag(name = "블로그 CRUD")
@Slf4j // 이거 쓰면 log 객체가 자동으로 생성됨
@RestController
@RequestMapping("/api")
public class BlogController {
    // RequestMapping (특정 url GET /articles)

    private final BlogService service;

    public BlogController(BlogService service) {
        this.service = service;
    }

    //    @RequestMapping(method = RequestMethod.POST)  // 둘이 동일
    @PostMapping("/articles")
    public ResponseEntity<ArticleResponse> writeArticle(@RequestBody AddArticleRequest request) {
        // logging level
        // debug, info, warn, error
//        log.debug("{},{}", request.getTitle(), request.getContent());

        Article article = service.saveArticle(request);
        ArticleResponse response = new ArticleResponse(article.getId(), article.getTitle(), article.getContent());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);  // 성공 201
    }

    // Request Mapping    조회 : HTTP METHOD?  GET
    @ApiResponses(value = {
            @ApiResponse(responseCode = "100", description = "요청에 성공했습니다.", content = @Content(mediaType = "application/json"))
    })
    @Operation(summary = "블로그 전체 목록 보기", description = "블로그 메인 화면에서 보여주는 전체 목록")
    @GetMapping("/articles")
    public ResponseEntity<List<ArticleResponse>> findAll() {
        // List<Article> -> List<ArticleResponse> 형태로 변환
        List<ArticleResponse> responseList = service.findAll().stream()
                .map(Article::convert)
                .toList();
        return ResponseEntity.ok(responseList);
    }

    // 단건 조회 API (Request Mapping) 만들기
    @Parameter(name = "id", description = "블로그 글 ID", example = "45")
    @GetMapping("/articles/{id}")
    public ResponseEntity<ArticleResponse> findById(@PathVariable Long id) {
        Article article = service.findBy(id);  // Exception (500) -> 4xx status code (client 오류)

        // Article -> ArticleResponse
        return ResponseEntity.ok(article.convert());
    }

    // DELETE /articles/{id}
    // @RequestMapping(method = RequestMethod.DELETE, value = "/articles/{id}")
    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteBy(id);
        return ResponseEntity.ok().build();
    }

    // PUT /article/{id} 수정 API body
    @PutMapping("/articles/{id}")
    public ResponseEntity<ArticleResponse> updateById(@PathVariable Long id,
                                                      @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = service.update(id, request);
        return ResponseEntity.ok(updatedArticle.convert());
    }

//    @GetMapping("/articles")
//    public ResponseEntity<List<ArticleResponse>> findAll() {
//        // List<Article> -> List<ArticleResponse> 형태로 변환
//        List<ArticleResponse> responseList = service.findAll().stream()
//                .map(Article::convert)
//                .toList();
//        return ResponseEntity.ok(responseList);
//    }

    @GetMapping("/articles/{articleId}/comments")
    public ResponseEntity<ArticleWithCommentDTO> findArticleWithComment(@PathVariable Long articleId) {
        ArticleWithCommentDTO article = service.getArticleWithComment(articleId);
        return ResponseEntity.ok(article);
    }
}

//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // reason : ""
//    }


