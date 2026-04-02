package me.scpark.springdeveloper;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;
    @GetMapping("/test")
    public ResponseEntity<List<Member>> findAllMembers() {
        List<Member> members = testService.findAllMembers();
        return ResponseEntity.ok(members);
    }
    @PostMapping("/test")
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        return ResponseEntity.ok(testService.saveMember(member));
    }
    @GetMapping("/test2")
    public String test() {
        return "Hello World";
    }
}