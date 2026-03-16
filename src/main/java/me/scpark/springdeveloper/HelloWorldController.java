package me.scpark.springdeveloper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//public class HelloWorldController {
//    @GetMapping("/hello")
//    public String hello() {
//        return "Hello World";
//    }
//
//    // http://localhost:8080/student?firstName=SungChul&lastName=Park
//    @GetMapping("/student")
//    public Student getStudent(@RequestParam("firstName") String firstName,
//                              @RequestParam("lastName") String lastName) {
//        return new Student(firstName, lastName);
//    }
//
//    // http://localhost:8080/student/ChanHo/Park
//    @GetMapping("/student/{firstName}/{lastName}")
//    public Student getStudent2(@PathVariable("firstName") String firstName,
//                               @PathVariable("lastName") String lastName) {
//        return new Student(firstName, lastName);
//    }
//}
@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String sayHello(@RequestParam(name = "name") String name) {
        return "반갑습니다, " + name + "님!";
    }
}
