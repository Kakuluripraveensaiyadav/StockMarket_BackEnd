package com.StocksBackEnd.Portfolio.model;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "trades")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private String side;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal executionPrice;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Trade() {}

    public Trade(String symbol, String side, int quantity,
                 BigDecimal executionPrice) {
        this.symbol = symbol;
        this.side = side;
        this.quantity = quantity;
        this.executionPrice = executionPrice;
        this.totalAmount = executionPrice.multiply(
                BigDecimal.valueOf(quantity));
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getSymbol() { return symbol; }
    public String getSide() { return side; }
    public int getQuantity() { return quantity; }
    public BigDecimal getExecutionPrice() { return executionPrice; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
