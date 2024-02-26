package com.kbtg.bootcamp.posttest.controller;

import com.kbtg.bootcamp.posttest.response.BuyLotteryResponse;
import com.kbtg.bootcamp.posttest.response.MyLotteryDataResponse;
import com.kbtg.bootcamp.posttest.response.SellBackResponse;
import com.kbtg.bootcamp.posttest.service.UserTicketService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/users")
public class UserTicketController {
    private final UserTicketService userTicketService;

    public UserTicketController(UserTicketService userTicketService) {
        this.userTicketService = userTicketService;
    }

    //EXP03
    @PostMapping("/{userId}/lotteries/{ticketId}")
    public BuyLotteryResponse buyLottery(
            @PathVariable("userId")
            @NotBlank @Size(min = 10, max = 10)
            String userId,

            @PathVariable("ticketId")
            @NotBlank @Size(min = 6, max = 6)
            String ticketId){
        return this.userTicketService.createUserTicket(userId, ticketId);
    }

    //EXP04
    @GetMapping("/{userId}/lotteries")
    public MyLotteryDataResponse myLotteryDataByUserId(@PathVariable("userId") String userId){
        return this.userTicketService.getMyLotteryDataByUserId(userId);
    }

    //EXP05
    @DeleteMapping("/{userId}/lotteries/{ticketId}")
    public SellBackResponse sellBackMyLottery(
            @PathVariable("userId")
            @NotBlank @Size(min = 10, max = 10)
            String userId,

            @PathVariable("ticketId")
            @NotBlank @Size(min = 6, max = 6)
            String ticketId){
        return this.userTicketService.deleteUserTicket(userId, ticketId);
    }
}
