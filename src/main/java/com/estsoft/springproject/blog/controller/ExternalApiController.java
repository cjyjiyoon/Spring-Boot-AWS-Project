package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.service.ExternalApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ExternalApiController {
    private static final Logger log = LoggerFactory.getLogger(ExternalApiController.class);
    public final String articleUrl = "https://jsonplaceholder.typicode.com/posts";
    public final String commentUrl = "https://jsonplaceholder.typicode.com/comments";
    public RestTemplate restTemplate;
    private ExternalApiService externalApiService;

    @GetMapping("/api/external")
    public String getExternalArticle(Model model) {
        externalApiService.fetchExternalRequest();
        return "ok";
    }


//        RestTemplate restTemplate = new RestTemplate();

        // 외부 API 호출, 역직렬화 (restTemplate)

//        ResponseEntity<List<Content>> resultList = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Content>>() {
//        });

//        log.info("code: {}",resultList.getStatusCode());
//        log.info("{}",resultList.getBody());
// https://jsonplaceholder.typicode.com/posts
//        return "OK";

}
