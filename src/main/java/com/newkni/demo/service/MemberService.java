package com.newkni.demo.service;

import com.newkni.demo.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    List<Member> findAll();

    List<Member> findPageMember(int page, int size);

    Optional<Member> findById(Long id);

    Member update(Long id, Member member);

    Member save(Member member);

    void deleteById(Long id);

    void init();
}
