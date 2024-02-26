package com.kbtg.bootcamp.posttest.response;

public class SellBackResponse {
    private String ticket;

    public SellBackResponse(String ticket) {
        this.ticket = ticket;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
