package com.sparta.outcome.controller;

import com.sparta.outcome.repository.VideoRepository;
import com.sparta.outcome.service.BatchVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/batch")
public class BatchAdController {

    public final BatchVideoService batchVideoService;
    private final VideoRepository videoRepository;



}
