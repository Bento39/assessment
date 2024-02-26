package com.kbtg.bootcamp.posttest.response;

import java.math.BigInteger;
import java.util.List;

public class MyLotteryDataResponse {
    private List<String> tickets;
    private BigInteger count;
    private long cost;

    public List<String> getTickets() {
        return tickets;
    }

    public void setTickets(List<String> tickets) {
        this.tickets = tickets;
    }

    public BigInteger getCount() {
        return count;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public MyLotteryDataResponse(List<String> tickets, BigInteger count, long cost) {
        this.tickets = tickets;
        this.count = count;
        this.cost = cost;
    }
}
