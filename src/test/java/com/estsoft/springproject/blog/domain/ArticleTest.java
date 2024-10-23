package com.estsoft.springproject.blog.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {

    @Test
    public void test() {
        Article article = new Article("제목","내용");

        // 빌더 패턴
        // builder로 생성한게 좀 더 가독성이 좋음
        Article articleBuilder = Article.builder()
                .title("제목")
                .content("내용")
                .build();
    }

}