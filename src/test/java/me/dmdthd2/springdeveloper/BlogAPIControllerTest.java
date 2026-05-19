package me.dmdthd2.springdeveloper;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.dmdthd2.springdeveloper.dao.Article;
import me.dmdthd2.springdeveloper.dto.AddArticleRequest;
import me.dmdthd2.springdeveloper.dto.UpdateArticleRequest;
import me.dmdthd2.springdeveloper.repository.BlogRepository;
import me.dmdthd2.springdeveloper.service.BlogService;
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
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BlogAPIControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected BlogRepository blogRepository;
    @Autowired
    private BlogService blogService;

    @BeforeEach
    public void cleanUp() {
        blogRepository.deleteAll();
    }

    @DisplayName("addArticle: creates an article")
    @Test
    public void addArticle() throws Exception {
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest article = new AddArticleRequest(title, content);
        final String requestBody = objectMapper.writeValueAsString(article);

        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        result.andExpect(status().isCreated());
        List<Article> articles = blogRepository.findAll();
        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }

    @DisplayName("findArticles: returns article list")
    @Test
    public void findArticles() throws Exception {
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";

        blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        ResultActions result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is(title)))
                .andExpect(jsonPath("$[0].content", is(content)));
    }

    @DisplayName("findAllArticles: returns article list")
    @Test
    public void findAllArticles() throws Exception {
        final String url = "/api/articles";

        blogRepository.save(Article.builder()
                .title("title")
                .content("content")
                .build());

        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("content"))
                .andExpect(jsonPath("$[0].title").value("title"));
    }

    @DisplayName("findArticle: 블로그글을 조회한다")
    @Test
    public void findArticle() throws Exception {
        //given (데이터 준비: 블로그글 하나 생성)
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";
        final Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());
        //when(실행: 위에서 생성된 블로그글을 조회)
        final ResultActions resultActions = mockMvc.perform(get(url, savedArticle.getId())
                .accept(MediaType.APPLICATION_JSON));
        //then(검증:status가 200이고 조회한 블로그 글 제목과 내용이 위에 삽입한 그것과 동일한지 확인)
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.content").value(content));
    }

    @DisplayName("deleteArticle: 블로그 글 삭제에 성공한다")
    @Test
    public void deleteArticle() throws Exception{
        //given
        final String title="김사월";
        final String content="사월의 눈";
        final String url="/api/articles/{id}";

        Article savedArticle=blogRepository.save(Article.builder().title(title).content(content).build()); //save는 업데이트 할 때도 씀 pk가 존재하느냐에 따라 판단

        //when
        ResultActions resultActions=mockMvc.perform(delete(url, savedArticle.getId())).andExpect(status().isOk());

        //then
        List<Article> articles=blogRepository.findAll();
        assertThat(articles).isEmpty();
    }

    @DisplayName("updateArticle: 블로그 글 수정에 성공한다")
    @Test
    public void updateArticle() throws Exception{
        //given: 레코드 생성, 변경 내용 작성
         final String title="배고파";
         final String contnet ="이기적인 교수";
         final String url="/api/articles/{id}";
         final String newTitle="JUnit에서 제목 변경";
         final String newContent="JUnit에서 내용 변경";
        Article savedArticle= blogRepository.save(Article.builder().title(title).content(contnet).build());
        UpdateArticleRequest request= new UpdateArticleRequest(newTitle,newContent);

        //when: /api/articles/생성된 레코드 id-> put 방식 요청
        ResultActions resultActions=mockMvc.perform(put(url,savedArticle.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)));

        //then: status code가 200,  repository에서 변경된 내용 검증
        resultActions.andExpect(status().isOk());
        Article article=blogRepository.findById(savedArticle.getId()).get();
        assertThat(article.getTitle()).isEqualTo(newTitle);
        assertThat(article.getContent()).isEqualTo(newContent);
    }

}
