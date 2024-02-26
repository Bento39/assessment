package com.kbtg.bootcamp.posttest.controller;

import com.kbtg.bootcamp.posttest.model.Lottery;
import com.kbtg.bootcamp.posttest.service.LotteryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class LotteryControllerTest {
    MockMvc mockMvc;
    @Mock
    LotteryService lotteryService;

    @BeforeEach
    void setUp() {
        LotteryController lotteryController = new LotteryController(lotteryService);
        mockMvc = MockMvcBuilders.standaloneSetup(lotteryController)
                .alwaysDo(print())
                .build();
    }

    private Lottery createLotteryForTest (String ticket){
        Lottery lottery = new Lottery();
        lottery.setTicket(ticket);
        return lottery;
    }

    @Test
    @DisplayName("when perform on GET: /lotteries should return all lotteries")
    void viewAllLotteries () throws Exception {
        String lotteryNo1 = "123456";
        String lotteryNo2 = "654321";

        List<String> LotteryNoList = List.of(lotteryNo1, lotteryNo2);

        when(lotteryService.getLotteryNumber()).thenReturn(LotteryNoList);

        ArrayList<String> LotteryNoArrList = new ArrayList<String>();
        LotteryNoArrList.add(lotteryNo1);
        LotteryNoArrList.add(lotteryNo2);

        mockMvc.perform(get("/lotteries"))
                .andExpect(jsonPath("$.tickets", is(LotteryNoArrList)))
                .andExpect(status().isOk());
    }

}