package jpabook.jpashop.member.controller;

import jpabook.jpashop.base.data.Address;
import jpabook.jpashop.member.entity.Member;
import jpabook.jpashop.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public String list(Model model) {
        // 화면에 맞는 필드만 가진 DTO 객체를 만들어서 출력하는 것이 좋다.
        model.addAttribute("members", memberService.findMembers());
        return "members/member-list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/new")
    public String create(@Valid MemberForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = Member.builder()
                .name(form.getName())
                .address(address)
                .build();

        memberService.join(member);

        return "redirect:/"; // Get "/"
    }
}
