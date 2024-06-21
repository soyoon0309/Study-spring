package hello.hello_spring.controller;

import org.springframework.ui.Model; //잘못 지정 시 addAttribute X
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "Hello !!");
        return "hello"; //hello.html 실행
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name); //param val이 name이므로, attribute name을 name으로
        return "hello-template";
    }
}
