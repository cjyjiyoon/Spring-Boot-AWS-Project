package com.estsoft.springproject.blog.domain.dto;

import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.util.DateFormatUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ArticleWithCommentDTO {
    private Long articleId;
    private String title;
    private String content;
    private String createdAt;
    private String updatedAt;
    private List<CommentResponseDTO> commentList;

    public ArticleWithCommentDTO(Article article) {
        this.articleId = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt().format(DateFormatUtil.formatter);
        this.updatedAt = article.getUpdatedAt().format(DateFormatUtil.formatter);
        this.commentList = article.getComments().stream()
                .map(CommentResponseDTO::new)
                .toList();
    }
}
