package com.estsoft.springproject.blog.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column
    private String body;

    @CreatedDate
    @Column
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    public Comment(String body, Article article) {
        this.body = body;
        this.article = article;
        this.createdAt = LocalDateTime.now();
    }

    public void updateCommentBody(String body) {
        this.body = body;
    }

}
