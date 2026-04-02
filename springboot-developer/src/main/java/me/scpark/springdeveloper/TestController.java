package me.scpark.springdeveloper;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @GetMapping("/test")
    public ResponseEntity<List<Member>> findAllMembers() {
        return ResponseEntity.ok(testService.findAllMembers());
    }

    @PostMapping("/test")
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        return ResponseEntity.ok(testService.saveMember(member));
    }
}

//    @PostMapping("/test")
//    public Member saveMember(@RequestBody Member member) {
//        return testService.saveMember(member);
//    }
//
//    @GetMapping("/test")
//    public List<Member> getAllMembers() {
//        return testService.getAllMembers();
//    }
//}
