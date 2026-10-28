
package com.StocksBackEnd.Portfolio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    private String symbol;

    @Column(nullable = false)
    private String name;

    private String sector;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal price;

    @Column(nullable = false, precision = 9, scale = 4)
    private BigDecimal changePercent;

    public Stock() {}

    public Stock(String symbol, String name, String sector,
                 BigDecimal price, BigDecimal changePercent) {
        this.symbol = symbol;
        this.name = name;
        this.sector = sector;
        this.price = price;
        this.changePercent = changePercent;
    }

    public String getSymbol() { return symbol; }
    public String getName() { return name; }
    public String getSector() { return sector; }
    public BigDecimal getPrice() { return price; }
    public BigDecimal getChange() { return changePercent; }

    public void setSymbol(String symbol) { this.symbol = symbol; }
    public void setName(String name) { this.name = name; }
    public void setSector(String sector) { this.sector = sector; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setChange(BigDecimal changePercent) {
        this.changePercent = changePercent;
    }
}
