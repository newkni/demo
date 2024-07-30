package com.newkni.demo.domain;

import java.time.LocalDate;

public record OrderSearchDto(
        Long memberId,
        String productName,
        LocalDate start,
        LocalDate end,
        int page,
        int size
) {
}
