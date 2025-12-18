package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @GetMapping("/members/new") //URL(localhost:8080/articles/new) 요청 접수
    public String newMembersForm(){
        return "members/new"; //반환값으로 뷰 페이지(articles/new.mustache)의 이름
    }

    @Autowired
    private MemberRepository memberRepository; //articleRepository 객체 생성하기

    @PostMapping( "/members/create" ) //PostMapping으로 URL요청 접수
    public String createArticle(MemberForm form){ //ArticleForm == DTO임
        System.out.println(form.toString());
        //1. DTO를 엔티티로 변환
        Member member = form.toEntity();
        System.out.println(member.toString());
        //2. 리파지토리로 엔티티를 DB에 저장
        Member saved = memberRepository.save(member);
        System.out.println(saved.toString());
        return "";
    }
}
