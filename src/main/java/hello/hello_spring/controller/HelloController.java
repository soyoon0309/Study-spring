package hello.hello_spring.controller;

import org.springframework.ui.Model; //잘못 지정 시 addAttribute X
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "Hello !!");
        return "hello"; //hello.html 실행
    }
}
