package com.newkni.demo.service;

import com.newkni.demo.domain.Member;

import java.util.List;


public interface StatisticsService {
    List<Member> findMembersWithOrderCountGreaterThan(int n);
}
