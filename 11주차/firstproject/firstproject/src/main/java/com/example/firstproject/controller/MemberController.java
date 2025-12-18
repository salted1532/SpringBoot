package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class MemberController {
    @GetMapping("/members/new") //URL(localhost:8080/articles/new) 요청 접수
    public String newMembersForm(){
        return "members/new"; //반환값으로 뷰 페이지(articles/new.mustache)의 이름
    }

    @Autowired
    private MemberRepository memberRepository; //articleRepository 객체 생성하기

    @GetMapping("/signup")
    public String signUpPage() {
        return "members/new";
    }

    @PostMapping("/join")
    public String join(MemberForm memberForm) {
        //log로 리펙토링
        //System.out.println(memberForm.toString());
        log.info(memberForm.toString());

        Member member = memberForm.toEntity();
        //System.out.println(member.toString());
        log.info(member.toString());

        Member saved = memberRepository.save(member);
        //System.out.println(saved.toString());
        log.info(saved.toString());
        return "redirect:/members/" + saved.getId();
    }

    @PostMapping( "/members/create" ) //PostMapping으로 URL요청 접수
    public String createMember(MemberForm form){ //ArticleForm == DTO임
        System.out.println(form.toString());
        //1. DTO를 엔티티로 변환
        Member member = form.toEntity();
        System.out.println(member.toString());
        //2. 리파지토리로 엔티티를 DB에 저장
        Member saved = memberRepository.save(member);
        System.out.println(saved.toString());
        return "redirect:/members/" + saved.getId();
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model) {
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model) {
        Iterable<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members/index";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Member memberEntity = memberRepository.findById(id).orElse(null);

        model.addAttribute("member", memberEntity);

        return "members/edit";
    }

    @PostMapping("/members/update")
    public String update(MemberForm form) {
        log.info(form.toString());
        //1. DTO를 엔티티로 변환
        Member memberEntity = form.toEntity();
        log.info(memberEntity.toString());
        //2. 리파지토리로 엔티티를 DB에 저장
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        //2.2 기존 데이터값 갱신하기
        if(target != null) {
            memberRepository.save(memberEntity);
        }
        return "redirect:/members/" + memberEntity.getId();
    }

    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("삭제 요청이 들어왔습니다!!");
        //1. 삭제할 대상 가져오기
        Member target = memberRepository.findById(id).orElse(null);
        log.info(target.toString());
        //2. 대상 엔티티 삭제하기
        if(target != null) {
            memberRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제되었습니다.");
        }
        //3. 결과 페이지로 리다이렉트하기
        return "redirect:/members";
    }
}
