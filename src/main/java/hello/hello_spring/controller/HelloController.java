package hello.hello_spring.controller;

import org.springframework.ui.Model; //잘못 지정 시 addAttribute X
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "Hello " + name; //응답 body부에 직접 넣겠다 / tag도 없이 그대로 문자만 적힘 ex) hello spring
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //객체를 넘김 (key)
    }

    static class Hello{
        private String name;
        //getter setter 의 경우 alt + insert key를 눌러 바로 setting 가능
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
