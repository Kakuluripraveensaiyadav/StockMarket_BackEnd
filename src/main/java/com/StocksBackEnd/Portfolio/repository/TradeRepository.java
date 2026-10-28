package com.StocksBackEnd.Portfolio.repository;



import com.StocksBackEnd.Portfolio.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {
}
