package com.kbtg.bootcamp.posttest.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigInteger;

public record AdminRequest(
        @NotNull(message = "Ticket cannot be null")
        @Size(min = 6, max = 6, message = "Ticket must be exactly 6 characters")
        String ticket,

        @NotNull(message = "Price cannot be null")
        @Min(value = 1, message = "Price must be greater than 0")
        BigInteger price,
        @NotNull(message = "Amount cannot be null")
        @Min(value = 1, message = "Amount must be greater than 0")
        Integer amount) {}
