package com.kbtg.bootcamp.posttest.controller;

import com.kbtg.bootcamp.posttest.service.LotteryService;
import com.kbtg.bootcamp.posttest.response.LotteryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lotteries")
public class LotteryController {
    private final LotteryService lotteryService;

    public LotteryController(LotteryService lotteryService){
        this.lotteryService = lotteryService;
    }

    //EXP02
    @GetMapping("")
    public LotteryResponse getLottery() {
        List<String> ticketList = this.lotteryService.getLotteryNumber();
        return new LotteryResponse(ticketList);
    }
}
