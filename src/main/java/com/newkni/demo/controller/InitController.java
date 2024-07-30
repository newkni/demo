package com.newkni.demo.controller;

import com.newkni.demo.service.MemberService;
import com.newkni.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("init")
public class InitController {

    private final MemberService memberService;
    private final ProductService productService;

    @GetMapping
    public String init() {
        memberService.init();
        productService.init();
        return "init finished";
    }
}
