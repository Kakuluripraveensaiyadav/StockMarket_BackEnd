package com.StocksBackEnd.Portfolio.controller;




import com.StocksBackEnd.Portfolio.model.Stock;
import com.StocksBackEnd.Portfolio.repository.StockRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:8082"
})
public class StockController {

    private final StockRepository stockRepository;

    public StockController(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @GetMapping
    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }
}
