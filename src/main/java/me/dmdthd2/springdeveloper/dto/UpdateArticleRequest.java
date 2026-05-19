package me.dmdthd2.springdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor //new UpdateArticleRequest('title','content'');
@NoArgsConstructor //new UpdateArticleRequest();
@Getter

public class UpdateArticleRequest {
    private String title;
    private String content;
}
