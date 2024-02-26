package com.kbtg.bootcamp.posttest.model;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "user_ticket")
public class UserTicket {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "user_id", nullable = false, length = 10)
    private String userId;
    @Column(name = "ticket", nullable = false, length = 6)
    private String ticket;
    @Column(name = "ticket_price", nullable = false)
    private BigInteger ticketPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public BigInteger getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigInteger ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

}
