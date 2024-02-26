package com.kbtg.bootcamp.posttest.controller;

import com.kbtg.bootcamp.posttest.model.UserTicket;
import com.kbtg.bootcamp.posttest.response.BuyLotteryResponse;
import com.kbtg.bootcamp.posttest.service.UserTicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserTicketControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserTicketService userTicketService;

    @BeforeEach
    void setUp() {
        UserTicketController userTicketController = new UserTicketController(userTicketService);
        mockMvc = MockMvcBuilders.standaloneSetup(userTicketController)
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("when perform on POST: /users/{userId}/lotteries/{ticketId} userTicketController should return Id of UserTicket")
    void createUserTicketTest() throws Exception {
        String NewUserTicketId = "9";

        when(userTicketService.createUserTicket(any(),any()))
                .thenReturn(new BuyLotteryResponse(NewUserTicketId));

        mockMvc.perform(post("/users/1/lotteries/123456"))
                .andExpect(jsonPath("$.id", is(NewUserTicketId)))
                .andExpect(status().isOk());
    }

//    @Test
//    @DisplayName("when perform on POST: /users/{userId}/lotteries/ userTicketController should return LotteryDataByUserId")
//    void LotteryDataByUserId() throws Exception {
//        String ticket1 = "123456";
//        String ticket2 = "654321";
//        UserTicket userTicket1 = new UserTicket();
//        userTicket1.setTicket(ticket1);
//        userTicket1.setTicketPrice(BigInteger.valueOf(80));
//
//        UserTicket userTicket2 = new UserTicket();
//        userTicket2.setTicket(ticket2);
//        userTicket2.setTicketPrice(BigInteger.valueOf(80));
//
//        List<UserTicket> userTicketList = List.of(userTicket1, userTicket2);
//
//        when(userTicketService.getUserTicketList(any())).thenReturn(userTicketList);
//
//        ArrayList<String> LotteryNoArrList = new ArrayList<>();
//        LotteryNoArrList.add(ticket1);
//        LotteryNoArrList.add(ticket2);
//
//        mockMvc.perform(get("/users/1/lotteries"))
//                .andExpect(jsonPath("$.count", is(2)))
//                .andExpect(jsonPath("$.cost", is(80+80)))
//                .andExpect(jsonPath("$.tickets", is(LotteryNoArrList)))
//                .andExpect(status().isOk());
//    }


}