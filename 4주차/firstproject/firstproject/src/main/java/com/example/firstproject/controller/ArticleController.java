package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @GetMapping("/articles/new") //URL(localhost:8080/articles/new) 요청 접수
    public String newArticleForm(){
        return "articles/new"; //반환값으로 뷰 페이지(articles/new.mustache)의 이름
    }

    @Autowired
    private ArticleRepository articleRepository; //articleRepository 객체 생성하기

    @PostMapping( "/articles/create" ) //PostMapping으로 URL요청 접수
    public String createArticle(ArticleForm form){
        System.out.println(form.toString());

        Article article = form.toEntity();
        System.out.println(article.toString());

        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());
        return "";
    }
}
