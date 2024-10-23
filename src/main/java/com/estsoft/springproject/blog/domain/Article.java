package com.estsoft.springproject.blog.domain;

import com.estsoft.springproject.blog.domain.dto.ArticleResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor // 디폴트 생성자 생성
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments = new ArrayList<>();

    //  생성자
    @Builder // lombok이 알아서 article에 필요한걸 챙겨줌
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ArticleResponse convert(){
        return new ArticleResponse(id,title,content);
    }

    public void update(String title, String content){
//        if(!title.isBlank()) {this.title = title;}
//        if(!content.isBlank()) {this.content = content;}
        this.title = title;
        this.content = content;
    }


}
