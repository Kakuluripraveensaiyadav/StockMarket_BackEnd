package com.StocksBackEnd.Portfolio.service;




import com.StocksBackEnd.Portfolio.dto.TradeRequest;
import com.StocksBackEnd.Portfolio.model.PortfolioHolding;
import com.StocksBackEnd.Portfolio.model.Stock;
import com.StocksBackEnd.Portfolio.model.Trade;
import com.StocksBackEnd.Portfolio.repository.PortfolioRepository;
import com.StocksBackEnd.Portfolio.repository.StockRepository;
import com.StocksBackEnd.Portfolio.repository.TradeRepository;



import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

@Service
public class TradeService {

    private final StockRepository stockRepository;
    private final PortfolioRepository portfolioRepository;
    private final TradeRepository tradeRepository;

    public TradeService(
            StockRepository stockRepository,
            PortfolioRepository portfolioRepository,
            TradeRepository tradeRepository) {
        this.stockRepository = stockRepository;
        this.portfolioRepository = portfolioRepository;
        this.tradeRepository = tradeRepository;
    }

    @Transactional
    public Trade executeTrade(TradeRequest request) {
        String symbol = request.symbol().trim()
                .toUpperCase(Locale.ROOT);
        String side = request.side().trim()
                .toUpperCase(Locale.ROOT);
        int quantity = request.quantity();

        if (quantity < 1) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Quantity must be at least 1");
        }

        if (!side.equals("BUY") && !side.equals("SELL")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Side must be BUY or SELL");
        }

        Stock stock = stockRepository.findById(symbol)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Stock not found: " + symbol));

        BigDecimal price = stock.getPrice();

        PortfolioHolding holding =
                (PortfolioHolding) portfolioRepository.findById(symbol).orElse(null);

        if (side.equals("BUY")) {
            if (holding == null) {
                holding = new PortfolioHolding(
                        symbol, quantity, price);
            } else {
                int oldQuantity = holding.getQuantity();

                BigDecimal oldCost = holding.getAveragePrice()
                        .multiply(BigDecimal.valueOf(oldQuantity));

                BigDecimal newCost = price.multiply(
                        BigDecimal.valueOf(quantity));

                BigDecimal averagePrice = oldCost.add(newCost)
                        .divide(
                                BigDecimal.valueOf(oldQuantity + quantity),
                                4,
                                RoundingMode.HALF_UP);

                holding.setQuantity(oldQuantity + quantity);
                holding.setAveragePrice(averagePrice);
            }

            portfolioRepository.save(holding);

        } else {
            if (holding == null ||
                    holding.getQuantity() < quantity) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "You cannot sell more shares than you own");
            }

            int remaining = holding.getQuantity() - quantity;

            if (remaining == 0) {
                portfolioRepository.delete(holding);
            } else {
                holding.setQuantity(remaining);
                // Keep the average cost of the remaining shares.
                portfolioRepository.save(holding);
            }
        }

        // Save the transaction price and total for both buys and sells.
        Trade trade = new Trade(symbol, side, quantity, price);
        return tradeRepository.save(trade);
    }
}
