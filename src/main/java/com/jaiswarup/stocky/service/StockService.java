package com.jaiswarup.stocky.service;

import com.jaiswarup.stocky.model.Stock;
import com.jaiswarup.stocky.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Stock addStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock updateStock(Long id, Stock updatedStock) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new RuntimeException("Stock not found"));
        stock.setStockName(updatedStock.getStockName());
        stock.setTicker(updatedStock.getTicker());
        stock.setQuantity(updatedStock.getQuantity());
        stock.setBuyPrice(updatedStock.getBuyPrice());
        return stockRepository.save(stock);
    }

    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public BigDecimal calculatePortfolioValue() {
        return stockRepository.findAll().stream()
                .map(stock -> stock.getBuyPrice().multiply(BigDecimal.valueOf(stock.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}