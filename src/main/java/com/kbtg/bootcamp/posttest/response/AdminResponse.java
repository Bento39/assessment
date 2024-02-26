package com.kbtg.bootcamp.posttest.response;

public class AdminResponse {
    private String ticket;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public AdminResponse(String ticket) {
        this.ticket = ticket;
    }
}
