package com.newkni.demo.service.impl;

import com.newkni.demo.domain.Member;
import com.newkni.demo.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MemberServiceImpl implements MemberService {
    private final List<Member> members = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    @Override
    public List<Member> findAll() {
        return members;
    }

    @Override
    public List<Member> findPageMember(int page, int size) {
        return members.stream().skip((long) page * size).limit(size).toList();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return members.stream().filter(member -> member.getId().equals(id)).findFirst();
    }

    @Override
    public Member save(Member member) {
        member.setId(counter.incrementAndGet());
        members.add(member);
        return member;
    }

    @Override
    public Member update(Long id, Member member) {
        Optional<Member> find = findById(id);
        if (find.isPresent()) {
            Member update = find.get();
            update.setName(member.getName());
            update.setEmail(member.getEmail());
            return update;
        }
        return null;

    }


    @Override
    public void deleteById(Long id) {
        members.removeIf(member -> member.getId().equals(id));
    }

    @Override
    public void init() {
        members.add(Member.builder().id(counter.incrementAndGet()).name("Cat").email("cat@gmail.com").build());
        members.add(Member.builder().id(counter.incrementAndGet()).name("dog").email("dog@gmail.com").build());
        members.add(Member.builder().id(counter.incrementAndGet()).name("sheep").email("sheep@gmail.com").build());
    }
}
