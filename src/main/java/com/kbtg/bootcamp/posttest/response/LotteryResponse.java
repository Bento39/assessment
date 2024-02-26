package com.kbtg.bootcamp.posttest.response;

import java.util.List;

public class LotteryResponse {
    private List<String> tickets;

    public List<String> getTickets() {
        return tickets;
    }

    public void setTickets(List<String> tickets) {
        this.tickets = tickets;
    }

    public LotteryResponse(List<String> tickets) {
        this.tickets = tickets;
    }
}
