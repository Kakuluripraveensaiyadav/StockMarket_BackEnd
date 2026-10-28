
package com.StocksBackEnd.Portfolio.repository;

import com.StocksBackEnd.Portfolio.model.PortfolioHolding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository
        extends JpaRepository<PortfolioHolding, String> {
}
