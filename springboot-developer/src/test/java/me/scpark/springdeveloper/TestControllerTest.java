package me.scpark.springdeveloper;

import me.scpark.springdeveloper.Member;
import me.scpark.springdeveloper.TestRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TestControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private TestRepository testRepository;

    @AfterEach
    public void cleanUp() {
        testRepository.deleteAll();
    }

    @DisplayName("getAllMember : 멤버 조회에 성공한다.")
    @Test
    public void getAllMembers() throws Exception {
        // given (데이터 준비)
        final String url = "/test";
        // email 필드가 nullable=false 이므로 포함해서 저장해야 합니다.
        Member savedMember = testRepository.save(new Member(1L, "홍길동"));

        // when (기능 실행)
        final ResultActions result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        // then (결과 검증)
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(savedMember.getId().intValue()))
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));
    }

    @Test
    @DisplayName("Get /test2 요청 시 hello World 반환")
    void getTestAPI() throws Exception {
        mockMvc.perform(get("/test2"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello World"));
    }
}
