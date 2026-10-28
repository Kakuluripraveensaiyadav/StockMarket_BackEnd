package com.StocksBackEnd.Portfolio.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record TradeRequest(
        @NotBlank String symbol,

        @NotBlank
        @Pattern(regexp = "(?i)BUY|SELL")
        String side,

        @NotNull
        @Min(1)
        Integer quantity
) {
}
