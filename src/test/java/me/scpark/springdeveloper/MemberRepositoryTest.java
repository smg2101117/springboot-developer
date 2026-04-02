package me.scpark.springdeveloper;


import me.scpark.springdeveloper.Member;
import me.scpark.springdeveloper.MemberRepository;
import org.hibernate.annotations.processing.SQL;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;


import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;




@DataJpaTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Sql("/insert-members.sql")

    void getAllMembers() {
        //given
        //when
        List<Member> members = memberRepository.findAll();
        //then (검증)
        assertThat(members.size()).isEqualTo(3);


    }
    @Test
    @Sql("/insert-members.sql")

    void getMemberById() {

        Member member = memberRepository.findById(2L).get();

        assertThat(member.getName()).isEqualTo("B");

    }

    @Test
    @Sql("/insert-members.sql")
    void getMemberByName(){
        Member member = memberRepository.findByName("C").get();
        assertThat(member.getName()).isEqualTo("C");
    }

}
