
package com.StocksBackEnd.Portfolio.controller;

import com.StocksBackEnd.Portfolio.model.PortfolioHolding;
import com.StocksBackEnd.Portfolio.repository.PortfolioRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:8082"
})
public class PortfolioController {

    private final PortfolioRepository portfolioRepository;

    public PortfolioController(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    @GetMapping
    public List<PortfolioHolding> getPortfolio() {
        return portfolioRepository.findAll();
    }
}
