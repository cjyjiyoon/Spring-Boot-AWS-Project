package com.estsoft.springproject.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Arrays;

@Controller
public class PageController {
    // Person    GET /thymeleaf/example
    @GetMapping("/thymeleaf/example")  // 컨트롤러. 로직, 값 세팅
    public String show(Model model){  // 모델
        Person person=new Person();
        person.setId(1L);
        person.setName("김자바");
        person.setAge(20);
        person.setHobbies(Arrays.asList("달리기","줄넘기","복싱","..."));

        model.addAttribute("person",person);
        model.addAttribute("today", LocalDateTime.now());

        return "examplePage";  //  html 페이지, 뷰
    }
}
