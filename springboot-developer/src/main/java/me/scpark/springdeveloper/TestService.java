package me.scpark.springdeveloper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    @Autowired
    private final TestRepository testRepository;

    public List<Member> findAllMembers() {
        return testRepository.findAll();
    }

    public Member saveMember(Member member) {
        return testRepository.save(member);
    }
}

//    public List<Member> getAllMembers() {
//        return testRepository.findAll();
//    }
//
//    public Member saveMember(Member member) {
//        return testRepository.save(member);
//    }
//}
//
