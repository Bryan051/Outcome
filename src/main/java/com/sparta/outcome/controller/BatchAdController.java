package com.sparta.outcome.controller;

import com.sparta.outcome.dto.BatchAdRequestDto;
import com.sparta.outcome.entity.VideoAd;
import com.sparta.outcome.repository.VideoAdRepository;
import com.sparta.outcome.service.BatchAdService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/batch")
public class BatchAdController {

    private final BatchAdService batchAdService;
    private final VideoAdRepository videoAdRepository;
    @GetMapping("/adcount")
    public String countAdViewsByAdIdAndDate(@RequestBody BatchAdRequestDto batchAdRequestDto){
        long dailyAdView = batchAdService.countAdViewsByAdIdAndDate(batchAdRequestDto);

        return "일 광고 총 조회 수: " + dailyAdView;
    }



}
