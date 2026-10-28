package com.StocksBackEnd.Portfolio.repository;






import com.StocksBackEnd.Portfolio.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, String> {
}

