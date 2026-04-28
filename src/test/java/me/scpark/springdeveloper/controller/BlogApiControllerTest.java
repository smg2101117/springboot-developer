package me.scpark.springdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.scpark.springdeveloper.dao.Article;
import me.scpark.springdeveloper.dao.UpdateArticleRequest;
import me.scpark.springdeveloper.dto.AddArticleRequest;
import me.scpark.springdeveloper.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put; // Added missing put import
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper; // 객체 json 변환

    @Autowired
    protected BlogRepository blogRepository;

    @BeforeEach
    public void setUp() {
        blogRepository.deleteAll();
    }

    @DisplayName("addArticle : 블로그 글 추가 성공")
    @Test
    public void addArticle() throws Exception {
        //given
        final String url = "/api/articles";
        final String title = "test";
        final String content = "블로그 첫글";
        final AddArticleRequest article = new AddArticleRequest(title, content);
        final String requestBody = objectMapper.writeValueAsString(article);

        //when
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        //then
        result.andExpect(status().isCreated());
        List<Article> articles = blogRepository.findAll();
        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
    }

    @DisplayName("findAllArticles : 블로그 글 목록 조회 성공")
    @Test
    public void findAllArticles() throws Exception {
        // given
        final String url = "/api/articles";
        blogRepository.save(Article.builder().title("title1").content("content1").build());

        // when :get 방식 api/articles
        ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE));

        // then :status ok 사용
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("title1"))
                .andExpect(jsonPath("$[0].content").value("content1"));
    }

    @DisplayName("findArticle: 블로그 글 조회에 성공")
    @Test
    public void findArticle() throws Exception {
        //given (데이터 준비 : 블로그 글 하나 생성)
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";
        Article savedArticle = blogRepository.save(Article.builder().title(title).content(content).build());

        //when (실행 : 위에서 생성된 블로그글을 조회 )
        ResultActions resultActions = mockMvc.perform(get(url, savedArticle.getId())
                .accept(MediaType.APPLICATION_JSON_VALUE));

        //then (검증 : status가 200이고 조회한 블로그 제목과 내용이 위에서 삽입한 것과 동일한지 증명)
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("title"))
                .andExpect(jsonPath("$.content").value("content"));
    }

    @DisplayName("deleteArticles: 블로그 글 삭제 테스트")
    @Test
    public void deleteArticles() throws Exception {
        //given
        final String url = "/api/articles/{id}";
        final String title = "4_16";
        final String contents = "backendProgramming";
        Article savedArticle = blogRepository.save(Article.builder().title(title).content(contents).build());

        //when
        mockMvc.perform(delete(url, savedArticle.getId())).andExpect(status().isOk());

        //then
        List<Article> articles = blogRepository.findAll();
        assertThat(articles).isEmpty();
    }

    @DisplayName("updateArticles : blog Update")
    @Test
    public void updateArticles() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String contents = "update";
        Article savedArticle = blogRepository.save(Article.builder().title(title).content(contents).build());

        final String newTitle = "newTitle";
        final String newContent = "newContent";
        UpdateArticleRequest updateArticleRequest = new UpdateArticleRequest(newTitle, newContent);

        // when
        ResultActions resultActions = mockMvc.perform(put(url, savedArticle.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(updateArticleRequest)));

        // then
        resultActions.andExpect(status().isOk());

        Article article = blogRepository.findById(savedArticle.getId()).get();
        assertThat(article.getTitle()).isEqualTo(newTitle);
        assertThat(article.getContent()).isEqualTo(newContent);
    }
}