package com.kbtg.bootcamp.posttest.controller;

import com.kbtg.bootcamp.posttest.response.AdminResponse;
import com.kbtg.bootcamp.posttest.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Base64;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {
    MockMvc mockMvc;
    @Mock
    AdminService adminService;

    @BeforeEach
    void setUp() {
        AdminController adminController = new AdminController(adminService);
        mockMvc = MockMvcBuilders.standaloneSetup(adminController)
                .alwaysDo(print())
                .build();
    }

    private String basicAuth(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        return "Basic " + new String(encodedAuth);
    }

    @Test
    @DisplayName("when perform on POST: /admin/lotteries with Besic Auth should return status 200")
    void besicAuthTest() throws Exception {
        mockMvc.perform(post("/admin/lotteries")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "ticket": "123456",
                                    "price": 80,
                                    "amount": 1
                                }
                                """)
                        .header("Authorization", basicAuth("admin", "password")))
                        .andExpect(status().isOk());
    }

    @Test
    @DisplayName("when perform on POST: /admin/lotteries with lottery ticket 123456 should return ticket 123456")
    void createLotteryTest() throws Exception {
        String ticketId = "123456";

        when(adminService.createLottery(any()))
                .thenReturn(new AdminResponse(ticketId));

        String jsonContent = String.format("""
                {   "ticket": "%s", "price": 80, "amount": 1    }
                """, ticketId);
        mockMvc.perform(post("/admin/lotteries")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .header("Authorization", basicAuth("admin", "password")))
                .andExpect(jsonPath("$.ticket", is(ticketId)))
                .andExpect(status().isOk());
    }
}