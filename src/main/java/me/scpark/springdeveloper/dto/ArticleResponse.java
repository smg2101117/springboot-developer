package me.scpark.springdeveloper.dto;

import lombok.Getter;
import me.scpark.springdeveloper.dao.Article;

@Getter
public class ArticleResponse {
    private final String title;
    private final String content;

    // 올바른 생성자: domain.Article을 받음
    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
