package com.newkni.demo.controller;

import com.newkni.demo.domain.Member;
import com.newkni.demo.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/orderGreaterThan/{n}")
    public List<Member> findMembersWithOrderCountGreaterThan(@PathVariable int n) {
        return statisticsService.findMembersWithOrderCountGreaterThan(n);
    }
}
