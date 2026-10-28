package com.StocksBackEnd.Portfolio.controller;



import com.StocksBackEnd.Portfolio.dto.TradeRequest;
import com.StocksBackEnd.Portfolio.model.Trade;
import com.StocksBackEnd.Portfolio.service.TradeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trades")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:8082"
})
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping
    public Trade executeTrade(@Valid @RequestBody TradeRequest request) {
        return tradeService.executeTrade(request);
    }
}
