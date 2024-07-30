package com.newkni.demo.controller;

import com.newkni.demo.domain.Member;
import com.newkni.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {


    private final MemberService memberService;

    @GetMapping
    public List<Member> getMembers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return memberService.findPageMember(page, size);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable long id) {
        Optional<Member> member = memberService.findById(id);
        return member.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        Member createdMember = memberService.save(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member member) {
        Member update = memberService.update(id, member);
        if (Objects.nonNull(update)) {
            return ResponseEntity.ok(update);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        memberService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
