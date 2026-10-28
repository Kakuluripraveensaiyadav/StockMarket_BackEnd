
package com.StocksBackEnd.Portfolio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "portfolio_holdings")
public class PortfolioHolding {

    @Id
    private String symbol;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal averagePrice;

    public PortfolioHolding() {}

    public PortfolioHolding(String symbol, int quantity,
                            BigDecimal averagePrice) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.averagePrice = averagePrice;
    }

    public String getSymbol() { return symbol; }
    public int getQuantity() { return quantity; }
    public BigDecimal getAveragePrice() { return averagePrice; }

    public void setSymbol(String symbol) { this.symbol = symbol; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }
}
