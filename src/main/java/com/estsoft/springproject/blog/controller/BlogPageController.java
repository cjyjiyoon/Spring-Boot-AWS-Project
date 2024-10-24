package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.ArticleViewResponse;
import com.estsoft.springproject.blog.service.BlogService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogPageController {  // 뷰컨트롤러
    private final BlogService blogService;

    public BlogPageController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/articles")
    String showArticle(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<Article> articleList = blogService.findAll();

        List<ArticleViewResponse> list = articleList.stream()
                .map(ArticleViewResponse::new)
                .toList();
        model.addAttribute("articles", list);

        return "articleList";  // articleList.html
    }

    // GET /articles/{id} 상세페이지 리턴
    @GetMapping("/articles/{id}")
    public String showDetails(@PathVariable Long id, Model model) {
        Article article = blogService.findBy(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";   // article.html
    }

    // GET /new-articles?id=1
    // GET /new-articles
    @GetMapping("/new-article")
    public String addArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {   // 새로운 게시글 생성
            model.addAttribute("article", new ArticleViewResponse());
        } else {  // 게시글 수정 : 기존 게시글 불러오기
            Article article = blogService.findBy(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        return "newArticle";    // newArticle.html
    }
}
