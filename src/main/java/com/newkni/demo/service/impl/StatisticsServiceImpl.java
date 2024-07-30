package com.newkni.demo.service.impl;

import com.newkni.demo.domain.Member;
import com.newkni.demo.service.OrderService;
import com.newkni.demo.service.StatisticsService;
import com.newkni.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {


    private final MemberService memberService;
    private final OrderService orderService;

    @Override
    public List<Member> findMembersWithOrderCountGreaterThan(int n) {
        return memberService.findAll().stream().filter(member -> orderService.findByMemberId(member.getId()).size() > n).toList();
    }
}
